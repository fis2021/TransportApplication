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
import org.loose.fis.transport.application.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.DeliveryRequest;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.services.*;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import static org.junit.jupiter.api.Assertions.*;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class CompanyAllBookingsTest {
    public static final String USERNAMECOMPANY = "company";
    public static final String USERNAMECUSTOMER = "customer";
    public static final String PASSWORD = "password";
    String ceva="ceva";
    int cevaint=5;
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        VehicleService.initDatabase();
        UserService.initDatabase();
        TripService.initDatabase();
        TripRequestService.initDatabase();
        DeliveryRequestService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
        UserService.getdatab().close();
        VehicleService.getdatab().close();
        TripService.getdatab().close();
        TripRequestService.getdatab().close();
        DeliveryRequestService.getdatab().close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
    @Test
    void TestBackButton(FxRobot robot) throws UsernameAlreadyExistsException, VehicleExists {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle(USERNAMECOMPANY,PASSWORD,1,2,3);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#deliveriesandtrips");
        robot.clickOn("#back");
    }
    @Test
    void TestPendingButton(FxRobot robot) throws Exception {
        DeliveryRequestService.addRequest("address","ve","del","add","name");
        DeliveryRequestService.addRequest("address2","v3e","1del","add","name");
        DeliveryRequestService.addRequest("address3","v3e","d1el","add","name");
        DeliveryRequestService.Approve();
        DeliveryRequestService.Deny();
        Trip r=new Trip("tip",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        r=new Trip("ti2p",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        r=new Trip("t2ip",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        TripRequestService.Approve();
        TripRequestService.Deny();
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle(USERNAMECOMPANY,PASSWORD,1,2,3);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#deliveriesandtrips");

        robot.clickOn("#pending");
    }
    @Test
    void TestApprovedButton(FxRobot robot) throws Exception {
        DeliveryRequestService.addRequest("address","ve","del","add","name");
        DeliveryRequestService.addRequest("address2","v3e","1del","add","name");
        DeliveryRequestService.addRequest("address3","v3e","d1el","add","name");
        DeliveryRequestService.Approve();
        DeliveryRequestService.Deny();
        Trip r=new Trip("tip",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        r=new Trip("ti2p",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        r=new Trip("t2ip",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        TripRequestService.Approve();
        TripRequestService.Deny();
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle(USERNAMECOMPANY,PASSWORD,1,2,3);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#deliveriesandtrips");

        robot.clickOn("#approved");
    }
    @Test
    void TestDeniedButton(FxRobot robot) throws Exception {
        DeliveryRequestService.addRequest("address","ve","del","add","name");
        DeliveryRequestService.addRequest("address2","v3e","1del","add","name");
        DeliveryRequestService.addRequest("address3","v3e","d1el","add","name");
        DeliveryRequestService.Approve();
        DeliveryRequestService.Deny();
        Trip r=new Trip("tip",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        r=new Trip("ti2p",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        r=new Trip("t2ip",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        TripRequestService.Approve();
        TripRequestService.Deny();
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle(USERNAMECOMPANY,PASSWORD,1,2,3);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#deliveriesandtrips");

        robot.clickOn("#denied");
    }
    @Test
    void TestAllButton(FxRobot robot) throws Exception {
        DeliveryRequestService.addRequest("address","ve","del","add","name");
        DeliveryRequestService.addRequest("address2","v3e","1del","add","name");
        DeliveryRequestService.addRequest("address3","v3e","d1el","add","name");
        DeliveryRequestService.Approve();
        DeliveryRequestService.Deny();
        Trip r=new Trip("tip",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        r=new Trip("ti2p",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        r=new Trip("t2ip",1,"date","time",2,"r",3);
        TripRequestService.addRequest(r,"name");
        TripRequestService.Approve();
        TripRequestService.Deny();
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle(USERNAMECOMPANY,PASSWORD,1,2,3);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#deliveriesandtrips");

        robot.clickOn("#all");
    }
}