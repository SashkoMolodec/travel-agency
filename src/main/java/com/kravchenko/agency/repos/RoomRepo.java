package com.kravchenko.agency.repos;

import com.kravchenko.agency.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {

}
