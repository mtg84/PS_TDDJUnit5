package com.airport;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    // Initial test:  then we divide it into 4 tests
    @Test
    public void testAirport() {

        Flight economyFlight = new EconomyFlight("1");
        Flight businessFlight = new BusinessFlight("2");

        Passenger jon = new Passenger("Jon", true);
        Passenger mike = new Passenger("Mike", false);

        assertTrue(economyFlight.addPassenger(jon));
        assertFalse(economyFlight.removePassenger(jon));
        assertTrue(businessFlight.addPassenger(jon));
        assertFalse(businessFlight.removePassenger(jon));

        assertTrue(economyFlight.addPassenger(mike));
        assertTrue(economyFlight.removePassenger(mike));
        assertFalse(businessFlight.addPassenger(mike));
        assertFalse(businessFlight.removePassenger(mike));


    }

    @DisplayName("Given there is an economy flight")
    @Nested
    class EconomyFlightTest {

        private Flight economyFlight;
        private Passenger mike;
        private Passenger jon;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
            mike = new Passenger("Mike", false);
            jon = new Passenger("Jon", true);
        }

        @DisplayName("When we have a usual passenger")
        @Nested
        class UsualPassenger {

            @Test
            @DisplayName("Then you can add and remove him from an economy flight")
            public void testEconomyFlightUsualPassenger() {

                assertAll("Verify all conditions for usual passenger and economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengerList().size()),
                        () -> assertEquals("Mike", economyFlight.getPassengerList().get(0).getName()),
                        () -> assertEquals(true, economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengerList().size())
                );

            }

        }

        @DisplayName("When we have a vip passenger")
        @Nested
        class VipPassenger {

            @Test
            @DisplayName("Then you can add and remove him from an economy flight")
            public void testEconomyFlightVipPassenger() {

                Passenger jon = new Passenger("Jon", true);

                assertEquals("1", economyFlight.getId());
                assertEquals(true, economyFlight.addPassenger(jon));
                assertEquals(1, economyFlight.getPassengerList().size());
                assertEquals("Jon", economyFlight.getPassengerList().get(0).getName());

                assertEquals(false, economyFlight.removePassenger(jon));
                assertEquals(1, economyFlight.getPassengerList().size());

            }
        }


    }


    @DisplayName("Given there is an business flight")
    @Nested
    class BusinessFlightTest {

        private Flight businessFlight;
        private Passenger mike;
        private Passenger jon;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
            mike = new Passenger("Mike", false);
            jon = new Passenger("Jon", true);
        }

        @DisplayName("When we have a usual passenger")
        @Nested
        class UsualPassenger {

            @Test
            @DisplayName("Then you cant NOT add or remove him from a business flight")
            public void testBusinessFlightUsualPassenger() {

                assertEquals(false, businessFlight.addPassenger(mike));
                assertEquals(0, businessFlight.getPassengerList().size());
                assertEquals(false, businessFlight.removePassenger(mike));
                assertEquals(0, businessFlight.getPassengerList().size());
            }
        }

        @DisplayName("When we have a vip passenger")
        @Nested
        class VipPassenger {
            @Test
            @DisplayName("Then you cant add or remove him from a business flight")
            public void testBusinessFlightVipPassenger() {

                assertEquals(true, businessFlight.addPassenger(jon));
                assertEquals(1, businessFlight.getPassengerList().size());
                assertEquals(false, businessFlight.removePassenger(jon));
                assertEquals(1, businessFlight.getPassengerList().size());
            }
        }


    }


}
