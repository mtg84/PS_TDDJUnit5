package com.airport;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportTest {

    // Initial test:  then we divide it into 4 tests
    @Test
    public void testAirport(){

        Flight economyFlight = new EconomyFlight("1");
        Flight businessFlight = new BusinessFlight("2");

        Passenger jon = new Passenger("Jon",true);
        Passenger mike = new Passenger("Mike",false);

        assertEquals(true,economyFlight.addPassenger(jon));
        assertEquals(false,economyFlight.removePassenger(jon));
        assertEquals(true,businessFlight.addPassenger(jon));
        assertEquals(false,businessFlight.removePassenger(jon));

        assertEquals(true,economyFlight.addPassenger(mike));
        assertEquals(true,economyFlight.removePassenger(mike));
        assertEquals(false,businessFlight.addPassenger(mike));
        assertEquals(false,businessFlight.removePassenger(mike));


    }

    @Test
    public void testEconomyFlightUsualPassenger(){

        Flight economyFlight = new EconomyFlight("1");
        Passenger mike = new Passenger("Mike",false);

        assertEquals("1",economyFlight.getId());
        assertEquals(true,economyFlight.addPassenger(mike));
        assertEquals(1,economyFlight.getPassengerList().size());
        assertEquals("Mike",economyFlight.getPassengerList().get(0).getName());

        assertEquals(true,economyFlight.removePassenger(mike));
        assertEquals(0,economyFlight.getPassengerList().size());
    }

    @Test
    public void testEconomyFlightVipPassenger(){
        Flight economyFlight = new EconomyFlight("1");
        Passenger jon = new Passenger("Jon",true);
        assertEquals("1",economyFlight.getId());
        assertEquals(true,economyFlight.addPassenger(jon));
        assertEquals(1,economyFlight.getPassengerList().size());
        assertEquals("Jon",economyFlight.getPassengerList().get(0).getName());

        assertEquals(false,economyFlight.removePassenger(jon));
        assertEquals(1,economyFlight.getPassengerList().size());

    }

    @Test
    public void testBusinessFlightUsualPassenger(){
        Flight businessFlight = new BusinessFlight("2");
        Passenger mike = new Passenger("Mike",false);

        assertEquals(false,businessFlight.addPassenger(mike));
        assertEquals(0,businessFlight.getPassengerList().size());
        assertEquals(false,businessFlight.removePassenger(mike));
        assertEquals(0,businessFlight.getPassengerList().size());
    }

    @Test
    public void testBusinessFlightVipPassenger(){
        Flight businessFlight = new BusinessFlight("2");
        Passenger jon = new Passenger("Jon",true);

        assertEquals(true,businessFlight.addPassenger(jon));
        assertEquals(1,businessFlight.getPassengerList().size());
        assertEquals(false,businessFlight.removePassenger(jon));
        assertEquals(1,businessFlight.getPassengerList().size());
    }



}
