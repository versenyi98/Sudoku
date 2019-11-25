package sample.model;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TableCell extends Button {

    private int value;

    public TableCell(int val, int groupId) {
        setValue(val);
        this.value = val;
        this.getStyleClass().addAll("TableCell", "TableCell_id" + groupId);
        if (value == 0) {
            this.getStyleClass().add("ModifiableCell");
            this.setOnKeyReleased(this::handleKeyPress);
        }

    }

    private void handleKeyPress(KeyEvent code) {
        int val;

        if (code.getCode() == KeyCode.DELETE || code.getCode() == KeyCode.BACK_SPACE) {
            setValue(0);
            return;
        }
        if (code.getCode().isDigitKey() && code.getCode() != KeyCode.DIGIT0 && code.getCode() != KeyCode.NUMPAD0) {
            val = code.getCode().isKeypadKey() ? keypadToInt(code) : Integer.parseInt(code.getCode().getName());
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
