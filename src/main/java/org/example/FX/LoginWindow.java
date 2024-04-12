package org.example.FX;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import org.example.BD.Database;


public class LoginWindow {

    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button applyButton;
    private Label messageLabel;
    private String username;

    public LoginWindow(Stage primaryStage) {
        this.stage = primaryStage;
        this.usernameField = new TextField();
        usernameField.setMaxWidth(200);

        this.passwordField = new PasswordField();
        passwordField.setMaxWidth(200);

        this.applyButton = new Button("Sign in");
        applyButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        this.messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);

        applyButton.setOnAction(e -> {
            // Логика при нажатии кнопки "Apply"
            username = usernameField.getText();
            String password = passwordField.getText();


            // Подключение к базе данных и проверка логина и пароля
            Database database = new Database();
            String result = database.checkUserCredentials(username, password);

            if (result.equals("success")) {
                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("Login successful!");

                // Открываем основное окно магазина
                PCPartsStoreWindow pcPartsStoreWindow = new PCPartsStoreWindow(stage, username);
                pcPartsStoreWindow.headerPCPartsStore();
                pcPartsStoreWindow.show();
                stage.setScene(new Scene(pcPartsStoreWindow.getMainPane(), 1800, 1000));

            } else if (result.equals("invalid")) {
                messageLabel.setText("Invalid username or password!");
            } else {
                messageLabel.setText("Account does not exist. Please register.");
            }

        });

    }

    public String getUsername()
    {
        return username;
    }

    public void show() {
        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-alignment: center;");

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        Label registrationLabel = new Label("Registration");
        registrationLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2196F3; -fx-underline: true; -fx-cursor: hand;");

        RegistrationWindow registrationWindow = new RegistrationWindow(stage);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(applyButton);
        buttonsBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                titleLabel,
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                buttonsBox,
                registrationLabel,
                messageLabel
        );

        // обработчик событий на надпись registration
        registrationLabel.setOnMouseClicked(e -> {

            registrationWindow.show();
        });

        Scene scene = new Scene(root, 1100, 900);
        stage.setScene(scene);
        stage.setTitle("Login Window");
        stage.show();
    }

}



