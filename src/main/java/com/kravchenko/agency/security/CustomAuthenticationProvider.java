package com.kravchenko.agency.security;

import com.kravchenko.agency.domain.Role;
import com.kravchenko.agency.domain.User;
import com.kravchenko.agency.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {


        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

        Optional<User> user = userService.findByUsername(name);
        if(user.isPresent()) {
            if (user.get().getRoles().contains(Role.MANAGER))
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_MANAGER"));

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return new UsernamePasswordAuthenticationToken(
                        name, password, grantedAuths);
            }
        }
        return null;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
