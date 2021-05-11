package org.loose.fis.transport.application.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.loose.fis.transport.application.exceptions.AccountExists;
import org.loose.fis.transport.application.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.User;
import org.loose.fis.transport.application.model.Vehicle;
import org.loose.fis.transport.application.services.VehicleService;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class MeansOfTransportController {

    @FXML
    private TableView<Vehicle> table;

    @FXML
    private TableColumn<Vehicle, String> colVehicleType;

    @FXML
    private TableColumn<Vehicle, String> colTransportType;

    @FXML
    private TableColumn<Vehicle, Integer> colSpace;

    @FXML
    private TableColumn<Vehicle, Integer> colNumberOfVehicles;

    @FXML
    private TableColumn<Vehicle, Integer> colAvailableVehicles;

    @FXML
    private TextField vehicleType;

    @FXML
    private TextField transportType;

    @FXML
    private TextField space;

    @FXML
    private TextField numberOfVehicles;

    @FXML
    private TextField availableVehicles;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button deliveriesAndTripsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button scheduleTripButton;

    @FXML
    private Button seeRequestsButton;
    @FXML
    private Text TEXT;

    @FXML
    public void initialize() {
        colVehicleType.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("vehicleType"));
        colTransportType.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("transportType"));
        colSpace.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("space"));
        colNumberOfVehicles.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("numberOfVehicles"));
        colAvailableVehicles.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("availableVehicles"));

        table.setItems(VehicleService.Lista());
    }

    @FXML
    public void handleDeleteButton(ActionEvent event) {

    }

    @FXML
    public void handleDeliveriesAndTripsButton(ActionEvent event) {
        try {
            Stage stage = (Stage) TEXT.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("allBookings.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 700);
            stage.setScene(scene);
        } catch (IOException p) {
            p.printStackTrace();
        }
    }

    @FXML
    public void handleEditButton(ActionEvent event) {

    }

    @FXML
    public void handleLogoutButton(ActionEvent event) {

    }

    @FXML
    public void handleScheduleTripButton(ActionEvent event) {

    }

    @FXML
    public void handleSeeRequestsButton(ActionEvent event) {

    }

    public void handleAddButton(javafx.event.ActionEvent actionEvent) {
        try {
            VehicleService.addVehicle(vehicleType.getText(), transportType.getText(), Integer.parseInt(space.getText()), Integer.parseInt(numberOfVehicles.getText()), Integer.parseInt(availableVehicles.getText()));
            colVehicleType.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("vehicleType"));
            colTransportType.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("transportType"));
            colSpace.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("space"));
            colNumberOfVehicles.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("numberOfVehicles"));
            colAvailableVehicles.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("availableVehicles"));

            table.setItems(VehicleService.Lista());
            TEXT.setText("");}
        catch(NumberFormatException k)
        {
            TEXT.setText("Wrong data type");
        }
        catch(VehicleExists k)
        {
            TEXT.setText("Vehicle type already exists");
        }

    }

    public void handleEditButton(javafx.event.ActionEvent actionEvent) {
        try {

            VehicleService.editVehicle(vehicleType.getText(),transportType.getText(),Integer.parseInt(space.getText()), Integer.parseInt(numberOfVehicles.getText()), Integer.parseInt(availableVehicles.getText()));
            table.setItems(VehicleService.Lista());
        }
        catch (NumberFormatException k)
        {
            TEXT.setText("Wrong data type");
        }

    }

    public void handleDeleteButton(javafx.event.ActionEvent actionEvent) {

            VehicleService.deleteVehicle(vehicleType.getText());
            table.setItems(VehicleService.Lista());

    }

    public void handleDeliveriesAndTripsButton(javafx.event.ActionEvent actionEvent) {
    }

    public void handleLogoutButton(javafx.event.ActionEvent actionEvent) {
        try{
        Stage stage = (Stage) TEXT.getScene().getWindow();
        Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Scene scene = new Scene(viewStudentsRoot, 600, 400);
        stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void handleScheduleTripButton(javafx.event.ActionEvent actionEvent) {
        try{
            Stage stage = (Stage) TEXT.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("CreateTrip.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 900);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSeeRequestsButton(javafx.event.ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) TEXT.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("approveRequests.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 700);
            stage.setScene(scene);
        } catch (IOException p) {
            p.printStackTrace();
        }
    }
}
