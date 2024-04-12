package org.example.FX;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.BD.Database;
import org.example.demo.Users;

public class userDataManagementButtonFX {

    private VBox contentPane;

    public userDataManagementButtonFX(VBox contentPane)
    {
        this.contentPane = contentPane;
    }

    //добавление пользователя в бд
    public void addUser()
    {
        if (contentPane == null)
        {
            System.out.println("ERROR!!");
        }
        else
        {
            TextField usernameField = new TextField();
            usernameField.setMaxWidth(200);
            TextField passwordField = new TextField();
            passwordField.setMaxWidth(200);
            TextField emailField = new TextField();
            emailField.setMaxWidth(200);

            RadioButton userRadioButton = new RadioButton("user");
            userRadioButton.setStyle("-fx-font-size: 16px; -fx-padding: 20px");
            RadioButton rootRadioButton = new RadioButton("root");
            rootRadioButton.setStyle("-fx-font-size: 16px; -fx-padding: 20px");
            RadioButton analystRadioButton = new RadioButton("analyst");
            rootRadioButton.setStyle("-fx-font-size: 16px; -fx-padding: 20px");

            ToggleGroup toggleGroup = new ToggleGroup();
            userRadioButton.setToggleGroup(toggleGroup);
            rootRadioButton.setToggleGroup(toggleGroup);
            analystRadioButton.setToggleGroup(toggleGroup);


            Label usernameLabel = new Label("Username:");
            usernameLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
            Label passwordLabel = new Label("Password:");
            passwordLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
            Label emailLabel = new Label("Email:");
            emailLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
            Label privilegeLabel = new Label("Privilege:");
            privilegeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");

            Label messageLabel = new Label();

            StringProperty privilege = new SimpleStringProperty("user");
            userRadioButton.setOnAction(e -> privilege.set("user"));
            rootRadioButton.setOnAction(e -> privilege.set("root"));
            analystRadioButton.setOnAction(e -> privilege.set("analyst"));


            Button applyButton = new Button("Apply");
            applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

            applyButton.setOnAction(e -> {
                // Perform actions based on the input values
                String username = usernameField.getText();
                String password = passwordField.getText();
                String email = emailField.getText();

                Database db = new Database();

                if(db.isUsernameTaken(username))
                {
                    messageLabel.setTextFill(Color.RED);
                    messageLabel.setText("Username is already taken");
                }
                else
                {
                    Users user = new Users();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setPriv(privilege.get());

                    db.addUserToDatabase(user);

                    messageLabel.setTextFill(Color.GREEN);
                    messageLabel.setText("(-_-) User successfully added (-_-)");
                }


                usernameField.clear();
                passwordField.clear();
                emailField.clear();
                toggleGroup.selectToggle(null);

            });


            HBox rbutton = new HBox(40);
            rbutton.getChildren().addAll(
                    userRadioButton, rootRadioButton,
                    analystRadioButton
            );

            rbutton.setAlignment(Pos.CENTER);


            VBox panel = new VBox();
            panel.getChildren().addAll(
                    usernameLabel, usernameField,
                    passwordLabel, passwordField,
                    emailLabel, emailField,
                    rbutton
            );
            panel.setAlignment(Pos.CENTER);


            contentPane.getChildren().addAll(panel, messageLabel, applyButton);
            contentPane.setAlignment(Pos.CENTER);
        }
    }

    //удаление пользователя из бд
    public void deleteUser()
    {
        if (contentPane == null)
        {
            System.out.println("ERROR!!");
        }
        else
        {
            TextField usernameField = new TextField();
            usernameField.setMaxWidth(200);

            Label usernameLabel = new Label("Username:");
            usernameLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");

            Button applyButton = new Button("Apply");
            applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

            Label messageLabel = new Label();

            applyButton.setOnAction(e -> {
                String username = usernameField.getText();

                Database db = new Database();
                String deleteUser = db.deleteUserByUsername(username);

                if (deleteUser.equals("deleted"))
                {
                    messageLabel.setTextFill(Color.GREEN);
                    messageLabel.setAlignment(Pos.CENTER);
                    messageLabel.setText("The deletion was successful");
                }
                else if(deleteUser.equals("not found"))
                {
                    messageLabel.setTextFill(Color.RED);
                    messageLabel.setAlignment(Pos.CENTER);
                    messageLabel.setText("The deletion did not happen. The username is incorrect");
                }

                usernameField.clear();
            });


            contentPane.getChildren().addAll(usernameLabel, usernameField, messageLabel, applyButton);
            contentPane.setAlignment(Pos.CENTER);
        }
    }

