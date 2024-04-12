package org.example.FX;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import org.example.demo.Users;
import org.example.BD.Database;

public class RegistrationWindow {

    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField repeatPasswordField;
    private TextField emailField;
    private Button applyButton;
    private Button closeButton;

    public RegistrationWindow(Stage primaryStage) {
        this.stage = primaryStage;
        this.usernameField = new TextField();
        usernameField.setMaxWidth(200);

        this.passwordField = new PasswordField();
        passwordField.setMaxWidth(200);

        this.repeatPasswordField = new PasswordField();
        repeatPasswordField.setMaxWidth(200);

        this.emailField = new TextField();
        emailField.setMaxWidth(200);

        this.applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        this.closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white;");

        LoginWindow loginWindow = new LoginWindow(stage);

        applyButton.setOnAction(e -> {
            // Логика при нажатии кнопки "Apply"
            String username = usernameField.getText();
            String password = passwordField.getText();
            String repeatPassword = repeatPasswordField.getText();
            String email = emailField.getText();

            Database db = new Database();
            if(db.isUsernameTaken(username))
            {
             showAlertError("Username is already taken");
            }
            else if (!password.equals(repeatPassword))
            {
                showAlertError("Passwords do not match!");
            }
            else if (!email.contains("@"))
            {
                showAlertError("Invalid email address!");
            }
            else
            {
                Users user = new Users();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                // Сохранение данных пользователя в базу данных SQL
                Database database = new Database();
                database.saveUserToDatabase(user);
                showAlertSuccess("User registered successfully!");

                loginWindow.show();
            }
        });

        closeButton.setOnAction(e -> {

            loginWindow.show();
        });
    }

    private void showAlertSuccess(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showAlertError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void show() {
        Label titleLabel = new Label("Registration");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-alignment: center;");

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label repeatPasswordLabel = new Label("Repeat Password:");
        Label emailLabel = new Label("Email:");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(applyButton, closeButton);
        buttonsBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                titleLabel,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                repeatPasswordLabel, repeatPasswordField,
                emailLabel, emailField,
                buttonsBox
        );


        Scene scene = new Scene(root, 1100, 900);
        stage.setScene(scene);
        stage.setTitle("Registration Window");
        stage.show();
    }
}