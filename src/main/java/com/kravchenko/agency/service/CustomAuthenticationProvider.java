package com.kravchenko.agency.service;

import com.kravchenko.agency.domain.Role;
import com.kravchenko.agency.domain.User;
import com.kravchenko.agency.repos.UserRepo;
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

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepo userRepo;

    public CustomAuthenticationProvider(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {


        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

        try{
            User user = userRepo.findByUsername(name);

            if(user.getRoles().contains(Role.MANAGER))
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_MANAGER"));

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(password, user.getPassword())){
                return new UsernamePasswordAuthenticationToken(
                        name, password, grantedAuths);
            }
            else return null;

        }catch (NullPointerException npe){
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
