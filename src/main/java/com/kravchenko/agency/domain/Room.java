package com.kravchenko.agency.domain;

import com.kravchenko.agency.repos.RoomRepo;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private Set<Order> orders;

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    public static List<Room> getFreeRooms(RoomRepo roomRepo, Instant instant){
        List<Room> freeRooms = new ArrayList<>();
        for(Room room: roomRepo.findAll()){
            boolean roomFree = true;
            for(Order order: room.getOrders()){
                boolean contains = (!instant.isBefore(order.getFromDate().toInstant()))
                        && (instant.isBefore(order.getToDate().toInstant()));

                if(contains){
                    roomFree = false;
                    break;
                }
            }
            if(roomFree) freeRooms.add(room);
        }
        return freeRooms;
    }


    public static List<Room> getBusyRooms(RoomRepo roomRepo, Instant instant){
        List<Room> busyRooms = new ArrayList<>();
        for(Room room: roomRepo.findAll()){
            boolean roomBusy = false;
            for(Order order: room.getOrders()){
                boolean contains = (!instant.isBefore(order.getFromDate().toInstant()))
                        && (instant.isBefore(order.getToDate().toInstant()));

                if(contains){
                    roomBusy = true;
                    break;
                }
            }
            if(roomBusy) busyRooms.add(room);
        }
        return busyRooms;
    }
}