    //обновление данных пользователя в бд
    public void updateUser()
    {
        if (contentPane == null)
        {
            System.out.println("ERROR!!");
        }
        else
        {
            Label messageLabel = new Label();

            Label userIdLabel = new Label("ID");
            userIdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
            TextField userIdField = new TextField();
            userIdField.setMaxWidth(50);

            Button checkButton = new Button("Check");
            checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

            checkButton.setOnAction(e -> {

                int userId = Integer.parseInt(userIdField.getText());

                Database db = new Database();

                if(!db.checkUserIdEqual(userId))
                {
                    messageLabel.setTextFill(Color.RED);
                    messageLabel.setText("Id not found((");
                }
                else
                {
                    Label userNameLabel = new Label("Username");
                    userNameLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
                    TextField userNameTextField = new TextField();
                    userNameTextField.setMaxWidth(200);

                    Label userPasswordLabel = new Label("Password");
                    userPasswordLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
                    TextField userPasswordTextField = new TextField();
                    userPasswordTextField.setMaxWidth(200);

                    Label userEmailLabel = new Label("Email");
                    userEmailLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
                    TextField userEmailTextField = new TextField();
                    userEmailTextField.setMaxWidth(200);

                    Label userPrivilegeLabel = new Label("Privilege");
                    userPrivilegeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
                    TextField userPrivilegeTextField = new TextField();
                    userPrivilegeTextField.setMaxWidth(200);

                    Button updateButton = new Button("Update");
                    updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                    updateButton.setOnAction(event -> {
                        String newUsername = userNameTextField.getText();
                        String newEmail = userEmailTextField.getText();
                        String newPassword = userPasswordTextField.getText();
                        String newPrivilege = userPrivilegeLabel.getText();

                        messageLabel.setTextFill(Color.BLACK);
                        messageLabel.setText(db.updateUser(userId, newUsername, newEmail, newPassword, newPrivilege));

                        userNameTextField.clear();
                        userEmailTextField.clear();
                        userPasswordTextField.clear();
                        userPrivilegeTextField.clear();

                    });

                    VBox inputFields = new VBox(10);
                    inputFields.getChildren().addAll(
                      userNameLabel, userNameTextField,
                      userEmailLabel, userEmailTextField,
                      userPasswordLabel, userPasswordTextField,
                      userPrivilegeLabel, userPrivilegeTextField,
                      messageLabel, updateButton
                    );

                    inputFields.setAlignment(Pos.CENTER);
                    contentPane.getChildren().clear();
                    contentPane.getChildren().addAll(inputFields);
                    contentPane.setAlignment(Pos.CENTER);
                }

            });

            contentPane.getChildren().clear();
            contentPane.getChildren().addAll(userIdLabel, userIdField, messageLabel, checkButton);
            contentPane.setAlignment(Pos.CENTER);
        }
    }

    //вывод все таблицы пользователей
    public void viewUserData()
    {
        TableView<Users> table = new TableView<>();

        if (contentPane == null)
        {
            System.out.println("ERROR!!");
        }
        else
        {

            TableColumn<Users, Integer> idColumn = new TableColumn<>("ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            idColumn.setPrefWidth(200.00);

            TableColumn<Users, String> usernameColumn = new TableColumn<>("username");
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            usernameColumn.setPrefWidth(200.00);

            TableColumn<Users, String> emailColumn = new TableColumn<>("email");
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            emailColumn.setPrefWidth(200.00);

            TableColumn<Users, String> passwordColumn = new TableColumn<>("password");
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            passwordColumn.setPrefWidth(200.00);

            TableColumn<Users, String> privColumn = new TableColumn<>("priv");
            privColumn.setCellValueFactory(new PropertyValueFactory<>("priv"));
            privColumn.setPrefWidth(200.00);

            table.getColumns().addAll(idColumn, usernameColumn, emailColumn, passwordColumn, privColumn);
            table.setMaxWidth(1020.00);

            Database db = new Database();
            table.getItems().addAll(db.getAllUsers());

        }


        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

}
