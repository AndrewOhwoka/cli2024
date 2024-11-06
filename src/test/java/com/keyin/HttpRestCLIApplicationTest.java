package com.keyin;

import com.keyin.domian.Aircraft;
import com.keyin.domian.Airport;
import com.keyin.domian.City;
import com.keyin.domian.Passenger;
import com.keyin.http.cli.HTTPRestCLIApplication;
import com.keyin.http.client.RESTClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HttpRestCLIApplicationTest {

    @Mock
    private RESTClient mockRestClient;

    @InjectMocks
    private HTTPRestCLIApplication httpRestCLIApplicationUnderTest;

    @BeforeEach
    void setUp() {
        httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();
        httpRestCLIApplicationUnderTest.setRestClient(mockRestClient);
    }

    @Test
    public void testGenerateAircraftReport() {
        Aircraft aircraft = new Aircraft();
        aircraft.setType("Boeing 747");
        aircraft.setAirlineName("Airline A");

        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(aircraft);

        Mockito.when(mockRestClient.getAllAircrafts()).thenReturn(aircraftList);
        httpRestCLIApplicationUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAircraftReport().contains("Boeing 747"));
    }

    @Test
    public void testGenerateListOfAirportsForSpecificCity() {
        Airport airport = new Airport();
        airport.setName("JFK International");
        airport.setCode("JFK");

        List<Airport> airportList = new ArrayList<>();
        airportList.add(airport);

        Mockito.when(mockRestClient.getAirportsForCity()).thenReturn(airportList);
        httpRestCLIApplicationUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateListOfAirportsForSpecificCity().contains("JFK International"));
    }

    @Test
    public void testGenerateCityReport() {
        City city = new City();
        city.setName("New York");
        city.setState("NY");

        List<City> cityList = new ArrayList<>();
        cityList.add(city);

        Mockito.when(mockRestClient.getAllCities()).thenReturn(cityList);
        httpRestCLIApplicationUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateCityReport().contains("New York"));
    }

    @Test
    public void testGeneratePassengerReport() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setPhoneNumber("1234567890");

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(passenger);

        Mockito.when(mockRestClient.getAllPassengers()).thenReturn(passengerList);
        httpRestCLIApplicationUnderTest.setRestClient(mockRestClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generatePassengerReport().contains("John Doe"));
    }
}