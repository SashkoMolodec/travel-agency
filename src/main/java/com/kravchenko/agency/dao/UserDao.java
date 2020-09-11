package com.kravchenko.agency.dao;

import com.kravchenko.agency.domain.User;

import java.util.Optional;

public interface UserDao extends Dao<User>{

    Optional<User> findByUsername(String username);

}
