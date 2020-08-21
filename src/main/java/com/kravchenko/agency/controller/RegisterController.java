package com.kravchenko.agency.controller;

import com.kravchenko.agency.domain.Role;
import com.kravchenko.agency.domain.User;
import com.kravchenko.agency.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegisterController {

    private final UserRepo userRepo;

    public RegisterController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/register")
    public String registration(){
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegistration(User user, BindingResult bindingResult){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (bindingResult.hasErrors() | userFromDb != null) {
            return "register";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }

}
