package org.loose.fis.transport.application.controllers;

import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.loose.fis.transport.application.model.Vehicle;
import org.loose.fis.transport.application.services.DeliveryRequestService;
import org.loose.fis.transport.application.services.TripRequestService;
import org.loose.fis.transport.application.services.VehicleService;

import java.io.IOException;

public class SelectDeliveryController {

    @FXML
    private TextField pickupAddress;

    @FXML
    private TextField vehicleType;

    @FXML
    private TextField deliveryAddress;

    @FXML
    private TextField additionalInformation;

    @FXML
    private Button backButton;

    @FXML
    private Button requestDeliveryButton;
    @FXML
    private Text TEXT;

    @FXML
    void handleBackButton(ActionEvent event) {
        try{
            Stage stage = (Stage) vehicleType.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("tripListCustomer.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleRequestDeliveryButton(ActionEvent event) throws Exception {
        ObservableList<Vehicle>l= VehicleService.Lista();
        int ok=0;
        for (Vehicle k:
             l) {
            if(k.getVehicleType().equals(vehicleType.getText()))
            {
                ok=1;
            }
        }
        if(ok==0)
            TEXT.setText("This type of vehicle is not available");
        else
        {
        DeliveryRequestService.addRequest(pickupAddress.getText(), vehicleType.getText(),deliveryAddress.getText(),additionalInformation.getText(), LoginController.customerName);
        try{
            Stage stage = (Stage) vehicleType.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("tripListCustomer.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }}

}
