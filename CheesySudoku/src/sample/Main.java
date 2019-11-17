package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final FXMLLoader MAIN_LOADER = new FXMLLoader();
    public static Stage mainStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        MAIN_LOADER.setLocation(getClass().getResource("game.fxml"));
        Parent root = MAIN_LOADER.load();
        Scene scene = new Scene(root, 300, 320);
        scene.getStylesheets().add("sample/mainStyle.css");

        mainStage.setTitle("CheesySudoku");
        mainStage.setScene(scene);
        mainStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
