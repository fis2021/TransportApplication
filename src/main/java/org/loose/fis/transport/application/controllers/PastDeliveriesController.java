package org.loose.fis.transport.application.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.loose.fis.transport.application.model.DeliveryRequest;
import org.loose.fis.transport.application.model.TripRequest;
import org.loose.fis.transport.application.model.TripRequest2;
import org.loose.fis.transport.application.services.DeliveryRequestService;
import org.loose.fis.transport.application.services.TripRequestService;

import java.awt.event.ActionEvent;

public class PastDeliveriesController {
    @FXML
    private TableView<DeliveryRequest> table;

    @FXML
    private TableColumn<DeliveryRequest, String> colVehicleType;

    @FXML
    private TableColumn<DeliveryRequest, String> colPickupAdress;

    @FXML
    private TableColumn<DeliveryRequest, String > colDeliveryAdress;

    @FXML
    private TableColumn<DeliveryRequest, String> colAditionalInformation;

    @FXML
    private TableColumn<DeliveryRequest, String> colStatus;

    @FXML
    private Button backButton;

    @FXML
    private Text TEXT;

    public void initialize() {

        colVehicleType.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("vehicleType"));
        colPickupAdress.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("pickupAddress"));
        colDeliveryAdress.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("deliveryAddress"));
        colAditionalInformation.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("additionalInformation"));
        colStatus.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("status"));

        table.setItems(DeliveryRequestService.Lista());
    }



    public void handleBackButton(javafx.event.ActionEvent actionEvent) {
    }
}
