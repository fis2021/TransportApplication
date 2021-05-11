package org.loose.fis.transport.application.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.transport.application.model.TripRequest;
import org.loose.fis.transport.application.services.TripRequestService;

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

    public void initialize() {
        ObservableList<TripRequest>l= TripRequestService.Lista();
        boolean ok=false;
        for (TripRequest k:
             l) {
            if(k.getApproved()==2)
            {
                ok=true;
                trips.setText(k.toString());
                break;
            }
        }
        if(ok==false)
            trips.setText("No trip requests");
    }

    public void handleApproveTripButton(javafx.event.ActionEvent actionEvent) {
        ObservableList<TripRequest>l= TripRequestService.Lista();
        boolean ok=false;
        TripRequestService.Approve();
        l=TripRequestService.Lista();

        for (TripRequest k:
                l) {
            if(k.getApproved()==2)
            {
                ok=true;
                trips.setText(k.toString());
                break;
            }
        }
        if(ok==false)
            trips.setText("No trip requests");
    }

    public void handleDenyTripButton(javafx.event.ActionEvent actionEvent) {
        ObservableList<TripRequest>l= TripRequestService.Lista();
        boolean ok=false;
        TripRequestService.Deny();
        l=TripRequestService.Lista();

        for (TripRequest k:
                l) {
            if(k.getApproved()==2)
            {
                ok=true;
                trips.setText(k.toString());
                break;
            }
        }
        if(ok==false)
            trips.setText("No trip requests");
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
