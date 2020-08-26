package com.kravchenko.agency.domain;

import org.springframework.util.ConcurrentReferenceHashMap;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

@Entity
@Table(name = "hotels")
public class Hotel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String country;
    private int capacity;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private Set<Room> rooms;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public Hotel incRoom(){
        this.capacity++;
        return this;
    }
    public Hotel decRoom(){
        this.capacity--;
        return this;
    }

    public static Set<String> getAllHotelsCountries(List<Hotel> hotelList){
        return hotelList.stream().map(Hotel::getCountry).collect(Collectors.toSet());
    }

    public static Map<Hotel, Integer> getFreeHotelsRooms(List<Hotel> hotelList, Instant from, Instant to) {
        Map<Hotel, Integer> hotelsList = new ConcurrentReferenceHashMap<>();
        for(Hotel hotel: hotelList){
            int busyRooms = 0;
            for(Room room: hotel.getRooms()){
                for(Order order: room.getOrders()){

                    boolean contains = (from.isBefore(order.getToDate().toInstant()))
                            && (to.isAfter(order.getFromDate().toInstant()));

                    if(contains){
                        busyRooms++;
                        break;
                    }
                }
            }
            int freeRooms = hotel.capacity - busyRooms;
            if(freeRooms < 0) freeRooms = 0;
            hotelsList.put(hotel, freeRooms);
        }
        return hotelsList;
    }



}
