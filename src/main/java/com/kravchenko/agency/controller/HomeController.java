package com.kravchenko.agency.controller;

import com.kravchenko.agency.domain.Role;
import com.kravchenko.agency.domain.User;
import com.kravchenko.agency.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final UserRepo userRepo;

    public HomeController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/management")
    public String manage(Model model){
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "management";
    }







}
