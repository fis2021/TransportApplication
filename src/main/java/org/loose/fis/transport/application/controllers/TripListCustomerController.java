package org.loose.fis.transport.application.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.services.TripService;

public class TripListCustomerController {

    @FXML
    private TableView<Trip> table;

    @FXML
    private TableColumn<Trip, String> colVehicleType;

    @FXML
    private TableColumn<Trip, Integer> colSpace;

    @FXML
    private TableColumn<Trip, String> colDate;

    @FXML
    private TableColumn<Trip, String> colTime;

    @FXML
    private TableColumn<Trip, Integer> colPrice;

    @FXML
    private TableColumn<Trip, String> colRoute;

    @FXML
    private TableColumn<Trip, Integer> colId;

    @FXML
    private Button bookTripButton;

    @FXML
    private Button myTripsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button myDeliveriesButton;

    @FXML
    private Text TEXT;

    @FXML
    private TextField id;

    @FXML
    private Button bookDelivery;

    @FXML
    public void initialize() {

        colVehicleType.setCellValueFactory(new PropertyValueFactory<Trip,String>("vehicleType"));
        colSpace.setCellValueFactory(new PropertyValueFactory<Trip,Integer>("space"));
        colDate.setCellValueFactory(new PropertyValueFactory<Trip,String>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<Trip,String>("time"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Trip,Integer>("price"));
        colRoute.setCellValueFactory(new PropertyValueFactory<Trip,String>("route"));
        colId.setCellValueFactory(new PropertyValueFactory<Trip,Integer>("id"));

        table.setItems(TripService.Lista());
    }

    @FXML
    void handleBookDeliveryButton(ActionEvent event) {

    }


    @FXML
    void handleBookTripButton(ActionEvent event) {

    }

    @FXML
    void handleLogoutButton(ActionEvent event) {

    }

    @FXML
    void handleMyDeliveriesButton(ActionEvent event) {

    }

    @FXML
    void handleMyTripsButton(ActionEvent event) {

    }

}
