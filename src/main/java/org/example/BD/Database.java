package org.example.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import org.example.demo.*;
import org.jetbrains.annotations.NotNull;


public class Database {

    // добавление в бд
    public void saveUserToDatabase(@NotNull Users user) {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO user (username, email, password, priv) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setString(4, "user");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save user to database");
        }
    }

    // вывод сообщения
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // проверка на сушествование польщователя в бд
    public String checkUserCredentials(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String dbUsername = "root";
        String dbPassword = "84628462";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return "success"; // Пользователь найден
                } else {
                    if (checkUsernameExists(username)) {
                        return "invalid"; // Неверный пароль
                    } else {
                        return "notfound"; // Аккаунт не существует
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    // проверкна на существование юзера по username
    private boolean checkUsernameExists(String username) {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String dbUsername = "root";
        String dbPassword = "84628462";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sql = "SELECT * FROM user WHERE username = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для проверки привилегий пользователя и возврата их значения
    public String getUserPrivileges(String username) {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String dbUsername = "root";
        String dbPassword = "84628462";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sql = "SELECT priv FROM user WHERE username = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String userPrivilege = resultSet.getString("priv");
                    return userPrivilege;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error retrieving user privileges from the database");
        }

        return null;
    }

    // добавление нового пользователя в бд
    public void addUserToDatabase(@NotNull Users user)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO user (username, email, password, priv) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getPriv());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save user to database");
        }
    }

    // удаление пользователя из бд
    public String deleteUserByUsername(String username) {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "DELETE FROM user WHERE username = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, username);
                int rowsDeleted = statement.executeUpdate();

                if (rowsDeleted > 0) {
                    return "deleted";
                } else {
                    return "not found";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to delete user from database");
            return "error";
        }
    }

    // удаление продукта из любой таблицы (продукта) бд
    public String deleteProductByID(String id, String table_name, String table_id_name) {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "DELETE FROM " + table_name + " WHERE " + table_id_name + " = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, id);
                int rowsDeleted = statement.executeUpdate();

                if (rowsDeleted > 0) {
                    return "deleted";
                } else {
                    return "not found";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to delete user from database");
            return "error";
        }
    }


    // считывает всех пользователей из бд
    public List<Users> getAllUsers()
    {
        List<Users> usersList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM user";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Users user = new Users();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setPriv(resultSet.getString("priv"));
                    usersList.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all users: " + e.getMessage());
        }
        return usersList;

    }

    // проверка на то что имя пользователя занято
    public boolean isUsernameTaken(String username)
    {
        List<Users> usersList =  getAllUsers();

        for(Users users : usersList)
        {
            if(users.getUsername().equals(username))
                return true;
        }
        return false;
    }

    // проверка по id юзера
    public boolean checkUserIdEqual(int userId)
    {
        List<Users> user = getAllUsers();

        for(Users users : user)
        {
            if(users.getId() == userId)
                return true;
        }
        return false;
    }

    // проверка по id продукта
    public boolean checkProductIdEqual(int id, String productType)
    {
        if(productType.equals("CP"))
        {
            List<CPs> cp = getAllCPs();

            for (CPs cps : cp)
            {
                if(cps.getId() == id)
                    return true;
            }
            return false;
        }
        else if(productType.equals("ProcessorCoolingSystems"))
        {
            List<ProcessorCoolingSystems> PCS = getAllProcessorCoolingSystems();

            for (ProcessorCoolingSystems PCSs : PCS)
            {
                if(PCSs.getId() == id)
                    return true;
            }
            return false;
        }
        else if(productType.equals("HousingCoolingSystems"))
        {
            List<HousingCoolingSystems> HCS = getAllHousingCoolingSystems();

            for (HousingCoolingSystems HCSs : HCS)
            {
                if(HCSs.getId() == id)
                    return true;
            }
            return false;
        }
        else if(productType.equals("VideoCards"))
        {
            List<VideoCards> vc = getAllVideoCards();

            for (VideoCards vcs : vc)
            {
                if(vcs.getId() == id)
                    return true;
            }
            return false;
        }
        else if(productType.equals("RAM"))
        {
            List<RAM> ram = getAllRAM();

            for (RAM rams : ram)
            {
                if(rams.getId() == id)
                    return true;
            }
            return false;
        }
        else if(productType.equals("PowerSupplyUnits"))
        {
            List<PowerSupplyUnits> PSU = getAllPowerSupplyUnits();

            for (PowerSupplyUnits PSUs : PSU)
            {
                if(PSUs.getId() == id)
                    return true;
            }
            return false;
        }
        else if(productType.equals("Enclosures"))
        {
            List<Enclosures> enc = getAllEnclosures();

            for (Enclosures encs : enc)
            {
                if(encs.getId() == id)
                    return true;
            }
            return false;
        }
        else if(productType.equals("HDD"))
        {
            List<HDD> hdd = getAllHDD();

            for (HDD hdds : hdd)
            {
                if(hdds.getId() == id)
                    return true;
            }
            return false;
        }
        else if(productType.equals("SSD"))
        {
            List<SSD> ssd = getAllSSD();

            for (SSD ssds : ssd)
            {
                if(ssds.getId() == id)
                    return true;
            }
            return false;
        }
        else if(productType.equals("Motherboards"))
        {
            List<Motherboards> moth = getAllMotherboards();

            for (Motherboards moths : moth)
            {
                if(moths.getId() == id)
                    return true;
            }
            return false;
        }
        else
            return false;
    }

    // обновление полей юзера
    public String updateUser(int id, String username, String email, String password, String priv)
    {
        if(username.isEmpty() && email.isEmpty() && password.isEmpty() && priv.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE user SET");
            List<Object> parameters = new ArrayList<>();

            if (!username.isEmpty()) {
                sqlBuilder.append(" username = ?,");
                parameters.add(username);
            }
            if (!email.isEmpty()) {
                sqlBuilder.append(" email = ?,");
                parameters.add(email);
            }
            if (!password.isEmpty()) {
                sqlBuilder.append(" password = ?,");
                parameters.add(password);
            }
            if (!priv.isEmpty()) {
                sqlBuilder.append(" priv = ?,");
                parameters.add(priv);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE id = ?");
            parameters.add(id);



            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {

                    mess = "Данные успешно обновлены для ID " + id;
                } else {

                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public String updateCP(int id, String brand, String model, String coreName, String coreNumber, String socet, String frequency, String threadsNumber, String price)
    {
        if(brand.isEmpty() && model.isEmpty() && coreName.isEmpty() && coreNumber.isEmpty() && socet.isEmpty() && frequency.isEmpty() && threadsNumber.isEmpty() && price.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE cp SET");
            List<Object> parameters = new ArrayList<>();

            if (!brand.isEmpty()) {
                sqlBuilder.append(" Brand = ?,");
                parameters.add(brand);
            }
            if (!model.isEmpty()) {
                sqlBuilder.append(" Model = ?,");
                parameters.add(model);
            }
            if (!coreName.isEmpty()) {
                sqlBuilder.append(" coreName = ?,");
                parameters.add(coreName);
            }
            if (!coreNumber.isEmpty()) {
                sqlBuilder.append(" coresNumber = ?,");
                parameters.add(Integer.parseInt(coreNumber));
            }
            if (!socet.isEmpty()) {
                sqlBuilder.append(" socket = ?,");
                parameters.add(socet);
            }
            if (!frequency.isEmpty()) {
                sqlBuilder.append(" frequency = ?,");
                parameters.add(frequency);
            }
            if (!threadsNumber.isEmpty()) {
                sqlBuilder.append(" threadsNumber = ?,");
                parameters.add(Integer.parseInt(threadsNumber));
            }
            if (!price.isEmpty()) {
                sqlBuilder.append(" price = ?,");
                parameters.add(price);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE cp_id = ?");
            parameters.add(id);

            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {

                    mess = "Данные успешно обновлены для ID " + id;
                } else {

                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public String updateProcessorCoolingSystems(int id, String brand, String model, String coolingType, String fansNumber, String fanSize, String fanRotationSpeed, String maxHeatDissipationProcessor, String price)
    {
        if(brand.isEmpty() && model.isEmpty() && coolingType.isEmpty() && fansNumber.isEmpty() && fanSize.isEmpty() && fanRotationSpeed.isEmpty() && maxHeatDissipationProcessor.isEmpty() && price.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE processor_cooling_systems SET");
            List<Object> parameters = new ArrayList<>();

            if (!brand.isEmpty()) {
                sqlBuilder.append(" Brand = ?,");
                parameters.add(brand);
            }
            if (!model.isEmpty()) {
                sqlBuilder.append(" Model = ?,");
                parameters.add(model);
            }
            if (!coolingType.isEmpty()) {
                sqlBuilder.append(" coolingType = ?,");
                parameters.add(coolingType);
            }
            if (!fansNumber.isEmpty()) {
                sqlBuilder.append(" fansNumber = ?,");
                parameters.add(Integer.parseInt(fansNumber));
            }
            if (!fanSize.isEmpty()) {
                sqlBuilder.append(" fanSize = ?,");
                parameters.add(fanSize);
            }
            if (!fanRotationSpeed.isEmpty()) {
                sqlBuilder.append(" fanRotationSpeed = ?,");
                parameters.add(fanRotationSpeed);
            }
            if (!maxHeatDissipationProcessor.isEmpty()) {
                sqlBuilder.append(" maxHeatDissipationProcessor = ?,");
                parameters.add(maxHeatDissipationProcessor);
            }
            if (!price.isEmpty()) {
                sqlBuilder.append(" price = ?,");
                parameters.add(price);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE PCS_id = ?");
            parameters.add(id);

            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {

                    mess = "Данные успешно обновлены для ID " + id;
                } else {

                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public String updateHousingCoolingSystems(int id, String brand, String model, String coolingType, String fansNumber, String fanSize, String fanRotationSpeed, String fanLife, String price)
    {
        if(brand.isEmpty() && model.isEmpty() && coolingType.isEmpty() && fansNumber.isEmpty() && fanSize.isEmpty() && fanRotationSpeed.isEmpty() && fanLife.isEmpty() && price.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE housing_cooling_systems SET");
            List<Object> parameters = new ArrayList<>();

            if (!brand.isEmpty()) {
                sqlBuilder.append(" Brand = ?,");
                parameters.add(brand);
            }
            if (!model.isEmpty()) {
                sqlBuilder.append(" Model = ?,");
                parameters.add(model);
            }
            if (!coolingType.isEmpty()) {
                sqlBuilder.append(" coolingType = ?,");
                parameters.add(coolingType);
            }
            if (!fansNumber.isEmpty()) {
                sqlBuilder.append(" fansNumber = ?,");
                parameters.add(Integer.parseInt(fansNumber));
            }
            if (!fanSize.isEmpty()) {
                sqlBuilder.append(" fanSize = ?,");
                parameters.add(fanSize);
            }
            if (!fanRotationSpeed.isEmpty()) {
                sqlBuilder.append(" fanRotationSpeed = ?,");
                parameters.add(fanRotationSpeed);
            }
            if (!fanLife.isEmpty()) {
                sqlBuilder.append(" fanLife = ?,");
                parameters.add(fanLife);
            }
            if (!price.isEmpty()) {
                sqlBuilder.append(" price = ?,");
                parameters.add(price);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE HCS_id = ?");
            parameters.add(id);

            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {

                    mess = "Данные успешно обновлены для ID " + id;
                } else {

                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public String updateVideoCards(int id, String brand, String model, String videoChipset, String gpf, String maxResolution, String videoMemory, String price)
    {
        if(brand.isEmpty() && model.isEmpty() && videoChipset.isEmpty() && gpf.isEmpty() && maxResolution.isEmpty() && videoMemory.isEmpty() && price.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE video_cards SET");
            List<Object> parameters = new ArrayList<>();

            if (!brand.isEmpty()) {
                sqlBuilder.append(" Brand = ?,");
                parameters.add(brand);
            }
            if (!model.isEmpty()) {
                sqlBuilder.append(" Model = ?,");
                parameters.add(model);
            }
            if (!videoChipset.isEmpty()) {
                sqlBuilder.append(" videoChipset = ?,");
                parameters.add(videoChipset);
            }
            if (!gpf.isEmpty()) {
                sqlBuilder.append(" GPF = ?,");
                parameters.add(gpf);
            }
            if (!maxResolution.isEmpty()) {
                sqlBuilder.append(" maxResolution = ?,");
                parameters.add(maxResolution);
            }
            if (!videoMemory.isEmpty()) {
                sqlBuilder.append(" videoMemory = ?,");
                parameters.add(videoMemory);
            }
            if (!price.isEmpty()) {
                sqlBuilder.append(" price = ?,");
                parameters.add(price);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE VC_id = ?");
            parameters.add(id);

            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {

                    mess = "Данные успешно обновлены для ID " + id;
                } else {

                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public String updateRAM(int id, String brand, String model, String formFactor, String memoryType, String volume, String clockFrequency, String throughputCapacity, String price)
    {
        if(brand.isEmpty() && model.isEmpty() && formFactor.isEmpty() && memoryType.isEmpty() && volume.isEmpty() && clockFrequency.isEmpty() && throughputCapacity.isEmpty() && price.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE ram SET");
            List<Object> parameters = new ArrayList<>();

            if (!brand.isEmpty()) {
                sqlBuilder.append(" Brand = ?,");
                parameters.add(brand);
            }
            if (!model.isEmpty()) {
                sqlBuilder.append(" Model = ?,");
                parameters.add(model);
            }
            if (!formFactor.isEmpty()) {
                sqlBuilder.append(" formFactor = ?,");
                parameters.add(formFactor);
            }
            if (!memoryType.isEmpty()) {
                sqlBuilder.append(" memoryType = ?,");
                parameters.add(memoryType);
            }
            if (!volume.isEmpty()) {
                sqlBuilder.append(" Volume = ?,");
                parameters.add(volume);
            }
            if (!clockFrequency.isEmpty()) {
                sqlBuilder.append(" clockFrequency = ?,");
                parameters.add(clockFrequency);
            }
            if (!throughputCapacity.isEmpty()) {
                sqlBuilder.append(" throughputCapacity = ?,");
                parameters.add(throughputCapacity);
            }
            if (!price.isEmpty()) {
                sqlBuilder.append(" price = ?,");
                parameters.add(price);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE RAM_id = ?");
            parameters.add(id);

            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {

                    mess = "Данные успешно обновлены для ID " + id;
                } else {

                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public String updatePowerSupplyUnits(int id, String brand, String model, String formFactor, String power, String minInpVolt, String maxInpVolt, String price)
    {
        if(brand.isEmpty() && model.isEmpty() && formFactor.isEmpty() && power.isEmpty() && minInpVolt.isEmpty() && maxInpVolt.isEmpty() && price.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE power_supply_units SET");
            List<Object> parameters = new ArrayList<>();

            if (!brand.isEmpty()) {
                sqlBuilder.append(" Brand = ?,");
                parameters.add(brand);
            }
            if (!model.isEmpty()) {
                sqlBuilder.append(" Model = ?,");
                parameters.add(model);
            }
            if (!formFactor.isEmpty()) {
                sqlBuilder.append(" formFactor = ?,");
                parameters.add(formFactor);
            }
            if (!power.isEmpty()) {
                sqlBuilder.append(" Power = ?,");
                parameters.add(power);
            }
            if (!minInpVolt.isEmpty()) {
                sqlBuilder.append(" minInpVolt = ?,");
                parameters.add(minInpVolt);
            }
            if (!maxInpVolt.isEmpty()) {
                sqlBuilder.append(" maxInpVolt = ?,");
                parameters.add(maxInpVolt);
            }
            if (!price.isEmpty()) {
                sqlBuilder.append(" price = ?,");
                parameters.add(price);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE PSU_id = ?");
            parameters.add(id);

            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {

                    mess = "Данные успешно обновлены для ID " + id;
                } else {

                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public String updateEnclosures(int id, String brand, String model, String formFactor, String sizeStandard, String BPLocation, String price)
    {
        if(brand.isEmpty() && model.isEmpty() && formFactor.isEmpty() && sizeStandard.isEmpty() && BPLocation.isEmpty() && price.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE enclosures SET");
            List<Object> parameters = new ArrayList<>();

            if (!brand.isEmpty()) {
                sqlBuilder.append(" Brand = ?,");
                parameters.add(brand);
            }
            if (!model.isEmpty()) {
                sqlBuilder.append(" Model = ?,");
                parameters.add(model);
            }
            if (!formFactor.isEmpty()) {
                sqlBuilder.append(" formFactor = ?,");
                parameters.add(formFactor);
            }
            if (!sizeStandard.isEmpty()) {
                sqlBuilder.append(" sizeStandard = ?,");
                parameters.add(sizeStandard);
            }
            if (!BPLocation.isEmpty()) {
                sqlBuilder.append(" BPLocation = ?,");
                parameters.add(BPLocation);
            }
            if (!price.isEmpty()) {
                sqlBuilder.append(" price = ?,");
                parameters.add(price);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE Enclosures_id = ?");
            parameters.add(id);

            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {

                    mess = "Данные успешно обновлены для ID " + id;
                } else {

                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public String updateHDD(int id, String brand, String model, String formFactor, String type, String capacityStorage, String spindleRotationSpeed, String price)
    {
        if(brand.isEmpty() && model.isEmpty() && formFactor.isEmpty() && type.isEmpty() && capacityStorage.isEmpty() && spindleRotationSpeed.isEmpty() && price.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE hard_drives SET");
            List<Object> parameters = new ArrayList<>();

            if (!brand.isEmpty()) {
                sqlBuilder.append(" Brand = ?,");
                parameters.add(brand);
            }
            if (!model.isEmpty()) {
                sqlBuilder.append(" Model = ?,");
                parameters.add(model);
            }
            if (!formFactor.isEmpty()) {
                sqlBuilder.append(" formFactor = ?,");
                parameters.add(formFactor);
            }
            if (!type.isEmpty()) {
                sqlBuilder.append(" Type = ?,");
                parameters.add(type);
            }
            if (!capacityStorage.isEmpty()) {
                sqlBuilder.append(" capacityStorage = ?,");
                parameters.add(capacityStorage);
            }
            if (!spindleRotationSpeed.isEmpty()) {
                sqlBuilder.append(" SpindleRotationSpeed = ?,");
                parameters.add(spindleRotationSpeed);
            }
            if (!price.isEmpty()) {
                sqlBuilder.append(" price = ?,");
                parameters.add(price);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE HardDrives_id = ?");
            parameters.add(id);

            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {

                    mess = "Данные успешно обновлены для ID " + id;
                } else {

                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public String updateSSD(int id, String brand, String model, String formFactor, String type, String capacityStorage, String maxReadingSpeed, String maxRecordingSpeed, String price)
    {
        if(brand.isEmpty() && model.isEmpty() && formFactor.isEmpty() && type.isEmpty() && capacityStorage.isEmpty() && maxReadingSpeed.isEmpty() && maxRecordingSpeed.isEmpty() && price.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE ssd SET");
            List<Object> parameters = new ArrayList<>();

            if (!brand.isEmpty()) {
                sqlBuilder.append(" Brand = ?,");
                parameters.add(brand);
            }
            if (!model.isEmpty()) {
                sqlBuilder.append(" Model = ?,");
                parameters.add(model);
            }
            if (!formFactor.isEmpty()) {
                sqlBuilder.append(" formFactor = ?,");
                parameters.add(formFactor);
            }
            if (!type.isEmpty()) {
                sqlBuilder.append(" Type = ?,");
                parameters.add(type);
            }
            if (!capacityStorage.isEmpty()) {
                sqlBuilder.append(" capacityStorage = ?,");
                parameters.add(capacityStorage);
            }
            if (!maxReadingSpeed.isEmpty()) {
                sqlBuilder.append(" maxReadingSpeed = ?,");
                parameters.add(maxReadingSpeed);
            }
            if (!maxRecordingSpeed.isEmpty()) {
                sqlBuilder.append(" maxRecordingSpeed = ?,");
                parameters.add(maxRecordingSpeed);
            }
            if (!price.isEmpty()) {
                sqlBuilder.append(" price = ?,");
                parameters.add(price);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE SSD_id = ?");
            parameters.add(id);

            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {

                    mess = "Данные успешно обновлены для ID " + id;
                } else {

                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public String updateMotherboards(int id, String brand, String model, String formFactor, String socket, String chipset, String memoryType, String price)
    {
        if(brand.isEmpty() && model.isEmpty() && formFactor.isEmpty() && socket.isEmpty() && chipset.isEmpty() && memoryType.isEmpty() && price.isEmpty())
            return "";

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String passwordDB = "84628462";

        String mess = "";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            StringBuilder sqlBuilder = new StringBuilder("UPDATE motherboards SET");
            List<Object> parameters = new ArrayList<>();

            if (!brand.isEmpty()) {
                sqlBuilder.append(" Brand = ?,");
                parameters.add(brand);
            }
            if (!model.isEmpty()) {
                sqlBuilder.append(" Model = ?,");
                parameters.add(model);
            }
            if (!formFactor.isEmpty()) {
                sqlBuilder.append(" formFactor = ?,");
                parameters.add(formFactor);
            }
            if (!socket.isEmpty()) {
                sqlBuilder.append(" Socket = ?,");
                parameters.add(socket);
            }
            if (!chipset.isEmpty()) {
                sqlBuilder.append(" Chipset = ?,");
                parameters.add(chipset);
            }
            if (!memoryType.isEmpty()) {
                sqlBuilder.append(" memoryType = ?,");
                parameters.add(memoryType);
            }
            if (!price.isEmpty()) {
                sqlBuilder.append(" price = ?,");
                parameters.add(price);
            }

            if (parameters.isEmpty()) {
                return "Ничего не было обновлено, так как все поля пустые.";
            }

            // Удаляем последнюю запятую
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE Motherboards_id = ?");
            parameters.add(id);

            try (PreparedStatement statement = conn.prepareStatement(sqlBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    statement.setObject(i + 1, parameters.get(i));
                }

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    mess = "Данные успешно обновлены для ID " + id;
                } else {
                    mess = "ID " + id + " не найден в базе данных.";
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
        }
        return mess;
    }

    public List<CPs> getAllCPs()
    {
        List<CPs> cPsList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM cp";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    CPs cPs = new CPs();
                    cPs.setId(resultSet.getInt("cp_id"));
                    cPs.setBrand(resultSet.getString("Brand"));
                    cPs.setModel(resultSet.getString("Model"));
                    cPs.setCoreName(resultSet.getString("coreName"));
                    cPs.setCoreNumber(resultSet.getInt("coresNumber"));
                    cPs.setSocket(resultSet.getString("socket"));
                    cPs.setFrequency(resultSet.getString("frequency"));
                    cPs.setThreadsNumber(resultSet.getInt("threadsNumber"));
                    cPs.setPrice(resultSet.getString("price"));
                    cPs.setImageFileName(resultSet.getString("imageFileName"));
                    cPsList.add(cPs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all: " + e.getMessage());
        }
        return cPsList;
    }

    public List<ProcessorCoolingSystems> getAllProcessorCoolingSystems()
    {
        List<ProcessorCoolingSystems> ProcessorCoolingSystemsList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM processor_cooling_systems";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    ProcessorCoolingSystems PCS = new ProcessorCoolingSystems();
                    PCS.setId(resultSet.getInt("PCS_id"));
                    PCS.setBrand(resultSet.getString("Brand"));
                    PCS.setModel(resultSet.getString("Model"));
                    PCS.setCoolingType(resultSet.getString("coolingType"));
                    PCS.setFansNumber(resultSet.getInt("fansNumber"));
                    PCS.setFanSize(resultSet.getString("fanSize"));
                    PCS.setFanRotationSpeed(resultSet.getString("fanRotationSpeed"));
                    PCS.setMaxHeatDissipationProcessor(resultSet.getString("maxHeatDissipationProcessor"));
                    PCS.setPrice(resultSet.getString("price"));
                    PCS.setImageFileName(resultSet.getString("imageFileName"));
                    ProcessorCoolingSystemsList.add(PCS);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all: " + e.getMessage());
        }
        return ProcessorCoolingSystemsList;
    }

    public List<HousingCoolingSystems> getAllHousingCoolingSystems()
    {
        List<HousingCoolingSystems> HousingCoolingSystemsList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM housing_cooling_systems";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    HousingCoolingSystems HCS = new HousingCoolingSystems();
                    HCS.setId(resultSet.getInt("HCS_id"));
                    HCS.setBrand(resultSet.getString("Brand"));
                    HCS.setModel(resultSet.getString("Model"));
                    HCS.setCoolingType(resultSet.getString("coolingType"));
                    HCS.setFansNumber(resultSet.getInt("fansNumber"));
                    HCS.setFanSize(resultSet.getString("fanSize"));
                    HCS.setFanRotationSpeed(resultSet.getString("fanRotationSpeed"));
                    HCS.setFanLife(resultSet.getString("fanLife"));
                    HCS.setPrice(resultSet.getString("price"));
                    HCS.setImageFileName(resultSet.getString("imageFileName"));
                    HousingCoolingSystemsList.add(HCS);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all: " + e.getMessage());
        }
        return HousingCoolingSystemsList;

    }

    public List<RAM> getAllRAM()
    {
        List<RAM> ramList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM ram";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    RAM ram = new RAM();
                    ram.setId(resultSet.getInt("RAM_id"));
                    ram.setBrand(resultSet.getString("Brand"));
                    ram.setModel(resultSet.getString("Model"));
                    ram.setFormFactor(resultSet.getString("formFactor"));
                    ram.setMemoryType(resultSet.getString("memoryType"));
                    ram.setVolume(resultSet.getString("Volume"));
                    ram.setClockFrequency(resultSet.getString("clockFrequency"));
                    ram.setThroughputCapacity(resultSet.getString("throughputCapacity"));
                    ram.setPrice(resultSet.getString("price"));
                    ram.setImageFileName(resultSet.getString("imageFileName"));
                    ramList.add(ram);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all: " + e.getMessage());
        }
        return ramList;
    }

    public List<VideoCards> getAllVideoCards()
    {
        List<VideoCards> videoCardsList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM video_cards";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    VideoCards videoCards = new VideoCards();
                    videoCards.setId(resultSet.getInt("VC_id"));
                    videoCards.setBrand(resultSet.getString("Brand"));
                    videoCards.setModel(resultSet.getString("Model"));
                    videoCards.setVideoChipset(resultSet.getString("videoChipset"));
                    videoCards.setGpf(resultSet.getString("GPF"));
                    videoCards.setMaxResolution(resultSet.getString("maxResolution"));
                    videoCards.setVideoMemory(resultSet.getString("videoMemory"));
                    videoCards.setPrice(resultSet.getString("price"));
                    videoCards.setImageFileName(resultSet.getString("imageFileName"));
                    videoCardsList.add(videoCards);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all : " + e.getMessage());
        }
        return videoCardsList;
    }

    public List<PowerSupplyUnits> getAllPowerSupplyUnits()
    {
        List<PowerSupplyUnits> powerSupplyUnitsList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM power_supply_units";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    PowerSupplyUnits PSU = new PowerSupplyUnits();
                    PSU.setId(resultSet.getInt("PSU_id"));
                    PSU.setBrand(resultSet.getString("Brand"));
                    PSU.setModel(resultSet.getString("Model"));
                    PSU.setFormFactor(resultSet.getString("formFactor"));
                    PSU.setPower(resultSet.getString("Power"));
                    PSU.setMinInpVolt(resultSet.getString("minInpVolt"));
                    PSU.setMaxInpVolt(resultSet.getString("maxInpVolt"));
                    PSU.setPrice(resultSet.getString("price"));
                    PSU.setImageFileName(resultSet.getString("imageFileName"));
                    powerSupplyUnitsList.add(PSU);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all: " + e.getMessage());
        }
        return powerSupplyUnitsList;
    }

    public List<Enclosures> getAllEnclosures()
    {
        List<Enclosures> enclosuresList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM enclosures";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Enclosures enclosures = new Enclosures();
                    enclosures.setId(resultSet.getInt("Enclosures_id"));
                    enclosures.setBrand(resultSet.getString("Brand"));
                    enclosures.setModel(resultSet.getString("Model"));
                    enclosures.setSizeStandard(resultSet.getString("sizeStandard"));
                    enclosures.setFormFactor(resultSet.getString("formFactor"));
                    enclosures.setBPLocation(resultSet.getString("BPLocation"));
                    enclosures.setPrice(resultSet.getString("price"));
                    enclosures.setImageFileName(resultSet.getString("imageFileName"));
                    enclosuresList.add(enclosures);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all: " + e.getMessage());
        }
        return enclosuresList;
    }

    public List<HDD> getAllHDD()
    {
        List<HDD> HDDList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM hard_drives";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    HDD hdd = new HDD();
                    hdd.setId(resultSet.getInt("HardDrives_id"));
                    hdd.setBrand(resultSet.getString("Brand"));
                    hdd.setModel(resultSet.getString("Model"));
                    hdd.setType(resultSet.getString("Type"));
                    hdd.setFormFactor(resultSet.getString("formFactor"));
                    hdd.setCapacityStorage(resultSet.getString("capacityStorage"));
                    hdd.setSpindleRotationSpeed(resultSet.getString("SpindleRotationSpeed"));
                    hdd.setPrice(resultSet.getString("price"));
                    hdd.setImageFileName(resultSet.getString("imageFileName"));
                    HDDList.add(hdd);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all: " + e.getMessage());
        }
        return HDDList;
    }

    public List<SSD> getAllSSD()
    {
        List<SSD> SSDList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM ssd";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    SSD ssd = new SSD();
                    ssd.setId(resultSet.getInt("SSD_id"));
                    ssd.setBrand(resultSet.getString("Brand"));
                    ssd.setModel(resultSet.getString("Model"));
                    ssd.setType(resultSet.getString("Type"));
                    ssd.setCapacityStorage(resultSet.getString("capacityStorage"));
                    ssd.setFormFactor(resultSet.getString("formFactor"));
                    ssd.setMaxReadingSpeed(resultSet.getString("maxReadingSpeed"));
                    ssd.setMaxRecordingSpeed(resultSet.getString("maxRecordingSpeed"));
                    ssd.setPrice(resultSet.getString("price"));
                    ssd.setImageFileName(resultSet.getString("imageFileName"));
                    SSDList.add(ssd);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all: " + e.getMessage());
        }
        return SSDList;
    }

    public List<Motherboards> getAllMotherboards()
    {
        List<Motherboards> MotherboardsList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/test_1";
        String usernameDB = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, usernameDB, password)) {
            String sql = "SELECT * FROM motherboards";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Motherboards motherboards = new Motherboards();
                    motherboards.setId(resultSet.getInt("Motherboards_id"));
                    motherboards.setBrand(resultSet.getString("Brand"));
                    motherboards.setModel(resultSet.getString("Model"));
                    motherboards.setFormFactor(resultSet.getString("formFactor"));
                    motherboards.setSocket(resultSet.getString("Socket"));
                    motherboards.setChipset(resultSet.getString("Chipset"));
                    motherboards.setMemoryType(resultSet.getString("memoryType"));
                    motherboards.setPrice(resultSet.getString("price"));
                    motherboards.setImageFileName(resultSet.getString("imageFileName"));
                    MotherboardsList.add(motherboards);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all: " + e.getMessage());
        }
        return MotherboardsList;
    }

    public void addCPsToDatabase(@NotNull CPs cp)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO cp (Brand, Model, coreName, coresNumber, socket, frequency, threadsNumber, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, cp.getBrand());
                statement.setString(2, cp.getModel());
                statement.setString(3, cp.getCoreName());
                statement.setInt(4, cp.getCoreNumber());
                statement.setString(5, cp.getSocket());
                statement.setString(6, cp.getFrequency());
                statement.setInt(7, cp.getThreadsNumber());
                statement.setString(8, cp.getPrice());
                statement.setString(9, cp.getImageFileName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save CP to database");
        }
    }

    public void addProcessorCoolingSystemsToDatabase(@NotNull ProcessorCoolingSystems PCS)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO processor_cooling_systems (Brand, Model, coolingType, fansNumber, fanSize, fanRotationSpeed, maxHeatDissipationProcessor, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, PCS.getBrand());
                statement.setString(2, PCS.getModel());
                statement.setString(3, PCS.getCoolingType());
                statement.setInt(4, PCS.getFansNumber());
                statement.setString(5, PCS.getFanSize());
                statement.setString(6, PCS.getFanRotationSpeed());
                statement.setString(7, PCS.getMaxHeatDissipationProcessor());
                statement.setString(8, PCS.getPrice());
                statement.setString(9,PCS.getImageFileName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save ProcessorCoolingSystems to database");
        }
    }

    public void addHousingCoolingSystemsToDatabase(@NotNull HousingCoolingSystems HCS)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO housing_cooling_systems (Brand, Model, coolingType, fansNumber, fanSize, fanRotationSpeed, fanLife, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, HCS.getBrand());
                statement.setString(2, HCS.getModel());
                statement.setString(3, HCS.getCoolingType());
                statement.setInt(4, HCS.getFansNumber());
                statement.setString(5, HCS.getFanSize());
                statement.setString(6, HCS.getFanRotationSpeed());
                statement.setString(7, HCS.getFanLife());
                statement.setString(8, HCS.getPrice());
                statement.setString(9, HCS.getImageFileName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save HousingCoolingSystems to database");
        }
    }

    public void addVideoCardsToDatabase(@NotNull VideoCards vc)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO video_cards (Brand, Model, videoChipset, GPF, maxResolution, videoMemory, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, vc.getBrand());
                statement.setString(2, vc.getModel());
                statement.setString(3, vc.getVideoChipset());
                statement.setString(4, vc.getGpf());
                statement.setString(5, vc.getMaxResolution());
                statement.setString(6, vc.getVideoMemory());
                statement.setString(7, vc.getPrice());
                statement.setString(8, vc.getImageFileName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save VideoCards to database");
        }
    }

    public void addRAMToDatabase(@NotNull RAM ram)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO ram (Brand, Model, formFactor, memoryType, Volume, clockFrequency, throughputCapacity, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, ram.getBrand());
                statement.setString(2, ram.getModel());
                statement.setString(3, ram.getFormFactor());
                statement.setString(4, ram.getMemoryType());
                statement.setString(5, ram.getVolume());
                statement.setString(6, ram.getClockFrequency());
                statement.setString(7, ram.getThroughputCapacity());
                statement.setString(8, ram.getPrice());
                statement.setString(9, ram.getImageFileName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save RAM to database");
        }
    }

    public void addPowerSupplyUnitsToDatabase(@NotNull PowerSupplyUnits psu)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO power_supply_units (Brand, Model, formFactor, Power, minInpVolt, maxInpVolt, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, psu.getBrand());
                statement.setString(2, psu.getModel());
                statement.setString(3, psu.getFormFactor());
                statement.setString(4, psu.getPower());
                statement.setString(5, psu.getMinInpVolt());
                statement.setString(6, psu.getMaxInpVolt());
                statement.setString(7, psu.getPrice());
                statement.setString(8, psu.getImageFileName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save PowerSupplyUnits to database");
        }
    }

    public void addEnclosuresToDatabase(@NotNull Enclosures enclosures)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO enclosures (Brand, Model, sizeStandard, formFactor, BPLocation, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, enclosures.getBrand());
                statement.setString(2, enclosures.getModel());
                statement.setString(3, enclosures.getSizeStandard());
                statement.setString(4, enclosures.getFormFactor());
                statement.setString(5, enclosures.getBPLocation());
                statement.setString(6, enclosures.getPrice());
                statement.setString(7, enclosures.getImageFileName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save Enclosures to database");
        }
    }

    public void addHDDToDatabase(@NotNull HDD hdd)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO hard_drives (Brand, Model, Type, formFactor, capacityStorage, SpindleRotationSpeed, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, hdd.getBrand());
                statement.setString(2, hdd.getModel());
                statement.setString(3, hdd.getType());
                statement.setString(4, hdd.getFormFactor());
                statement.setString(5, hdd.getCapacityStorage());
                statement.setString(6, hdd.getSpindleRotationSpeed());
                statement.setString(7, hdd.getPrice());
                statement.setString(8, hdd.getImageFileName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save HDD to database");
        }
    }

    public void addSSDToDatabase(@NotNull SSD ssd)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO ssd (Brand, Model, Type, capacityStorage, formFactor, maxReadingSpeed, maxRecordingSpeed, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, ssd.getBrand());
                statement.setString(2, ssd.getModel());
                statement.setString(3, ssd.getType());
                statement.setString(4, ssd.getCapacityStorage());
                statement.setString(5, ssd.getFormFactor());
                statement.setString(6, ssd.getMaxReadingSpeed());
                statement.setString(7, ssd.getMaxRecordingSpeed());
                statement.setString(8, ssd.getPrice());
                statement.setString(9, ssd.getImageFileName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save SSD to database");
        }
    }

    public void addMotherboardsToDatabase(@NotNull Motherboards motherboards)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO motherboards (Brand, Model, formFactor, Socket, Chipset, memoryType, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, motherboards.getBrand());
                statement.setString(2, motherboards.getModel());
                statement.setString(3, motherboards.getFormFactor());
                statement.setString(4, motherboards.getSocket());
                statement.setString(5, motherboards.getChipset());
                statement.setString(6, motherboards.getMemoryType());
                statement.setString(7, motherboards.getPrice());
                statement.setString(8, motherboards.getImageFileName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to save Motherboards to database");
        }
    }

    public int countProductsInTable(String tableName)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        int count = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO motherboards (Brand, Model, formFactor, Socket, Chipset, memoryType, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + tableName);
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed count product");
        }
        return count;
    }

    public int countUserPriv(String priv)
    {
        String url = "jdbc:mysql://localhost:3306/test_1";
        String username = "root";
        String password = "84628462";

        int count = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO motherboards (Brand, Model, formFactor, Socket, Chipset, memoryType, price, imageFileName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM user WHERE priv='" + priv + "'");
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed count product");
        }
        return count;
    }

}
