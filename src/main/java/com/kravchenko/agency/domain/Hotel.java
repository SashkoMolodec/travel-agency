package com.kravchenko.agency.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String country;
    private int capacity;

    @OneToMany(mappedBy = "hotel")
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
}
