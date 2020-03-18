package com.airport;

public class PremiunFlight extends Flight {

    public PremiunFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passenger.isVip()) {
            return passengerSet.add(passenger);
        }
        return false;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        if (passenger.isVip()) {
            return passengerSet.remove(passenger);
        }
        return false;
    }
}
