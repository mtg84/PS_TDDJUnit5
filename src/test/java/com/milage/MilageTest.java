package com.milage;

import com.airport.Flight;
import com.airport.Passenger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MilageTest {

    private Milage milage;

    @BeforeAll
    void beforeAll() {
        milage = new Milage();
    }

    @ParameterizedTest
    // disabled to execute the text with the csv input
    @Disabled
    // It passess  a csv that contains number of the flight; type of flight; passengerName; if passenger is VIP; mileage of flight;
    @ValueSource(strings = {"1; e; Mike; false; 349", "2; b; Jon; true; 270", "3; e; Mike; false; 319", "4; p; Jon; true; 817"})
    void checkGivenPoints(@ConvertWith(FlightArgumentConverter.class) Flight flight) {

        for (Passenger passenger : flight.getPassengerSet()) {
            milage.addMilage(passenger, flight.getDistance());
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/flights_information.csv")
    void checkGivenPointsWithCsvInput(@ConvertWith(FlightArgumentConverter.class) Flight flight) {
        for (Passenger passenger : flight.getPassengerSet()) {
            milage.addMilage(passenger, flight.getDistance());
        }
    }

    @AfterAll
    void afterAll() {
        milage.calculateGivenPoints();
        assertEquals(33, milage.getPassengersPointsMap().get(new Passenger("Mike", false)).intValue());
        assertEquals(109, milage.getPassengersPointsMap().get(new Passenger("Jon", true)).intValue());
    }

}