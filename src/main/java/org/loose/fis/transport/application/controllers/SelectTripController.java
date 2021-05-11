package org.loose.fis.transport.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.loose.fis.transport.application.services.TripRequestService;

import java.io.IOException;

public class SelectTripController {

    @FXML
    private Button backButton;

    @FXML
    private Button requestSpotButton;

    @FXML
    private Text someText;

    public void initialize(){
        someText.setText(TripListCustomerController.t.toString() + " ");
    }

    public void handleBackButton(javafx.event.ActionEvent actionEvent) {
        try{
            Stage stage = (Stage) someText.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("tripListCustomer.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRequestSpotButton(javafx.event.ActionEvent actionEvent) throws Exception {
        TripRequestService.addRequest(TripListCustomerController.t, LoginController.customerName);
        //someText.setText(TripListCustomerController.t.toString() + " " +LoginController.customerName );
        try{
            Stage stage = (Stage) someText.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("tripListCustomer.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
