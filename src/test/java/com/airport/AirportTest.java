package com.airport;


import org.junit.jupiter.api.*;

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
                        () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                        () -> assertTrue(economyFlight.getPassengerSet().contains(mike)),
                        () -> assertEquals(true, economyFlight.removePassenger(mike)),
                        () -> assertEquals("Mike", mike.getName()),
                        () -> assertEquals(0, economyFlight.getPassengerSet().size())
                );

            }

            @RepeatedTest(5)
            @DisplayName("Then you can NOT  add him to an economy flight more than once")
            public void testEconomyFlightUsualPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(mike);
                }
                assertAll("Verify an usual passenger can be added to an economy flight only once",
                        () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                        () -> assertTrue(economyFlight.getPassengerSet().contains(mike)),
                        () -> assertTrue(economyFlight.getPassengerSet().contains(mike))
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
                assertEquals(1, economyFlight.getPassengerSet().size());
                assertTrue(economyFlight.getPassengerSet().contains(jon));

                assertEquals(false, economyFlight.removePassenger(jon));
                assertEquals(1, economyFlight.getPassengerSet().size());

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
                assertEquals(0, businessFlight.getPassengerSet().size());
                assertEquals(false, businessFlight.removePassenger(mike));
                assertEquals(0, businessFlight.getPassengerSet().size());
            }
        }

        @DisplayName("When we have a vip passenger")
        @Nested
        class VipPassenger {
            @Test
            @DisplayName("Then you cant add or remove him from a business flight")
            public void testBusinessFlightVipPassenger() {

                assertEquals(true, businessFlight.addPassenger(jon));
                assertEquals(1, businessFlight.getPassengerSet().size());
                assertEquals(false, businessFlight.removePassenger(jon));
                assertEquals(1, businessFlight.getPassengerSet().size());
            }
        }


    }

    @DisplayName("Given there is a premium flight")
    @Nested
    class PremiumFlightTest {

        private Flight premiumFlight;
        private Passenger mike;
        private Passenger jon;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiunFlight("3");
            mike = new Passenger("Mike", false);
            jon = new Passenger("Jon", true);
        }

        @Nested
        @DisplayName("When we have a usual passenger")
        class UsualPassenger {

            @Test
            @DisplayName("Then you can NOT add or remove him from a premium flight")
            public void testPremiumFlightUsualPassenger() {
                assertAll("Verify all conditions for an usual passenger and a premium flight",
                        () -> assertFalse(premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengerSet().size()),
                        () -> assertFalse(premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengerSet().size())
                );
            }

        }


        @Nested
        @DisplayName("When we have a vip passenger")
        class VipPassenger {

            @Test
            @DisplayName("Then you can add and remove him from a premium flight")
            public void testPremiumFlightVipPassenger() {
                assertAll("Verify all conditions for a vip passenger and a premium flight",
                        () -> assertTrue(premiumFlight.addPassenger(jon)),
                        () -> assertEquals(1, premiumFlight.getPassengerSet().size()),
                        () -> assertTrue(premiumFlight.removePassenger(jon)),
                        () -> assertEquals(0, premiumFlight.getPassengerSet().size())

                );
            }

        }

    }

}
