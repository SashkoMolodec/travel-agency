package com.kravchenko.agency.service;

import com.kravchenko.agency.dao.UserDao;
import com.kravchenko.agency.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public List<User> list() {
        return userDao.getAll();
    }

    public Optional<User> findById(long id) {
        return userDao.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}