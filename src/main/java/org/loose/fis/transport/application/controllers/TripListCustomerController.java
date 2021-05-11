package org.loose.fis.transport.application.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.services.TripService;

import java.io.IOException;

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
        try{
            Stage stage = (Stage) TEXT.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("selectDelivery.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Trip t;
    @FXML
    void handleBookTripButton(ActionEvent event) {
        t = TripService.searchById(Integer.parseInt(id.getText()));
        if(t == null) TEXT.setText("Incorrect id!");
        else {
            try{
                Stage stage = (Stage) TEXT.getScene().getWindow();
                Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("selectTrip.fxml"));
                Scene scene = new Scene(viewStudentsRoot, 600, 400);
                stage.setScene(scene);
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void handleLogoutButton(ActionEvent event) {
        try{
            Stage stage = (Stage) TEXT.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleMyDeliveriesButton(ActionEvent event) {
        try {
            Stage stage = (Stage) TEXT.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("pastDeliveries.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 900);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleMyTripsButton(ActionEvent event) {
        try {
            Stage stage = (Stage) TEXT.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("pastBookings.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 900);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
