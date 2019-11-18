package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import controllers.*;

import java.util.HashMap;

public class Main extends Application {

    private HashMap<String, Scene> scenes = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        scenes.put("mainmenu", new Scene(root, 300, 275));

        primaryStage.setTitle("Cheesy Sudoku - every sudoku game in one place");
        primaryStage.setScene(scenes.get("mainmenu"));
        primaryStage.show();

        Controller c = loader.getController();
        c.setMenuClicks();
        for (Node menubutton : c.main_menu_grid.getChildren()) {
            GridPane.setHalignment(menubutton, HPos.CENTER);
        }


        BaseSudokuGenerator generator = new IrregularSudokuGenerator();

        generator.setCellWidth(3);
        generator.setCellHeight(3);
        generator.setCellNumbersHorizontal(3);
        generator.setCellNumbersVertical(3);

        generator.generate(30);
        generator.printSudoku();
    }

    private void goToNewGameScene() {
        Scene s;
        if(scenes.containsKey("newgame")) {
            s = scenes.get("newgame");
        }
        else {

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
