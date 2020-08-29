package com.kravchenko.agency.dao.impl;

import com.kravchenko.agency.dao.HotelDao;
import com.kravchenko.agency.domain.Hotel;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HotelDaoImpl extends AbstractDao<Hotel> implements HotelDao {

    public HotelDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Hotel.class);
        }

}