package com.kravchenko.agency.controller;

import com.kravchenko.agency.domain.Role;
import com.kravchenko.agency.domain.User;
import com.kravchenko.agency.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registration(Principal principal) {
        return principal == null ? "register" : "redirect:/home";
    }

    @PostMapping("/register")
    public String confirmRegistration(@Valid User user, BindingResult bindingResult, Model model) {
        Optional<User> userFromDb = userService.findByUsername(user.getUsername());

        if (bindingResult.hasErrors() | userFromDb.isPresent()) {
            model.addAttribute("isError", true);
            return "register";
        }

        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encoded);

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);
        return "redirect:/login";
    }


}
