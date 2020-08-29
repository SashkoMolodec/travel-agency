package com.kravchenko.agency.service;

import com.kravchenko.agency.dao.OrderDao;
import com.kravchenko.agency.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private final OrderDao orderDao;
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    public List<Order> list() {
        return orderDao.getAll();
    }

    public Optional<Order> findById(long id) {
        return orderDao.findById(id);
    }
}