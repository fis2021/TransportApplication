package org.loose.fis.transport.application.model;

public class TripRequest {

    private Trip trip;
    private String name;
    private int approved;

    public TripRequest(){}

    public TripRequest(Trip trip, String name) {
        this.trip = trip;
        this.name = name;
    }

    public TripRequest(Trip trip, String name, int approved) {
        this.trip = trip;
        this.name = name;
        this.approved = approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public int getApproved() {
        return approved;
    }

    @Override
    public String toString() {
        return "TripRequest:" +
                "trip: " + trip.toString() +
                "name: " + name;
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
