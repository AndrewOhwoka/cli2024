package com.keyin.http.cli;

import com.keyin.domian.Aircraft;
import com.keyin.domian.Airport;
import com.keyin.domian.City;
import com.keyin.domian.Passenger;
import com.keyin.http.client.RESTClient;

import java.util.List;

public class HTTPRestCLIApplication {

    private RESTClient restClient;

    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }

        return restClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    public String generateAircraftReport() {
        List<Aircraft> aircrafts = getRestClient().getAllAircrafts();

        StringBuilder report = new StringBuilder();

        for (Aircraft aircraft : aircrafts) {
            report.append(aircraft.getType());
            report.append(" - ");
            report.append(aircraft.getAirlineName());

            if (aircrafts.indexOf(aircraft) != (aircrafts.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    public String generateListOfAirportsForSpecificCity() {
        List<Airport> airports = getRestClient().getAirportsForCity();

        StringBuffer report = new StringBuffer();

        for (Airport airport : airports) {
            report.append(airport.getName());
            report.append(" - ");
            report.append(airport.getCode());

            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }

        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();

        String serverURL = args[0];

        if (serverURL != null && !serverURL.isEmpty()) {

            RESTClient restClient = new RESTClient();
            restClient.setServerURL(serverURL);

            cliApp.setRestClient(restClient);

            if (serverURL.contains("getAircrafts")) {
                cliApp.generateAircraftReport();
            } else if (serverURL.contains("getAirportsForCity")) {
                cliApp.generateListOfAirportsForSpecificCity();
            } else {
                System.out.println("Invalid URL");
            }
        }
    }
}