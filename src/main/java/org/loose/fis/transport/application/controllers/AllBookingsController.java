package org.loose.fis.transport.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AllBookingsController {
    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> colVehicleType;

    @FXML
    private TableColumn<?, ?> colSpace;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colRoute;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<?> table2;

    @FXML
    private TableColumn<?, ?> colVehicleType2;

    @FXML
    private TableColumn<?, ?> colPickupAddress;

    @FXML
    private TableColumn<?, ?> colDeliveryAddress;

    @FXML
    private TableColumn<?, ?> colAdditionalInformation;

    @FXML
    private TableColumn<?, ?> colStatus2;

    @FXML
    private Button backButton;

    @FXML
    private Text TEXT;

    @FXML
    private Button pendingButton;

    @FXML
    private Button deniedButton;

    @FXML
    private Button approvedButton;

    @FXML
    void handleApprovedButton(ActionEvent event) {

    }

    @FXML
    void handleBackButton(ActionEvent event) {
        try {
            Stage stage = (Stage) TEXT.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("meansOfTransport.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 700);
            stage.setScene(scene);
        } catch (IOException p) {
            p.printStackTrace();
        }
    }

    @FXML
    void handleDeniedButton(ActionEvent event) {

    }

    @FXML
    void handlePendingButton(ActionEvent event) {

    }
}
