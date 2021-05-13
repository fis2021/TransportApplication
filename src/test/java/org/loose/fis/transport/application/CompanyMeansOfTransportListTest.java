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
import org.loose.fis.transport.application.services.*;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class CompanyMeansOfTransportListTest {
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
    void TestLogout(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle("vehicleType" , "transportType" ,1,1, 1);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#logout");
    }
    @Test
    void TestScheduleTrip(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle("vehicleType" , "transportType" ,1,1, 1);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#scheduletrip");
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
        robot.clickOn("#seerequests");
    }
    @Test
    void TestAdd(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#vehicletype");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#transporttype");
        robot.write(PASSWORD);
        robot.clickOn("#space");
        robot.write("1");
        robot.clickOn("#number");
        robot.write("2");
        robot.clickOn("#available");
        robot.write("3");
        robot.clickOn("#add");
        assertThat(VehicleService.Lista().size()).isEqualTo(1);
        robot.clickOn("#vehicletype");
        robot.write(USERNAMECOMPANY+"1");
        robot.clickOn("#transporttype");
        robot.write(PASSWORD);
        robot.clickOn("#space");
        robot.write("1");
        robot.clickOn("#number");
        robot.write("2");
        robot.clickOn("#available");
        robot.write("3");
        robot.clickOn("#add");
        assertThat(VehicleService.Lista().size()).isEqualTo(2);
    }
    @Test
    void TestAddWrongDatatype(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#vehicletype");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#transporttype");
        robot.write(PASSWORD);
        robot.clickOn("#space");
        robot.write("1");
        robot.clickOn("#number");
        robot.write("a");
        robot.clickOn("#available");
        robot.write("3");
        robot.clickOn("#add");
        assertThat(robot.lookup("#TEXT").queryText()).hasText(
                String.format("Wrong data type")
        );
    }
    @Test
    void TestAddTwice(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#vehicletype");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#transporttype");
        robot.write(PASSWORD);
        robot.clickOn("#space");
        robot.write("1");
        robot.clickOn("#number");
        robot.write("2");
        robot.clickOn("#available");
        robot.write("3");
        robot.clickOn("#add");
        robot.clickOn("#add");
        assertThat(robot.lookup("#TEXT").queryText()).hasText(
                String.format("Vehicle type already exists")
        );
    }
    @Test
    void TestEdit(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle(USERNAMECOMPANY,PASSWORD,1,2,3);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#vehicletype");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#transporttype");
        robot.write("edited");
        robot.clickOn("#space");
        robot.write("12");
        robot.clickOn("#number");
        robot.write("22");
        robot.clickOn("#available");
        robot.write("23");
        robot.clickOn("#edit");
        assertThat(VehicleService.Lista().get(0).getTransportType()).isEqualTo("edited");
        assertThat(VehicleService.Lista().get(0).getSpace()).isEqualTo(12);
        assertThat(VehicleService.Lista().get(0).getNumberOfVehicles()).isEqualTo(22);
        assertThat(VehicleService.Lista().get(0).getAvailableVehicles()).isEqualTo(23);
    }
    @Test
    void TestEditWongDatatype(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle(USERNAMECOMPANY,PASSWORD,1,2,3);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#vehicletype");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#transporttype");
        robot.write("edited");
        robot.clickOn("#space");
        robot.write("a12");
        robot.clickOn("#number");
        robot.write("22");
        robot.clickOn("#available");
        robot.write("23");
        robot.clickOn("#edit");
        assertThat(robot.lookup("#TEXT").queryText()).hasText(
                String.format("Wrong data type")
        );
    }
    @Test
    void TestDelete(FxRobot robot) throws VehicleExists, UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle(USERNAMECOMPANY,PASSWORD,1,2,3);
        VehicleService.addVehicle(USERNAMECOMPANY+2,PASSWORD,1,2,3);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        robot.clickOn("#vehicletype");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#delete");
        assertThat(VehicleService.Lista().size()).isEqualTo(1);
        robot.clickOn("#vehicletype");
        robot.write("2");
        robot.clickOn("#delete");
        assertThat(VehicleService.Lista().size()).isEqualTo(0);
    }
}