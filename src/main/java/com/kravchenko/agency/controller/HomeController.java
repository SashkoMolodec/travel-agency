package com.kravchenko.agency.controller;

import com.kravchenko.agency.domain.Hotel;
import com.kravchenko.agency.domain.Order;
import com.kravchenko.agency.domain.Room;
import com.kravchenko.agency.repos.HotelRepo;
import com.kravchenko.agency.repos.OrderRepo;
import com.kravchenko.agency.repos.RoomRepo;
import com.kravchenko.agency.repos.UserRepo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final HotelRepo hotelRepo;
    private final RoomRepo roomRepo;
    private final UserRepo userRepo;

    public HomeController(HotelRepo hotelRepo, RoomRepo roomRepo, UserRepo userRepo) {
        this.hotelRepo = hotelRepo;
        this.roomRepo = roomRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/home")
    public String homePage(Model model){
        Instant now = Instant.now();

        Map<Hotel, Integer> hotelsList = Hotel.getFreeHotelsRooms(hotelRepo.findAll(), now);

        //List<Room> freeRooms = Room.getFreeRooms(roomRepo, now);
        //Map<Hotel, Long> hotelsList = freeRooms.stream().collect(Collectors.groupingBy(Room::getHotel, Collectors.counting()));

        hotelsList.forEach((key,val) -> System.out.println(key.getTitle() + ": " + val));
        model.addAttribute("hotelList", hotelsList);

        model.addAttribute("countries", Hotel.getAllHotelsCountries(hotelRepo.findAll()));
        return "home";
    }

    @GetMapping("/home/filter")
    public String homeFilter(@RequestParam("country") String country,
                             @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                             @RequestParam("daysPeriod") Integer daysPeriod,
                             Model model){

        Map<Hotel, Integer> hotelsList = Hotel.getFreeHotelsRooms(hotelRepo.findAll(), date.toInstant());

        if(!country.equals("world"))
            hotelsList.forEach((key,val) -> {
                if(!key.getCountry().equals(country))
                    hotelsList.remove(key);
            });

        //hotelsList.removeIf(room -> !room.getHotel().getCountry().equals(country));
        //Map<Hotel, Long> hotelsList = freeRooms.stream().collect(Collectors.groupingBy(Room::getHotel, Collectors.counting()));

        hotelsList.forEach((key,val) -> System.out.println(key.getTitle() + ": " + val));
        model.addAttribute("hotelList", hotelsList);

        model.addAttribute("countries", Hotel.getAllHotelsCountries(hotelRepo.findAll()));
        return "home";
    }

}
