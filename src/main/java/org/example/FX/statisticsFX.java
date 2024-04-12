package org.example.FX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.BD.Database;
import org.example.demo.*;

import java.util.Arrays;
import java.util.List;

public class statisticsFX {

    private VBox contentPane;
    private Database db = new Database();

    public statisticsFX(VBox contentPane)
    {
        this.contentPane = contentPane;
    }

    // Статистика разделов товаров
    public void statisticsProductSections()
    {
        int productsInCP = db.countProductsInTable("cp");
        int productsInEnclosures = db.countProductsInTable("enclosures");
        int productsInHDD = db.countProductsInTable("hard_drives");
        int productsInHCS = db.countProductsInTable("housing_cooling_systems");
        int productsInMotherboards = db.countProductsInTable("motherboards");
        int productsInPSU = db.countProductsInTable("power_supply_units");
        int productsInPCS = db.countProductsInTable("processor_cooling_systems");
        int productsInRAM = db.countProductsInTable("ram");
        int productsInSSD = db.countProductsInTable("ssd");
        int productsInVC = db.countProductsInTable("video_cards");
        int productsTotalSum =  productsInCP + productsInEnclosures + productsInHDD + productsInHCS + productsInMotherboards +
                productsInPSU + productsInPCS + productsInRAM + productsInSSD + productsInVC;


        // Рассчитываем проценты для каждого раздела
        double percentInCP = (double) productsInCP / productsTotalSum * 100;
        double percentInEnclosures = (double) productsInEnclosures / productsTotalSum * 100;
        double percentInHDD = (double) productsInHDD / productsTotalSum * 100;
        double percentInHCS = (double) productsInHCS / productsTotalSum * 100;
        double percentInMotherboards = (double) productsInMotherboards / productsTotalSum * 100;
        double percentInPSU = (double) productsInPSU / productsTotalSum * 100;
        double percentInPCS = (double) productsInPCS / productsTotalSum * 100;
        double percentInRAM = (double) productsInRAM / productsTotalSum * 100;
        double percentInSSD = (double) productsInSSD / productsTotalSum * 100;
        double percentInVC = (double) productsInVC / productsTotalSum * 100;

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("CP - " + String.format("%.1f", percentInCP) + "%", productsInCP),
                new PieChart.Data("Корпуса - " + String.format("%.1f", percentInEnclosures) + "%", productsInEnclosures),
                new PieChart.Data("HDD - " + String.format("%.1f", percentInHDD) + "%", productsInHDD),
                new PieChart.Data("Системы охлаждения корпуса - " + String.format("%.1f", percentInHCS) + "%", productsInHCS),
                new PieChart.Data("Материнские платы - " + String.format("%.1f", percentInMotherboards) + "%", productsInMotherboards),
                new PieChart.Data("Блоки питания - " + String.format("%.1f", percentInPSU) + "%", productsInPSU),
                new PieChart.Data("Системы охлаждения процессора - " + String.format("%.1f", percentInPCS) + "%", productsInPCS),
                new PieChart.Data("Оперативная память - " + String.format("%.1f", percentInRAM) + "%", productsInRAM),
                new PieChart.Data("SSD накопители - " + String.format("%.1f", percentInSSD) + "%", productsInSSD),
                new PieChart.Data("Видеокарты - " + String.format("%.1f", percentInVC) + "%", productsInVC)

        );

        Label percentInCPLabel = new Label("| CP - " + productsInCP + " шт |\n");
        Label percentInEnclosuresLabel = new Label("| Корпуса - " + productsInEnclosures + " шт |\n");
        Label percentInHDDLabel = new Label("| HDD - " + productsInHDD + " шт |\n");
        Label percentInHCSLabel = new Label("| Системы охлаждения корпуса - " + productsInHCS + " шт |\n");
        Label percentInMotherboardsLabel = new Label("| Материнские платы - " + productsInMotherboards + " шт |\n");
        Label percentInPSULabel = new Label("| Блоки питания - " + productsInPSU + " шт |\n");
        Label percentInPCSLabel = new Label("| Системы охлаждения процессора - " + productsInPCS + " шт |\n");
        Label percentInRAMLabel = new Label("| Оперативная память - " + productsInRAM + " шт |\n");
        Label percentInSSDLabel = new Label("SSD накопители - " + productsInSSD + " шт |\n");
        Label percentInVCLabel = new Label("| Видеокарты - " + productsInVC + " шт |\n");

        VBox column_1 = new VBox(10);
        column_1.getChildren().addAll(
                percentInCPLabel, percentInEnclosuresLabel,
                percentInHDDLabel, percentInSSDLabel,
                percentInMotherboardsLabel
        );
        column_1.setStyle("-fx-font-size: 16px");

