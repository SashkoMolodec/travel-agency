package com.kravchenko.agency.controller;

import com.kravchenko.agency.domain.Hotel;
import com.kravchenko.agency.domain.Order;
import com.kravchenko.agency.domain.User;
import com.kravchenko.agency.repos.HotelRepo;
import com.kravchenko.agency.repos.OrderRepo;
import com.kravchenko.agency.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ManagementController {

    private final UserRepo userRepo;
    private final HotelRepo hotelRepo;
    private final OrderRepo orderRepo;

    public ManagementController(UserRepo userRepo, HotelRepo hotelRepo, OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.hotelRepo = hotelRepo;
        this.orderRepo = orderRepo;
    }

    @GetMapping("/management")
    public String manage(Model model){
        List<User> users = userRepo.findAll();
        List<Hotel> hotels = hotelRepo.findAll();
        List<Order> orders = orderRepo.findAll();

        model.addAttribute("users", users);
        model.addAttribute("hotels", hotels);
        model.addAttribute("orders", orders);

        return "management";
    }

    @PostMapping("/management/addHotel")
    public String addHotel(Hotel hotel, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "management";
        }
        hotelRepo.save(hotel);
        return "redirect:/management";
    }

    @PostMapping("/management/increment-room/{id}")
    public String incrementRoom(@PathVariable Long id){
        Optional<Hotel> h = hotelRepo.findById(id);
        h.ifPresent(hotel -> hotelRepo.save(hotel.incRoom()));
        return "redirect:/management";
    }

    @PostMapping("/management/decrement-room/{id}")
    public String decrementRoom(@PathVariable Long id){
        Optional<Hotel> h = hotelRepo.findById(id);
        h.ifPresent(hotel -> hotelRepo.save(hotel.decRoom()));
        return "redirect:/management";
    }


}
