package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class SettingsController {
    private static HashMap<Button, String> settingNames = new HashMap<>();

    @FXML
    private GridPane mainGrid;

    @FXML
    private Button soundButton;
    @FXML
    private Button checkButton;

    @FXML
    public void initialize() {
        settingNames.put(soundButton, "sound");
        settingNames.put(checkButton, "check");

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(50);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(50);
        c2.setFillWidth(true);

        mainGrid.getColumnConstraints().add(c1);
        mainGrid.getColumnConstraints().add(c2);

        soundButton.setMaxWidth(Double.MAX_VALUE);
        checkButton.setMaxWidth(Double.MAX_VALUE);

        soundButton.setOnMouseClicked(this::handleSettingSwitchEnabled);
        checkButton.setOnMouseClicked(this::handleSettingSwitchEnabled);
    }

    private void handleSettingSwitchEnabled(MouseEvent e) {
        Button b = (Button)e.getSource();

        if(b.getStyleClass().contains("settingEnabled")) {
            b.getStyleClass().removeAll("settingEnabled");
            b.getStyleClass().add("settingDisabled");
            b.setText("Kikapcsolva");
            SettingsHandler.set(settingNames.get(b), false);
        }
        else {
            b.getStyleClass().removeAll("settingDisabled");
            b.getStyleClass().add("settingEnabled");
            b.setText("Bekapcsolva");
            SettingsHandler.set(settingNames.get(b), true);
        }
    }
}
