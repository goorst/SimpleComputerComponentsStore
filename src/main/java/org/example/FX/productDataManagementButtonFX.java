package org.example.FX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.example.BD.Database;
import org.example.demo.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class productDataManagementButtonFX {

    private VBox contentPane;
    private Label messageLabel = new Label();
    private ComboBox<String> comboBox;
    private File selectedFile;
    private String imageFileName;

    public productDataManagementButtonFX(VBox contentPane){
        this.contentPane = contentPane;

        ObservableList<String> productCategories = FXCollections.observableArrayList(
                "ЦП",
                "Системы охлаждения процессора",
                "Системы охлаждения корпуса",
                "ОЗ",
                "Видеокарты",
                "БП",
                "Корпуса",
                "HDD",
                "SSD",
                "Материнские платы"
        );
        comboBox = new ComboBox<>(productCategories);
        comboBox.setPromptText("Выберите категорию продукта");
    }

    public String getImageFileName(){return imageFileName;}

    // добавление продукта в бд и (?созадние карточки?) ---------ECC------------------------------
    public void addProduct()
    {
        if (contentPane == null)
        {
            System.out.println("ERROR!!");
        }
        else
        {
            comboBox.setOnAction(event -> {
                String selectedCategory = comboBox.getValue();

                if(selectedCategory.equals("ЦП"))
                {
                    addProductCP();
                }
                else if(selectedCategory.equals("Системы охлаждения процессора"))
                {
                    addProductProcessorCoolingSystems();
                }
                else if(selectedCategory.equals("Системы охлаждения корпуса"))
                {
                    addProductHousingCoolingSystems();
                }
                else if(selectedCategory.equals("ОЗ"))
                {
                    addProductRAM();
                }
                else if(selectedCategory.equals("Видеокарты"))
                {
                    addProductVideoCards();
                }
                else if(selectedCategory.equals("БП"))
                {
                    addProductPowerSupplyUnits();
                }
                else if(selectedCategory.equals("Корпуса"))
                {
                    addProductEnclosures();
                }
                else if(selectedCategory.equals("HDD"))
                {
                    addProductHDD();
                }
                else if(selectedCategory.equals("SSD"))
                {
                    addProductSSD();
                }
                else if(selectedCategory.equals("Материнские платы"))
                {
                    addProductMotherboards();
                }
                else
                    System.out.println("error");

            });


            contentPane.getChildren().add(comboBox);
            contentPane.setAlignment(Pos.CENTER);
        }
    }
    private void addProductCP()
    {
        int startClearIndex = contentPane.getChildren().indexOf(comboBox) + 1;
        contentPane.getChildren().remove(startClearIndex, contentPane.getChildren().size());

        TextField brandField = new TextField();
        brandField.setMaxWidth(200);
        TextField modelField = new TextField();
        modelField.setMaxWidth(200);
        TextField coreNameField = new TextField();
        coreNameField.setMaxWidth(200);
        TextField coresNumberField = new TextField();
        coresNumberField.setMaxWidth(200);
        TextField socketField = new TextField();
        socketField.setMaxWidth(200);
        TextField frequencyField = new TextField();
        frequencyField.setMaxWidth(200);
        TextField threadsNumberField = new TextField();
        threadsNumberField.setMaxWidth(200);
        TextField priceField = new TextField();
        priceField.setMaxWidth(200);

        Label brandLabel = new Label("Бренд:");
        brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label modelLabel = new Label("Модель:");
        modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label coreNameLabel = new Label("Имя ядра:");
        coreNameLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label coresNumberLabel = new Label("Количество ядер:");
        coresNumberLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label socketLabel = new Label("Сокет:");
        socketLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label frequencyLabel = new Label("Частота:");
        frequencyLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label threadsNumberLabel = new Label("Количество потоков:");
        threadsNumberLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label priceLabel = new Label("Цена:");
        priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");



        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        Button selectFileButton = new Button("Выбрать изображение");
        selectFileButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label filePathLabel = new Label();

        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
                MyImage.loadImageIntoResources(selectedFile);
            }
        });


        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        applyButton.setOnAction(event -> {
            // Perform actions based on the input values
            String brand = brandField.getText();
            String model = modelField.getText();
            String coreName = coreNameField.getText();
            int coreNumber = Integer.parseInt(coresNumberField.getText());
            String socet = socketField.getText();
            String frequency = frequencyField.getText();
            int threadsNumber = Integer.parseInt(threadsNumberField.getText());
            String price = priceField.getText();

            Database db = new Database();

            CPs cp = new CPs();
            cp.setBrand(brand);
            cp.setModel(model);
            cp.setCoreName(coreName);
            cp.setCoreNumber(coreNumber);
            cp.setSocket(socet);
            cp.setFrequency(frequency);
            cp.setThreadsNumber(threadsNumber);
            cp.setPrice(price);
            cp.setImageFileName(MyImage.imageFilePath);

            db.addCPsToDatabase(cp);

            brandField.clear();
            modelField.clear();
            coreNameField.clear();
            coresNumberField.clear();
            socketField.clear();
            frequencyField.clear();
            threadsNumberField.clear();
            priceField.clear();
            filePathLabel.setText("");

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("(-_-) CP successfully added (-_-)");
        });


        VBox panel = new VBox(2);
        panel.getChildren().addAll(
                brandLabel, brandField,
                modelLabel, modelField,
                coreNameLabel, coreNameField,
                coresNumberLabel, coresNumberField,
                socketLabel, socketField,
                frequencyLabel, frequencyField,
                threadsNumberLabel, threadsNumberField,
                priceLabel, priceField,
                selectFileButton, filePathLabel
        );
        panel.setAlignment(Pos.CENTER);

        contentPane.getChildren().addAll(panel, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }
    private void addProductProcessorCoolingSystems()
    {
        int startClearIndex = contentPane.getChildren().indexOf(comboBox) + 1;
        contentPane.getChildren().remove(startClearIndex, contentPane.getChildren().size());

        TextField brandField = new TextField();
        brandField.setMaxWidth(200);
        TextField modelField = new TextField();
        modelField.setMaxWidth(200);
        TextField coolingTypeField = new TextField();
        coolingTypeField.setMaxWidth(200);
        TextField fansNumberField = new TextField();
        fansNumberField.setMaxWidth(200);
        TextField fanSizeField = new TextField();
        fanSizeField.setMaxWidth(200);
        TextField fanRotationSpeedField = new TextField();
        fanRotationSpeedField.setMaxWidth(200);
        TextField maxHeatDissipationProcessorField = new TextField();
        maxHeatDissipationProcessorField.setMaxWidth(200);
        TextField priceField = new TextField();
        priceField.setMaxWidth(200);

        Label brandLabel = new Label("Бренд:");
        brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label modelLabel = new Label("Модель:");
        modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label coolingTypeLabel = new Label("Тип охлаждения:");
        coolingTypeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label fansNumberLabel = new Label("Количество вентиляторов:");
        fansNumberLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label fanSizeLabel = new Label("Размер вентилятора:");
        fanSizeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label fanRotationSpeedLabel = new Label("Скорость вращения вентилятора:");
        fanRotationSpeedLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label maxHeatDissipationProcessorLabel = new Label("Максимальное тепловыделение процессора:");
        maxHeatDissipationProcessorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label priceLabel = new Label("Цена:");
        priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        Button selectFileButton = new Button("Выбрать изображение");
        selectFileButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label filePathLabel = new Label();

        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
                MyImage.loadImageIntoResources(selectedFile);
            }
        });

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        applyButton.setOnAction(e -> {

            String brand = brandField.getText();
            String model = modelField.getText();
            String coolingType = coolingTypeField.getText();
            int fansNumber = Integer.parseInt(fansNumberField.getText());
            String fanSize = fanSizeField.getText();
            String fanRotationSpeed = fanRotationSpeedField.getText();
            String maxHeatDissipationProcessor = maxHeatDissipationProcessorField.getText();
            String price = priceField.getText();

            Database db = new Database();

            ProcessorCoolingSystems PCS = new ProcessorCoolingSystems();

            PCS.setBrand(brand);
            PCS.setModel(model);
            PCS.setCoolingType(coolingType);
            PCS.setFansNumber(fansNumber);
            PCS.setFanSize(fanSize);
            PCS.setFanRotationSpeed(fanRotationSpeed);
            PCS.setMaxHeatDissipationProcessor(maxHeatDissipationProcessor);
            PCS.setPrice(price);
            PCS.setImageFileName(MyImage.imageFilePath);

            db.addProcessorCoolingSystemsToDatabase(PCS);

            brandField.clear();
            modelField.clear();
            coolingTypeField.clear();
            fansNumberField.clear();
            fanSizeField.clear();
            fanRotationSpeedField.clear();
            maxHeatDissipationProcessorField.clear();
            priceField.clear();
            filePathLabel.setText("");

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("(-_-) ProcessorCoolingSystems successfully added (-_-)");
        });


        VBox panel = new VBox();
        panel.getChildren().addAll(
                brandLabel, brandField,
                modelLabel, modelField,
                coolingTypeLabel, coolingTypeField,
                fansNumberLabel, fansNumberField,
                fanSizeLabel, fanSizeField,
                fanRotationSpeedLabel, fanRotationSpeedField,
                maxHeatDissipationProcessorLabel, maxHeatDissipationProcessorField,
                priceLabel, priceField,
                selectFileButton, filePathLabel
        );
        panel.setAlignment(Pos.CENTER);


        contentPane.getChildren().addAll(panel, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }
    private void addProductHousingCoolingSystems()
    {
        int startClearIndex = contentPane.getChildren().indexOf(comboBox) + 1;
        contentPane.getChildren().remove(startClearIndex, contentPane.getChildren().size());

        TextField brandField = new TextField();
        brandField.setMaxWidth(200);
        TextField modelField = new TextField();
        modelField.setMaxWidth(200);
        TextField coolingTypeField = new TextField();
        coolingTypeField.setMaxWidth(200);
        TextField fansNumberField = new TextField();
        fansNumberField.setMaxWidth(200);
        TextField fanSizeField = new TextField();
        fanSizeField.setMaxWidth(200);
        TextField fanRotationSpeedField = new TextField();
        fanRotationSpeedField.setMaxWidth(200);
        TextField fanLifeField = new TextField();
        fanLifeField.setMaxWidth(200);
        TextField priceField = new TextField();
        priceField.setMaxWidth(200);

        Label brandLabel = new Label("Бренд:");
        brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label modelLabel = new Label("Модель:");
        modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label coolingTypeLabel = new Label("Тип охлаждения:");
        coolingTypeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label fansNumberLabel = new Label("Количество вентиляторов:");
        fansNumberLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label fanSizeLabel = new Label("Размер вентилятора:");
        fanSizeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label fanRotationSpeedLabel = new Label("Скорость вращения вентилятора:");
        fanRotationSpeedLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label fanLifeLabel = new Label("Срок службы вентилятора:");
        fanLifeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label priceLabel = new Label("Цена:");
        priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        Button selectFileButton = new Button("Выбрать изображение");
        selectFileButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label filePathLabel = new Label();

        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
                MyImage.loadImageIntoResources(selectedFile);
            }
        });

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        applyButton.setOnAction(e -> {

            String brand = brandField.getText();
            String model = modelField.getText();
            String coolingType = coolingTypeField.getText();
            int fansNumber = Integer.parseInt(fansNumberField.getText());
            String fanSize = fanSizeField.getText();
            String fanRotationSpeed = fanRotationSpeedField.getText();
            String fanLife = fanLifeField.getText();
            String price = priceField.getText();

            Database db = new Database();

            HousingCoolingSystems HCS = new HousingCoolingSystems();

            HCS.setBrand(brand);
            HCS.setModel(model);
            HCS.setCoolingType(coolingType);
            HCS.setFansNumber(fansNumber);
            HCS.setFanSize(fanSize);
            HCS.setFanRotationSpeed(fanRotationSpeed);
            HCS.setFanLife(fanLife);
            HCS.setPrice(price);
            HCS.setImageFileName(MyImage.imageFilePath);

            db.addHousingCoolingSystemsToDatabase(HCS);

            brandField.clear();
            modelField.clear();
            coolingTypeField.clear();
            fansNumberField.clear();
            fanSizeField.clear();
            fanRotationSpeedField.clear();
            fanLifeField.clear();
            priceField.clear();
            filePathLabel.setText("");

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("(-_-) HousingCoolingSystems successfully added (-_-)");
        });


        VBox panel = new VBox();
        panel.getChildren().addAll(
                brandLabel, brandField,
                modelLabel, modelField,
                coolingTypeLabel, coolingTypeField,
                fansNumberLabel, fansNumberField,
                fanSizeLabel, fanSizeField,
                fanRotationSpeedLabel, fanRotationSpeedField,
                fanLifeLabel, fanLifeField,
                priceLabel, priceField,
                selectFileButton, filePathLabel
        );
        panel.setAlignment(Pos.CENTER);


        contentPane.getChildren().addAll(panel, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }
    private void addProductVideoCards()
    {
        int startClearIndex = contentPane.getChildren().indexOf(comboBox) + 1;
        contentPane.getChildren().remove(startClearIndex, contentPane.getChildren().size());

        TextField brandField = new TextField();
        brandField.setMaxWidth(200);
        TextField modelField = new TextField();
        modelField.setMaxWidth(200);
        TextField videoChipsetField = new TextField();
        videoChipsetField.setMaxWidth(200);
        TextField gpfField = new TextField();
        gpfField.setMaxWidth(200);
        TextField maxResolutionField = new TextField();
        maxResolutionField.setMaxWidth(200);
        TextField videoMemoryField = new TextField();
        videoMemoryField.setMaxWidth(200);
        TextField priceField = new TextField();
        priceField.setMaxWidth(200);

        Label brandLabel = new Label("Бренд:");
        brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label modelLabel = new Label("Модель:");
        modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label videoChipsetLabel = new Label("Видеочипсет:");
        videoChipsetLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label gpfLabel = new Label("Частота графического процессора:");
        gpfLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label videoMemoryLabel = new Label("Объем видеопамяти:");
        videoMemoryLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label priceLabel = new Label("Цена:");
        priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label maxResolutionLabel = new Label("Максимальное разрешение:");
        maxResolutionLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        Button selectFileButton = new Button("Выбрать изображение");
        selectFileButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label filePathLabel = new Label();

        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
                MyImage.loadImageIntoResources(selectedFile);
            }
        });

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        applyButton.setOnAction(e -> {

            String brand = brandField.getText();
            String model = modelField.getText();
            String videoChipset = videoChipsetField.getText();
            String gpf = gpfField.getText();
            String maxResolution = maxResolutionField.getText();
            String videoMemory = videoMemoryField.getText();
            String price = priceField.getText();

            Database db = new Database();

            VideoCards vc = new VideoCards();

            vc.setBrand(brand);
            vc.setModel(model);
            vc.setVideoChipset(videoChipset);
            vc.setGpf(gpf);
            vc.setMaxResolution(maxResolution);
            vc.setVideoMemory(videoMemory);
            vc.setPrice(price);
            vc.setImageFileName(MyImage.imageFilePath);

            db.addVideoCardsToDatabase(vc);

            brandField.clear();
            modelField.clear();
            videoChipsetField.clear();
            gpfField.clear();
            maxResolutionField.clear();
            videoMemoryField.clear();
            priceField.clear();
            filePathLabel.setText("");

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("(-_-) VideoCards successfully added (-_-)");
        });


        VBox panel = new VBox();
        panel.getChildren().addAll(
                brandLabel, brandField,
                modelLabel, modelField,
                videoChipsetLabel, videoChipsetField,
                gpfLabel, gpfField,
                maxResolutionLabel, maxResolutionField,
                videoMemoryLabel, videoMemoryField,
                priceLabel, priceField,
                selectFileButton, filePathLabel
        );
        panel.setAlignment(Pos.CENTER);


        contentPane.getChildren().addAll(panel, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }
    private void addProductRAM()
    {
        int startClearIndex = contentPane.getChildren().indexOf(comboBox) + 1;
        contentPane.getChildren().remove(startClearIndex, contentPane.getChildren().size());

        TextField brandField = new TextField();
        brandField.setMaxWidth(200);
        TextField modelField = new TextField();
        modelField.setMaxWidth(200);
        TextField formFactorField = new TextField();
        formFactorField.setMaxWidth(200);
        TextField memoryTypeField = new TextField();
        memoryTypeField.setMaxWidth(200);
        TextField volumeField = new TextField();
        volumeField.setMaxWidth(200);
        TextField clockFrequencyField = new TextField();
        clockFrequencyField.setMaxWidth(200);
        TextField throughputCapacityField = new TextField();
        throughputCapacityField.setMaxWidth(200);
        TextField priceField = new TextField();
        priceField.setMaxWidth(200);

        Label brandLabel = new Label("Бренд:");
        brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label modelLabel = new Label("Модель:");
        modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label formFactorLabel = new Label("Форм-фактор:");
        formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label memoryTypeLabel = new Label("Тип памяти:");
        memoryTypeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label volumeLabel = new Label("Объем:");
        volumeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label clockFrequencyLabel = new Label("Тактовая частота:");
        clockFrequencyLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label throughputCapacityLabel = new Label("Пропускная способность:");
        throughputCapacityLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label priceLabel = new Label("Цена:");
        priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        Button selectFileButton = new Button("Выбрать изображение");
        selectFileButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label filePathLabel = new Label();

        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
                MyImage.loadImageIntoResources(selectedFile);
            }
        });

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        applyButton.setOnAction(e -> {

            String brand = brandField.getText();
            String model = modelField.getText();
            String formFactor = formFactorField.getText();
            String memoryType = memoryTypeField.getText();
            String volume = volumeField.getText();
            String clockFrequency = clockFrequencyField.getText();
            String throughputCapacity = throughputCapacityField.getText();
            String price = priceField.getText();

            Database db = new Database();

            RAM ram = new RAM();

            ram.setBrand(brand);
            ram.setModel(model);
            ram.setFormFactor(formFactor);
            ram.setMemoryType(memoryType);
            ram.setVolume(volume);
            ram.setClockFrequency(clockFrequency);
            ram.setThroughputCapacity(throughputCapacity);
            ram.setPrice(price);
            ram.setImageFileName(MyImage.imageFilePath);

            db.addRAMToDatabase(ram);

            brandField.clear();
            modelField.clear();
            formFactorField.clear();
            memoryTypeField.clear();
            volumeField.clear();
            clockFrequencyField.clear();
            throughputCapacityField.clear();
            priceField.clear();
            filePathLabel.setText("");

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("(-_-) RAM successfully added (-_-)");
        });


        VBox panel = new VBox();
        panel.getChildren().addAll(
                brandLabel, brandField,
                modelLabel, modelField,
                formFactorLabel, formFactorField,
                memoryTypeLabel, memoryTypeField,
                volumeLabel, volumeField,
                clockFrequencyLabel, clockFrequencyField,
                throughputCapacityLabel, throughputCapacityField,
                priceLabel, priceField,
                selectFileButton, filePathLabel
        );
        panel.setAlignment(Pos.CENTER);


        contentPane.getChildren().addAll(panel, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }
    private void addProductPowerSupplyUnits()
    {
        int startClearIndex = contentPane.getChildren().indexOf(comboBox) + 1;
        contentPane.getChildren().remove(startClearIndex, contentPane.getChildren().size());

        TextField brandField = new TextField();
        brandField.setMaxWidth(200);
        TextField modelField = new TextField();
        modelField.setMaxWidth(200);
        TextField formFactorField = new TextField();
        formFactorField.setMaxWidth(200);
        TextField powerField = new TextField();
        powerField.setMaxWidth(200);
        TextField minInpVoltField = new TextField();
        minInpVoltField.setMaxWidth(200);
        TextField maxInpVoltField = new TextField();
        maxInpVoltField.setMaxWidth(200);
        TextField priceField = new TextField();
        priceField.setMaxWidth(200);

        Label brandLabel = new Label("Бренд:");
        brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label modelLabel = new Label("Модель:");
        modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label formFactorLabel = new Label("Форм-фактор:");
        formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label powerLabel = new Label("Мощность:");
        powerLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label minInpVoltLabel = new Label("Минимальное входное напряжение:");
        minInpVoltLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label maxInpVoltLabel = new Label("Максимальное входное напряжение:");
        maxInpVoltLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label priceLabel = new Label("Цена:");
        priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        Button selectFileButton = new Button("Выбрать изображение");
        selectFileButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label filePathLabel = new Label();

        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
                MyImage.loadImageIntoResources(selectedFile);
            }
        });

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        applyButton.setOnAction(e -> {

            String brand = brandField.getText();
            String model = modelField.getText();
            String formFactor = formFactorField.getText();
            String power = powerField.getText();
            String minInpVolt = minInpVoltField.getText();
            String maxInpVolt = maxInpVoltField.getText();
            String price = priceField.getText();

            Database db = new Database();

            PowerSupplyUnits psu = new PowerSupplyUnits();

            psu.setBrand(brand);
            psu.setModel(model);
            psu.setFormFactor(formFactor);
            psu.setPower(power);
            psu.setMinInpVolt(minInpVolt);
            psu.setMaxInpVolt(maxInpVolt);
            psu.setPrice(price);
            psu.setImageFileName(MyImage.imageFilePath);

            db.addPowerSupplyUnitsToDatabase(psu);


            brandField.clear();
            modelField.clear();
            formFactorField.clear();
            powerField.clear();
            minInpVoltField.clear();
            maxInpVoltField.clear();
            priceField.clear();
            filePathLabel.setText("");

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("(-_-) PowerSupplyUnits successfully added (-_-)");
        });


        VBox panel = new VBox();
        panel.getChildren().addAll(
                brandLabel, brandField,
                modelLabel, modelField,
                formFactorLabel, formFactorField,
                powerLabel, powerField,
                minInpVoltLabel, minInpVoltField,
                maxInpVoltLabel, maxInpVoltField,
                priceLabel, priceField,
                selectFileButton, filePathLabel
        );
        panel.setAlignment(Pos.CENTER);


        contentPane.getChildren().addAll(panel, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }
    private void addProductEnclosures()
    {
        int startClearIndex = contentPane.getChildren().indexOf(comboBox) + 1;
        contentPane.getChildren().remove(startClearIndex, contentPane.getChildren().size());

        TextField brandField = new TextField();
        brandField.setMaxWidth(200);
        TextField modelField = new TextField();
        modelField.setMaxWidth(200);
        TextField sizeStandardField = new TextField();
        sizeStandardField.setMaxWidth(200);
        TextField formFactorField = new TextField();
        formFactorField.setMaxWidth(200);
        TextField BPLocationField = new TextField();
        BPLocationField.setMaxWidth(200);
        TextField priceField = new TextField();
        priceField.setMaxWidth(200);

        Label brandLabel = new Label("Бренд:");
        brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label modelLabel = new Label("Модель:");
        modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label sizeStandardLabel = new Label("Типоразмер:");
        sizeStandardLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label formFactorLabel = new Label("Форм-фактор материнской платы:");
        formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label BPLocationLabel = new Label("РасположениеБП:");
        BPLocationLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label priceLabel = new Label("Цена:");
        priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        Button selectFileButton = new Button("Выбрать изображение");
        selectFileButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label filePathLabel = new Label();

        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
                MyImage.loadImageIntoResources(selectedFile);
            }
        });

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        applyButton.setOnAction(e -> {

            String brand = brandField.getText();
            String model = modelField.getText();
            String formFactor = formFactorField.getText();
            String sizeStandard = sizeStandardField.getText();
            String BPLocation = BPLocationField.getText();
            String price = priceField.getText();

            Database db = new Database();

            Enclosures enclosures = new Enclosures();

            enclosures.setBrand(brand);
            enclosures.setModel(model);
            enclosures.setFormFactor(formFactor);
            enclosures.setSizeStandard(sizeStandard);
            enclosures.setBPLocation(BPLocation);
            enclosures.setPrice(price);
            enclosures.setImageFileName(MyImage.imageFilePath);

            db.addEnclosuresToDatabase(enclosures);

            brandField.clear();
            modelField.clear();
            formFactorField.clear();
            sizeStandardField.clear();
            BPLocationField.clear();
            priceField.clear();
            filePathLabel.setText("");

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("(-_-) Enclosures successfully added (-_-)");
        });


        VBox panel = new VBox();
        panel.getChildren().addAll(
                brandLabel, brandField,
                modelLabel, modelField,
                sizeStandardLabel, sizeStandardField,
                formFactorLabel, formFactorField,
                BPLocationLabel, BPLocationField,
                priceLabel, priceField,
                selectFileButton, filePathLabel
        );
        panel.setAlignment(Pos.CENTER);


        contentPane.getChildren().addAll(panel, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }
    private void addProductHDD()
    {
        int startClearIndex = contentPane.getChildren().indexOf(comboBox) + 1;
        contentPane.getChildren().remove(startClearIndex, contentPane.getChildren().size());

        TextField brandField = new TextField();
        brandField.setMaxWidth(200);
        TextField modelField = new TextField();
        modelField.setMaxWidth(200);
        TextField typeField = new TextField();
        typeField.setMaxWidth(200);
        TextField formFactorField = new TextField();
        formFactorField.setMaxWidth(200);
        TextField capacityStorageField = new TextField();
        capacityStorageField.setMaxWidth(200);
        TextField spindleRotationSpeedField = new TextField();
        spindleRotationSpeedField.setMaxWidth(200);
        TextField priceField = new TextField();
        priceField.setMaxWidth(200);

        Label brandLabel = new Label("Бренд:");
        brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label modelLabel = new Label("Модель:");
        modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label typeLabel = new Label("Тип жесткого диска:");
        typeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label formFactorLabel = new Label("Форм-фактор:");
        formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label capacityStorageLabel = new Label("Объем накопителя:");
        capacityStorageLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label spindleRotationSpeedLabel = new Label("Скорость вращения шпинделя:");
        spindleRotationSpeedLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label priceLabel = new Label("Цена:");
        priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        Button selectFileButton = new Button("Выбрать изображение");
        selectFileButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label filePathLabel = new Label();

        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
                MyImage.loadImageIntoResources(selectedFile);
            }
        });

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        applyButton.setOnAction(e -> {

            String brand = brandField.getText();
            String model = modelField.getText();
            String formFactor = formFactorField.getText();
            String type = typeField.getText();
            String capacityStorage = capacityStorageField.getText();
            String spindleRotationSpeed = spindleRotationSpeedField.getText();
            String price = priceField.getText();

            Database db = new Database();

            HDD hdd = new HDD();

            hdd.setBrand(brand);
            hdd.setModel(model);
            hdd.setType(type);
            hdd.setFormFactor(formFactor);
            hdd.setCapacityStorage(capacityStorage);
            hdd.setSpindleRotationSpeed(spindleRotationSpeed);
            hdd.setPrice(price);
            hdd.setImageFileName(MyImage.imageFilePath);

            db.addHDDToDatabase(hdd);

            brandField.clear();
            modelField.clear();
            formFactorField.clear();
            typeField.clear();
            capacityStorageField.clear();
            spindleRotationSpeedField.clear();
            priceField.clear();
            filePathLabel.setText("");

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("(-_-) HDD successfully added (-_-)");
        });


        VBox panel = new VBox();
        panel.getChildren().addAll(
                brandLabel, brandField,
                modelLabel, modelField,
                typeLabel, typeField,
                formFactorLabel, formFactorField,
                capacityStorageLabel, capacityStorageField,
                spindleRotationSpeedLabel, spindleRotationSpeedField,
                priceLabel, priceField,
                selectFileButton, filePathLabel
        );
        panel.setAlignment(Pos.CENTER);


        contentPane.getChildren().addAll(panel, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }
    private void addProductSSD()
    {
        int startClearIndex = contentPane.getChildren().indexOf(comboBox) + 1;
        contentPane.getChildren().remove(startClearIndex, contentPane.getChildren().size());

        TextField brandField = new TextField();
        brandField.setMaxWidth(200);
        TextField modelField = new TextField();
        modelField.setMaxWidth(200);
        TextField typeField = new TextField();
        typeField.setMaxWidth(200);
        TextField capacityStorageField = new TextField();
        capacityStorageField.setMaxWidth(200);
        TextField formFactorField = new TextField();
        formFactorField.setMaxWidth(200);
        TextField maxReadingSpeedField = new TextField();
        maxReadingSpeedField.setMaxWidth(200);
        TextField maxRecordingSpeedField = new TextField();
        maxRecordingSpeedField.setMaxWidth(200);
        TextField priceField = new TextField();
        priceField.setMaxWidth(200);

        Label brandLabel = new Label("Бренд:");
        brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label modelLabel = new Label("Модель:");
        modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label typeLabel = new Label("Тип ssd:");
        typeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label capacityStorageLabel = new Label("Объем накопителя:");
        capacityStorageLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label formFactorLabel = new Label("Форм-фактор:");
        formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label maxReadingSpeedLabel = new Label("Максимальная скорость чтения:");
        maxReadingSpeedLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label maxRecordingSpeedLabel = new Label("Максимальная скорость записи:");
        maxRecordingSpeedLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label priceLabel = new Label("Цена:");
        priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        Button selectFileButton = new Button("Выбрать изображение");
        selectFileButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label filePathLabel = new Label();

        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
                MyImage.loadImageIntoResources(selectedFile);
            }
        });

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        applyButton.setOnAction(e -> {

            String brand = brandField.getText();
            String model = modelField.getText();
            String formFactor = formFactorField.getText();
            String type = typeField.getText();
            String capacityStorage = capacityStorageField.getText();
            String maxReadingSpeed = maxReadingSpeedField.getText();
            String maxRecordingSpeed = maxRecordingSpeedField.getText();
            String price = priceField.getText();

            Database db = new Database();

            SSD ssd = new SSD();

            ssd.setBrand(brand);
            ssd.setModel(model);
            ssd.setType(type);
            ssd.setFormFactor(formFactor);
            ssd.setCapacityStorage(capacityStorage);
            ssd.setMaxReadingSpeed(maxReadingSpeed);
            ssd.setMaxRecordingSpeed(maxRecordingSpeed);
            ssd.setPrice(price);
            ssd.setImageFileName(MyImage.imageFilePath);

            db.addSSDToDatabase(ssd);

            brandField.clear();
            modelField.clear();
            formFactorField.clear();
            typeField.clear();
            capacityStorageField.clear();
            maxReadingSpeedField.clear();
            maxRecordingSpeedField.clear();
            priceField.clear();
            filePathLabel.setText("");

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("(-_-) SSD successfully added (-_-)");
        });


        VBox panel = new VBox();
        panel.getChildren().addAll(
                brandLabel, brandField,
                modelLabel, modelField,
                typeLabel, typeField,
                capacityStorageLabel, capacityStorageField,
                formFactorLabel, formFactorField,
                maxReadingSpeedLabel, maxReadingSpeedField,
                maxRecordingSpeedLabel, maxRecordingSpeedField,
                priceLabel, priceField,
                selectFileButton, filePathLabel
        );
        panel.setAlignment(Pos.CENTER);


        contentPane.getChildren().addAll(panel, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }
    private void addProductMotherboards()
    {
        int startClearIndex = contentPane.getChildren().indexOf(comboBox) + 1;
        contentPane.getChildren().remove(startClearIndex, contentPane.getChildren().size());

        TextField brandField = new TextField();
        brandField.setMaxWidth(200);
        TextField modelField = new TextField();
        modelField.setMaxWidth(200);
        TextField formFactorField = new TextField();
        formFactorField.setMaxWidth(200);
        TextField socketField = new TextField();
        socketField.setMaxWidth(200);
        TextField chipsetField = new TextField();
        chipsetField.setMaxWidth(200);
        TextField memoryTypeField = new TextField();
        memoryTypeField.setMaxWidth(200);
        TextField priceField = new TextField();
        priceField.setMaxWidth(200);

        Label brandLabel = new Label("Бренд:");
        brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label modelLabel = new Label("Модель:");
        modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label formFactorLabel = new Label("Форм-фактор:");
        formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label socketLabel = new Label("Сокет:");
        socketLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label chipsetLabel = new Label("Чипсет:");
        chipsetLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label memoryTypeLabel = new Label("Тип памяти:");
        memoryTypeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
        Label priceLabel = new Label("Цена:");
        priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите изображение");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.jpeg", "*.png", "*.gif"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        Button selectFileButton = new Button("Выбрать изображение");
        selectFileButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label filePathLabel = new Label();

        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                filePathLabel.setText(selectedFile.getAbsolutePath());
                MyImage.loadImageIntoResources(selectedFile);
            }
        });

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        applyButton.setOnAction(e -> {

            String brand = brandField.getText();
            String model = modelField.getText();
            String formFactor = formFactorField.getText();
            String socket = socketField.getText();
            String chipset = chipsetField.getText();
            String memoryType = memoryTypeField.getText();
            String price = priceField.getText();

            Database db = new Database();

            Motherboards motherboards = new Motherboards();

            motherboards.setBrand(brand);
            motherboards.setModel(model);
            motherboards.setSocket(socket);
            motherboards.setFormFactor(formFactor);
            motherboards.setChipset(chipset);
            motherboards.setMemoryType(memoryType);
            motherboards.setPrice(price);
            motherboards.setImageFileName(MyImage.imageFilePath);

            db.addMotherboardsToDatabase(motherboards);

            brandField.clear();
            modelField.clear();
            formFactorField.clear();
            socketField.clear();
            chipsetField.clear();
            memoryTypeField.clear();
            priceField.clear();
            filePathLabel.setText("");

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("(-_-) Motherboards successfully added (-_-)");
        });


        VBox panel = new VBox();
        panel.getChildren().addAll(
                brandLabel, brandField,
                modelLabel, modelField,
                formFactorLabel, formFactorField,
                socketLabel, socketField,
                chipsetLabel, chipsetField,
                memoryTypeLabel, memoryTypeField,
                priceLabel, priceField,
                selectFileButton, filePathLabel
        );
        panel.setAlignment(Pos.CENTER);


        contentPane.getChildren().addAll(panel, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }

