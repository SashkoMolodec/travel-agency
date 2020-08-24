package com.kravchenko.agency.controller;

import com.kravchenko.agency.domain.Hotel;
import com.kravchenko.agency.domain.Order;
import com.kravchenko.agency.domain.Room;
import com.kravchenko.agency.repos.HotelRepo;
import com.kravchenko.agency.repos.OrderRepo;
import com.kravchenko.agency.repos.RoomRepo;
import com.kravchenko.agency.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final HotelRepo hotelRepo;
    private final RoomRepo roomRepo;
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;

    public HomeController(HotelRepo hotelRepo, RoomRepo roomRepo, OrderRepo orderRepo, UserRepo userRepo) {
        this.hotelRepo = hotelRepo;
        this.roomRepo = roomRepo;
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/home")
    public String homePage(Model model){
        Set<String> countries = new HashSet<>();
        for(Hotel hotel: hotelRepo.findAll()) {
            countries.add(hotel.getCountry());
        }
        model.addAttribute("countries", countries);

        List<Room> rooms = roomRepo.findAll();


        List<Order> orders = orderRepo.findAll();

        Instant now = Instant.now();

        List<Order> ordersUser = userRepo.findByUsername("sanykk2014@gmail.com").getOrders();
        System.out.println("\n\n\n\n\n\n\n\n" + ordersUser.toString());
        /*boolean containsNow = (!now.isBefore(orders.get(0).getFromDate().toInstant()))
                    && (now.isBefore( orders.get(0).getToDate().toInstant())) ;

        System.out.println("\n\n\n\n\n\n\n\n\n\n" + containsNow);*/

        //rooms.get(0).getOrders().forEach(s->System.out.print(s.getUser().getUsername()));
        //could use parallel, if there would be MANY orders


        return "home";
    }

}
