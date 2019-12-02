package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final FXMLLoader MAIN_LOADER = new FXMLLoader();
    public static Stage mainStage = new Stage();

    public static int GAME_WIDTH    = 300,
                      GAME_HEIGHT   = 310;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        MAIN_LOADER.setLocation(getClass().getResource("fxml/menu.fxml"));
        Parent root = MAIN_LOADER.load();
        Scene scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
        scene.getStylesheets().add("sample/css/menuStyle.css");
        mainStage.setResizable(false);
        mainStage.setTitle("CheesySudoku");
        mainStage.setScene(scene);
        mainStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
