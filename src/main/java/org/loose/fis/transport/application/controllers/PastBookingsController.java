package org.loose.fis.transport.application.controllers;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.model.TripRequest;
import org.loose.fis.transport.application.model.TripRequest2;
import org.loose.fis.transport.application.model.User;
import org.loose.fis.transport.application.services.TripRequestService;
import org.loose.fis.transport.application.services.TripService;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class PastBookingsController {
    @FXML
    private TableView<TripRequest2> table;

    @FXML
    private TableColumn<TripRequest2, String> colVehicleType;

    @FXML
    private TableColumn<TripRequest2, Integer> colSpace;

    @FXML
    private TableColumn<TripRequest2, String> colDate;

    @FXML
    private TableColumn<TripRequest2, String> colTime;

    @FXML
    private TableColumn<TripRequest2, Integer> colPrice;

    @FXML
    private TableColumn<TripRequest2, String> colRoute;

    @FXML
    private TableColumn<TripRequest2, String> colStatus;

    @FXML
    private Button backButton;

    @FXML
    private Text TEXT;
    @FXML
    public void initialize() {

        colVehicleType.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("vehicleType"));
        colSpace.setCellValueFactory(new PropertyValueFactory<TripRequest2,Integer>("space"));
        colDate.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("time"));
        colPrice.setCellValueFactory(new PropertyValueFactory<TripRequest2,Integer>("price"));
        colRoute.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("route"));
        colStatus.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("approved"));

        ObservableList<TripRequest>l=TripRequestService.Lista();
        ObservableList<TripRequest2>list= FXCollections.observableArrayList();
        for (TripRequest k:
             l) {
            String a="Pending";
            if(k.getApproved()==0)
                a="Denied";
            if(k.getApproved()==1)
                a="Approved";
            list.add(new TripRequest2(k.getTrip().getVehicleType(),k.getTrip().getSpace(),k.getTrip().getDate(),k.getTrip().getTime(),k.getTrip().getPrice(),k.getTrip().getRoute(),a));
        }
        table.setItems(list);
    }

    @FXML
    void handleBackButton(ActionEvent event) {
        try {
            Stage stage = (Stage) TEXT.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("tripListCustomer.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 700);
            stage.setScene(scene);
        } catch (IOException p) {
            p.printStackTrace();
        }
    }
}
