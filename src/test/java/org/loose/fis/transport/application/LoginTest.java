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
import org.loose.fis.transport.application.services.FileSystemService;
import org.loose.fis.transport.application.services.TripService;
import org.loose.fis.transport.application.services.UserService;
import org.loose.fis.transport.application.services.VehicleService;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class LoginTest {
    public static final String USERNAMECOMPANY = "company";
    public static final String USERNAMECUSTOMER = "customer";
    public static final String PASSWORD = "password";

    @AfterEach
    void tearDown() {
        UserService.getdatab().close();
        VehicleService.getdatab().close();
        TripService.getdatab().close();
    }

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".test";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        VehicleService.initDatabase();
        TripService.initDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    @Test
    void testLoginCompany(FxRobot robot) throws UsernameAlreadyExistsException, VehicleExists {
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Company",PASSWORD,PASSWORD,PASSWORD);
        VehicleService.addVehicle("vehicleType" , "transportType" ,1,1, 1);
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
    }

    @Test
    void testLoginCustomer(FxRobot robot) throws UsernameAlreadyExistsException, VehicleExists {
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer",PASSWORD,PASSWORD,PASSWORD);
        TripService.addTrip("vehicleType" , 1,"date","time", 1, "route");
        robot.clickOn("#username");
        robot.write(USERNAMECUSTOMER);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
    }

    @Test
    void testCustomerCanNotEnterInvalidCredentials(FxRobot robot) throws UsernameAlreadyExistsException{
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,PASSWORD,PASSWORD,PASSWORD,PASSWORD);;
        robot.clickOn("#username");
        robot.write("customerWRONG");
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText(String.format("Incorrect login!"));
    }

    @Test
    void testCustomerCanNotEnterNoUsername(FxRobot robot) throws UsernameAlreadyExistsException{
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,PASSWORD,PASSWORD,PASSWORD,PASSWORD);;
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText(String.format("Please type in a username!"));
    }

    @Test
    void testCustomerCanNotEnterNoPassword(FxRobot robot) throws UsernameAlreadyExistsException{
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,PASSWORD,PASSWORD,PASSWORD,PASSWORD);;
        robot.clickOn("#username");
        robot.write("customer");
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText(String.format("Password cannot be empty"));
    }

    @Test
    void testRedirectToRegister(FxRobot robot) {
        robot.clickOn("#registerButton");
    }
}