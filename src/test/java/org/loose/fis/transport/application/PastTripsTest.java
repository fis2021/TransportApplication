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
class PastTripsTest {
    public static final String customerName="customerName";
    public static final String USERNAMECUSTOMER = "customer";
    public static final String PASSWORD = "password";

    @AfterEach
    void tearDown() {
        UserService.getdatab().close();
        VehicleService.getdatab().close();
        TripService.getdatab().close();
        DeliveryRequestService.getdatab().close();
        TripRequestService.getdatab().close();
    }

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".test";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        VehicleService.initDatabase();
        UserService.initDatabase();
        TripService.initDatabase();
        DeliveryRequestService.initDatabase();
        TripRequestService.initDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("PastTripsCustomer");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    @Test
    void testBackButton(FxRobot robot) throws UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECUSTOMER);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#myTripsButton");
        robot.clickOn("#backButton");
    }

    @Test
    void testTripRequest(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECUSTOMER);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        Trip t=new Trip("vehicleType",1,"date","time",1,"route",1);
        TripRequestService.addRequest(t,customerName);
        robot.clickOn("#myTripsButton");
        assertThat(TripRequestService.Lista().size()).isEqualTo(1);
    }

    @Test
    void testApproved(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECUSTOMER);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        Trip t=new Trip("vehicleType",1,"date","time",1,"route",1);
        TripRequestService.addRequest(t,customerName);
        TripRequestService.Approve();
        robot.clickOn("#myTripsButton");
        assertThat(TripRequestService.Lista().size()).isEqualTo(1);
    }

    @Test
    void testDenied(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECUSTOMER);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        Trip t=new Trip("vehicleType",1,"date","time",1,"route",1);
        TripRequestService.addRequest(t,customerName);
        TripRequestService.Deny();
        robot.clickOn("#myTripsButton");
        assertThat(TripRequestService.Lista().size()).isEqualTo(1);
    }
}