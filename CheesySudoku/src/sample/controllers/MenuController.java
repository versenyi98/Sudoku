package sample.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.Main.*;

public class MenuController {
    @FXML
    private Button newGameButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button exitButton;

    @FXML
    public void initialize() {
        newGameButton.setOnMouseClicked((e) -> {
            loadScene("../fxml/game.fxml", "sample/css/gameStyle.css");
        });

        settingsButton.setOnMouseClicked((e) -> {
            loadScene("../fxml/settings.fxml", "sample/css/settingsStyle.css");

        });

        exitButton.setOnMouseClicked((e) -> {
            Platform.exit();
        });
    }

    private void loadScene(String fxml, String style)
    {
        try {
            // cannot change "root value" of MAIN_LOADER, need a new loader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxml));
            Parent root = loader.load();
            Scene scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
            if(!(style == null || ("").equals(style))) { // to avoid null reference exceptions
                scene.getStylesheets().add(style);
            }
            mainStage.setResizable(false);
            mainStage.setTitle("CheesySudoku");
            mainStage.setScene(scene);
        }
        catch (IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, "Failed to load game: " + ex.getMessage());
        }
    }
}
