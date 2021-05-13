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
import org.loose.fis.transport.application.model.DeliveryRequest;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.model.TripRequest;
import org.loose.fis.transport.application.services.*;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ApproveRequestsTest {
    public static final String customerName="customerName";
    public static final String USERNAMECOMPANY = "company";
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
        primaryStage.setTitle("ApproveRequests");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    @Test
    void testBackButton(FxRobot robot) throws UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#seerequests");
        robot.clickOn("#backButton");
    }

    @Test
    void testDisplayingTripInformation(FxRobot robot) throws UsernameAlreadyExistsException, VehicleExists{
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        VehicleService.addVehicle("vehicleType", "transportType",1,1,1);
        TripService.addTrip("vehicleType",1,"date","time",1,"route");
        Trip t=new Trip("vehicleType",1,"date","time",1,"route",1);
        TripRequestService.addRequest(t,customerName);
        TripRequest tr = new TripRequest(t,customerName);
        robot.clickOn("#seerequests");
        assertThat(robot.lookup("#textTrips").queryText()).hasText(tr.toString());
    }

    @Test
    void testDisplayingDeliveryInformation(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        VehicleService.addVehicle("vehicleType", "transportType",1,1,1);
        DeliveryRequestService.addRequest("pickupAddress", "vehicleType","deliveryAddress", "additionalInformation", customerName);
        DeliveryRequest d = new DeliveryRequest("pickupAddress", "vehicleType","deliveryAddress", "additionalInformation", customerName, 2,1);
        robot.clickOn("#seerequests");
        assertThat(robot.lookup("#textDeliveries").queryText()).hasText(d.toString());
    }

    @Test
    void testNoTripIRequests(FxRobot robot) throws UsernameAlreadyExistsException, VehicleExists{
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#seerequests");
        assertThat(robot.lookup("#textTrips").queryText()).hasText("No trip requests");
    }

    @Test
    void testNoDeliveryRequests(FxRobot robot) throws UsernameAlreadyExistsException, VehicleExists{
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",customerName,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#seerequests");
        assertThat(robot.lookup("#textDeliveries").queryText()).hasText("No delivery requests");
    }

    @Test
    void testApproveTrip(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECOMPANY, PASSWORD, "Company", customerName, PASSWORD, PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        VehicleService.addVehicle("vehicleType", "transportType", 1, 1, 1);
        TripService.addTrip("vehicleType", 1, "date", "time", 1, "route");
        Trip t = new Trip("vehicleType", 1, "date", "time", 1, "route", 1);
        TripRequestService.addRequest(t, customerName);
        TripRequestService.addRequest(t, customerName);
        robot.clickOn("#seerequests");
        robot.clickOn("#approveTripButton");
    }

    @Test
    void testDenyTrip(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECOMPANY, PASSWORD, "Company", customerName, PASSWORD, PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        VehicleService.addVehicle("vehicleType", "transportType", 1, 1, 1);
        TripService.addTrip("vehicleType", 1, "date", "time", 1, "route");
        Trip t = new Trip("vehicleType", 1, "date", "time", 1, "route", 1);
        TripRequestService.addRequest(t, customerName);
        TripRequestService.addRequest(t, customerName);
        robot.clickOn("#seerequests");
        robot.clickOn("#denyTripButton");
    }

    @Test
    void testApproveDelivery(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECOMPANY, PASSWORD, "Company", customerName, PASSWORD, PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        VehicleService.addVehicle("vehicleType", "transportType",1,1,1);
        DeliveryRequestService.addRequest("pickupAddress", "vehicleType","deliveryAddress", "additionalInformation", customerName);
        DeliveryRequestService.addRequest("pickupAddress", "vehicleType","deliveryAddress", "additionalInformation", customerName);
        DeliveryRequest d = new DeliveryRequest("pickupAddress", "vehicleType","deliveryAddress", "additionalInformation", customerName, 2,1);
        robot.clickOn("#seerequests");
        robot.clickOn("#approveDelivery");
        robot.clickOn("#approveDelivery");
        assertThat(robot.lookup("#textDeliveries").queryText()).hasText("No delivery requests");
    }

    @Test
    void testDenyDelivery(FxRobot robot) throws Exception {
        UserService.addUser(USERNAMECOMPANY, PASSWORD, "Company", customerName, PASSWORD, PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        VehicleService.addVehicle("vehicleType", "transportType",1,1,1);
        DeliveryRequestService.addRequest("pickupAddress", "vehicleType","deliveryAddress", "additionalInformation", customerName);
        DeliveryRequestService.addRequest("pickupAddress", "vehicleType","deliveryAddress", "additionalInformation", customerName);
        DeliveryRequest d = new DeliveryRequest("pickupAddress", "vehicleType","deliveryAddress", "additionalInformation", customerName, 2,1);
        robot.clickOn("#seerequests");
        robot.clickOn("#denyDelivery");
        robot.clickOn("#denyDelivery");
        assertThat(robot.lookup("#textDeliveries").queryText()).hasText("No delivery requests");
    }
}