package com.kravchenko.agency.controller;

import com.kravchenko.agency.domain.Role;
import com.kravchenko.agency.domain.User;
import com.kravchenko.agency.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    private final UserRepo userRepo;

    public HomeController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/management")
    public String greetings(){
        User user = new User();
        user.setActive(true);
        user.setPassword("1111");
        user.setUsername("sasha");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.USER);
        user.setRoles(roleSet);
        userRepo.save(user);
        return "management";
    }



}
