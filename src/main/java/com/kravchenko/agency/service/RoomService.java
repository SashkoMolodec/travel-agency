package com.kravchenko.agency.service;

import com.kravchenko.agency.dao.RoomDao;
import com.kravchenko.agency.domain.Room;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomService {

    private final RoomDao roomDao;
    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void save(Room room) {
        roomDao.save(room);
    }

    public void update(Room room) {
        roomDao.update(room);
    }

    public List<Room> list() {
        return roomDao.getAll();
    }

    public Optional<Room> findById(long id) {
        return roomDao.findById(id);
    }
}