package sample.controllers;

import controllers.BaseSudokuGenerator;
import controllers.IrregularSudokuGenerator;
import controllers.XSudokuGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import sample.model.Difficulty;
import sample.Main;
import sample.model.TableCell;

import java.util.HashMap;

public class GameController {

    @FXML
    private AnchorPane tableHolder;

    @FXML
    private RadioMenuItem soundSetting;
    @FXML
    private RadioMenuItem checkSetting;

    public final BaseSudokuGenerator bsg = new BaseSudokuGenerator();
    public final IrregularSudokuGenerator isg = new IrregularSudokuGenerator();
    public final XSudokuGenerator xsg = new XSudokuGenerator();
    private final int CELL_WIDTH = 3;
    private final int CELL_HEIGHT = 3;
    private final int CELL_NUMBERS_HORIZONTAL = 3;
    private final int CELL_NUMBERS_VERTICAL = 3;
    private final int WIDTH = CELL_WIDTH * CELL_NUMBERS_HORIZONTAL;
    private final int HEIGHT = CELL_HEIGHT * CELL_NUMBERS_VERTICAL;
    private Difficulty.value difficulty = Difficulty.value.MEDIUM;
    private int game_type = 0;

    @FXML
    private void initialize() {
        prepareGenerator();
        newGame();
        readSettings();
    }

    private void readSettings() {
        HashMap<String, Boolean> settings = SettingsHandler.getAll();
        soundSetting.setSelected(settings.get("sound"));
        checkSetting.setSelected(settings.get("check"));

        soundSetting.selectedProperty().addListener((selected) -> SettingsHandler.set("sound", soundSetting.isSelected()));
        checkSetting.selectedProperty().addListener((selected) -> SettingsHandler.set("check", checkSetting.isSelected()));
    }

    @FXML
    private void closeGame() {
        Main.mainStage.close();
    }

    @FXML
    private void newGame() {
        if (game_type == 0) {
            generateNewBaseSudoku(difficulty);
        } else if (game_type == 1) {
            generateNewIrregularSudoku(difficulty);
        } else {
            generateXSudoku(difficulty);
        }
    }

    @FXML
    private void switchBase() {
        game_type = 0;
        newGame();
    }

    @FXML
    private void switchIrregular() {
        game_type = 1;
        newGame();
    }

    @FXML
    private void switchX() {
        game_type = 2;
        newGame();
    }

    @FXML
    private void switchEasy() {
        difficulty = Difficulty.value.EASY;
        newGame();
    }

    @FXML
    private void switchMedium() {
        difficulty = Difficulty.value.MEDIUM;
        newGame();
    }

    @FXML
    private void switchHard() {
        difficulty = Difficulty.value.HARD;
        newGame();
    }

    @FXML
    private void switchExtreme() {
        difficulty = Difficulty.value.EXTREME;
        newGame();
    }

    private void generateNewBaseSudoku(Difficulty.value d) {
        tableHolder.getChildren().clear();
        bsg.generate(Difficulty.Convert.toInt(d));
        TableCell temp;
        for (int col = 0; col < HEIGHT; col++) {
            for (int row = 0; row < WIDTH; row++) {
                final int r = row;
                final int c = col;
                temp = new TableCell(bsg.getTableParam(row, col), getGroupId(row, col),
                        e -> bsg.setTableParam(r, c, e));
                AnchorPane.setTopAnchor(temp, (double)col*30);
                AnchorPane.setLeftAnchor(temp, (double)row*30);
                tableHolder.getChildren().add(temp);
            }
        }
    }

    private void generateNewIrregularSudoku(Difficulty.value d) {
        tableHolder.getChildren().clear();
        isg.generate(Difficulty.Convert.toInt(d));
        TableCell temp;
        for (int col = 0; col < HEIGHT; col++) {
            for (int row = 0; row < WIDTH; row++) {
                final int r = row;
                final int c = col;
                temp = new TableCell(isg.getTableParam(row,col), isg.getPattern()[row][col],
                        e -> isg.setTableParam(r, c, e));
                AnchorPane.setTopAnchor(temp, (double)col*30);
                AnchorPane.setLeftAnchor(temp, (double)row*30);
                tableHolder.getChildren().add(temp);
            }
        }
    }

    private void generateXSudoku(Difficulty.value d) {
        tableHolder.getChildren().clear();
        xsg.generate(Difficulty.Convert.toInt(d));
        TableCell temp;
        for (int col = 0; col < HEIGHT; col++) {
            for (int row = 0; row < WIDTH; row++) {
                final int r = row;
                final int c = col;
                temp = new TableCell(xsg.getTableParam(row,col), getGroupId(row, col),
                        e -> xsg.setTableParam(r, c, e));
                AnchorPane.setTopAnchor(temp, (double)col*30);
                AnchorPane.setLeftAnchor(temp, (double)row*30);
                tableHolder.getChildren().add(temp);
            }
        }
        Line l1 = new Line(4,4,268,268);
        Line l2 = new Line(268,5, 4,269);
        l1.setStyle("-fx-stroke: #45454520");
        l2.setStyle("-fx-stroke: #45454520");
        tableHolder.getChildren().addAll(l1, l2);
    }

    private int getGroupId(int row, int col) {
        int x = col / 3;
        int y = row / 3;
        return (x+y)%2;
    }

    private void prepareGenerator() {
        bsg.setCellWidth(CELL_WIDTH);
        bsg.setCellHeight(CELL_HEIGHT);
        bsg.setCellNumbersHorizontal(CELL_NUMBERS_HORIZONTAL);
        bsg.setCellNumbersVertical(CELL_NUMBERS_VERTICAL);

        isg.setCellWidth(CELL_WIDTH);
        isg.setCellHeight(CELL_HEIGHT);
        isg.setCellNumbersHorizontal(CELL_NUMBERS_HORIZONTAL);
        isg.setCellNumbersVertical(CELL_NUMBERS_VERTICAL);

        xsg.setCellWidth(CELL_WIDTH);
        xsg.setCellHeight(CELL_HEIGHT);
        xsg.setCellNumbersHorizontal(CELL_NUMBERS_HORIZONTAL);
        xsg.setCellNumbersVertical(CELL_NUMBERS_VERTICAL);
    }

    public boolean validate() {
        if (game_type == 0) {
            return bsg.isValid(bsg.getTable());
        } else if (game_type == 1) {
            return isg.isValid((isg.getTable()));
        } else {
            return xsg.isValid((xsg.getTable()));
        }
    }
}
