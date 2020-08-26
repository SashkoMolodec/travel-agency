package com.kravchenko.agency.domain;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int number;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private Set<Order> orders;

    public Room() {}

    public Room(Hotel hotel, int number) {
        this.hotel = hotel;
        this.number = number;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number='" + number + '\'' +
                ", hotel=" + hotel +
                ", orders=" + orders +
                '}';
    }

    public static Room pickRoomForOrder(Hotel hotel, Instant from, Instant to) {
        for(Room room: hotel.getRooms()){
            boolean roomFree = true;
            for(Order order: room.getOrders()){

                boolean contains = (from.isBefore(order.getToDate().toInstant()))
                        && (to.isAfter(order.getFromDate().toInstant()));

                if(contains) roomFree = false;
            }
            if(roomFree) return room;
        }
        return new Room(hotel, hotel.getRooms().size()+1);
    }

}
