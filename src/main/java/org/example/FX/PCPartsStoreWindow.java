package org.example.FX;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import org.example.BD.Database;

public class PCPartsStoreWindow {
    private BorderPane mainPane;
    private Label title;
    private Stage stage;
    private String username; // username из LoginWindow
    public String priv; // привелегия

    public PCPartsStoreWindow(Stage primaryStage, String username) {
        this.stage = primaryStage;
        this.mainPane = new BorderPane();
        this.username = username;
    }

    public void headerPCPartsStore()
    {
        this.title = new Label("PC Parts Store");
        title.setStyle("-fx-font-size: 35px; -fx-padding: 20px;");

        Button logoutButton = new Button("Выход");
        logoutButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");

        HBox buttonTop = new HBox(35);
        buttonTop.getChildren().addAll(
                logoutButton
        );

        // Обработчик события для кнопки "Выход"
        logoutButton.setOnAction(e -> {
            LoginWindow loginWindow = new LoginWindow(stage);
            loginWindow.show();
        });

        BorderPane topBar = new BorderPane();
        topBar.setRight(buttonTop);
        topBar.setCenter(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        mainPane.setTop(topBar);
    }

    public void show()
    {
        // Карточки товаров user
        ProductCard cpuCard = new ProductCard(stage);
        cpuCard.creatingCard("Процессоры", "Processor3.jpg");
        ProductCard ProcessorCoolingSystemsCard = new ProductCard(stage);
        ProcessorCoolingSystemsCard.creatingCard("Системы охлаждения\nпроцессора", "ProcessorCoolingSystemsCard.jpg");
        ProductCard HousingCoolingSystemsCard = new ProductCard(stage);
        HousingCoolingSystemsCard.creatingCard("Системы охлаждения\nкорпуса", "HousingCoolingSystemsCard.jpg");
        ProductCard RAMCard = new ProductCard(stage);
        RAMCard.creatingCard("Оперативная память", "RAM.jpg");
        ProductCard VideoCardsCard = new ProductCard(stage);
        VideoCardsCard.creatingCard("Видеокарты", "VideoCardsCard.jpeg");
        ProductCard PowerSupplyUnitsCard = new ProductCard(stage);
        PowerSupplyUnitsCard.creatingCard("Блоки питания", "PowerSupplyUnitsCard.jpg");
        ProductCard EnclosuresCard = new ProductCard(stage);
        EnclosuresCard.creatingCard("Корпуса", "EnclosuresCard.jpg");
        ProductCard HDDCard = new ProductCard(stage);
        HDDCard.creatingCard("Жесткие диски", "HDDCard.jpg");
        ProductCard SSDCard = new ProductCard(stage);
        SSDCard.creatingCard("SSD накопители", "SSDCard.jpg");
        ProductCard MotherboardsCard = new ProductCard(stage);
        MotherboardsCard.creatingCard("Материнские платы", "MotherboardsCard.jpg");

        // Доп карточки для root
        ProductCard productDataManagement = new ProductCard(stage);
        productDataManagement.creatingCard("Управление товарами", "upravlenie_produktom_dr76ar9syzb2_256.png");
        ProductCard userDataManagement = new ProductCard(stage);
        userDataManagement.creatingCard("Управление данными пользователя", "baza_dannyh_2fbgl6usmzoi_256.png");

        // Доп карточки для analyst
        ProductCard statsDataManagement = new ProductCard(stage);
        statsDataManagement.creatingCard("Статистика", "statistika_pj1ya3eqg33j_256.png");

        Database db = new Database();

        String userPrivelege = db.getUserPrivileges(username);

        if(userPrivelege != null)
        {
            priv = userPrivelege; // запись привелегии

            if("user".equals(userPrivelege))
            {
                // для user
                TilePane productCardsUser = new TilePane();
                productCardsUser.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
                productCardsUser.setVgap(20); // Устанавливаем вертикальный отступ между элементами
                productCardsUser.setPrefColumns(4); // Устанавливаем количество колонок

                productCardsUser.getChildren().addAll(
                        cpuCard, SSDCard,
                        ProcessorCoolingSystemsCard, HousingCoolingSystemsCard,
                        RAMCard, VideoCardsCard,
                        PowerSupplyUnitsCard, EnclosuresCard,
                        HDDCard, MotherboardsCard
                );

                // вывод для user
                mainPane.setCenter(productCardsUser); // Выводим карточки товаров в центр окна
                productCardsUser.setStyle("-fx-border-width: 1px 1px 10px 10px");
            }
            else if("root".equals(userPrivelege))
            {
                TilePane productCardsRoot = new TilePane();
                productCardsRoot.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
                productCardsRoot.setVgap(20); // Устанавливаем вертикальный отступ между элементами
                productCardsRoot.setPrefColumns(4); // Устанавливаем количество колонок

                // для root пользователя
                productCardsRoot.getChildren().addAll(
                        cpuCard, SSDCard,
                        ProcessorCoolingSystemsCard, HousingCoolingSystemsCard,
                        RAMCard, VideoCardsCard,
                        PowerSupplyUnitsCard, EnclosuresCard,
                        HDDCard, MotherboardsCard,
                        productDataManagement, userDataManagement
                );

                // вывод для root
                mainPane.setCenter(productCardsRoot); // Выводим карточки товаров в центр окна
                productCardsRoot.setStyle("-fx-border-width: 1px 1px 10px 10px");
            }
            else if("analyst".equals(userPrivelege))
            {
                TilePane productCardsAnalyst = new TilePane();
                productCardsAnalyst.setHgap(20); // Устанавливаем горизонтальный отступ между элементами
                productCardsAnalyst.setVgap(20); // Устанавливаем вертикальный отступ между элементами
                productCardsAnalyst.setPrefColumns(4); // Устанавливаем количество колонок

                // для analyst пользователя
                productCardsAnalyst.getChildren().addAll(
                        cpuCard, SSDCard,
                        ProcessorCoolingSystemsCard, HousingCoolingSystemsCard,
                        RAMCard, VideoCardsCard,
                        PowerSupplyUnitsCard, EnclosuresCard,
                        HDDCard, MotherboardsCard,
                        statsDataManagement
                );

                // вывод для analyst
                mainPane.setCenter(productCardsAnalyst); // Выводим карточки товаров в центр окна
                productCardsAnalyst.setStyle("-fx-border-width: 1px 1px 10px 10px");
            }
        }
        else
        {
            RegistrationWindow registrationWindow = new RegistrationWindow(stage);
            registrationWindow.showAlertError("Unsigned username((");
        }

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public BorderPane getMainPane() {
        return mainPane;
    }
}