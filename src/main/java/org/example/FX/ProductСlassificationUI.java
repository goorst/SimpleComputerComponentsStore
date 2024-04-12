package org.example.FX;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.BD.Database;
import org.example.FX.ProductCard.getPriwius;
import org.example.demo.*;

import java.util.ArrayList;
import java.util.List;

public class ProductСlassificationUI {

    private Label title;
    private Stage stage;
    private BorderPane mainPane;
    private TextField searchField;
    private List<ProductCard> originalProductCards = new ArrayList<>();


    public ProductСlassificationUI(Stage primaryStage)
    {
        this.stage = primaryStage;
        this.mainPane = new BorderPane();
        searchField = new TextField();
        searchField.setPromptText("Search...\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\uD83D\uDD0D");
        searchField.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-padding: 5px; -fx-border-width: 10px;");
        searchField.setMaxWidth(500);
    }

    public void CPWindowShow()
    {
        this.title = new Label("Процессоры");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);

        buttonTop.getChildren().addAll(
                backButton
        );

        // Обработчик события для кнопки "назад"
        backButton.setOnAction(e -> {
            exitButton();
        });

        //------------------

        TilePane productCardsUser = new TilePane();
        productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
        productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
        productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок
        productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");

        // динамическое добавление карточк вроде работает
        Database db = new Database();
        productCardsUser.getChildren().clear();
        List<CPs> CPs = db.getAllCPs();

        for (CPs cp : CPs) {
            ProductCard newProductCard = new ProductCard(stage);
            Image image = productDataManagementButtonFX.MyImage.getImageByName(cp.getImageFileName());

            newProductCard.creatingCardCP(cp.getBrand(), cp.getModel(), cp.getCoreName(), cp.getCoreNumber(), cp.getSocket(), cp.getPrice(), cp.getFrequency(), cp.getThreadsNumber(), image, productCardsUser);

            // Проверяем, нет ли уже такой карточки в productCardsUser перед добавлением
            if (!productCardsUser.getChildren().contains(newProductCard)) {
                Platform.runLater(() -> productCardsUser.getChildren().add(newProductCard));
            }
        }

