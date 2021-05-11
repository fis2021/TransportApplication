package org.loose.fis.transport.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ApproveRequestsController {

    @FXML
    private Button approveTripButton;

    @FXML
    private Button denyTripButton;

    @FXML
    private Text trips;

    @FXML
    private Text deliveries;

    @FXML
    private Button denyDelivery;

    @FXML
    private Button approveDelivery;

    @FXML
    private Button backButton;

    public void handleApproveTripButton(javafx.event.ActionEvent actionEvent) {
    }

    public void handleDenyTripButton(javafx.event.ActionEvent actionEvent) {
    }

    public void handleDenyDelivery(javafx.event.ActionEvent actionEvent) {
    }

    public void handleApproveDelivery(javafx.event.ActionEvent actionEvent) {
    }

    public void handleBackButton(javafx.event.ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) deliveries.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("meansOfTransport.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 700);
            stage.setScene(scene);
        } catch (IOException p) {
            p.printStackTrace();
        }
    }
}
