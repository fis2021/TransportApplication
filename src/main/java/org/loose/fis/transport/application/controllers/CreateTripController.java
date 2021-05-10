package org.loose.fis.transport.application.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.model.Vehicle;
import org.loose.fis.transport.application.services.TripService;


public class CreateTripController {
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
    private TextField vehicleType;

    @FXML
    private TextField space;

    @FXML
    private TextField date;

    @FXML
    private TextField time;

    @FXML
    private TextField price;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button deliveriesAndTripsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button editVehiclesButton;

    @FXML
    private Button seeRequestsButton;

    @FXML
    private Text TEXT;

    @FXML
    private TextField route;

    @FXML
    public void initialize() {
        colVehicleType.setCellValueFactory(new PropertyValueFactory<Trip,String>("vehicleType"));
        colDate.setCellValueFactory(new PropertyValueFactory<Trip,String>("date"));
        colSpace.setCellValueFactory(new PropertyValueFactory<Trip,Integer>("space"));
        colRoute.setCellValueFactory(new PropertyValueFactory<Trip,String>("route"));
        colTime.setCellValueFactory(new PropertyValueFactory<Trip,String>("time"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Trip,Integer>("price"));

        table.setItems(TripService.Lista());
    }

    @FXML
    public void handleAddButton(ActionEvent event) {
        try {
            TripService.addTrip(vehicleType.getText(), Integer.parseInt(space.getText()), date.getText(), time.getText(), Integer.parseInt(price.getText()),route.getText());
            colVehicleType.setCellValueFactory(new PropertyValueFactory<Trip,String>("vehicleType"));
            colDate.setCellValueFactory(new PropertyValueFactory<Trip,String>("date"));
            colSpace.setCellValueFactory(new PropertyValueFactory<Trip,Integer>("space"));
            colRoute.setCellValueFactory(new PropertyValueFactory<Trip,String>("route"));
            colTime.setCellValueFactory(new PropertyValueFactory<Trip,String>("time"));
            colPrice.setCellValueFactory(new PropertyValueFactory<Trip,Integer>("price"));
            table.setItems(TripService.Lista());
            TEXT.setText("");}
        catch(NumberFormatException k)
        {
            TEXT.setText("Wrong data type");
        }
    }

    @FXML
    void handleDeleteButton(ActionEvent event) {

    }

    @FXML
    void handleDeliveriesAndTripsButton(ActionEvent event) {

    }

    @FXML
    void handleEditVehiclesButton(ActionEvent event) {

    }

    @FXML
    void handleLogoutButton(ActionEvent event) {

    }

    @FXML
    void handleSeeRequestsButton(ActionEvent event) {

    }

}