// удаление продукта из бд -------------ECC--------------------------------------------
    public void deleteProduct()
    {
        if (contentPane == null)
        {
            System.out.println("ERROR!!");
        }
        else
        {
            comboBox.setOnAction(event -> {
                String selectedCategory = comboBox.getValue();

                if(selectedCategory.equals("ЦП"))
                {
                    delPr("cp", "cp_id");
                }
                else if(selectedCategory.equals("Системы охлаждения процессора"))
                {
                    delPr("processor_cooling_systems", "PCS_id");
                }
                else if(selectedCategory.equals("Системы охлаждения корпуса"))
                {
                    delPr("housing_cooling_systems", "HCS_id");
                }
                else if(selectedCategory.equals("ОЗ"))
                {
                    delPr("ram", "RAM_id");
                }
                else if(selectedCategory.equals("Видеокарты"))
                {
                    delPr("video_cards", "VC_id");
                }
                else if(selectedCategory.equals("БП"))
                {
                    delPr("power_supply_units", "PSU_id");
                }
                else if(selectedCategory.equals("Корпуса"))
                {
                    delPr("enclosures", "Enclosures_id");
                }
                else if(selectedCategory.equals("HDD"))
                {
                    delPr("hard_drives", "HardDrives_id");
                }
                else if(selectedCategory.equals("SSD"))
                {
                    delPr("ssd", "SSD_id");
                }
                else if(selectedCategory.equals("Материнские платы"))
                {
                    delPr("motherboards", "Motherboards_id");
                }
                else
                    System.out.println("error");
            });

            contentPane.getChildren().add(comboBox);
            contentPane.setAlignment(Pos.CENTER);
        }
    }

    private void delPr(String table_name, String table_id_name)
    {
        TextField idField = new TextField();
        idField.setMaxWidth(50);

        Label idLabel = new Label("ID:");
        idLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

        Label messageLabel = new Label();

        applyButton.setOnAction(e -> {
            String idFieldText = idField.getText();

            Database db = new Database();
            String deleteUser = db.deleteProductByID(idFieldText, table_name, table_id_name);

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

            idField.clear();
        });


        contentPane.getChildren().addAll(idLabel, idField, messageLabel, applyButton);
        contentPane.setAlignment(Pos.CENTER);
    }


