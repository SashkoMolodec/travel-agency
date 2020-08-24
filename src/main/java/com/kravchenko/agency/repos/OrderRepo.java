package com.kravchenko.agency.repos;

import com.kravchenko.agency.domain.Hotel;
import com.kravchenko.agency.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