        VBox productCardsWrapper = new VBox(productCardsUser);
        productCardsWrapper.setMinHeight(Region.USE_PREF_SIZE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productCardsWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saveOriginalProductCards(productCardsUser);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productCardsUser.getChildren().clear();
                for (ProductCard productCard : originalProductCards) {
                    productCardsUser.getChildren().add(productCard);
                    productCard.setVisible(true);
                }

            } else {
                filterCards(newValue, productCardsUser);
                Platform.runLater(() -> scrollPane.setVvalue(0));
            }
        });


        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        topBar.setBottom(searchField);
        BorderPane.setAlignment(title, Pos.CENTER);

        mainPane.setTop(topBar);
        mainPane.setCenter(scrollPane);
    }
    public void ProcessorCoolingSystemsCard()
    {
        this.title = new Label("Системы охлаждения процессора");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);

        buttonTop.getChildren().addAll(
                 backButton
        );

        // Обработчик события для кнопки "назад"
        backButton.setOnAction(e -> {
            exitButton();
        });


        TilePane productCardsUser = new TilePane();
        productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
        productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
        productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок
        productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");

        // динамическое добавление карточк вроде работает
        Database db = new Database();
        productCardsUser.getChildren().clear();
        List<ProcessorCoolingSystems> PCSs = db.getAllProcessorCoolingSystems();

        for (ProcessorCoolingSystems pcs : PCSs) {
            ProductCard newProductCard = new ProductCard(stage);
            Image image = productDataManagementButtonFX.MyImage.getImageByName(pcs.getImageFileName());

            // creatingCard поменять на свою
            newProductCard.creatingCardProcessorCoolingSystems(pcs.getBrand(), pcs.getModel(), pcs.getCoolingType(), pcs.getFansNumber(), pcs.getFanSize(), pcs.getFanRotationSpeed(), pcs.getMaxHeatDissipationProcessor(), pcs.getPrice(), image, productCardsUser);

            // Проверяем, нет ли уже такой карточки в productCardsUser перед добавлением
            if (!productCardsUser.getChildren().contains(newProductCard)) {
                Platform.runLater(() -> productCardsUser.getChildren().add(newProductCard));
            }
        }


        VBox productCardsWrapper = new VBox(productCardsUser);
        productCardsWrapper.setMinHeight(Region.USE_PREF_SIZE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productCardsWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saveOriginalProductCards(productCardsUser);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productCardsUser.getChildren().clear();
                for (ProductCard productCard : originalProductCards) {
                    productCardsUser.getChildren().add(productCard);
                    productCard.setVisible(true);
                }

            } else {
                filterCards(newValue, productCardsUser);
                Platform.runLater(() -> scrollPane.setVvalue(0));
            }
        });

        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        topBar.setBottom(searchField);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setCenter(scrollPane);
        mainPane.setTop(topBar);
    }
    public void HousingCoolingSystemsCard()
    {
        this.title = new Label("Системы охлаждения корпуса");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);

        buttonTop.getChildren().addAll(
                backButton
        );

        // Обработчик события для кнопки "назад"
        backButton.setOnAction(e -> {
            exitButton();
        });

        TilePane productCardsUser = new TilePane();
        productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
        productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
        productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок
        productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");

        // динамическое добавление карточк вроде работает
        Database db = new Database();
        productCardsUser.getChildren().clear();
        List<HousingCoolingSystems> HCSs = db.getAllHousingCoolingSystems();

        for (HousingCoolingSystems hcs : HCSs) {
            ProductCard newProductCard = new ProductCard(stage);
            Image image = productDataManagementButtonFX.MyImage.getImageByName(hcs.getImageFileName());

            // creatingCard поменять на свою
            newProductCard.creatingCardHousingCoolingSystems(hcs.getBrand(), hcs.getModel(), hcs.getCoolingType(), hcs.getFansNumber(), hcs.getFanSize(), hcs.getFanRotationSpeed(), hcs.getFanLife(), hcs.getPrice(), image, productCardsUser);

            // Проверяем, нет ли уже такой карточки в productCardsUser перед добавлением
            if (!productCardsUser.getChildren().contains(newProductCard)) {
                Platform.runLater(() -> productCardsUser.getChildren().add(newProductCard));
            }
        }


        VBox productCardsWrapper = new VBox(productCardsUser);
        productCardsWrapper.setMinHeight(Region.USE_PREF_SIZE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productCardsWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saveOriginalProductCards(productCardsUser);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productCardsUser.getChildren().clear();
                for (ProductCard productCard : originalProductCards) {
                    productCardsUser.getChildren().add(productCard);
                    productCard.setVisible(true);
                }

            } else {
                filterCards(newValue, productCardsUser);
                Platform.runLater(() -> scrollPane.setVvalue(0));
            }
        });

        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        topBar.setBottom(searchField);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setCenter(scrollPane);
        mainPane.setTop(topBar);
    }
    public void RAM()
    {
        this.title = new Label("Оперативная память");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);

        buttonTop.getChildren().addAll(
                backButton
        );

        // Обработчик события для кнопки "назад"
        backButton.setOnAction(e -> {
            exitButton();
        });

        TilePane productCardsUser = new TilePane();
        productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
        productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
        productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок
        productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");

        // динамическое добавление карточк вроде работает
        Database db = new Database();
        productCardsUser.getChildren().clear();
        List<RAM> RAMs = db.getAllRAM();

        for (RAM ram : RAMs) {
            ProductCard newProductCard = new ProductCard(stage);
            Image image = productDataManagementButtonFX.MyImage.getImageByName(ram.getImageFileName());

            // creatingCard поменять на свою
            newProductCard.creatingCardRAM(ram.getBrand(), ram.getModel(), ram.getFormFactor(), ram.getMemoryType(), ram.getVolume(), ram.getClockFrequency(), ram.getThroughputCapacity(), ram.getPrice(), image, productCardsUser);

            // Проверяем, нет ли уже такой карточки в productCardsUser перед добавлением
            if (!productCardsUser.getChildren().contains(newProductCard)) {
                Platform.runLater(() -> productCardsUser.getChildren().add(newProductCard));
            }
        }


        VBox productCardsWrapper = new VBox(productCardsUser);
        productCardsWrapper.setMinHeight(Region.USE_PREF_SIZE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productCardsWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saveOriginalProductCards(productCardsUser);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productCardsUser.getChildren().clear();
                for (ProductCard productCard : originalProductCards) {
                    productCardsUser.getChildren().add(productCard);
                    productCard.setVisible(true);
                }

            } else {
                filterCards(newValue, productCardsUser);
                Platform.runLater(() -> scrollPane.setVvalue(0));
            }
        });

        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        topBar.setBottom(searchField);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setCenter(scrollPane);
        mainPane.setTop(topBar);
    }
    public void VideoCardsCard()
    {
        this.title = new Label("Видеокарты");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);

        buttonTop.getChildren().addAll(
                backButton
        );

        // Обработчик события для кнопки "назад"
        backButton.setOnAction(e -> {
            exitButton();
        });

        TilePane productCardsUser = new TilePane();
        productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
        productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
        productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок
        productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");

        // динамическое добавление карточк вроде работает
        Database db = new Database();
        productCardsUser.getChildren().clear();
        List<VideoCards> VCs = db.getAllVideoCards();

        for (VideoCards vc : VCs) {
            ProductCard newProductCard = new ProductCard(stage);
            Image image = productDataManagementButtonFX.MyImage.getImageByName(vc.getImageFileName());

            // creatingCard поменять на свою
            newProductCard.creatingCardVideoCards(vc.getBrand(), vc.getModel(), vc.getVideoChipset(), vc.getGpf(), vc.getMaxResolution(), vc.getVideoMemory(), vc.getPrice(), image, productCardsUser);

            // Проверяем, нет ли уже такой карточки в productCardsUser перед добавлением
            if (!productCardsUser.getChildren().contains(newProductCard)) {
                Platform.runLater(() -> productCardsUser.getChildren().add(newProductCard));
            }
        }


        VBox productCardsWrapper = new VBox(productCardsUser);
        productCardsWrapper.setMinHeight(Region.USE_PREF_SIZE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productCardsWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saveOriginalProductCards(productCardsUser);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productCardsUser.getChildren().clear();
                for (ProductCard productCard : originalProductCards) {
                    productCardsUser.getChildren().add(productCard);
                    productCard.setVisible(true);
                }

            } else {
                filterCards(newValue, productCardsUser);
                Platform.runLater(() -> scrollPane.setVvalue(0));
            }
        });

        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        topBar.setBottom(searchField);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setCenter(scrollPane);
        mainPane.setTop(topBar);
    }
    public void PowerSupplyUnitsCard()
    {
        this.title = new Label("Блоки питания");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);

        buttonTop.getChildren().addAll(
                backButton
        );

        // Обработчик события для кнопки "назад"
        backButton.setOnAction(e -> {
            exitButton();
        });

        TilePane productCardsUser = new TilePane();
        productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
        productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
        productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок
        productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");

        // динамическое добавление карточк вроде работает
        Database db = new Database();
        productCardsUser.getChildren().clear();
        List<PowerSupplyUnits> PSUs = db.getAllPowerSupplyUnits();

        for (PowerSupplyUnits psu : PSUs) {
            ProductCard newProductCard = new ProductCard(stage);
            Image image = productDataManagementButtonFX.MyImage.getImageByName(psu.getImageFileName());

            // creatingCard поменять на свою
            newProductCard.creatingCardPowerSupplyUnits(psu.getBrand(), psu.getModel(), psu.getFormFactor(), psu.getPower(), psu.getMinInpVolt(), psu.getMaxInpVolt(), psu.getPrice(), image, productCardsUser);

            // Проверяем, нет ли уже такой карточки в productCardsUser перед добавлением
            if (!productCardsUser.getChildren().contains(newProductCard)) {
                Platform.runLater(() -> productCardsUser.getChildren().add(newProductCard));
            }
        }


        VBox productCardsWrapper = new VBox(productCardsUser);
        productCardsWrapper.setMinHeight(Region.USE_PREF_SIZE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productCardsWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saveOriginalProductCards(productCardsUser);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productCardsUser.getChildren().clear();
                for (ProductCard productCard : originalProductCards) {
                    productCardsUser.getChildren().add(productCard);
                    productCard.setVisible(true);
                }

            } else {
                filterCards(newValue, productCardsUser);
                Platform.runLater(() -> scrollPane.setVvalue(0));
            }
        });

        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        topBar.setBottom(searchField);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setCenter(scrollPane);
        mainPane.setTop(topBar);
    }
    public void EnclosuresCard()
    {
        this.title = new Label("Корпуса");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);

        buttonTop.getChildren().addAll(
                backButton
        );

        // Обработчик события для кнопки "назад"
        backButton.setOnAction(e -> {
            exitButton();
        });

        TilePane productCardsUser = new TilePane();
        productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
        productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
        productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок
        productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");

        // динамическое добавление карточк вроде работает
        Database db = new Database();
        productCardsUser.getChildren().clear();
        List<Enclosures> ENC = db.getAllEnclosures();

        for (Enclosures enc : ENC) {
            ProductCard newProductCard = new ProductCard(stage);
            Image image = productDataManagementButtonFX.MyImage.getImageByName(enc.getImageFileName());

            // creatingCard поменять на свою
            newProductCard.creatingCardEnclosures(enc.getBrand(), enc.getModel(), enc.getFormFactor(), enc.getSizeStandard(), enc.getBPLocation(), enc.getPrice(), image, productCardsUser);

            // Проверяем, нет ли уже такой карточки в productCardsUser перед добавлением
            if (!productCardsUser.getChildren().contains(newProductCard)) {
                Platform.runLater(() -> productCardsUser.getChildren().add(newProductCard));
            }
        }


        VBox productCardsWrapper = new VBox(productCardsUser);
        productCardsWrapper.setMinHeight(Region.USE_PREF_SIZE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productCardsWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saveOriginalProductCards(productCardsUser);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productCardsUser.getChildren().clear();
                for (ProductCard productCard : originalProductCards) {
                    productCardsUser.getChildren().add(productCard);
                    productCard.setVisible(true);
                }

            } else {
                filterCards(newValue, productCardsUser);
                Platform.runLater(() -> scrollPane.setVvalue(0));
            }
        });

        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        topBar.setBottom(searchField);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setCenter(scrollPane);
        mainPane.setTop(topBar);
    }
    public void HDDCard()
    {
        this.title = new Label("Жесткие диски");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);

        buttonTop.getChildren().addAll(
                backButton
        );

        // Обработчик события для кнопки "назад"
        backButton.setOnAction(e -> {
            exitButton();
        });

        TilePane productCardsUser = new TilePane();
        productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
        productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
        productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок
        productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");

        // динамическое добавление карточк вроде работает
        Database db = new Database();
        productCardsUser.getChildren().clear();
        List<HDD> HDDs = db.getAllHDD();

        for (HDD hdd : HDDs) {
            ProductCard newProductCard = new ProductCard(stage);
            Image image = productDataManagementButtonFX.MyImage.getImageByName(hdd.getImageFileName());

            // creatingCard поменять на свою
            newProductCard.creatingCardHDD(hdd.getBrand(), hdd.getModel(), hdd.getFormFactor(), hdd.getType(), hdd.getCapacityStorage(), hdd.getSpindleRotationSpeed(), hdd.getPrice(), image, productCardsUser);

            // Проверяем, нет ли уже такой карточки в productCardsUser перед добавлением
            if (!productCardsUser.getChildren().contains(newProductCard)) {
                Platform.runLater(() -> productCardsUser.getChildren().add(newProductCard));
            }
        }


        VBox productCardsWrapper = new VBox(productCardsUser);
        productCardsWrapper.setMinHeight(Region.USE_PREF_SIZE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productCardsWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saveOriginalProductCards(productCardsUser);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productCardsUser.getChildren().clear();
                for (ProductCard productCard : originalProductCards) {
                    productCardsUser.getChildren().add(productCard);
                    productCard.setVisible(true);
                }

            } else {
                filterCards(newValue, productCardsUser);
                Platform.runLater(() -> scrollPane.setVvalue(0));
            }
        });

        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        topBar.setBottom(searchField);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setCenter(scrollPane);
        mainPane.setTop(topBar);
    }
    public void SSDCard()
    {
        this.title = new Label("SSD накопители");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);

        buttonTop.getChildren().addAll(
                backButton
        );

        // Обработчик события для кнопки "назад"
        backButton.setOnAction(e -> {
            exitButton();
        });

        TilePane productCardsUser = new TilePane();
        productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
        productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
        productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок
        productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");

        // динамическое добавление карточк вроде работает
        Database db = new Database();
        productCardsUser.getChildren().clear();
        List<SSD> SSDs = db.getAllSSD();

        for (SSD ssd : SSDs) {
            ProductCard newProductCard = new ProductCard(stage);
            Image image = productDataManagementButtonFX.MyImage.getImageByName(ssd.getImageFileName());

            // creatingCard поменять на свою
            newProductCard.creatingCardSSD(ssd.getBrand(), ssd.getModel(), ssd.getFormFactor(), ssd.getType(), ssd.getCapacityStorage(), ssd.getMaxReadingSpeed(), ssd.getMaxRecordingSpeed(), ssd.getPrice(), image, productCardsUser);

            // Проверяем, нет ли уже такой карточки в productCardsUser перед добавлением
            if (!productCardsUser.getChildren().contains(newProductCard)) {
                Platform.runLater(() -> productCardsUser.getChildren().add(newProductCard));
            }
        }


        VBox productCardsWrapper = new VBox(productCardsUser);
        productCardsWrapper.setMinHeight(Region.USE_PREF_SIZE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productCardsWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saveOriginalProductCards(productCardsUser);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productCardsUser.getChildren().clear();
                for (ProductCard productCard : originalProductCards) {
                    productCardsUser.getChildren().add(productCard);
                    productCard.setVisible(true);
                }

            } else {
                filterCards(newValue, productCardsUser);
                Platform.runLater(() -> scrollPane.setVvalue(0));
            }
        });

        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        topBar.setBottom(searchField);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setCenter(scrollPane);
        mainPane.setTop(topBar);
    }
    public void MotherboardsCard()
    {
        this.title = new Label("Материнские платы");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);

        buttonTop.getChildren().addAll(
                backButton
        );

        // Обработчик события для кнопки "назад"
        backButton.setOnAction(e -> {
            exitButton();
        });

        TilePane productCardsUser = new TilePane();
        productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
        productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
        productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок
        productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");

        // динамическое добавление карточк вроде работает
        Database db = new Database();
        productCardsUser.getChildren().clear();
        List<Motherboards> MB = db.getAllMotherboards();

        for (Motherboards mb : MB) {
            ProductCard newProductCard = new ProductCard(stage);
            Image image = productDataManagementButtonFX.MyImage.getImageByName(mb.getImageFileName());

            // creatingCard поменять на свою
            newProductCard.creatingCardMotherboards(mb.getBrand(), mb.getModel(), mb.getFormFactor(), mb.getSocket(), mb.getChipset(), mb.getMemoryType(), mb.getPrice(), image, productCardsUser);

            // Проверяем, нет ли уже такой карточки в productCardsUser перед добавлением
            if (!productCardsUser.getChildren().contains(newProductCard)) {
                Platform.runLater(() -> productCardsUser.getChildren().add(newProductCard));
            }
        }


        VBox productCardsWrapper = new VBox(productCardsUser);
        productCardsWrapper.setMinHeight(Region.USE_PREF_SIZE);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productCardsWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        saveOriginalProductCards(productCardsUser);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productCardsUser.getChildren().clear();
                for (ProductCard productCard : originalProductCards) {
                    productCardsUser.getChildren().add(productCard);
                    productCard.setVisible(true);
                }

            } else {
                filterCards(newValue, productCardsUser);
                Platform.runLater(() -> scrollPane.setVvalue(0));
            }
        });

        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        topBar.setBottom(searchField);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setCenter(scrollPane);
        mainPane.setTop(topBar);
    }

    // Для Root пользователя ----------------------------------------------------------------------------

    public void productDataManagement()
    {
        this.title = new Label("Управление товарами");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        backButton.setOnAction(e -> {
            exitButton();
        });

        // добавление продукта
        final boolean[] isAddProductFormOpen = {false};
        Button addProductButton = new Button("Add Product");
        addProductButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");

        addProductButton.setOnAction(e -> {

            VBox contentPaneAddProduct = new VBox(10);

            if (!isAddProductFormOpen[0])
            {
                productDataManagementButtonFX pdmbFX = new productDataManagementButtonFX(contentPaneAddProduct);
                pdmbFX.addProduct();
                isAddProductFormOpen[0] = true;
            }

            mainPane.setCenter(contentPaneAddProduct);
            isAddProductFormOpen[0] = false;
        });

        // удаление продукта
        final boolean[] isDeleteProductFormOpen = {false};
        Button deleteProductButton = new Button("Delete Product");
        deleteProductButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");

        deleteProductButton.setOnAction(e -> {

            VBox contentPaneDeleteProduct = new VBox(10);

            if (!isDeleteProductFormOpen[0])
            {
                productDataManagementButtonFX pdmbFX = new productDataManagementButtonFX(contentPaneDeleteProduct);
                pdmbFX.deleteProduct();
                isDeleteProductFormOpen[0] = true;
            }

            mainPane.setCenter(contentPaneDeleteProduct);
            isDeleteProductFormOpen[0] = false;
        });

        // обновление данных продукта
        final boolean[] isUpdateProductFormOpen = {false};
        Button updateProductButton = new Button("Update Product");
        updateProductButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");

        updateProductButton.setOnAction(e -> {

            VBox contentPaneUpdateProduct = new VBox(10);

            if (!isUpdateProductFormOpen[0])
            {
                productDataManagementButtonFX pdmbFX = new productDataManagementButtonFX(contentPaneUpdateProduct);
                pdmbFX.updateProduct();
                isUpdateProductFormOpen[0] = true;
            }

            mainPane.setCenter(contentPaneUpdateProduct);
            isUpdateProductFormOpen[0] = false;
        });

        // отображение таблицы дб продукта
        final boolean[] isViewProductFormOpen = {false};
        Button viewProductDataButton = new Button("View Product");
        viewProductDataButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");

        viewProductDataButton.setOnAction(e -> {

            VBox contentPaneViewProduct = new VBox(10);

            if (!isViewProductFormOpen[0])
            {
                productDataManagementButtonFX pdmbFX = new productDataManagementButtonFX(contentPaneViewProduct);
                pdmbFX.viewProductData();
                isViewProductFormOpen[0] = true;
            }

            mainPane.setCenter(contentPaneViewProduct);
            isViewProductFormOpen[0] = false;
        });


        HBox buttonBar = new HBox(30);
        buttonBar.getChildren().addAll(addProductButton, deleteProductButton, updateProductButton, viewProductDataButton);
        buttonBar.setAlignment(Pos.TOP_CENTER);

        BorderPane topBar = new BorderPane();
        topBar.setCenter(title);
        topBar.setRight(backButton);
        topBar.setBottom(buttonBar);

        mainPane.setTop(topBar);
    }

    public void userDataManagement()
    {
        this.title = new Label("Управление данными пользователя");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        backButton.setOnAction(e -> {
            exitButton();
        });

        // добавление пользователя в бд

        final boolean[] isAddUserFormOpen = {false};
        Button addUserButton = new Button("Add User");
        addUserButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");

        addUserButton.setOnAction(e -> {

            VBox contentPaneAddUser = new VBox(10);

            if (!isAddUserFormOpen[0])
            {
                userDataManagementButtonFX udmbFX = new userDataManagementButtonFX(contentPaneAddUser);
                udmbFX.addUser();
                isAddUserFormOpen[0] = true;
            }

            mainPane.setCenter(contentPaneAddUser);
            isAddUserFormOpen[0] = false;
        });


        // удаление пользователя из бд

        final boolean[] deleteUserFormOpen = {false};
        Button deleteUserButton = new Button("Delete User");
        deleteUserButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");
        deleteUserButton.setOnAction(e -> {

            VBox contentPaneDeleteUser = new VBox(10);

            if(!deleteUserFormOpen[0])
            {
                userDataManagementButtonFX udmbFX = new userDataManagementButtonFX(contentPaneDeleteUser);
                udmbFX.deleteUser();
                deleteUserFormOpen[0] = true;
            }

            mainPane.setCenter(contentPaneDeleteUser);
            deleteUserFormOpen[0] = false;
        });



        // изменение пользователя в бд
        final boolean[] updateUserFormOpen = {false};
        Button updateUserButton = new Button("Update User");
        updateUserButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");
        updateUserButton.setOnAction(e -> {

            VBox contentPaneDeleteUser = new VBox(10);

            if(!updateUserFormOpen[0])
            {
                userDataManagementButtonFX udmFX = new userDataManagementButtonFX(contentPaneDeleteUser);
                udmFX.updateUser();
                updateUserFormOpen[0] = true;
            }

            mainPane.setCenter(contentPaneDeleteUser);
            updateUserFormOpen[0] = false;
        });



        // вывод бд users
        final boolean[] viewUserFormOpen = {false};
        Button viewUserDataButton = new Button("View User Data");
        viewUserDataButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");
        viewUserDataButton.setOnAction(e -> {

            VBox contentPaneDeleteUser = new VBox(10);

            if(!viewUserFormOpen[0])
            {
                userDataManagementButtonFX udmFX = new userDataManagementButtonFX(contentPaneDeleteUser);
                udmFX.viewUserData();
                viewUserFormOpen[0] = true;
            }

            mainPane.setCenter(contentPaneDeleteUser);
            viewUserFormOpen[0] = false;
        });


        HBox buttonBar = new HBox(30);
        buttonBar.getChildren().addAll(addUserButton, deleteUserButton, updateUserButton, viewUserDataButton);
        buttonBar.setAlignment(Pos.TOP_CENTER);

        BorderPane topBar = new BorderPane();
        topBar.setCenter(title);
        topBar.setRight(backButton);
        topBar.setBottom(buttonBar);


        mainPane.setTop(topBar);
    }

    // Для analyst пользователя ----------------------------------------------------------------------------

    public void analystDataManagement()
    {
        this.title = new Label("Статистика");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button backButton = new Button("Назад");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        backButton.setOnAction(e -> {
            exitButton();
        });


        // Статистика разделов товаров
        final boolean[] isDeleteProductFormOpen = {false};
        Button sectionStatisticsButton = new Button("Section statistics");
        sectionStatisticsButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");

        sectionStatisticsButton.setOnAction(e -> {

            VBox contentPanesectionStatistics = new VBox(10);

            if (!isDeleteProductFormOpen[0])
            {
                statisticsFX sfx = new statisticsFX(contentPanesectionStatistics);
                sfx.statisticsProductSections();
                isDeleteProductFormOpen[0] = true;
            }

            mainPane.setCenter(contentPanesectionStatistics);
            isDeleteProductFormOpen[0] = false;
        });

        // Статистика пользователей
        final boolean[] isUpdateProductFormOpen = {false};
        Button userStatisticsButton = new Button("User statistics");
        userStatisticsButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");

        userStatisticsButton.setOnAction(e -> {

            VBox contentPaneUserStatistics = new VBox(10);

            if (!isUpdateProductFormOpen[0])
            {
                statisticsFX sfx = new statisticsFX(contentPaneUserStatistics);
                sfx.statisticsUser();
                isUpdateProductFormOpen[0] = true;
            }

            mainPane.setCenter(contentPaneUserStatistics);
            isUpdateProductFormOpen[0] = false;
        });

        // Статистика товаров в выбранном разделе
        final boolean[] isViewProductFormOpen = {false};
        Button productStatisticsButton = new Button("Price statistics");
        productStatisticsButton.setStyle("-fx-background-color: #783302; -fx-text-fill: white; -fx-font-size: 16px;");

        productStatisticsButton.setOnAction(e -> {

            VBox contentPaneProductStatistics = new VBox(10);

            if (!isViewProductFormOpen[0])
            {
                statisticsFX sfx = new statisticsFX(contentPaneProductStatistics);
                sfx.statisticsProductInSelectedSection();
                isViewProductFormOpen[0] = true;
            }

            mainPane.setCenter(contentPaneProductStatistics);
            isViewProductFormOpen[0] = false;
        });


        HBox buttonBar = new HBox(30);
        buttonBar.getChildren().addAll(sectionStatisticsButton, userStatisticsButton, productStatisticsButton);
        buttonBar.setAlignment(Pos.TOP_CENTER);

        BorderPane topBar = new BorderPane();
        topBar.setCenter(title);
        topBar.setRight(backButton);
        topBar.setBottom(buttonBar);

        mainPane.setTop(topBar);
    }

