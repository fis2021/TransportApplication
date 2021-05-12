package org.loose.fis.transport.application.model;

import org.dizitart.no2.objects.Id;

public class Trip {
    private String vehicleType;
    private int space;
    private String date;
    private String time;
    private int price;
    private String route;
    @Id
    private int id;

    public Trip()
    {

    }

    public Trip(String vehicleType, int space, String date, String time, int price, String route, int id) {
        this.vehicleType = vehicleType;
        this.space = space;
        this.date = date;
        this.time = time;
        this.price = price;
        this.route = route;
        this.id = id;
    }

    /*public Trip(String vehicleType, int space, String date, String time, int price, String route) {
        this.vehicleType = vehicleType;
        this.space = space;
        this.date = date;
        this.time = time;
        this.price = price;
        this.route = route;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Trip:\n" +
                "vehicle type " + vehicleType + "\n" +
                "space " + space + "\n" +
                "date " + date + "\n" +
                "time " + time + "\n" +
                "price " + price + "\n" +
                "route" + route + "\n" +
                "id " + id + "\n" ;
    }
}
