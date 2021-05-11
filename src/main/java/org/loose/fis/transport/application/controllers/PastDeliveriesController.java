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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.transport.application.model.DeliveryRequest;
import org.loose.fis.transport.application.model.TripRequest;
import org.loose.fis.transport.application.model.TripRequest2;
import org.loose.fis.transport.application.services.DeliveryRequestService;
import org.loose.fis.transport.application.services.TripRequestService;

import java.awt.event.ActionEvent;
import java.io.IOException;

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
        ObservableList<DeliveryRequest>l=DeliveryRequestService.Lista();
        ObservableList<DeliveryRequest>list= FXCollections.observableArrayList();
        for (DeliveryRequest k:
                l) {
            if(k.getCustomerName().equals(LoginController.customerName)){
                list.add(k);
            }}
        table.setItems(list);
    }



    public void handleBackButton(javafx.event.ActionEvent actionEvent) {
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
