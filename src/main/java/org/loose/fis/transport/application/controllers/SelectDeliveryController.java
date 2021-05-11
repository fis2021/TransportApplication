package org.loose.fis.transport.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.loose.fis.transport.application.services.DeliveryRequestService;
import org.loose.fis.transport.application.services.TripRequestService;

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
        DeliveryRequestService.addRequest(pickupAddress.getText(), vehicleType.getText(),deliveryAddress.getText(),additionalInformation.getText(), LoginController.customerName);
        try{
            Stage stage = (Stage) vehicleType.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("tripListCustomer.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
