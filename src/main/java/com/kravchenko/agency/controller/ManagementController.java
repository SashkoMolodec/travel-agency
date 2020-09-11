package com.kravchenko.agency.controller;

import com.kravchenko.agency.domain.Hotel;
import com.kravchenko.agency.domain.Order;
import com.kravchenko.agency.domain.User;
import com.kravchenko.agency.service.HotelService;
import com.kravchenko.agency.service.OrderService;
import com.kravchenko.agency.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ManagementController {

    private final UserService userService;
    private final HotelService hotelService;
    private final OrderService orderService;

    public ManagementController(UserService userService, HotelService hotelService, OrderService orderService) {
        this.userService = userService;
        this.hotelService = hotelService;
        this.orderService = orderService;
    }

    @GetMapping("/management")
    public String manage(Model model){
        List<User> users = userService.list();
        List<Hotel> hotels = hotelService.list();
        List<Order> orders = orderService.list();

        model.addAttribute("users", users);
        model.addAttribute("hotels", hotels);
        model.addAttribute("orders", orders);

        return "management";
    }

    @PostMapping("/management/addHotel")
    public String addHotel(@Valid Hotel hotel, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("isError", true);
            return "management";
        }
        hotelService.save(hotel);
        return "redirect:/management";
    }

    @PostMapping("/management/increment-room/{id}")
    public String incrementRoom(@PathVariable Long id){
        Optional<Hotel> h = hotelService.findById(id);
        h.ifPresent(hotel -> hotelService.update(hotel.incRoom()));
        return "redirect:/management";
    }

    @PostMapping("/management/decrement-room/{id}")
    public String decrementRoom(@PathVariable Long id){
        Optional<Hotel> h = hotelService.findById(id);
        h.ifPresent(hotel -> hotelService.update(hotel.decRoom()));
        return "redirect:/management";
    }

}
