package org.loose.fis.transport.application.model;

import org.dizitart.no2.objects.Id;

public class Trip {
    @Id
    private String vehicleType;
    private int space;
    private String date;
    private String time;
    private int price;
    private String route;
    public Trip()
    {

    }

    public Trip(String vehicleType, int space, String date, String time, int price, String route) {
        this.vehicleType = vehicleType;
        this.space = space;
        this.date = date;
        this.time = time;
        this.price = price;
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
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
}