//--обновление данных о продукте в бд--------------EEEСССС--------------------------------------
    public void updateProduct()
    {
        if (contentPane == null)
        {
            System.out.println("ERROR!!");
        }
        else
        {
            comboBox.setOnAction(event -> {
                String selectedCategory = comboBox.getValue();

                if(selectedCategory.equals("ЦП"))
                {
                    updateCP();
                }
                else if(selectedCategory.equals("Системы охлаждения процессора"))
                {
                    updateProcessorCoolingSystems();
                }
                else if(selectedCategory.equals("Системы охлаждения корпуса"))
                {
                    updateHousingCoolingSystems();
                }
                else if(selectedCategory.equals("ОЗ"))
                {
                    updateRAM();
                }
                else if(selectedCategory.equals("Видеокарты"))
                {
                    updateVideoCards();
                }
                else if(selectedCategory.equals("БП"))
                {
                    updatePowerSupplyUnits();
                }
                else if(selectedCategory.equals("Корпуса"))
                {
                    updateEnclosures();
                }
                else if(selectedCategory.equals("HDD"))
                {
                    updateHDD();
                }
                else if(selectedCategory.equals("SSD"))
                {
                    updateSSD();
                }
                else if(selectedCategory.equals("Материнские платы"))
                {
                    updateMotherboards();
                }
                else
                    System.out.println("error");
            });

            contentPane.getChildren().add(comboBox);
            contentPane.setAlignment(Pos.CENTER);
        }
    }

    private void updateCP()
    {
        Label cpIdLabel = new Label("ID");
        cpIdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
        TextField cpIdField = new TextField();
        cpIdField.setMaxWidth(50);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

        checkButton.setOnAction(e -> {

            int cpId = Integer.parseInt(cpIdField.getText());

            Database db = new Database();

            if(!db.checkProductIdEqual(cpId, "CP"))
            {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Id not found((");
            }
            else
            {
                TextField brandField = new TextField();
                brandField.setMaxWidth(200);
                TextField modelField = new TextField();
                modelField.setMaxWidth(200);
                TextField coreNameField = new TextField();
                coreNameField.setMaxWidth(200);
                TextField coresNumberField = new TextField();
                coresNumberField.setMaxWidth(200);
                TextField socketField = new TextField();
                socketField.setMaxWidth(200);
                TextField frequencyField = new TextField();
                frequencyField.setMaxWidth(200);
                TextField threadsNumberField = new TextField();
                threadsNumberField.setMaxWidth(200);
                TextField priceField = new TextField();
                priceField.setMaxWidth(200);

                Label brandLabel = new Label("Бренд:");
                brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label modelLabel = new Label("Модель:");
                modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label coreNameLabel = new Label("Имя ядра:");
                coreNameLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label coresNumberLabel = new Label("Количество ядер:");
                coresNumberLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label socketLabel = new Label("Сокет:");
                socketLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label frequencyLabel = new Label("Частота:");
                frequencyLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label threadsNumberLabel = new Label("Количество потоков:");
                threadsNumberLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label priceLabel = new Label("Цена:");
                priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

                Button updateButton = new Button("Update");
                updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                updateButton.setOnAction(event -> {

                    String brand = brandField.getText();
                    String model = modelField.getText();
                    String coreName = coreNameField.getText();
                    String coreNumber = coresNumberField.getText();
                    String socet = socketField.getText();
                    String frequency = frequencyField.getText();
                    String threadsNumber = threadsNumberField.getText();
                    String price = priceField.getText();

                    messageLabel.setTextFill(Color.BLACK);
                    messageLabel.setText(db.updateCP(cpId, brand, model, coreName, coreNumber, socet, frequency, threadsNumber, price));

                    brandField.clear();
                    modelField.clear();
                    coreNameField.clear();
                    coresNumberField.clear();
                    socketField.clear();
                    frequencyField.clear();
                    threadsNumberField.clear();
                    priceField.clear();
                });

                VBox inputFields = new VBox(10);
                inputFields.getChildren().addAll(
                        brandLabel, brandField,
                        modelLabel, modelField,
                        coreNameLabel, coreNameField,
                        coresNumberLabel, coresNumberField,
                        socketLabel, socketField,
                        frequencyLabel, frequencyField,
                        threadsNumberLabel, threadsNumberField,
                        priceLabel, priceField,
                        messageLabel, updateButton
                );

                inputFields.setAlignment(Pos.CENTER);
                contentPane.getChildren().clear();
                contentPane.getChildren().addAll(inputFields);
                contentPane.setAlignment(Pos.CENTER);
            }

        });

        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(cpIdLabel, cpIdField, messageLabel, checkButton);
        contentPane.setAlignment(Pos.CENTER);
    }

    private void updateProcessorCoolingSystems()
    {
        Label IdLabel = new Label("ID");
        IdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
        TextField IdField = new TextField();
        IdField.setMaxWidth(50);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

        checkButton.setOnAction(e -> {

            int Id = Integer.parseInt(IdField.getText());

            Database db = new Database();

            if(!db.checkProductIdEqual(Id, "ProcessorCoolingSystems"))
            {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Id not found((");
            }
            else
            {
                TextField brandField = new TextField();
                brandField.setMaxWidth(200);
                TextField modelField = new TextField();
                modelField.setMaxWidth(200);
                TextField coolingTypeField = new TextField();
                coolingTypeField.setMaxWidth(200);
                TextField fansNumberField = new TextField();
                fansNumberField.setMaxWidth(200);
                TextField fanSizeField = new TextField();
                fanSizeField.setMaxWidth(200);
                TextField fanRotationSpeedField = new TextField();
                fanRotationSpeedField.setMaxWidth(200);
                TextField maxHeatDissipationProcessorField = new TextField();
                maxHeatDissipationProcessorField.setMaxWidth(200);
                TextField priceField = new TextField();
                priceField.setMaxWidth(200);

                Label brandLabel = new Label("Бренд:");
                brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label modelLabel = new Label("Модель:");
                modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label coolingTypeLabel = new Label("Тип охлаждения:");
                coolingTypeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label fansNumberLabel = new Label("Количество вентиляторов:");
                fansNumberLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label fanSizeLabel = new Label("Размер вентилятора:");
                fanSizeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label fanRotationSpeedLabel = new Label("Скорость вращения вентилятора:");
                fanRotationSpeedLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label maxHeatDissipationProcessorLabel = new Label("Максимальное тепловыделение процессора:");
                maxHeatDissipationProcessorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label priceLabel = new Label("Цена:");
                priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

                Button updateButton = new Button("Update");
                updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                updateButton.setOnAction(event -> {

                    String brand = brandField.getText();
                    String model = modelField.getText();
                    String coolingType = coolingTypeField.getText();
                    String fansNumber = fansNumberField.getText();
                    String fanSize = fanSizeField.getText();
                    String fanRotationSpeed = fanRotationSpeedField.getText();
                    String maxHeatDissipationProcessor = maxHeatDissipationProcessorField.getText();
                    String price = priceField.getText();

                    messageLabel.setTextFill(Color.BLACK);
                    messageLabel.setText(db.updateProcessorCoolingSystems(Id, brand, model, coolingType, fansNumber, fanSize, fanRotationSpeed, maxHeatDissipationProcessor, price));

                    brandField.clear();
                    modelField.clear();
                    coolingTypeField.clear();
                    fansNumberField.clear();
                    fanSizeField.clear();
                    fanRotationSpeedField.clear();
                    maxHeatDissipationProcessorField.clear();
                    priceField.clear();
                });

                VBox inputFields = new VBox(10);
                inputFields.getChildren().addAll(
                        brandLabel, brandField,
                        modelLabel, modelField,
                        coolingTypeLabel, coolingTypeField,
                        fansNumberLabel, fansNumberField,
                        fanSizeLabel, fanSizeField,
                        fanRotationSpeedLabel, fanRotationSpeedField,
                        maxHeatDissipationProcessorLabel, maxHeatDissipationProcessorField,
                        priceLabel, priceField,
                        messageLabel, updateButton
                );

                inputFields.setAlignment(Pos.CENTER);
                contentPane.getChildren().clear();
                contentPane.getChildren().addAll(inputFields);
                contentPane.setAlignment(Pos.CENTER);
            }

        });

        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(IdLabel, IdField, messageLabel, checkButton);
        contentPane.setAlignment(Pos.CENTER);
    }

    private void updateHousingCoolingSystems()
    {
        Label IdLabel = new Label("ID");
        IdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
        TextField IdField = new TextField();
        IdField.setMaxWidth(50);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

        checkButton.setOnAction(e -> {

            int Id = Integer.parseInt(IdField.getText());

            Database db = new Database();

            if(!db.checkProductIdEqual(Id, "HousingCoolingSystems"))
            {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Id not found((");
            }
            else
            {
                TextField brandField = new TextField();
                brandField.setMaxWidth(200);
                TextField modelField = new TextField();
                modelField.setMaxWidth(200);
                TextField coolingTypeField = new TextField();
                coolingTypeField.setMaxWidth(200);
                TextField fansNumberField = new TextField();
                fansNumberField.setMaxWidth(200);
                TextField fanSizeField = new TextField();
                fanSizeField.setMaxWidth(200);
                TextField fanRotationSpeedField = new TextField();
                fanRotationSpeedField.setMaxWidth(200);
                TextField fanLifeField = new TextField();
                fanLifeField.setMaxWidth(200);
                TextField priceField = new TextField();
                priceField.setMaxWidth(200);

                Label brandLabel = new Label("Бренд:");
                brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label modelLabel = new Label("Модель:");
                modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label coolingTypeLabel = new Label("Тип охлаждения:");
                coolingTypeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label fansNumberLabel = new Label("Количество вентиляторов:");
                fansNumberLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label fanSizeLabel = new Label("Размер вентилятора:");
                fanSizeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label fanRotationSpeedLabel = new Label("Скорость вращения вентилятора:");
                fanRotationSpeedLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label fanLifeLabel = new Label("Срок службы вентилятора:");
                fanLifeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label priceLabel = new Label("Цена:");
                priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

                Button updateButton = new Button("Update");
                updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                updateButton.setOnAction(event -> {

                    String brand = brandField.getText();
                    String model = modelField.getText();
                    String coolingType = coolingTypeField.getText();
                    String fansNumber = fansNumberField.getText();
                    String fanSize = fanSizeField.getText();
                    String fanRotationSpeed = fanRotationSpeedField.getText();
                    String fanLife = fanLifeField.getText();
                    String price = priceField.getText();

                    messageLabel.setTextFill(Color.BLACK);
                    messageLabel.setText(db.updateHousingCoolingSystems(Id, brand, model, coolingType, fansNumber, fanSize, fanRotationSpeed, fanLife, price));

                    brandField.clear();
                    modelField.clear();
                    coolingTypeField.clear();
                    fansNumberField.clear();
                    fanSizeField.clear();
                    fanRotationSpeedField.clear();
                    fanLifeField.clear();
                    priceField.clear();
                });

                VBox inputFields = new VBox(10);
                inputFields.getChildren().addAll(
                        brandLabel, brandField,
                        modelLabel, modelField,
                        coolingTypeLabel, coolingTypeField,
                        fansNumberLabel, fansNumberField,
                        fanSizeLabel, fanSizeField,
                        fanRotationSpeedLabel, fanRotationSpeedField,
                        fanLifeLabel, fanLifeField,
                        priceLabel, priceField,
                        messageLabel, updateButton
                );

                inputFields.setAlignment(Pos.CENTER);
                contentPane.getChildren().clear();
                contentPane.getChildren().addAll(inputFields);
                contentPane.setAlignment(Pos.CENTER);
            }

        });

        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(IdLabel, IdField, messageLabel, checkButton);
        contentPane.setAlignment(Pos.CENTER);
    }

    private void updateVideoCards()
    {
        Label IdLabel = new Label("ID");
        IdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
        TextField IdField = new TextField();
        IdField.setMaxWidth(50);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

        checkButton.setOnAction(e -> {

            int Id = Integer.parseInt(IdField.getText());

            Database db = new Database();

            if(!db.checkProductIdEqual(Id, "VideoCards"))
            {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Id not found((");
            }
            else
            {
                TextField brandField = new TextField();
                brandField.setMaxWidth(200);
                TextField modelField = new TextField();
                modelField.setMaxWidth(200);
                TextField videoChipsetField = new TextField();
                videoChipsetField.setMaxWidth(200);
                TextField gpfField = new TextField();
                gpfField.setMaxWidth(200);
                TextField maxResolutionField = new TextField();
                maxResolutionField.setMaxWidth(200);
                TextField videoMemoryField = new TextField();
                videoMemoryField.setMaxWidth(200);
                TextField priceField = new TextField();
                priceField.setMaxWidth(200);

                Label brandLabel = new Label("Бренд:");
                brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label modelLabel = new Label("Модель:");
                modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label videoChipsetLabel = new Label("Видеочипсет:");
                videoChipsetLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label gpfLabel = new Label("Частота графического процессора:");
                gpfLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label videoMemoryLabel = new Label("Объем видеопамяти:");
                videoMemoryLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label priceLabel = new Label("Цена:");
                priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label maxResolutionLabel = new Label("Максимальное разрешение:");
                maxResolutionLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

                Button updateButton = new Button("Update");
                updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                updateButton.setOnAction(event -> {

                    String brand = brandField.getText();
                    String model = modelField.getText();
                    String videoChipset = videoChipsetField.getText();
                    String gpf = gpfField.getText();
                    String maxResolution = maxResolutionField.getText();
                    String videoMemory = videoMemoryField.getText();
                    String price = priceField.getText();

                    messageLabel.setTextFill(Color.BLACK);
                    messageLabel.setText(db.updateVideoCards(Id, brand, model, videoChipset, gpf, maxResolution, videoMemory, price));

                    brandField.clear();
                    modelField.clear();
                    videoChipsetField.clear();
                    gpfField.clear();
                    maxResolutionField.clear();
                    videoMemoryField.clear();
                    priceField.clear();
                });

                VBox inputFields = new VBox(10);
                inputFields.getChildren().addAll(
                        brandLabel, brandField,
                        modelLabel, modelField,
                        videoChipsetLabel, videoChipsetField,
                        gpfLabel, gpfField,
                        maxResolutionLabel, maxResolutionField,
                        videoMemoryLabel, videoMemoryField,
                        priceLabel, priceField,
                        messageLabel, updateButton
                );

                inputFields.setAlignment(Pos.CENTER);
                contentPane.getChildren().clear();
                contentPane.getChildren().addAll(inputFields);
                contentPane.setAlignment(Pos.CENTER);
            }

        });

        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(IdLabel, IdField, messageLabel, checkButton);
        contentPane.setAlignment(Pos.CENTER);
    }

    private void updateRAM()
    {
        Label IdLabel = new Label("ID");
        IdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
        TextField IdField = new TextField();
        IdField.setMaxWidth(50);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

        checkButton.setOnAction(e -> {

            int Id = Integer.parseInt(IdField.getText());

            Database db = new Database();

            if(!db.checkProductIdEqual(Id, "RAM"))
            {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Id not found((");
            }
            else
            {
                TextField brandField = new TextField();
                brandField.setMaxWidth(200);
                TextField modelField = new TextField();
                modelField.setMaxWidth(200);
                TextField formFactorField = new TextField();
                formFactorField.setMaxWidth(200);
                TextField memoryTypeField = new TextField();
                memoryTypeField.setMaxWidth(200);
                TextField volumeField = new TextField();
                volumeField.setMaxWidth(200);
                TextField clockFrequencyField = new TextField();
                clockFrequencyField.setMaxWidth(200);
                TextField throughputCapacityField = new TextField();
                throughputCapacityField.setMaxWidth(200);
                TextField priceField = new TextField();
                priceField.setMaxWidth(200);

                Label brandLabel = new Label("Бренд:");
                brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label modelLabel = new Label("Модель:");
                modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label formFactorLabel = new Label("Форм-фактор:");
                formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label memoryTypeLabel = new Label("Тип памяти:");
                memoryTypeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label volumeLabel = new Label("Объем:");
                volumeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label clockFrequencyLabel = new Label("Тактовая частота:");
                clockFrequencyLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label throughputCapacityLabel = new Label("Пропускная способность:");
                throughputCapacityLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label priceLabel = new Label("Цена:");
                priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

                Button updateButton = new Button("Update");
                updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                updateButton.setOnAction(event -> {

                    String brand = brandField.getText();
                    String model = modelField.getText();
                    String formFactor = formFactorField.getText();
                    String memoryType = memoryTypeField.getText();
                    String volume = volumeField.getText();
                    String clockFrequency = clockFrequencyField.getText();
                    String throughputCapacity = throughputCapacityField.getText();
                    String price = priceField.getText();

                    messageLabel.setTextFill(Color.BLACK);
                    messageLabel.setText(db.updateRAM(Id, brand, model, formFactor, memoryType, volume, clockFrequency, throughputCapacity, price));

                    brandField.clear();
                    modelField.clear();
                    formFactorField.clear();
                    memoryTypeField.clear();
                    volumeField.clear();
                    clockFrequencyField.clear();
                    throughputCapacityField.clear();
                    priceField.clear();
                });

                VBox inputFields = new VBox(10);
                inputFields.getChildren().addAll(
                        brandLabel, brandField,
                        modelLabel, modelField,
                        formFactorLabel, formFactorField,
                        memoryTypeLabel, memoryTypeField,
                        volumeLabel, volumeField,
                        clockFrequencyLabel, clockFrequencyField,
                        throughputCapacityLabel, throughputCapacityField,
                        priceLabel, priceField,
                        messageLabel, updateButton
                );

                inputFields.setAlignment(Pos.CENTER);
                contentPane.getChildren().clear();
                contentPane.getChildren().addAll(inputFields);
                contentPane.setAlignment(Pos.CENTER);
            }

        });

        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(IdLabel, IdField, messageLabel, checkButton);
        contentPane.setAlignment(Pos.CENTER);
    }

    private void updatePowerSupplyUnits()
    {
        Label IdLabel = new Label("ID");
        IdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
        TextField IdField = new TextField();
        IdField.setMaxWidth(50);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

        checkButton.setOnAction(e -> {

            int Id = Integer.parseInt(IdField.getText());

            Database db = new Database();

            if(!db.checkProductIdEqual(Id, "PowerSupplyUnits"))
            {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Id not found((");
            }
            else
            {
                TextField brandField = new TextField();
                brandField.setMaxWidth(200);
                TextField modelField = new TextField();
                modelField.setMaxWidth(200);
                TextField formFactorField = new TextField();
                formFactorField.setMaxWidth(200);
                TextField powerField = new TextField();
                powerField.setMaxWidth(200);
                TextField minInpVoltField = new TextField();
                minInpVoltField.setMaxWidth(200);
                TextField maxInpVoltField = new TextField();
                maxInpVoltField.setMaxWidth(200);
                TextField priceField = new TextField();
                priceField.setMaxWidth(200);

                Label brandLabel = new Label("Бренд:");
                brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label modelLabel = new Label("Модель:");
                modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label formFactorLabel = new Label("Форм-фактор:");
                formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label powerLabel = new Label("Мощность:");
                powerLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label minInpVoltLabel = new Label("Минимальное входное напряжение:");
                minInpVoltLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label maxInpVoltLabel = new Label("Максимальное входное напряжение:");
                maxInpVoltLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label priceLabel = new Label("Цена:");
                priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

                Button updateButton = new Button("Update");
                updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                updateButton.setOnAction(event -> {

                    String brand = brandField.getText();
                    String model = modelField.getText();
                    String formFactor = formFactorField.getText();
                    String power = powerField.getText();
                    String minInpVolt = minInpVoltField.getText();
                    String maxInpVolt = maxInpVoltField.getText();
                    String price = priceField.getText();

                    messageLabel.setTextFill(Color.BLACK);
                    messageLabel.setText(db.updatePowerSupplyUnits(Id, brand, model, formFactor, power, minInpVolt, maxInpVolt, price));

                    brandField.clear();
                    modelField.clear();
                    formFactorField.clear();
                    powerField.clear();
                    minInpVoltField.clear();
                    maxInpVoltField.clear();
                    priceField.clear();
                });

                VBox inputFields = new VBox(10);
                inputFields.getChildren().addAll(
                        brandLabel, brandField,
                        modelLabel, modelField,
                        formFactorLabel, formFactorField,
                        powerLabel, powerField,
                        minInpVoltLabel, minInpVoltField,
                        maxInpVoltLabel, maxInpVoltField,
                        priceLabel, priceField,
                        messageLabel, updateButton
                );

                inputFields.setAlignment(Pos.CENTER);
                contentPane.getChildren().clear();
                contentPane.getChildren().addAll(inputFields);
                contentPane.setAlignment(Pos.CENTER);
            }

        });

        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(IdLabel, IdField, messageLabel, checkButton);
        contentPane.setAlignment(Pos.CENTER);
    }

    private void updateEnclosures()
    {
        Label IdLabel = new Label("ID");
        IdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
        TextField IdField = new TextField();
        IdField.setMaxWidth(50);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

        checkButton.setOnAction(e -> {

            int Id = Integer.parseInt(IdField.getText());

            Database db = new Database();

            if(!db.checkProductIdEqual(Id, "Enclosures"))
            {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Id not found((");
            }
            else
            {
                TextField brandField = new TextField();
                brandField.setMaxWidth(200);
                TextField modelField = new TextField();
                modelField.setMaxWidth(200);
                TextField sizeStandardField = new TextField();
                sizeStandardField.setMaxWidth(200);
                TextField formFactorField = new TextField();
                formFactorField.setMaxWidth(200);
                TextField BPLocationField = new TextField();
                BPLocationField.setMaxWidth(200);
                TextField priceField = new TextField();
                priceField.setMaxWidth(200);

                Label brandLabel = new Label("Бренд:");
                brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label modelLabel = new Label("Модель:");
                modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label sizeStandardLabel = new Label("Типоразмер:");
                sizeStandardLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label formFactorLabel = new Label("Форм-фактор материнской платы:");
                formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label BPLocationLabel = new Label("РасположениеБП:");
                BPLocationLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label priceLabel = new Label("Цена:");
                priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

                Button updateButton = new Button("Update");
                updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                updateButton.setOnAction(event -> {

                    String brand = brandField.getText();
                    String model = modelField.getText();
                    String formFactor = formFactorField.getText();
                    String sizeStandard = sizeStandardField.getText();
                    String BPLocation = BPLocationField.getText();
                    String price = priceField.getText();

                    messageLabel.setTextFill(Color.BLACK);
                    messageLabel.setText(db.updateEnclosures(Id, brand, model, formFactor, sizeStandard, BPLocation, price));

                    brandField.clear();
                    modelField.clear();
                    formFactorField.clear();
                    sizeStandardField.clear();
                    BPLocationField.clear();
                    priceField.clear();
                });

                VBox inputFields = new VBox(10);
                inputFields.getChildren().addAll(
                        brandLabel, brandField,
                        modelLabel, modelField,
                        formFactorLabel, formFactorField,
                        sizeStandardLabel, sizeStandardField,
                        BPLocationLabel, BPLocationField,
                        priceLabel, priceField,
                        messageLabel, updateButton
                );

                inputFields.setAlignment(Pos.CENTER);
                contentPane.getChildren().clear();
                contentPane.getChildren().addAll(inputFields);
                contentPane.setAlignment(Pos.CENTER);
            }

        });

        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(IdLabel, IdField, messageLabel, checkButton);
        contentPane.setAlignment(Pos.CENTER);
    }

    private void updateHDD()
    {
        Label IdLabel = new Label("ID");
        IdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
        TextField IdField = new TextField();
        IdField.setMaxWidth(50);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

        checkButton.setOnAction(e -> {

            int Id = Integer.parseInt(IdField.getText());

            Database db = new Database();

            if(!db.checkProductIdEqual(Id, "HDD"))
            {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Id not found((");
            }
            else
            {
                TextField brandField = new TextField();
                brandField.setMaxWidth(200);
                TextField modelField = new TextField();
                modelField.setMaxWidth(200);
                TextField typeField = new TextField();
                typeField.setMaxWidth(200);
                TextField formFactorField = new TextField();
                formFactorField.setMaxWidth(200);
                TextField capacityStorageField = new TextField();
                capacityStorageField.setMaxWidth(200);
                TextField spindleRotationSpeedField = new TextField();
                spindleRotationSpeedField.setMaxWidth(200);
                TextField priceField = new TextField();
                priceField.setMaxWidth(200);

                Label brandLabel = new Label("Бренд:");
                brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label modelLabel = new Label("Модель:");
                modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label typeLabel = new Label("Тип жесткого диска:");
                typeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label formFactorLabel = new Label("Форм-фактор:");
                formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label capacityStorageLabel = new Label("Объем накопителя:");
                capacityStorageLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label spindleRotationSpeedLabel = new Label("Скорость вращения шпинделя:");
                spindleRotationSpeedLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label priceLabel = new Label("Цена:");
                priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

                Button updateButton = new Button("Update");
                updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                updateButton.setOnAction(event -> {

                    String brand = brandField.getText();
                    String model = modelField.getText();
                    String formFactor = formFactorField.getText();
                    String type = typeField.getText();
                    String capacityStorage = capacityStorageField.getText();
                    String spindleRotationSpeed = spindleRotationSpeedField.getText();
                    String price = priceField.getText();

                    messageLabel.setTextFill(Color.BLACK);
                    messageLabel.setText(db.updateHDD(Id, brand, model, formFactor, type, capacityStorage, spindleRotationSpeed, price));

                    brandField.clear();
                    modelField.clear();
                    formFactorField.clear();
                    typeField.clear();
                    capacityStorageField.clear();
                    spindleRotationSpeedField.clear();
                    priceField.clear();
                });

                VBox inputFields = new VBox(10);
                inputFields.getChildren().addAll(
                        brandLabel, brandField,
                        modelLabel, modelField,
                        formFactorLabel, formFactorField,
                        typeLabel, typeField,
                        capacityStorageLabel, capacityStorageField,
                        spindleRotationSpeedLabel, spindleRotationSpeedField,
                        priceLabel, priceField,
                        messageLabel, updateButton
                );

                inputFields.setAlignment(Pos.CENTER);
                contentPane.getChildren().clear();
                contentPane.getChildren().addAll(inputFields);
                contentPane.setAlignment(Pos.CENTER);
            }

        });

        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(IdLabel, IdField, messageLabel, checkButton);
        contentPane.setAlignment(Pos.CENTER);
    }

    private void updateSSD()
    {
        Label IdLabel = new Label("ID");
        IdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
        TextField IdField = new TextField();
        IdField.setMaxWidth(50);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

        checkButton.setOnAction(e -> {

            int Id = Integer.parseInt(IdField.getText());

            Database db = new Database();

            if(!db.checkProductIdEqual(Id, "SSD"))
            {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Id not found((");
            }
            else
            {
                TextField brandField = new TextField();
                brandField.setMaxWidth(200);
                TextField modelField = new TextField();
                modelField.setMaxWidth(200);
                TextField typeField = new TextField();
                typeField.setMaxWidth(200);
                TextField capacityStorageField = new TextField();
                capacityStorageField.setMaxWidth(200);
                TextField formFactorField = new TextField();
                formFactorField.setMaxWidth(200);
                TextField maxReadingSpeedField = new TextField();
                maxReadingSpeedField.setMaxWidth(200);
                TextField maxRecordingSpeedField = new TextField();
                maxRecordingSpeedField.setMaxWidth(200);
                TextField priceField = new TextField();
                priceField.setMaxWidth(200);

                Label brandLabel = new Label("Бренд:");
                brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label modelLabel = new Label("Модель:");
                modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label typeLabel = new Label("Тип ssd:");
                typeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label capacityStorageLabel = new Label("Объем накопителя:");
                capacityStorageLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label formFactorLabel = new Label("Форм-фактор:");
                formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label maxReadingSpeedLabel = new Label("Максимальная скорость чтения:");
                maxReadingSpeedLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label maxRecordingSpeedLabel = new Label("Максимальная скорость записи:");
                maxRecordingSpeedLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label priceLabel = new Label("Цена:");
                priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

                Button updateButton = new Button("Update");
                updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                updateButton.setOnAction(event -> {

                    String brand = brandField.getText();
                    String model = modelField.getText();
                    String formFactor = formFactorField.getText();
                    String type = typeField.getText();
                    String capacityStorage = capacityStorageField.getText();
                    String maxReadingSpeed = maxReadingSpeedField.getText();
                    String maxRecordingSpeed = maxRecordingSpeedField.getText();
                    String price = priceField.getText();

                    messageLabel.setTextFill(Color.BLACK);
                    messageLabel.setText(db.updateSSD(Id, brand, model, formFactor, type, capacityStorage, maxReadingSpeed, maxRecordingSpeed, price));

                    brandField.clear();
                    modelField.clear();
                    formFactorField.clear();
                    typeField.clear();
                    capacityStorageField.clear();
                    maxReadingSpeedField.clear();
                    maxRecordingSpeedField.clear();
                    priceField.clear();
                });

                VBox inputFields = new VBox(10);
                inputFields.getChildren().addAll(
                        brandLabel, brandField,
                        modelLabel, modelField,
                        formFactorLabel, formFactorField,
                        typeLabel, typeField,
                        capacityStorageLabel, capacityStorageField,
                        maxReadingSpeedLabel, maxReadingSpeedField,
                        maxRecordingSpeedLabel, maxRecordingSpeedField,
                        priceLabel, priceField,
                        messageLabel, updateButton
                );

                inputFields.setAlignment(Pos.CENTER);
                contentPane.getChildren().clear();
                contentPane.getChildren().addAll(inputFields);
                contentPane.setAlignment(Pos.CENTER);
            }

        });

        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(IdLabel, IdField, messageLabel, checkButton);
        contentPane.setAlignment(Pos.CENTER);
    }

    private void updateMotherboards()
    {
        Label IdLabel = new Label("ID");
        IdLabel.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
        TextField IdField = new TextField();
        IdField.setMaxWidth(50);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-background-color: #0d6359; -fx-text-fill: white; -fx-font-size: 16px;");

        checkButton.setOnAction(e -> {

            int Id = Integer.parseInt(IdField.getText());

            Database db = new Database();

            if(!db.checkProductIdEqual(Id, "Motherboards"))
            {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Id not found((");
            }
            else
            {
                TextField brandField = new TextField();
                brandField.setMaxWidth(200);
                TextField modelField = new TextField();
                modelField.setMaxWidth(200);
                TextField formFactorField = new TextField();
                formFactorField.setMaxWidth(200);
                TextField socketField = new TextField();
                socketField.setMaxWidth(200);
                TextField chipsetField = new TextField();
                chipsetField.setMaxWidth(200);
                TextField memoryTypeField = new TextField();
                memoryTypeField.setMaxWidth(200);
                TextField priceField = new TextField();
                priceField.setMaxWidth(200);

                Label brandLabel = new Label("Бренд:");
                brandLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label modelLabel = new Label("Модель:");
                modelLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label formFactorLabel = new Label("Форм-фактор:");
                formFactorLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label socketLabel = new Label("Сокет:");
                socketLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label chipsetLabel = new Label("Чипсет:");
                chipsetLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label memoryTypeLabel = new Label("Тип памяти:");
                memoryTypeLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");
                Label priceLabel = new Label("Цена:");
                priceLabel.setStyle("-fx-font-size: 16px; -fx-padding: 5px;");

                Button updateButton = new Button("Update");
                updateButton.setStyle("-fx-background-color: #287335; -fx-text-fill: white; -fx-font-size: 16px;");

                updateButton.setOnAction(event -> {

                    String brand = brandField.getText();
                    String model = modelField.getText();
                    String formFactor = formFactorField.getText();
                    String socket = socketField.getText();
                    String chipset = chipsetField.getText();
                    String memoryType = memoryTypeField.getText();
                    String price = priceField.getText();

                    messageLabel.setTextFill(Color.BLACK);
                    messageLabel.setText(db.updateMotherboards(Id, brand, model, formFactor, socket, chipset, memoryType, price));

                    brandField.clear();
                    modelField.clear();
                    formFactorField.clear();
                    socketField.clear();
                    chipsetField.clear();
                    memoryTypeField.clear();
                    priceField.clear();
                });

                VBox inputFields = new VBox(10);
                inputFields.getChildren().addAll(
                        brandLabel, brandField,
                        modelLabel, modelField,
                        formFactorLabel, formFactorField,
                        socketLabel, socketField,
                        chipsetLabel, chipsetField,
                        memoryTypeLabel, memoryTypeField,
                        priceLabel, priceField,
                        messageLabel, updateButton
                );

                inputFields.setAlignment(Pos.CENTER);
                contentPane.getChildren().clear();
                contentPane.getChildren().addAll(inputFields);
                contentPane.setAlignment(Pos.CENTER);
            }

        });

        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(IdLabel, IdField, messageLabel, checkButton);
        contentPane.setAlignment(Pos.CENTER);
    }

// Вывод таблиц из БД --------ECC----------------------------------------------------------
    public void viewProductData()
    {
        if (contentPane == null)
        {
            System.out.println("ERROR!!");
        }
        else
        {
            comboBox.setOnAction(event -> {
                String selectedCategory = comboBox.getValue();


                if(selectedCategory.equals("ЦП"))
                {
                    tableCP();
                }
                else if(selectedCategory.equals("Системы охлаждения процессора"))
                {
                    tableProcessorCoolingSystems();
                }
                else if(selectedCategory.equals("Системы охлаждения корпуса"))
                {
                    tableHousingCoolingSystems();
                }
                else if(selectedCategory.equals("ОЗ"))
                {
                    tableRAM();
                }
                else if(selectedCategory.equals("Видеокарты"))
                {
                    tableVideoCards();
                }
                else if(selectedCategory.equals("БП"))
                {
                    tablePowerSupplyUnits();
                }
                else if(selectedCategory.equals("Корпуса"))
                {
                    tableEnclosures();
                }
                else if(selectedCategory.equals("HDD"))
                {
                    tableHDD();
                }
                else if(selectedCategory.equals("SSD"))
                {
                    tableSSD();
                }
                else if(selectedCategory.equals("Материнские платы"))
                {
                    tableMotherboards();
                }
                else
                    System.out.println("error");

            });

            contentPane.getChildren().add(comboBox);
            contentPane.setAlignment(Pos.CENTER);
        }
    }

    // таблицы ЦП
    private void tableCP()
    {
        contentPane.getChildren().removeIf(node -> node instanceof TableView);

        TableView<CPs> table = new TableView<>();

        TableColumn<CPs, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(188.00);

        TableColumn<CPs, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(188.00);

        TableColumn<CPs, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setMaxWidth(188.00);

        TableColumn<CPs, Integer> coreNameColumn = new TableColumn<>("Core Name");
        coreNameColumn.setCellValueFactory(new PropertyValueFactory<>("coreName"));
        coreNameColumn.setMaxWidth(188.00);

        TableColumn<CPs, Integer> coreNumberColumn = new TableColumn<>("Core Number");
        coreNumberColumn.setCellValueFactory(new PropertyValueFactory<>("coreNumber"));
        coreNumberColumn.setMaxWidth(188.00);

        TableColumn<CPs, String> socketColumn = new TableColumn<>("Socket");
        socketColumn.setCellValueFactory(new PropertyValueFactory<>("socket"));
        socketColumn.setMaxWidth(188.00);

        TableColumn<CPs, String> frequencyColumn = new TableColumn<>("Frequency");
        frequencyColumn.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        frequencyColumn.setMaxWidth(188.00);

        TableColumn<CPs, Integer> threadsNumberColumn = new TableColumn<>("Threads Number");
        threadsNumberColumn.setCellValueFactory(new PropertyValueFactory<>("threadsNumber"));
        threadsNumberColumn.setMaxWidth(188.00);

        TableColumn<CPs, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setMaxWidth(188.00);

        TableColumn<CPs, String> imageFileName = new TableColumn<>("imageFileName");
        imageFileName.setCellValueFactory(new PropertyValueFactory<>("imageFileName"));
        imageFileName.setMaxWidth(188.00);

        table.getColumns().addAll(idColumn, brandColumn, modelColumn, coreNameColumn, coreNumberColumn,
                socketColumn, frequencyColumn, threadsNumberColumn, priceColumn, imageFileName);
        table.setMaxWidth(1500.00);

        Database db = new Database();
        table.getItems().addAll(db.getAllCPs());


        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

    // таблицы Системы охлаждения процессора
    private void tableProcessorCoolingSystems()
    {
        contentPane.getChildren().removeIf(node -> node instanceof TableView);

        TableView<ProcessorCoolingSystems> table = new TableView<>();

        TableColumn<ProcessorCoolingSystems, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(200.00);

        TableColumn<ProcessorCoolingSystems, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(188.00);

        TableColumn<ProcessorCoolingSystems, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setMaxWidth(188.00);

        TableColumn<ProcessorCoolingSystems, String> coolingTypeColumn = new TableColumn<>("Cooling Type");
        coolingTypeColumn.setCellValueFactory(new PropertyValueFactory<>("coolingType"));
        coolingTypeColumn.setMaxWidth(188.00);

        TableColumn<ProcessorCoolingSystems, Integer> fansNumberColumn = new TableColumn<>("Fans Number");
        fansNumberColumn.setCellValueFactory(new PropertyValueFactory<>("fansNumber"));
        fansNumberColumn.setMaxWidth(188.00);

        TableColumn<ProcessorCoolingSystems, String> fanSizeColumn = new TableColumn<>("Fan Size");
        fanSizeColumn.setCellValueFactory(new PropertyValueFactory<>("fanSize"));
        fanSizeColumn.setMaxWidth(188.00);

        TableColumn<ProcessorCoolingSystems, String> fanRotationSpeedColumn = new TableColumn<>("Fan Rotation Speed");
        fanRotationSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("fanRotationSpeed"));
        fanRotationSpeedColumn.setMaxWidth(188.00);

        TableColumn<ProcessorCoolingSystems, String> maxHeatDissipationProcessorColumn = new TableColumn<>("Max Heat Dissipation Processor");
        maxHeatDissipationProcessorColumn.setCellValueFactory(new PropertyValueFactory<>("maxHeatDissipationProcessor"));
        maxHeatDissipationProcessorColumn.setMaxWidth(188.00);

        TableColumn<ProcessorCoolingSystems, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setMaxWidth(188.00);

        TableColumn<ProcessorCoolingSystems, String> imageFileName = new TableColumn<>("imageFileName");
        imageFileName.setCellValueFactory(new PropertyValueFactory<>("imageFileName"));
        imageFileName.setMaxWidth(188.00);

        table.getColumns().addAll(
                idColumn, brandColumn,
                modelColumn, coolingTypeColumn,
                fansNumberColumn, fanSizeColumn,
                fanRotationSpeedColumn, maxHeatDissipationProcessorColumn,
                priceColumn, imageFileName
        );

        table.setMaxWidth(1500.00);

        Database db = new Database();
        table.getItems().addAll(db.getAllProcessorCoolingSystems());

        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

    // таблицы Системы охлаждения корпуса
    private void tableHousingCoolingSystems()
    {
        contentPane.getChildren().removeIf(node -> node instanceof TableView);

        TableView<HousingCoolingSystems> table = new TableView<>();

        TableColumn<HousingCoolingSystems, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(200.00);

        TableColumn<HousingCoolingSystems, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(188.00);

        TableColumn<HousingCoolingSystems, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setMaxWidth(188.00);

        TableColumn<HousingCoolingSystems, String> coolingTypeColumn = new TableColumn<>("coolingType");
        coolingTypeColumn.setCellValueFactory(new PropertyValueFactory<>("coolingType"));
        coolingTypeColumn.setMaxWidth(180.00);

        TableColumn<HousingCoolingSystems, Integer> fansNumberColumn = new TableColumn<>("Fans Number");
        fansNumberColumn.setCellValueFactory(new PropertyValueFactory<>("fansNumber"));
        fansNumberColumn.setMaxWidth(188.00);

        TableColumn<HousingCoolingSystems, String> fanSizeColumn = new TableColumn<>("Fan Size");
        fanSizeColumn.setCellValueFactory(new PropertyValueFactory<>("fanSize"));
        fanSizeColumn.setMaxWidth(188.00);

        TableColumn<HousingCoolingSystems, String> fanRotationSpeedColumn = new TableColumn<>("Fan Rotation Speed");
        fanRotationSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("fanRotationSpeed"));
        fanRotationSpeedColumn.setMaxWidth(188.00);

        TableColumn<HousingCoolingSystems, String> fanLifeColumn = new TableColumn<>("Fan Life");
        fanLifeColumn.setCellValueFactory(new PropertyValueFactory<>("fanLife"));
        fanLifeColumn.setMaxWidth(188.00);

        TableColumn<HousingCoolingSystems, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setMaxWidth(188.00);

        TableColumn<HousingCoolingSystems, String> imageFileName = new TableColumn<>("imageFileName");
        imageFileName.setCellValueFactory(new PropertyValueFactory<>("imageFileName"));
        imageFileName.setMaxWidth(188.00);

        table.getColumns().addAll(
                idColumn, brandColumn,
                modelColumn, coolingTypeColumn,
                fansNumberColumn, fanSizeColumn,
                fanRotationSpeedColumn, fanLifeColumn,
                priceColumn, imageFileName
        );
        table.setMaxWidth(1500.00);

        Database db = new Database();
        table.getItems().addAll(db.getAllHousingCoolingSystems());

        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

    // таблицы Видеокарты
    private void tableVideoCards()
    {
        contentPane.getChildren().removeIf(node -> node instanceof TableView);

        TableView<VideoCards> table = new TableView<>();

        TableColumn<VideoCards, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(200.00);

        TableColumn<VideoCards, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(188.00);

        TableColumn<VideoCards, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setMaxWidth(188.00);

        TableColumn<VideoCards, String> videoChipsetColumn = new TableColumn<>("Video Chipset");
        videoChipsetColumn.setCellValueFactory(new PropertyValueFactory<>("videoChipset"));
        videoChipsetColumn.setMaxWidth(188.00);

        TableColumn<VideoCards, String> GPFColumn = new TableColumn<>("GPF");
        GPFColumn.setCellValueFactory(new PropertyValueFactory<>("gpf"));
        GPFColumn.setMaxWidth(188.00);

        TableColumn<VideoCards, String> maxResolutionColumn = new TableColumn<>("Max Resolution");
        maxResolutionColumn.setCellValueFactory(new PropertyValueFactory<>("maxResolution"));
        maxResolutionColumn.setMaxWidth(188.00);

        TableColumn<VideoCards, String> videoMemoryColumn = new TableColumn<>("Video Memory");
        videoMemoryColumn.setCellValueFactory(new PropertyValueFactory<>("videoMemory"));
        videoMemoryColumn.setMaxWidth(188.00);

        TableColumn<VideoCards, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setMaxWidth(188.00);

        TableColumn<VideoCards, String> imageFileName = new TableColumn<>("imageFileName");
        imageFileName.setCellValueFactory(new PropertyValueFactory<>("imageFileName"));
        imageFileName.setMaxWidth(188.00);

        table.getColumns().addAll(
                idColumn, brandColumn,
                modelColumn, videoChipsetColumn,
                GPFColumn, maxResolutionColumn,
                videoMemoryColumn, priceColumn,
                imageFileName
        );
        table.setMaxWidth(1500.00);

        Database db = new Database();
        table.getItems().addAll(db.getAllVideoCards());

        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

    // таблицы ОЗ
    private void tableRAM()
    {
        contentPane.getChildren().removeIf(node -> node instanceof TableView);

        TableView<RAM> table = new TableView<>();

        TableColumn<RAM, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(200.00);

        TableColumn<RAM, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(188.00);

        TableColumn<RAM, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setMaxWidth(188.00);

        TableColumn<RAM, String> formFactorColumn = new TableColumn<>("Form Factor");
        formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
        formFactorColumn.setMaxWidth(188.00);

        TableColumn<RAM, String> memoryTypeColumn = new TableColumn<>("Memory Type");
        memoryTypeColumn.setCellValueFactory(new PropertyValueFactory<>("memoryType"));
        memoryTypeColumn.setMaxWidth(188.00);

        TableColumn<RAM, String> volumeColumn = new TableColumn<>("Volume");
        volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volume"));
        volumeColumn.setMaxWidth(188.00);

        TableColumn<RAM, String> clockFrequencyColumn = new TableColumn<>("Clock Frequency");
        clockFrequencyColumn.setCellValueFactory(new PropertyValueFactory<>("clockFrequency"));
        clockFrequencyColumn.setMaxWidth(188.00);

        TableColumn<RAM, String> throughputCapacityColumn = new TableColumn<>("Throughput Capacity");
        throughputCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("throughputCapacity"));
        throughputCapacityColumn.setMaxWidth(188.00);

        TableColumn<RAM, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setMaxWidth(188.00);

        TableColumn<RAM, String> imageFileName = new TableColumn<>("imageFileName");
        imageFileName.setCellValueFactory(new PropertyValueFactory<>("imageFileName"));
        imageFileName.setMaxWidth(188.00);

        table.getColumns().addAll(
                idColumn, brandColumn,
                modelColumn, formFactorColumn,
                memoryTypeColumn, volumeColumn,
                clockFrequencyColumn, throughputCapacityColumn,
                priceColumn, imageFileName
        );
        table.setMaxWidth(1500.00);

        Database db = new Database();
        table.getItems().addAll(db.getAllRAM());

        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

    // таблицы БП
    private void tablePowerSupplyUnits()
    {
        contentPane.getChildren().removeIf(node -> node instanceof TableView);

        TableView<PowerSupplyUnits> table = new TableView<>();

        TableColumn<PowerSupplyUnits, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(200.00);

        TableColumn<PowerSupplyUnits, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(188.00);

        TableColumn<PowerSupplyUnits, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setMaxWidth(188.00);

        TableColumn<PowerSupplyUnits, String> formFactorColumn = new TableColumn<>("Form Factor");
        formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
        formFactorColumn.setMaxWidth(188.00);

        TableColumn<PowerSupplyUnits, String> powerColumn = new TableColumn<>("Power");
        powerColumn.setCellValueFactory(new PropertyValueFactory<>("Power"));
        powerColumn.setMaxWidth(188.00);

        TableColumn<PowerSupplyUnits, String> minInpVoltColumn = new TableColumn<>("Min Inp Volt");
        minInpVoltColumn.setCellValueFactory(new PropertyValueFactory<>("minInpVolt"));
        minInpVoltColumn.setMaxWidth(188.00);

        TableColumn<PowerSupplyUnits, String> maxInpVoltColumn = new TableColumn<>("Max Inp Volt");
        maxInpVoltColumn.setCellValueFactory(new PropertyValueFactory<>("maxInpVolt"));
        maxInpVoltColumn.setMaxWidth(188.00);

        TableColumn<PowerSupplyUnits, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setMaxWidth(188.00);

        TableColumn<PowerSupplyUnits, String> imageFileName = new TableColumn<>("imageFileName");
        imageFileName.setCellValueFactory(new PropertyValueFactory<>("imageFileName"));
        imageFileName.setMaxWidth(188.00);

        table.getColumns().addAll(
                idColumn, brandColumn,
                modelColumn, formFactorColumn,
                powerColumn, minInpVoltColumn,
                maxInpVoltColumn, priceColumn,
                imageFileName
        );
        table.setMaxWidth(1500.00);

        Database db = new Database();
        table.getItems().addAll(db.getAllPowerSupplyUnits());

        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

    // таблицы Корпуса
    private void tableEnclosures()
    {
        contentPane.getChildren().removeIf(node -> node instanceof TableView);

        TableView<Enclosures> table = new TableView<>();

        TableColumn<Enclosures, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(200.00);

        TableColumn<Enclosures, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(188.00);

        TableColumn<Enclosures, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setMaxWidth(188.00);

        TableColumn<Enclosures, String> sizeStandardColumn = new TableColumn<>("Size Standard");
        sizeStandardColumn.setCellValueFactory(new PropertyValueFactory<>("sizeStandard"));
        sizeStandardColumn.setMaxWidth(188.00);

        TableColumn<Enclosures, String> formFactorColumn = new TableColumn<>("Form Factor");
        formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
        formFactorColumn.setMaxWidth(188.00);

        TableColumn<Enclosures, String> BPLocationColumn = new TableColumn<>("BPLocation");
        BPLocationColumn.setCellValueFactory(new PropertyValueFactory<>("BPLocation"));
        BPLocationColumn.setMaxWidth(188.00);

        TableColumn<Enclosures, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setMaxWidth(188.00);

        TableColumn<Enclosures, String> imageFileName = new TableColumn<>("imageFileName");
        imageFileName.setCellValueFactory(new PropertyValueFactory<>("imageFileName"));
        imageFileName.setMaxWidth(188.00);

        table.getColumns().addAll(
                idColumn, brandColumn,
                modelColumn, sizeStandardColumn,
                formFactorColumn, BPLocationColumn,
                priceColumn, imageFileName
        );
        table.setMaxWidth(1500.00);

        Database db = new Database();
        table.getItems().addAll(db.getAllEnclosures());

        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

    // таблицы HDD
    private void tableHDD()
    {
        contentPane.getChildren().removeIf(node -> node instanceof TableView);

        TableView<HDD> table = new TableView<>();

        TableColumn<HDD, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(200.00);

        TableColumn<HDD, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(188.00);

        TableColumn<HDD, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setMaxWidth(188.00);

        TableColumn<HDD, String> TypeColumn = new TableColumn<>("Type");
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        TypeColumn.setMaxWidth(188.00);

        TableColumn<HDD, String> formFactorColumn = new TableColumn<>("Form Factor");
        formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
        formFactorColumn.setMaxWidth(188.00);

        TableColumn<HDD, String> capacityStorageColumn = new TableColumn<>("Capacity Storage");
        capacityStorageColumn.setCellValueFactory(new PropertyValueFactory<>("capacityStorage"));
        capacityStorageColumn.setMaxWidth(188.00);

        TableColumn<HDD, String> SpindleRotationSpeedColumn = new TableColumn<>("Spindle Rotation Speed");
        SpindleRotationSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("SpindleRotationSpeed"));
        SpindleRotationSpeedColumn.setMaxWidth(188.00);

        TableColumn<HDD, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setMaxWidth(188.00);

        TableColumn<HDD, String> imageFileName = new TableColumn<>("imageFileName");
        imageFileName.setCellValueFactory(new PropertyValueFactory<>("imageFileName"));
        imageFileName.setMaxWidth(188.00);

        table.getColumns().addAll(
                idColumn, brandColumn,
                modelColumn, TypeColumn,
                formFactorColumn, capacityStorageColumn,
                SpindleRotationSpeedColumn, priceColumn,
                imageFileName
        );
        table.setMaxWidth(1500.00);

        Database db = new Database();
        table.getItems().addAll(db.getAllHDD());

        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

    // таблицы SSD
    private void tableSSD()
    {
        contentPane.getChildren().removeIf(node -> node instanceof TableView);

        TableView<SSD> table = new TableView<>();

        TableColumn<SSD, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(200.00);

        TableColumn<SSD, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(188.00);

        TableColumn<SSD, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setMaxWidth(188.00);

        TableColumn<SSD, String> TypeColumn = new TableColumn<>("Type");
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        TypeColumn.setMaxWidth(188.00);

        TableColumn<SSD, String> capacityStorageColumn = new TableColumn<>("Capacity Storage");
        capacityStorageColumn.setCellValueFactory(new PropertyValueFactory<>("capacityStorage"));
        capacityStorageColumn.setMaxWidth(188.00);

        TableColumn<SSD, String> formFactorColumn = new TableColumn<>("Form Factor");
        formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
        formFactorColumn.setMaxWidth(188.00);

        TableColumn<SSD, String> maxReadingSpeedColumn = new TableColumn<>("Max Reading Speed");
        maxReadingSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("maxReadingSpeed"));
        maxReadingSpeedColumn.setMaxWidth(188.00);

        TableColumn<SSD, String> maxRecordingSpeedColumn = new TableColumn<>("Max Recording Speed");
        maxRecordingSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("maxRecordingSpeed"));
        maxRecordingSpeedColumn.setMaxWidth(188.00);

        TableColumn<SSD, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setMaxWidth(188.00);

        TableColumn<SSD, String> imageFileName = new TableColumn<>("imageFileName");
        imageFileName.setCellValueFactory(new PropertyValueFactory<>("imageFileName"));
        imageFileName.setMaxWidth(188.00);

        table.getColumns().addAll(
                idColumn, brandColumn,
                modelColumn, TypeColumn,
                capacityStorageColumn, formFactorColumn,
                maxReadingSpeedColumn, maxRecordingSpeedColumn,
                priceColumn, imageFileName
        );
        table.setMaxWidth(1500.00);

        Database db = new Database();
        table.getItems().addAll(db.getAllSSD());

        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

    // таблицы Материнские платы
    private void tableMotherboards()
    {
        contentPane.getChildren().removeIf(node -> node instanceof TableView);

        TableView<Motherboards> table = new TableView<>();

        TableColumn<Motherboards, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(200.00);

        TableColumn<Motherboards, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setMaxWidth(188.00);

        TableColumn<Motherboards, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setMaxWidth(188.00);

        TableColumn<Motherboards, String> formFactorColumn = new TableColumn<>("Form Factor");
        formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
        formFactorColumn.setMaxWidth(188.00);

        TableColumn<Motherboards, String> socketColumn = new TableColumn<>("Socket");
        socketColumn.setCellValueFactory(new PropertyValueFactory<>("socket"));
        socketColumn.setMaxWidth(188.00);

        TableColumn<Motherboards, String> chipsetColumn = new TableColumn<>("Chipset");
        chipsetColumn.setCellValueFactory(new PropertyValueFactory<>("chipset"));
        chipsetColumn.setMaxWidth(188.00);

        TableColumn<Motherboards, String> memoryTypeColumn = new TableColumn<>("Memory Type");
        memoryTypeColumn.setCellValueFactory(new PropertyValueFactory<>("memoryType"));
        memoryTypeColumn.setMaxWidth(188.00);

        TableColumn<Motherboards, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setMaxWidth(188.00);

        TableColumn<Motherboards, String> imageFileName = new TableColumn<>("imageFileName");
        imageFileName.setCellValueFactory(new PropertyValueFactory<>("imageFileName"));
        imageFileName.setMaxWidth(188.00);

        table.getColumns().addAll(
                idColumn, brandColumn,
                modelColumn, formFactorColumn,
                socketColumn, chipsetColumn,
                memoryTypeColumn, priceColumn,
                imageFileName
        );
        table.setMaxWidth(1500.00);

        Database db = new Database();
        table.getItems().addAll(db.getAllMotherboards());

        contentPane.getChildren().add(table);
        contentPane.setAlignment(Pos.CENTER);
    }

// -------------------------------------------------------------------

    public  static  class MyImage
    {
        public static String imageFilePath;
        //Загрузите выбранное изображение в ресурсы
        public static void loadImageIntoResources(File imageFile) {

            if (imageFile != null) {

                String imagesPath = System.getProperty("user.dir") + File.separator + "images";

                File imagesDirectory = new File(imagesPath);
                if (!imagesDirectory.exists()) {
                    imagesDirectory.mkdir();
                }

                File destFile = new File(imagesPath + File.separator + imageFile.getName());
                imageFilePath = imageFile.getName();

                try {
                    Files.copy(imageFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        public static Image getImageByName(String imageName) {
            String imagesPath = System.getProperty("user.dir") + File.separator + "images" + File.separator + imageName;

            File imageFile = new File(imagesPath);
            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                return image;
            }

            return null;
        }
    }

}