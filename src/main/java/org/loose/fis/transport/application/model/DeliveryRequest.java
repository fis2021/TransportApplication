package org.loose.fis.transport.application.model;

public class DeliveryRequest {

    private String pickupAddress;
    private String vehicleType;
    private String deliveryAddress;
    private String additionalInformation;
    private String customerName;

    public DeliveryRequest(){}

    public DeliveryRequest(String pickupAddress, String vehicleType, String deliveryAddress, String additionalInformation, String customerName) {
        this.pickupAddress = pickupAddress;
        this.vehicleType = vehicleType;
        this.deliveryAddress = deliveryAddress;
        this.additionalInformation = additionalInformation;
        this.customerName = customerName;
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
}
