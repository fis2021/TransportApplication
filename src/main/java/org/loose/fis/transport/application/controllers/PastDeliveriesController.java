package org.loose.fis.transport.application.controllers;

import com.sun.imageio.plugins.common.SingleTileRenderedImage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import org.loose.fis.transport.application.model.DeliveryRequest;

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

    @FXML
    void handleBackButton(ActionEvent event) {

    }

}
