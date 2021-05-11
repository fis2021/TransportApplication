package org.loose.fis.transport.application.model;

import org.dizitart.no2.objects.Id;

public class DeliveryRequest {
    @Id
    private int id;
    private String pickupAddress;
    private String vehicleType;
    private String deliveryAddress;
    private String additionalInformation;
    private String customerName;
    private int approved;

    public DeliveryRequest(){}


    public DeliveryRequest(String pickupAddress, String vehicleType, String deliveryAddress, String additionalInformation, String customerName, int approved,int id) {
        this.pickupAddress = pickupAddress;
        this.vehicleType = vehicleType;
        this.deliveryAddress = deliveryAddress;
        this.additionalInformation = additionalInformation;
        this.customerName = customerName;
        this.approved = approved;
        this.id=id;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString() {
        return "DeliveryRequest:\n" +
                "Pickup address " + pickupAddress +
                "\nVehicle type " + vehicleType +
                "\nDelivery address " + deliveryAddress +
                "\nAdditional information " + additionalInformation +
                "\nCustomer name " + customerName;
    }
}
