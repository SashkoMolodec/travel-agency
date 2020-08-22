package com.kravchenko.agency.domain;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.Period;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room room;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private Period period;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
