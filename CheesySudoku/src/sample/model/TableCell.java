package sample.model;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.controllers.GameController;


import java.util.function.Consumer;

import static sample.Main.GAME_LOADER;


public class TableCell extends Button {

    private int value;
    private GameController gc = GAME_LOADER.getController();

    public TableCell(int val, int groupId, Consumer<Integer> c) {
        setValue(val);
        this.value = val;
        this.getStyleClass().addAll("TableCell", "TableCell_id" + groupId);
        if (value == 0) {
            this.getStyleClass().add("ModifiableCell");
            this.setOnKeyReleased(e -> handleKeyPress(e, c));
            gc.validation.addEventHandler(ActionEvent.ACTION, e -> {
                if (!gc.validation.isSelected()) {
                    this.setStyle(this.getStyle() + "\n-fx-text-fill: #007cad;");
                }
            });
        }

    }

    private void handleKeyPress(KeyEvent code, Consumer<Integer> c) {
        int val;

        if (code.getCode() == KeyCode.DELETE || code.getCode() == KeyCode.BACK_SPACE) {
            setValue(0);
            c.accept(0);
            return;
        }
        if (code.getCode().isDigitKey() && code.getCode() != KeyCode.DIGIT0 && code.getCode() != KeyCode.NUMPAD0) {
            val = code.getCode().isKeypadKey() ? keypadToInt(code) : Integer.parseInt(code.getCode().getName());
            c.accept(val);
            if (gc.validation.isSelected() && !gc.validate()) {
                this.setStyle(this.getStyle() + "\n-fx-text-fill: #ad007c;");
            } else {
                this.setStyle(this.getStyle() + "\n-fx-text-fill: #007cad;");
            }
            this.setValue(val);
        }
    }

    private int keypadToInt(KeyEvent code) {
        String codeName = code.getCode().getName();
        codeName = codeName.substring(codeName.length() - 1);
        return Integer.parseInt(codeName);
    }

    public int  getValue()          { return value; }
    public void setValue(int value) {
        this.setText(value == 0 ? "" :Integer.toString(value));
        this.value = -value;
    }

}
