package com.kravchenko.agency.controller;

import com.kravchenko.agency.domain.Hotel;
import com.kravchenko.agency.domain.Order;
import com.kravchenko.agency.domain.Room;
import com.kravchenko.agency.domain.User;
import com.kravchenko.agency.repos.HotelRepo;
import com.kravchenko.agency.repos.OrderRepo;
import com.kravchenko.agency.repos.RoomRepo;
import com.kravchenko.agency.repos.UserRepo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class HomeController {

    private final HotelRepo hotelRepo;
    private final RoomRepo roomRepo;
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;

    public HomeController(HotelRepo hotelRepo, RoomRepo roomRepo, UserRepo userRepo, OrderRepo orderRepo) {
        this.hotelRepo = hotelRepo;
        this.roomRepo = roomRepo;
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }

    @GetMapping("/home")
    public String homePage(Model model) {

        Instant now = Instant.now();
        Instant tomorrow = now.plus(1, ChronoUnit.DAYS);
        Map<Hotel, Integer> hotelsList = Hotel.getFreeHotelsRooms(hotelRepo.findAll(), now, tomorrow);

        model.addAttribute("hotelList", hotelsList);
        //hotelsList.forEach((key, val) -> System.out.println(key.getTitle() + ": " + val));

        model.addAttribute("countries", Hotel.getAllHotelsCountries(hotelRepo.findAll()));
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

        Map<Hotel, Integer> hotelsList = Hotel.getFreeHotelsRooms(hotelRepo.findAll(), dateFrom, dateTo);

        if (!country.equals("world"))
            hotelsList.forEach((key, val) -> {
                if (!key.getCountry().equals(country))
                    hotelsList.remove(key);
            });

        model.addAttribute("hotelList", hotelsList);
        //hotelsList.forEach((key, val) -> System.out.println(key.getTitle() + ": " + val));

        model.addAttribute("countries", Hotel.getAllHotelsCountries(hotelRepo.findAll()));
        model.addAttribute("fromDate", Timestamp.from(dateFrom));
        model.addAttribute("toDate", Timestamp.from(dateTo));
        return "home";
    }

    @PostMapping("/home/bookHotel")
    @ResponseStatus(value = HttpStatus.OK)
    private void submitOrder(@RequestParam Map<String, String> reqParam, Principal principal) {
        User user = userRepo.findByUsername(principal.getName());

        String idHotel = reqParam.get("hotelId");
        String dateString = reqParam.get("date");
        String daysString = reqParam.get("daysPeriod");

        if (idHotel != null && dateString != null && daysString != null) {

            Optional<Hotel> hotel = hotelRepo.findById(Long.parseLong(idHotel));

            LocalDate date = LocalDate.parse(dateString);
            Instant dateFrom = date.atStartOfDay(ZoneId.of("UTC")).toInstant();
            dateFrom = dateFrom.atZone(ZoneOffset.UTC).withHour(11).toInstant();
            Instant dateTo = dateFrom.plus(Integer.parseInt(daysString), ChronoUnit.DAYS);

            if(hotel.isPresent()) {
                Room room = Room.pickRoomForOrder(hotel.get(), dateFrom, dateTo);
                roomRepo.save(room);
                Order order = new Order(room, user, Timestamp.from(dateFrom), Timestamp.from(dateTo), true);
                orderRepo.save(order);
            }
        }
    }
}
