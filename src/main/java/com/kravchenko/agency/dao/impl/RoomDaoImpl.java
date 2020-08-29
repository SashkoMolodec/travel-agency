package com.kravchenko.agency.dao.impl;

import com.kravchenko.agency.dao.RoomDao;
import com.kravchenko.agency.domain.Room;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDaoImpl extends AbstractDao<Room> implements RoomDao {

    public RoomDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Room.class);
        }

}