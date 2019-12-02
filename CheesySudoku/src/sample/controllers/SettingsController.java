package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            writeSetting(settingNames.get(b), false);
        }
        else {
            b.getStyleClass().removeAll("settingDisabled");
            b.getStyleClass().add("settingEnabled");
            b.setText("Bekapcsolva");
            writeSetting(settingNames.get(b), true);
        }
    }

    private void writeSetting(String setting, boolean value)
    {
        try {
            ArrayList<String> lines = new ArrayList<>();
            int neededLine = -1;
            if(new File("settings.txt").exists()) {
                FileReader reader = new FileReader("settings.txt");
                BufferedReader breader = new BufferedReader(reader);
                int lineNum = 0;
                String line;

                while ((line = breader.readLine()) != null) {
                    lines.add(line);
                    if(line.split("=")[0].equals(setting)) {
                        neededLine = lineNum;
                    }
                    lineNum ++;
                }

                breader.close();
            }


            FileWriter writer = new FileWriter("settings.txt", false);
            for (int i = 0; i < lines.size(); i ++) {
                if(i != neededLine) {
                    writer.write(lines.get(i) + "\n");
                }
                else {
                    writer.write(lines.get(i).split("=")[0] + "=" + (value ? "true" : "false") + "\n");
                }
            }
            if(neededLine == -1) {
                writer.write(setting + "=" + (value ? "true" : "false"));
            }
            writer.close();
        }
        catch (IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, "Cannot save settings.");
        }
    }
}
