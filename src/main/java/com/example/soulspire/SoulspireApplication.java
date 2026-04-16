package com.example.soulspire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SoulspireApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Vytvoříme pomocné objekty, které tvoje menu potřebuje
        // Musíme jim dát to, co vyžadují v konstruktoru (podle tvých screenshotů)

        com.example.soulspire.Core.InputHandler inputHandler = new com.example.soulspire.Core.InputHandler();
        com.example.soulspire.Core.GameEngine engine = new com.example.soulspire.Core.GameEngine(inputHandler);

        javafx.scene.layout.StackPane root = new javafx.scene.layout.StackPane();
        com.example.soulspire.UI.ScreenManager screenManager = new com.example.soulspire.UI.ScreenManager(root, engine);

        // Teď vytvoříme samotné menu
        com.example.soulspire.UI.MainMenuScreen mainMenu = new com.example.soulspire.UI.MainMenuScreen(engine, screenManager);

        // Zobrazíme to
        Scene scene = new Scene(mainMenu, 800, 600);
        stage.setTitle("SoulSpire - Preview");
        stage.setScene(scene);
        stage.show();
    }
}
