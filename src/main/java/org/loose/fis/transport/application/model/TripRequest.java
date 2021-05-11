package org.loose.fis.transport.application.model;
import org.dizitart.no2.objects.Id;

public class TripRequest {

    @Id
    private Trip trip;
    private String name;

    public TripRequest(){}

    public TripRequest(Trip trip, String name) {
        this.trip = trip;
        this.name = name;
    }

    public Trip getTrip() {
        return trip;
    }

    public String getName() {
        return name;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setName(String name) {
        this.name = name;
    }
}
