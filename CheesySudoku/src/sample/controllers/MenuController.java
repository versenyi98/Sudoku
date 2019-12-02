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
            try {
                // cannot change "root value" of MAIN_LOADER, need a new loader
                FXMLLoader loader = new FXMLLoader();
                // we are in the sample/controllers directory, fxml file is in the sample/fxml directory
                loader.setLocation(getClass().getResource("../fxml/game.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
                scene.getStylesheets().add("sample/css/gameStyle.css");
                mainStage.setResizable(false);
                mainStage.setTitle("CheesySudoku");
                mainStage.setScene(scene);
            }
            catch (IOException ex) {
                Logger.getGlobal().log(Level.SEVERE, "Failed to load game: " + ex.getMessage());
            }
            catch (IllegalStateException ex) {
                System.out.println(ex.getMessage() + ex.toString());
            }
        });

        exitButton.setOnMouseClicked((e) -> {
            Platform.exit();
        });
    }
}
