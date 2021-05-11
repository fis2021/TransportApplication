package org.loose.fis.transport.application.model;

import org.dizitart.no2.objects.Id;

public class TripRequest2 {
    private String vehicleType;
    private int space;
    private String date;
    private String time;
    private int price;
    private String route;
    private String approved;

    public TripRequest2(String vehicleType, int space, String date, String time, int price, String route,String approved) {
        this.vehicleType = vehicleType;
        this.space = space;
        this.date = date;
        this.time = time;
        this.price = price;
        this.route = route;
        this.approved = approved;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRoute(String route) {
        this.route = route;
    }


    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getSpace() {
        return space;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public String getRoute() {
        return route;
    }


    public String getApproved() {
        return approved;
    }
}
