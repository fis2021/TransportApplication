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
import org.loose.fis.transport.application.services.FileSystemService;
import org.loose.fis.transport.application.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class RegistrationTest {

    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
        UserService.getdatab().close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Registration Example");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @Test
    void testRegistrationOneUser(FxRobot robot) {
        robot.clickOn("#username");
        robot.write(USERNAME);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#address");
        robot.write(PASSWORD);
        robot.clickOn("#email");
        robot.write(PASSWORD);
        robot.clickOn("#name");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.clickOn("#registerButton");
        Assertions.assertThat(UserService.Lista().size()).isEqualTo(1);
    }
    @Test
    void testRegistrationSameUser(FxRobot robot) throws UsernameAlreadyExistsException {
        UserService.addUser(USERNAME,PASSWORD,PASSWORD,PASSWORD,PASSWORD,PASSWORD);
        robot.clickOn("#username");
        robot.write(USERNAME);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#address");
        robot.write(PASSWORD);
        robot.clickOn("#email");
        robot.write(PASSWORD);
        robot.clickOn("#name");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);

        robot.clickOn("#registerButton");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(
                String.format("An account with the username %s already exists!", USERNAME)
        );
        Assertions.assertThat(UserService.Lista().size()).isEqualTo(1);
    }
}