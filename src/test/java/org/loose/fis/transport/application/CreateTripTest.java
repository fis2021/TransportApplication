package org.loose.fis.transport.application;

import static org.junit.jupiter.api.Assertions.*;
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
import org.loose.fis.transport.application.services.*;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class CreateTripTest {
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
    void TestDeliveriesAndTrips(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle("vehicleType" , "transportType" ,1,1, 1);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#scheduletrip");
        robot.clickOn("#deliveriesandtrips");
    }
    @Test
    void TestSeeRequests(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle("vehicleType" , "transportType" ,1,1, 1);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#scheduletrip");
        robot.clickOn("#seerequests");
    }
    @Test
    void TestLogout(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle("vehicleType" , "transportType" ,1,1, 1);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#scheduletrip");
        robot.clickOn("#logout");
    }
    @Test
    void TestCompanyMeansOfTransport(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle("vehicleType" , "transportType" ,1,1, 1);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#scheduletrip");
        robot.clickOn("#meansoftransport");
    }
    @Test
    void TestAdd(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#scheduletrip");
        robot.clickOn("#vehicletype");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#space");
        robot.write("1");
        robot.clickOn("#date");
        robot.write("2");
        robot.clickOn("#time");
        robot.write("3");
        robot.clickOn("#price");
        robot.write("4");
        robot.clickOn("#route");
        robot.write("ru");
        robot.clickOn("#add");
        assertThat(TripService.Lista().size()).isEqualTo(1);
        robot.clickOn("#vehicletype");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#space");
        robot.write("1");
        robot.clickOn("#date");
        robot.write("2");
        robot.clickOn("#time");
        robot.write("3");
        robot.clickOn("#price");
        robot.write("4");
        robot.clickOn("#route");
        robot.write("ru");
        robot.clickOn("#add");
        assertThat(TripService.Lista().size()).isEqualTo(2);
    }
    @Test
    void TestAddWrongDatatype(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY, PASSWORD, "Company", PASSWORD, PASSWORD, PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#scheduletrip");
        robot.clickOn("#vehicletype");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#space");
        robot.write("r1");
        robot.clickOn("#date");
        robot.write("r2");
        robot.clickOn("#time");
        robot.write("r3");
        robot.clickOn("#price");
        robot.write("r4");
        robot.clickOn("#route");
        robot.write("ru");
        robot.clickOn("#add");
        assertThat(robot.lookup("#TEXT").queryText()).hasText(
                String.format("Wrong data type")
        );
    }
    @Test
    void TestDelete(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        TripService.addTrip("a",1,"b","d",3,"r");
        TripService.addTrip("ad",11,"b","d",3,"r");
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#scheduletrip");
        robot.clickOn("#vehicletype");
        robot.write("a");
        robot.clickOn("#delete");
        assertThat(TripService.Lista().size()).isEqualTo(1);
        robot.clickOn("#vehicletype");
        robot.write("d");
        robot.clickOn("#delete");
        assertThat(TripService.Lista().size()).isEqualTo(0);
    }
}