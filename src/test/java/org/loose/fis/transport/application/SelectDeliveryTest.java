package org.loose.fis.transport.application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.transport.application.controllers.SelectTripController;
import org.loose.fis.transport.application.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.services.*;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class SelectDeliveryTest {
    public static final String customerName="customerName";
    public static final String USERNAMECUSTOMER = "customer";
    public static final String PASSWORD = "password";

    @AfterEach
    void tearDown() {
        UserService.getdatab().close();
        DeliveryRequestService.getdatab().close();
        VehicleService.getdatab().close();
        TripService.getdatab().close();
    }

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".test";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        VehicleService.initDatabase();
        DeliveryRequestService.initDatabase();
        UserService.initDatabase();
        TripService.initDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("SelectDelivery");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    @Test
    void testRequestDelivery(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECUSTOMER);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        VehicleService.addVehicle("vehicleType", "transportType",1,1,1);
        robot.clickOn("#bookDeliveryButton");
        robot.clickOn("#pickupAddress");
        robot.write("pickupAddress");
        robot.clickOn("#vehicleType");
        robot.write("vehicleType");
        robot.clickOn("#deliveryAddress");
        robot.write("deliveryAddress");
        robot.clickOn("#additionalInformation");
        robot.write("additionalInformation");
        DeliveryRequestService.addRequest("pickupAddress", "vehicleType","deliveryAddress", "additionalInformation", customerName);
        robot.clickOn("#requestDeliveryButton");
    }

    @Test
    void testBackButton(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECUSTOMER);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#bookDeliveryButton");
        robot.clickOn("#backButton");
    }

    @Test
    void testIncorrectVehicleRequestedMessage(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECUSTOMER);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#bookDeliveryButton");
        VehicleService.addVehicle("vehicleType", "transportType",1,1,1);
        robot.clickOn("#vehicleType");
        robot.write("vehicleTypeWRONG");
        robot.clickOn("#requestDeliveryButton");
        assertThat(robot.lookup("#text").queryText()).hasText("This type of vehicle is not available");
    }
}