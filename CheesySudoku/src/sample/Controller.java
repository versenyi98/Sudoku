package sample;

import controllers.BaseSudokuGenerator;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private AnchorPane tableHolder;

    private final BaseSudokuGenerator bsg = new BaseSudokuGenerator();
    private final int CELL_WIDTH = 3;
    private final int CELL_HEIGHT = 3;
    private final int CELL_NUMBERS_HORIZONTAL = 3;
    private final int CELL_NUMBERS_VERTICAL = 3;
    private final int WIDTH = CELL_WIDTH * CELL_NUMBERS_HORIZONTAL;
    private final int HEIGHT = CELL_HEIGHT * CELL_NUMBERS_VERTICAL;

    @FXML
    private void initialize() {
        prepareGenerator();

        TableCell temp;
        for (int col = 0; col < HEIGHT; col++) {
            for (int row = 0; row < WIDTH; row++) {
                temp = new TableCell(bsg.getTableParam(row,col), getGroupId(col, row));
                AnchorPane.setTopAnchor(temp, (double)col*30);
                AnchorPane.setLeftAnchor(temp, (double)row*30);
                tableHolder.getChildren().add(temp);
            }
        }
    }

    @FXML
    private void closeGame() {
        Main.mainStage.close();
    }

    private int getGroupId(int col, int row) {
        int x = col / 3;
        int y = row / 3;
        return (x+y)%2;
    }

    private void prepareGenerator() {
        bsg.setCellWidth(CELL_WIDTH);
        bsg.setCellHeight(CELL_HEIGHT);
        bsg.setCellNumbersHorizontal(CELL_NUMBERS_HORIZONTAL);
        bsg.setCellNumbersVertical(CELL_NUMBERS_VERTICAL);
        bsg.generate(30);
    }
}
