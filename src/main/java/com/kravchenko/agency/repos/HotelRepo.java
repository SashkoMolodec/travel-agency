package com.kravchenko.agency.repos;

import com.kravchenko.agency.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel, Long> {
    Hotel findByTitle(String title);
}
