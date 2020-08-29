package com.kravchenko.agency.controller;

import com.kravchenko.agency.domain.*;
import com.kravchenko.agency.service.HotelService;
import com.kravchenko.agency.service.OrderService;
import com.kravchenko.agency.service.RoomService;
import com.kravchenko.agency.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
public class HomeController {

    private final HotelService hotelService;
    private final RoomService roomService;
    private final UserService userService;
    private final OrderService orderService;

    public HomeController(HotelService hotelService, RoomService roomService, UserService userService, OrderService orderService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {

        Instant now = Instant.now();
        Instant tomorrow = now.plus(1, ChronoUnit.DAYS);
        Map<Hotel, Integer> hotelsList = Hotel.getFreeHotelsRooms(hotelService.list(), now, tomorrow);

        model.addAttribute("hotelList", hotelsList);
        //hotelsList.forEach((key, val) -> System.out.println(key.getTitle() + ": " + val));

        model.addAttribute("countries", Hotel.getAllHotelsCountries(hotelService.list()));
        model.addAttribute("fromDate", Timestamp.from(now));
        model.addAttribute("toDate", Timestamp.from(tomorrow));
        return "home";
    }

    @GetMapping("/home/filter")
    public String homeFilter(@RequestParam("country") String country,
                             @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                             @RequestParam("daysPeriod") Integer daysPeriod,
                             Model model) {

        Instant dateFrom = date.toInstant();
        Instant dateTo = date.toInstant().plus(daysPeriod, ChronoUnit.DAYS);

        Map<Hotel, Integer> hotelsList = Hotel.getFreeHotelsRooms(hotelService.list(), dateFrom, dateTo);

        if (!country.equals("world"))
            hotelsList.forEach((key, val) -> {
                if (!key.getCountry().equals(country))
                    hotelsList.remove(key);
            });

        model.addAttribute("hotelList", hotelsList);
        //hotelsList.forEach((key, val) -> System.out.println(key.getTitle() + ": " + val));

        model.addAttribute("countries", Hotel.getAllHotelsCountries(hotelService.list()));
        model.addAttribute("fromDate", Timestamp.from(dateFrom));
        model.addAttribute("toDate", Timestamp.from(dateTo));
        return "home";
    }

    @PostMapping("/home/bookHotel")
    @ResponseStatus(value = HttpStatus.OK)
    private void submitOrder(@RequestParam Map<String, String> reqParam, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());

        String idHotel = reqParam.get("hotelId");
        String dateString = reqParam.get("date");
        String daysString = reqParam.get("daysPeriod");

        if (idHotel != null && dateString != null && daysString != null && user.isPresent()) {

            Optional<Hotel> hotel = hotelService.findById(Long.parseLong(idHotel));

            LocalDate date = LocalDate.parse(dateString);
            Instant dateFrom = date.atStartOfDay(ZoneId.of("UTC")).toInstant();
            dateFrom = dateFrom.atZone(ZoneOffset.UTC).withHour(11).toInstant();
            Instant dateTo = dateFrom.plus(Integer.parseInt(daysString), ChronoUnit.DAYS);

            if(hotel.isPresent()) {
                Room room = Room.pickRoomForOrder(hotel.get(), dateFrom, dateTo);
                roomService.save(room);
                Order order = new Order(room, user.get(), Timestamp.from(dateFrom), Timestamp.from(dateTo), true);
                orderService.save(order);
            }
        }
    }

    @GetMapping("/becomeManager")
    public String becomeManager(Principal principal){
        Optional<User> user = userService.findByUsername(principal.getName());

        if(user.isPresent()){
            user.get().getRoles().add(Role.MANAGER);
            userService.update(user.get());
        }

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) user.get().getAuthorities();
        authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                        SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                        authorities)
        );
        return "redirect:/home";
    }

}
