package org.loose.fis.transport.application.controllers;

import javafx.collections.FXCollections;
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
import org.loose.fis.transport.application.model.DeliveryRequest;
import org.loose.fis.transport.application.model.TripRequest;
import org.loose.fis.transport.application.model.TripRequest2;
import org.loose.fis.transport.application.services.DeliveryRequestService;
import org.loose.fis.transport.application.services.TripRequestService;

import java.io.IOException;

public class AllBookingsController {
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
    private TableView<DeliveryRequest> table2;

    @FXML
    private TableColumn<DeliveryRequest, String> colVehicleType2;

    @FXML
    private TableColumn<DeliveryRequest, String> colPickupAddress;

    @FXML
    private TableColumn<DeliveryRequest, String> colDeliveryAddress;

    @FXML
    private TableColumn<DeliveryRequest, String> colAdditionalInformation;

    @FXML
    private TableColumn<DeliveryRequest, String> colStatus2;

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
    private Button allButton;

    @FXML
    void handleAllButton(ActionEvent event) {
        colVehicleType.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("vehicleType"));
        colSpace.setCellValueFactory(new PropertyValueFactory<TripRequest2,Integer>("space"));
        colDate.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("time"));
        colPrice.setCellValueFactory(new PropertyValueFactory<TripRequest2,Integer>("price"));
        colRoute.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("route"));
        colStatus.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("approved"));

        colVehicleType2.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("vehicleType"));
        colPickupAddress.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("pickupAddress"));
        colDeliveryAddress.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("deliveryAddress"));
        colAdditionalInformation.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("additionalInformation"));
        colStatus2.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("status"));

        ObservableList<TripRequest> l= TripRequestService.Lista();
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

        table2.setItems(DeliveryRequestService.Lista());
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
    void handleApprovedButton(ActionEvent event) {
        colVehicleType.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("vehicleType"));
        colSpace.setCellValueFactory(new PropertyValueFactory<TripRequest2,Integer>("space"));
        colDate.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("time"));
        colPrice.setCellValueFactory(new PropertyValueFactory<TripRequest2,Integer>("price"));
        colRoute.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("route"));
        colStatus.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("approved"));

        colVehicleType2.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("vehicleType"));
        colPickupAddress.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("pickupAddress"));
        colDeliveryAddress.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("deliveryAddress"));
        colAdditionalInformation.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("additionalInformation"));
        colStatus2.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("status"));

        ObservableList<TripRequest> l= TripRequestService.Lista();
        ObservableList<TripRequest2>list= FXCollections.observableArrayList();
        for (TripRequest k:
                l) {
            String a="Pending";
            if(k.getApproved()==0)
                a="Denied";
            if(k.getApproved()==1)
                a="Approved";
            if(a.equals("Approved")){
            list.add(new TripRequest2(k.getTrip().getVehicleType(),k.getTrip().getSpace(),k.getTrip().getDate(),k.getTrip().getTime(),k.getTrip().getPrice(),k.getTrip().getRoute(),a));
        }}
        table.setItems(list);

        ObservableList<DeliveryRequest>l2=DeliveryRequestService.Lista();
        ObservableList<DeliveryRequest>list2= FXCollections.observableArrayList();
        for (DeliveryRequest x:
                l2) {
            if(x.getApproved()==1){
                list2.add(x);
            }}

        table2.setItems(list2);
    }

    @FXML
    void handleDeniedButton(ActionEvent event) {
        colVehicleType.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("vehicleType"));
        colSpace.setCellValueFactory(new PropertyValueFactory<TripRequest2,Integer>("space"));
        colDate.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("time"));
        colPrice.setCellValueFactory(new PropertyValueFactory<TripRequest2,Integer>("price"));
        colRoute.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("route"));
        colStatus.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("approved"));

        colVehicleType2.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("vehicleType"));
        colPickupAddress.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("pickupAddress"));
        colDeliveryAddress.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("deliveryAddress"));
        colAdditionalInformation.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("additionalInformation"));
        colStatus2.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("status"));

        ObservableList<TripRequest> l= TripRequestService.Lista();
        ObservableList<TripRequest2>list= FXCollections.observableArrayList();
        for (TripRequest k:
                l) {
            String a="Pending";
            if(k.getApproved()==0)
                a="Denied";
            if(k.getApproved()==1)
                a="Approved";
            if(a.equals("Denied")){
                list.add(new TripRequest2(k.getTrip().getVehicleType(),k.getTrip().getSpace(),k.getTrip().getDate(),k.getTrip().getTime(),k.getTrip().getPrice(),k.getTrip().getRoute(),a));
            }}
        table.setItems(list);

        ObservableList<DeliveryRequest>l2=DeliveryRequestService.Lista();
        ObservableList<DeliveryRequest>list2= FXCollections.observableArrayList();
        for (DeliveryRequest x:
                l2) {
            if(x.getApproved()==0){
                list2.add(x);
            }}

        table2.setItems(list2);
    }

    @FXML
    void handlePendingButton(ActionEvent event) {

    }

    @FXML
    public void initialize() {

        colVehicleType.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("vehicleType"));
        colSpace.setCellValueFactory(new PropertyValueFactory<TripRequest2,Integer>("space"));
        colDate.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("time"));
        colPrice.setCellValueFactory(new PropertyValueFactory<TripRequest2,Integer>("price"));
        colRoute.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("route"));
        colStatus.setCellValueFactory(new PropertyValueFactory<TripRequest2,String>("approved"));

        colVehicleType2.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("vehicleType"));
        colPickupAddress.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("pickupAddress"));
        colDeliveryAddress.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("deliveryAddress"));
        colAdditionalInformation.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("additionalInformation"));
        colStatus2.setCellValueFactory(new PropertyValueFactory<DeliveryRequest,String>("status"));

        ObservableList<TripRequest> l= TripRequestService.Lista();
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

        table2.setItems(DeliveryRequestService.Lista());
    }
}
