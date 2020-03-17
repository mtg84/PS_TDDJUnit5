package com.airport;

public class Airport {

    public static void main(String[] args) {

        EconomyFlight economyFlight = new EconomyFlight("1");
        BusinessFlight businessFlight = new BusinessFlight("2");

        Passenger jon = new Passenger("Jon",true);
        Passenger mike = new Passenger("Mike",false);

        businessFlight.addPassenger(jon);
        businessFlight.removePassenger(jon);
        businessFlight.addPassenger(mike);
        economyFlight.addPassenger(mike);

        System.out.println("Business flight passenger list: ");
        for(Passenger passenger : businessFlight.getPassengerList()){
            System.out.println(passenger.getName());
        }

        System.out.println("Economy flight passenger list: ");
        for(Passenger passenger : economyFlight.getPassengerList()){
            System.out.println(passenger.getName());
        }


    }
}
