package com.airport;

import java.util.*;

public abstract class Flight {

    private String id;
    List<Passenger> passengerList = new ArrayList<Passenger>();


    public Flight(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Passenger> getPassengerList() {
        return Collections.unmodifiableList(passengerList);
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }


    public abstract  boolean addPassenger(Passenger passenger);

    public  abstract  boolean removePassenger (Passenger passenger);




}
