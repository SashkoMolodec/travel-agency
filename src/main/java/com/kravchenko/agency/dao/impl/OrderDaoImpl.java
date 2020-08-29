package com.kravchenko.agency.dao.impl;

import com.kravchenko.agency.dao.OrderDao;
import com.kravchenko.agency.domain.Order;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Order.class);
        }

}