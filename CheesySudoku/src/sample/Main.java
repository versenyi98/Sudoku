package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import controllers.*;

import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {

    private HashMap<String, Scene> scenes = new HashMap<>();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        scenes.put("mainmenu", new Scene(root, 300, 275));

        primaryStage.setTitle("Cheesy Sudoku - every sudoku game in one place");
        primaryStage.setScene(scenes.get("mainmenu"));
        primaryStage.show();

        setupMainMenu(loader.getController());

        BaseSudokuGenerator generator = new IrregularSudokuGenerator();

        generator.setCellWidth(3);
        generator.setCellHeight(3);
        generator.setCellNumbersHorizontal(3);
        generator.setCellNumbersVertical(3);

        generator.generate(30);
        generator.printSudoku();
    }

    private void setupMainMenu(Controller c)
    {
        for (Node menubutton : c.main_menu_grid.getChildren()) {
            GridPane.setHalignment(menubutton, HPos.CENTER);
        }

        c.new_game_button.setOnMouseClicked((MouseEvent e) -> {
            goToNewGameScene();
        });

        c.exit_button.setOnMouseClicked((MouseEvent e) -> {
            Platform.exit();
        });
    }

    private boolean goToNewGameScene() {
        Scene s;
        if(scenes.containsKey("newgame")) {
            s = scenes.get("newgame");
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.new_game.fxml"));
                Parent root = loader.load();
                s = new Scene(root, 300, 275);
                scenes.put("newgame", s);

            }
            catch (IOException e) {
                return false;
            }
        }

        primaryStage.setScene(s);

        return true;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