// ----------------------------------------------------------------------------

    private void exitButton()
    {
        stage.setScene(new getPriwius().get());
    }

    public BorderPane getMainPane() {
        return mainPane;
    }


    // методы реализации поисковой строки
    private void reorderCards(TilePane tilePane, List<ProductCard> remainingCards) {
        tilePane.getChildren().clear(); // Очищаем TilePane

        // Добавляем оставшиеся карточки в том же порядке
        for (ProductCard card : remainingCards) {
            tilePane.getChildren().add(card);
        }
    }

    private void filterCards(String searchText, TilePane productCardsUser) {
        ObservableList<Node> productCardsChildren = productCardsUser.getChildren();
        List<ProductCard> remainingCards = new ArrayList<>();

        for (Node node : productCardsChildren) {
            ProductCard productCard = (ProductCard) node;

            if (productCard.getProductName().toLowerCase().contains(searchText.toLowerCase())) {
                productCard.setVisible(true);
                remainingCards.add(productCard); // Добавляем видимую карточку в список remainingCards
            } else {
                productCard.setVisible(false);
            }
        }

        reorderCards(productCardsUser, remainingCards);
    }

    private void saveOriginalProductCards(TilePane productCardsUser) {
        originalProductCards.clear();

        for (Node node : productCardsUser.getChildren()) {
            if (node instanceof ProductCard) {
                ProductCard productCard = (ProductCard) node;
                originalProductCards.add(productCard);
            }
        }
    }
}
