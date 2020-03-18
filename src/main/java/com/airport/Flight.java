package com.airport;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Flight {

    private String id;
    private int distance;
    Set<Passenger> passengerSet = new HashSet<>();



    public Flight(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Set<Passenger> getPassengerSet() {
        return Collections.unmodifiableSet(passengerSet);
    }


    public abstract  boolean addPassenger(Passenger passenger);

    public  abstract  boolean removePassenger (Passenger passenger);




}
