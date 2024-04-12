package org.example.FX;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProductCard extends VBox {
    private Stage stage;
    private String productName;
    private ImageView productImageView;

    public ProductCard(Stage primaryStage) {

        this.stage = primaryStage;
    }

    // карточки главного экрана
    public void creatingCard(String productName, String imagePath)
    {
        this.productName = productName;
        productImageView = new ImageView(new Image(imagePath));
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(productName);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        // Делаем надпись кликабельной
        productNameLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            eventHandler();
        });

        this.getChildren().addAll(productImageView, productNameLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");

    }

    // карточки продуктов(товаров)
    public void creatingCardCP(String brand, String model, String coreName, int coresNumber, String socket, String price, String frequency, int threadsNumber, Image imagePath, TilePane productCardsUser)
    {
        this.productName = brand;

        ImageView productImageView = new ImageView(imagePath);
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(brand);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 18px;");
        priceLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Описание");
        descriptionLabel.setStyle("-fx-font-size: 16px;");
        descriptionLabel.setAlignment(Pos.CENTER);


        descriptionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            String message = "\t\t\tОписание\n\n" +
                    "Бренд  _____________________________  " + brand + "\n\n" +
                    "Модель  _____________________________  " + model + "\n\n" +
                    "Имя Ядра  _____________________________  " + coreName + "\n\n" +
                    "Количество ядер  _____________________________  " + coresNumber + "\n\n" +
                    "Сокет  _____________________________  " + socket + "\n\n" +
                    "Частота  _____________________________  " + frequency + "\n\n" +
                    "Количество потоков  _____________________________  " + threadsNumber + "\n\n" +
                    "Цена  _____________________________  " + price + " руб" + "\n\n";

            showAlert(message);
        });

        this.getChildren().addAll(productImageView, productNameLabel, priceLabel, descriptionLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");
        productCardsUser.getChildren().add(this);
    }

    public void creatingCardProcessorCoolingSystems(String brand, String model, String coolingType, int fansNumber, String fanSize, String fanRotationSpeed, String maxHeatDissipationProcessor, String price, Image imagePath, TilePane productCardsUser)
    {
        this.productName = brand;

        ImageView productImageView = new ImageView(imagePath);
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(brand);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 18px;");
        priceLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Описание");
        descriptionLabel.setStyle("-fx-font-size: 16px;");
        descriptionLabel.setAlignment(Pos.CENTER);


        descriptionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            String message = "\t\t\tОписание\n\n" +
                    "Бренд  _____________________________  " + brand + "\n\n" +
                    "Модель  _____________________________  " + model + "\n\n" +
                    "Тип охлаждения  _____________________________  " + coolingType + "\n\n" +
                    "Количество вентиляторов  _____________________________  " + fansNumber + "\n\n" +
                    "Размер вентилятора  _____________________________  " + fanSize + "\n\n" +
                    "Скорость вращения вентилятора  _____________________________  " + fanRotationSpeed + "\n\n" +
                    "Максимальное тепловыделение процессора  _____________________________  " + maxHeatDissipationProcessor + "\n\n" +
                    "Цена  _____________________________  " + price + " руб" + "\n\n";

            showAlert(message);
        });

        this.getChildren().addAll(productImageView, productNameLabel, priceLabel, descriptionLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");
        productCardsUser.getChildren().add(this);
    }

    public void creatingCardHousingCoolingSystems(String brand, String model, String coolingType, int fansNumber, String fanSize, String fanRotationSpeed, String fanLife, String price, Image imagePath, TilePane productCardsUser)
    {
        this.productName = brand;

        ImageView productImageView = new ImageView(imagePath);
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(brand);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 18px;");
        priceLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Описание");
        descriptionLabel.setStyle("-fx-font-size: 16px;");
        descriptionLabel.setAlignment(Pos.CENTER);


        descriptionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            String message = "\t\t\tОписание\n\n" +
                    "Бренд  _____________________________  " + brand + "\n\n" +
                    "Модель  _____________________________  " + model + "\n\n" +
                    "Тип охлаждения  _____________________________  " + coolingType + "\n\n" +
                    "Количество вентиляторов  _____________________________  " + fansNumber + "\n\n" +
                    "Размер вентилятора  _____________________________  " + fanSize + "\n\n" +
                    "Скорость вращения вентилятора  _____________________________  " + fanRotationSpeed + "\n\n" +
                    "Срок службы вентилятора  _____________________________  " + fanLife + "\n\n" +
                    "Цена  _____________________________  " + price + " руб" + "\n\n";

            showAlert(message);

        });

        this.getChildren().addAll(productImageView, productNameLabel, priceLabel, descriptionLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");
        productCardsUser.getChildren().add(this);
    }

    public void creatingCardRAM(String brand, String model, String formFactor, String memoryType, String Volume, String clockFrequency, String throughputCapacity, String price, Image imagePath, TilePane productCardsUser)
    {
        this.productName = brand;

        ImageView productImageView = new ImageView(imagePath);
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(brand);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 18px;");
        priceLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Описание");
        descriptionLabel.setStyle("-fx-font-size: 16px;");
        descriptionLabel.setAlignment(Pos.CENTER);


        descriptionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            String message = "\t\t\tОписание\n\n" +
                    "Бренд  _____________________________  " + brand + "\n\n" +
                    "Модель  _____________________________  " + model + "\n\n" +
                    "Форм-фактор  _____________________________  " + formFactor + "\n\n" +
                    "Тип памяти  _____________________________  " + memoryType + "\n\n" +
                    "Объем  _____________________________  " + Volume + "\n\n" +
                    "Тактовая частота  _____________________________  " + clockFrequency + "\n\n" +
                    "Пропускная способность  _____________________________  " + throughputCapacity + "\n\n" +
                    "Цена  _____________________________  " + price + " руб" + "\n\n";

            showAlert(message);
        });

        this.getChildren().addAll(productImageView, productNameLabel, priceLabel, descriptionLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");
        productCardsUser.getChildren().add(this);
    }

    public void creatingCardVideoCards(String brand, String model, String videoChipset, String GPF, String maxResolution, String videoMemory, String price, Image imagePath, TilePane productCardsUser)
    {
        this.productName = brand;

        ImageView productImageView = new ImageView(imagePath);
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(brand);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 18px;");
        priceLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Описание");
        descriptionLabel.setStyle("-fx-font-size: 16px;");
        descriptionLabel.setAlignment(Pos.CENTER);


        descriptionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            String message = "\t\t\tОписание\n\n" +
                    "Бренд  _____________________________  " + brand + "\n\n" +
                    "Модель  _____________________________  " + model + "\n\n" +
                    "Видео чипсет  _____________________________  " + videoChipset + "\n\n" +
                    "Частота графического процессора  _____________________________  " + GPF + "\n\n" +
                    "Максимальное разрешение  _____________________________  " + maxResolution + "\n\n" +
                    "Объем видеопамяти  _____________________________  " + videoMemory + "\n\n" +
                    "Цена  _____________________________  " + price + " руб" + "\n\n";

            showAlert(message);

        });

        this.getChildren().addAll(productImageView, productNameLabel, priceLabel, descriptionLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");
        productCardsUser.getChildren().add(this);
    }

    public void creatingCardPowerSupplyUnits(String brand, String model, String formFactor, String Power, String minInpVolt, String maxInpVolt, String price, Image imagePath, TilePane productCardsUser)
    {
        this.productName = brand;

        ImageView productImageView = new ImageView(imagePath);
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(brand);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 18px;");
        priceLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Описание");
        descriptionLabel.setStyle("-fx-font-size: 16px;");
        descriptionLabel.setAlignment(Pos.CENTER);


        descriptionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            String message = "\t\t\tОписание\n\n" +
                    "Бренд  _____________________________  " + brand + "\n\n" +
                    "Модель  _____________________________  " + model + "\n\n" +
                    "Форм-фактор  _____________________________  " + formFactor + "\n\n" +
                    "Мощность  _____________________________  " + Power + "\n\n" +
                    "Минимальное входное напряжение  _____________________________  " + minInpVolt + "\n\n" +
                    "Максимальное входное напряжение  _____________________________  " + maxInpVolt + "\n\n" +
                    "Цена  _____________________________  " + price + " руб" + "\n\n";

            showAlert(message);
        });

        this.getChildren().addAll(productImageView, productNameLabel, priceLabel, descriptionLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");
        productCardsUser.getChildren().add(this);
    }

    public void creatingCardEnclosures(String brand, String model, String formFactor, String sizeStandard, String BPLocation, String price, Image imagePath, TilePane productCardsUser)
    {
        this.productName = brand;

        ImageView productImageView = new ImageView(imagePath);
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(brand);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 18px;");
        priceLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Описание");
        descriptionLabel.setStyle("-fx-font-size: 16px;");
        descriptionLabel.setAlignment(Pos.CENTER);


        descriptionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            String message = "\t\t\tОписание\n\n" +
                    "Бренд  _____________________________  " + brand + "\n\n" +
                    "Модель  _____________________________  " + model + "\n\n" +
                    "Форм-фактор  _____________________________  " + formFactor + "\n\n" +
                    "Типоразмер  _____________________________  " + sizeStandard + "\n\n" +
                    "Расположение БП  _____________________________  " + BPLocation + "\n\n" +
                    "Цена  _____________________________  " + price + " руб" + "\n\n";

            showAlert(message);
        });

        this.getChildren().addAll(productImageView, productNameLabel, priceLabel, descriptionLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");
        productCardsUser.getChildren().add(this);
    }

    public void creatingCardHDD(String brand, String model, String formFactor, String Type, String capacityStorage, String spindleRotationSpeed, String price, Image imagePath, TilePane productCardsUser)
    {
        this.productName = brand;

        ImageView productImageView = new ImageView(imagePath);
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(brand);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 18px;");
        priceLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Описание");
        descriptionLabel.setStyle("-fx-font-size: 16px;");
        descriptionLabel.setAlignment(Pos.CENTER);


        descriptionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            String message = "\t\t\tОписание\n\n" +
                    "Бренд  _____________________________  " + brand + "\n\n" +
                    "Модель  _____________________________  " + model + "\n\n" +
                    "Форм-фактор  _____________________________  " + formFactor + "\n\n" +
                    "Тип жесткого диска  _____________________________  " + Type + "\n\n" +
                    "Объем накопителя  _____________________________  " + capacityStorage + "\n\n" +
                    "Скорость вращения шпинделя  _____________________________  " + spindleRotationSpeed + "\n\n" +
                    "Цена  _____________________________  " + price + " руб" + "\n\n";

            showAlert(message);
        });

        this.getChildren().addAll(productImageView, productNameLabel, priceLabel, descriptionLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");
        productCardsUser.getChildren().add(this);
    }

    public void creatingCardSSD(String brand, String model, String formFactor, String Type, String capacityStorage, String maxReadingSpeed, String maxRecordingSpeed, String price, Image imagePath, TilePane productCardsUser)
    {
        this.productName = brand;

        ImageView productImageView = new ImageView(imagePath);
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(brand);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 18px;");
        priceLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Описание");
        descriptionLabel.setStyle("-fx-font-size: 16px;");
        descriptionLabel.setAlignment(Pos.CENTER);


        descriptionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            String message = "\t\t\tОписание\n\n" +
                    "Бренд  _____________________________  " + brand + "\n\n" +
                    "Модель  _____________________________  " + model + "\n\n" +
                    "Форм-фактор  _____________________________  " + formFactor + "\n\n" +
                    "Тип жесткого диска  _____________________________  " + Type + "\n\n" +
                    "Объем накопителя  _____________________________  " + capacityStorage + "\n\n" +
                    "Максимальная скорость чтения  _____________________________  " + maxReadingSpeed + "\n\n" +
                    "Максимальная скорость записи  _____________________________  " + maxRecordingSpeed + "\n\n" +
                    "Цена  _____________________________  " + price + " руб" + "\n\n";

            showAlert(message);
        });

        this.getChildren().addAll(productImageView, productNameLabel, priceLabel, descriptionLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");
        productCardsUser.getChildren().add(this);
    }

    public void creatingCardMotherboards(String brand, String model, String formFactor, String socket, String chipset, String memoryType, String price, Image imagePath, TilePane productCardsUser)
    {
        this.productName = brand;

        ImageView productImageView = new ImageView(imagePath);
        productImageView.setFitWidth(250); // Устанавливаем ширину изображения
        productImageView.setFitHeight(200); // Устанавливаем высоту изображения

        Label productNameLabel = new Label(brand);
        productNameLabel.setStyle("-fx-font-size: 20px;");
        productNameLabel.setAlignment(Pos.CENTER); // Устанавливаем выравнивание по центру

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-font-size: 18px;");
        priceLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Описание");
        descriptionLabel.setStyle("-fx-font-size: 16px;");
        descriptionLabel.setAlignment(Pos.CENTER);


        descriptionLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {

            String message = "\t\t\tОписание\n\n" +
                    "Бренд  _____________________________  " + brand + "\n\n" +
                    "Модель  _____________________________  " + model + "\n\n" +
                    "Форм-фактор  _____________________________  " + formFactor + "\n\n" +
                    "Сокет  _____________________________  " + socket + "\n\n" +
                    "Чипсет  _____________________________  " + chipset + "\n\n" +
                    "Тип памяти  _____________________________  " + memoryType + "\n\n" +
                    "Цена  _____________________________  " + price + " руб" + "\n\n";

            showAlert(message);
        });

        this.getChildren().addAll(productImageView, productNameLabel, priceLabel, descriptionLabel);
        this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px; -fx-spacing: 10px;");
        productCardsUser.getChildren().add(this);
    }

    private void eventHandler()
    {
        ProductСlassificationUI productСlassificationUI = new ProductСlassificationUI(stage);

        if (productName.equals("Процессоры"))
        {
            Scene new_Scene =  new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.CPWindowShow();
            stage.setScene(new_Scene);
        }

        else if(productName.equals("Системы охлаждения\nпроцессора"))
        {
            Scene new_Scene =  new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.ProcessorCoolingSystemsCard();
            stage.setScene(new_Scene);
        }

        else if(productName.equals("Системы охлаждения\nкорпуса"))
        {
            Scene new_Scene =  new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.HousingCoolingSystemsCard();
            stage.setScene(new_Scene);
        }

        else if(productName.equals("Оперативная память"))
        {
            Scene new_Scene =  new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.RAM();
            stage.setScene(new_Scene);
        }

        else if(productName.equals("Видеокарты"))
        {
            Scene new_Scene =  new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.VideoCardsCard();
            stage.setScene(new_Scene);
        }

        else if(productName.equals("Блоки питания"))
        {
            Scene new_Scene =  new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.PowerSupplyUnitsCard();
            stage.setScene(new_Scene);
        }

        else if(productName.equals("Корпуса"))
        {
            Scene new_Scene =  new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.EnclosuresCard();
            stage.setScene(new_Scene);
        }

        else if(productName.equals("Жесткие диски"))
        {
            Scene new_Scene =  new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.HDDCard();
            stage.setScene(new_Scene);
        }

        else if(productName.equals("SSD накопители"))
        {
            Scene new_Scene =  new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.SSDCard();
            stage.setScene(new_Scene);
        }

        else if(productName.equals("Материнские платы"))
        {
            Scene new_Scene = new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.MotherboardsCard();
            stage.setScene(new_Scene);
        }

        // Все для Root пользователя
        else if(productName.equals("Управление товарами"))
        {
            Scene new_Scene = new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.productDataManagement();
            stage.setScene(new_Scene);
        }
        else if(productName.equals("Управление данными пользователя"))
        {
            Scene new_Scene = new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.userDataManagement();
            stage.setScene(new_Scene);
        }
        // Все для analyst пользователя
        else if(productName.equals("Статистика"))
        {
            Scene new_Scene = new Scene(productСlassificationUI.getMainPane(), 1800, 1000);
            previous = stage.getScene();
            current = new_Scene;
            productСlassificationUI.analystDataManagement();
            stage.setScene(new_Scene);
        }

        else
            System.out.println("Error!!");
    }

    private void showAlert(String message)
    {
        Label label = new Label(message);
        label.setFont(Font.font("Verdana", 16));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Описание");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(label);
        alert.showAndWait();
    }

    public Scene current = null;
    public static Scene previous = null;

    public static class getPriwius
    {
        public Scene get()
        {
            return previous;
        }
    }

    public String getProductName() {
        return productName;
    }
}