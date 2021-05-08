package org.loose.fis.transport.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.transport.application.exceptions.AccountExists;
import org.loose.fis.transport.application.exceptions.UsernameAlreadyExistsException;

import java.io.IOException;

public class LoginController {

    @FXML
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;

    @FXML
    public void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty()) {
            loginMessage.setText("Please type in a username!");
            return;
        }

        if (password == null || password.isEmpty()) {
            loginMessage.setText("Password cannot be empty");
            return;
        }
        try{
            org.loose.fis.transport.application.services.UserService.checkUsernameAndPassword(username,password);
        }
        catch(AccountExists e)
        {
            loginMessage.setText("Correct");
            try {
                Stage stage = (Stage) loginMessage.getScene().getWindow();
                Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("meansOfTransport.fxml"));
                Scene scene = new Scene(viewStudentsRoot, 900, 700);
                stage.setScene(scene);
            } catch (IOException p) {
                p.printStackTrace();
            }
            return;
        }
/*
        if (username.equals("student") && password.equals("student")) {
            loginMessage.setText("Logged in as student!");
            return;
        }

        if (username.equals("teacher") && password.equals("teacher")) {
            try {
                Stage stage = (Stage) loginMessage.getScene().getWindow();
                Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
                Scene scene = new Scene(viewStudentsRoot, 600, 400);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return;
        }*/


        loginMessage.setText("Incorrect login!");
    }

    @FXML
    public void handleRegisterButton()
    {
        try {
            Stage stage = (Stage) loginMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
