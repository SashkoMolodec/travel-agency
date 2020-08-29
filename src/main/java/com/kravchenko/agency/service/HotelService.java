package com.kravchenko.agency.service;

import com.kravchenko.agency.dao.HotelDao;
import com.kravchenko.agency.domain.Hotel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HotelService {

    private final HotelDao hotelDao;
    public HotelService(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public void save(Hotel hotel) {
        hotelDao.save(hotel);
    }

    public void update(Hotel hotel) {
        hotelDao.update(hotel);
    }

    public List<Hotel> list() {
        return hotelDao.getAll();
    }

    public Optional<Hotel> findById(long id) {
        return hotelDao.findById(id);
    }
}