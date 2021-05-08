package org.loose.fis.transport.application.model;

import org.dizitart.no2.objects.Id;

public class Vehicle {
    @Id
    private String vehicleType;
    private String transportType;
    private int space;
    private int numberOfVehicles;
    private int availableVehicles;
    public Vehicle()
    {

    }
    public Vehicle(String vehicleType, String transportType, int space, int numberOfVehicles, int availableVehicles) {
        this.vehicleType = vehicleType;
        this.transportType = transportType;
        this.space = space;
        this.numberOfVehicles = numberOfVehicles;
        this.availableVehicles = availableVehicles;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getTransportType() {
        return transportType;
    }

    public int getSpace() {
        return space;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public int getAvailableVehicles() {
        return availableVehicles;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public void setAvailableVehicles(int availableVehicles) {
        this.availableVehicles = availableVehicles;
    }

}