        VBox column_2 = new VBox(10);
        column_2.getChildren().addAll(
                percentInPSULabel, percentInPCSLabel,
                percentInRAMLabel, percentInHCSLabel,
                percentInVCLabel
        );
        column_2.setStyle("-fx-font-size: 16px");

        HBox countProd = new HBox(10);
        countProd.getChildren().addAll(
                column_1, column_2
        );
        countProd.setAlignment(Pos.CENTER);

        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Статистика разделов товаров");

        contentPane.getChildren().addAll(chart, countProd);
    }

    // Статистика пользователей
    public void statisticsUser()
    {
        int privUser = db.countUserPriv("user");
        int privRoot = db.countUserPriv("root");
        int privAnalyst = db.countUserPriv("analyst");
        int privTotalSum = privUser + privAnalyst + privRoot;

        // Рассчитываем проценты для каждого индикатора доступа пользователя
        double percentUser = (double) privUser / privTotalSum * 100;
        double percentRoot = (double) privRoot / privTotalSum * 100;
        double prcentAnalyst = (double) privAnalyst / privTotalSum * 100;

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("User - " + String.format("%.1f", percentUser) + "%", privUser),
                new PieChart.Data("Root - " + String.format("%.1f", percentRoot) + "%", privRoot),
                new PieChart.Data("Analyst - " + String.format("%.1f", prcentAnalyst) + "%", privAnalyst)
        );

        Label privUserLabel = new Label("| User - " + privUser + " шт |\n");
        Label privRootLabel = new Label("| Root - " + privRoot + " шт |\n");
        Label privAnalystLabel = new Label("| Analyst - " + privAnalyst + " шт |\n");

        VBox box = new VBox(10);
        box.getChildren().addAll(
                privUserLabel, privRootLabel, privAnalystLabel
        );
        box.setStyle("-fx-font-size: 16px");
        box.setAlignment(Pos.CENTER);

        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Статистика идентификатора пользователей");

        contentPane.getChildren().addAll(chart, box);
    }

    // Статистика товаров в выбранном разделе
    public void statisticsProductInSelectedSection()
    {
        HBox button = new HBox(10);
        // Создание кнопок для управления данными на графике
        Button btnCategoryTotalPrice = new Button("Отображения цены каждого раздела от общей цены");
        btnCategoryTotalPrice.setStyle("-fx-background-color: #0d6344; -fx-text-fill: white; -fx-font-size: 16px; ");
        Button btnProductToCategoryPrice = new Button("Отображения цены каждого продукта от цены раздела");
        btnProductToCategoryPrice.setStyle("-fx-background-color: #0d6344; -fx-text-fill: white; -fx-font-size: 16px;");

        button.getChildren().addAll(btnCategoryTotalPrice, btnProductToCategoryPrice);
        button.setStyle("-fx-padding: 10px 0 0 0;");
        button.setAlignment(Pos.CENTER);

        VBox categoriesVBox = new VBox(10);
        // Создаем ComboBox с категориями товаров
        ObservableList<String> categories = FXCollections.observableArrayList(
                "CPs", "Enclosures", "HDD", "SSD", "HousingCoolingSystems",
                "Motherboards", "PowerSupplyUnits", "ProcessorCoolingSystems",
                "RAM", "VideoCards");

        ComboBox<String> categoryComboBox = new ComboBox<>(categories);
        categoryComboBox.setPromptText("Выбери...");
        categoriesVBox.getChildren().addAll(categoryComboBox);
        categoriesVBox.setAlignment(Pos.CENTER);


        VBox categoryTotalPriceVB = new VBox(10);
        btnCategoryTotalPrice.setOnAction(e -> {
            // Логика для отображения цены каждого раздела от общей цены
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll(button);
            categoryTotalPriceVB.getChildren().clear();
            CategoryTotalPrice(categoryTotalPriceVB);
            contentPane.getChildren().addAll(categoryTotalPriceVB);
        });


        VBox ProductToCategoryPriceVB = new VBox(10);

        btnProductToCategoryPrice.setOnAction(e -> {
            contentPane.getChildren().clear();
            contentPane.getChildren().addAll(button, categoriesVBox);
            // Логика для отображения цены каждого продукта от цены раздела
            categoryComboBox.setOnAction(event -> {
                ProductToCategoryPriceVB.getChildren().clear();
                ProductToCategoryPrice(categoryComboBox.getValue(), ProductToCategoryPriceVB); // Вызываем CategoryTotalPrice с новым выбранным значением
            });
            contentPane.getChildren().addAll(ProductToCategoryPriceVB);
        });

        contentPane.getChildren().addAll(button);
    }

    private void CategoryTotalPrice(VBox root)
    {
        double totalOverallPrice = 0.0;

        // Считаем общую цену всех продуктов из всех разделов
        List<String> allCategories = Arrays.asList("CPs", "Enclosures", "HDD", "SSD", "HousingCoolingSystems",
                "Motherboards", "PowerSupplyUnits", "ProcessorCoolingSystems", "RAM", "VideoCards");

        for (String category : allCategories) {
            List<? extends Product> productsInCategory = getProductsInCategory(category);
            if (productsInCategory != null && !productsInCategory.isEmpty()) {
                for (Product product : productsInCategory) {
                    try {
                        totalOverallPrice += Double.parseDouble(product.getPrice());
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка при парсинге цены товара: Раздел - " + category + ", Бренд товара - " + product.getBrand() + ", Строка: " + product.getPrice() + ", Ошибка: " + e.getLocalizedMessage());
                        continue;
                    }
                }
            }
        }

        // Создаем данные для графика
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String category : allCategories) {
            List<? extends Product> productsInCategory = getProductsInCategory(category);
            double totalCategoryPrice = 0.0;

            if (productsInCategory != null && !productsInCategory.isEmpty()) {
                for (Product product : productsInCategory) {
                    try {
                        totalCategoryPrice += Double.parseDouble(product.getPrice());
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка при парсинге цены товара: Раздел - " + category + ", Бренд товара - " + product.getBrand() + ", Строка: " + product.getPrice() + ", Ошибка: " + e.getLocalizedMessage());
                        continue;
                    }
                }

                double percentage = (totalCategoryPrice / totalOverallPrice) * 100;
                pieChartData.add(new PieChart.Data(category + " - " + String.format("%.1f", percentage) + "%", totalCategoryPrice));
            }
        }

        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Статистика цен по разделам");


        root.getChildren().add(chart);
    }

    private void ProductToCategoryPrice(String category, VBox root)
    {
        List<? extends Product> products = getProductsInCategory(category);

        if (products == null || products.isEmpty()) {
            System.out.println("Нет товаров в разделе " + category);

        }

        double totalCategoryPrice = 0.0;
        double maxPrice = Double.MIN_VALUE;
        double minPrice = Double.MAX_VALUE;

        Product maxPriceProduct = null;
        Product minPriceProduct = null;

        // Вычисляем общую цену раздела и находим максимальную и минимальную цену
        for (Product product : products) {
            try {
                double productPrice = Double.parseDouble(product.getPrice());
                totalCategoryPrice += productPrice;

                if (productPrice > maxPrice) {
                    maxPrice = productPrice;
                    maxPriceProduct = product;
                }

                if (productPrice < minPrice) {
                    minPrice = productPrice;
                    minPriceProduct = product;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка при парсинге цены товара: Раздел - " + category + ", Бренд товара - " + product.getBrand() + ", Строка: " + product.getPrice() + ", Ошибка: " + e.getLocalizedMessage());
                continue;
            }
        }

        // Создаем текстовые метки для отображения максимальной и минимальной цен
        Label maxPriceLabel = new Label("Максимальная цена: " + maxPrice + " (" + maxPriceProduct.getBrand() + ")");
        maxPriceLabel.setStyle("-fx-font-size: 16px");
        Label minPriceLabel = new Label("Минимальная цена: " + minPrice + " (" + minPriceProduct.getBrand() + ")");
        minPriceLabel.setStyle("-fx-font-size: 16px");

        // Создаем оси графика
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Товары");
        yAxis.setLabel("Доля цены от общей цены %");

        // Создаем объект графика
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Настройка графика
        lineChart.setTitle("Цена каждого товара от общей цены по категории " + category);

        // Добавляем данные на график
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Product product : products)
        {
            try {
                double productPrice = Double.parseDouble(product.getPrice());
                double pricePercentage = (productPrice / totalCategoryPrice) * 100;
                series.getData().add(new XYChart.Data<>(product.getBrand(), pricePercentage));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка при парсинге цены товара: Раздел - " + category + ", Бренд товара - " + product.getBrand() + ", Строка: " + product.getPrice() + ", Ошибка: " + e.getLocalizedMessage());
                continue;
            }
        }

        lineChart.getData().add(series);
        root.getChildren().addAll(lineChart, maxPriceLabel, minPriceLabel);
        root.setAlignment(Pos.CENTER);
    }


    private List<? extends Product> getProductsInCategory(String category) {
        switch (category) {
            case "CPs":
                return db.getAllCPs();
            case "Enclosures":
                return db.getAllEnclosures();
            case "HDD":
                return db.getAllHDD();
            case "SSD":
                return db.getAllSSD();
            case "HousingCoolingSystems":
                return db.getAllHousingCoolingSystems();
            case "Motherboards":
                return db.getAllMotherboards();
            case "PowerSupplyUnits":
                return db.getAllPowerSupplyUnits();
            case "ProcessorCoolingSystems":
                return db.getAllProcessorCoolingSystems();
            case "RAM":
                return db.getAllRAM();
            case "VideoCards":
                return db.getAllVideoCards();
            default:
                return null;
        }
    }
}
