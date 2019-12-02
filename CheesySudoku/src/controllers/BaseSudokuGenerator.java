package controllers;

import java.util.*;
import javafx.util.*;

/**
 * This class is the generator class for Regular Sudoku
 *
 * In Regular Sudoku every element of a row / column / cell
 * is unique in that row / column / cell, or empty.
 *
 * @author Péter Versényi
 */
public class BaseSudokuGenerator {

    /**
     * This parameterless constructor creates a new instance of the {@link BaseSudokuGenerator} class.
     * Using this constructor leaves the generator uninitialized. Set dimensions via:
     * <ul>
     *     <li>{@link BaseSudokuGenerator#setCellWidth(int)}</li>
     *     <li>{@link BaseSudokuGenerator#setCellHeight(int)}</li>
     *     <li>{@link BaseSudokuGenerator#setCellNumbersHorizontal(int)}</li>
     *     <li>{@link BaseSudokuGenerator#setCellNumbersVertical(int)}</li>
     * </ul>
     *
     * @author Szilárd Ádám
     */
    public BaseSudokuGenerator() {}

    /**
     * The result of the generation
     *
     * @author Péter Versényi
     */
    protected int table[][];

    /**
     * The width of the cell
     *
     * <p>
     *     Note: Most Regular Sudokus are 9x9. This 9x9 board contains
     *     9 3x3 areas. This 3x3 areas are referenced as Cells in
     *     this project.
     * </p>
     *
     * @author Péter Versényi
     */
    protected int cellWidth = -1;

    /**
     * The height of the cell
     *
     * @author Péter Versényi
     */
    protected int cellHeight = -1;

    /**
     * The number of cells horizontally
     *
     * @author Péter Versényi
     */
    protected int cellNumbersHorizontal = -1;

    /**
     * The number of cells vertically
     *
     * @author Péter Versényi
     */
    protected int cellNumbersVertical = -1;

    /**
     * The width of the generated sudoku
     *
     * <p>
     *     Note: the value of width is equal to {@link cellWidth} * {@link cellNumbersHorizontal}
     * </p>
     *
     * @author Péter Versényi
     */
    protected int width = -1;

    /**
     * The height of the generated sudoku
     *
     * <p>
     *     Note: the value of height is equal to {@link cellHeight} * {@link cellNumbersVertical}
     * </p>
     *
     * @author Péter Versényi
     */
    protected int height = -1;

    public int getCellWidth() { return cellWidth; }
    public int getCellHeight() { return cellHeight; }
    public int getCellNumbersHorizontal() { return cellNumbersHorizontal; }
    public int getCellNumbersVertical() { return cellNumbersVertical; }
    public int getTableParam(int row, int col) { return table[row][col]; }
    public void setTableParam(int row, int col, int value) { table[row][col] = value; }
    public int[][] getTable() { return table.clone(); }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
        this.width = cellWidth * cellNumbersHorizontal;
    }
    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
        this.height = cellHeight * cellNumbersVertical;
    }

    public void setCellNumbersHorizontal(int cellNumbersHorizontal) {
        this.cellNumbersHorizontal = cellNumbersHorizontal;
        this.width = cellWidth * cellNumbersHorizontal;
    }
    public void setCellNumbersVertical(int cellNumbersVertical) {
        this.cellNumbersVertical = cellNumbersVertical;
        this.height = cellHeight * cellNumbersVertical;
    }

    /**
     * Checks whether {@link cellHeight}, {@link cellWidth}, {@link cellNumbersHorizontal} and {@link cellNumbersVertical} are set
     * @return true, if {@link cellHeight}, {@link cellWidth}, {@link cellNumbersHorizontal} and {@link cellNumbersVertical} are set
     *         false, otherwise
     *
     * @author Péter Versényi
     */
    public boolean isPropertiesSet() {
        return (cellHeight > 0 && cellWidth > 0 && cellNumbersHorizontal > 0 && cellNumbersVertical > 0);
    }

    /**
     * Initializes the {@link table}
     *
     * Set every value to -1.
     *
     * @author Péter Versényi
     */
    protected void fillBlankTable() {

        table = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                table[i][j] = -1;
            }
        }
    }

    /**
     * Decides whether the {@link table} has such an element with the given position or not
     *
     * @param x the x coordinate of the position
     * @param y the y coordinate of the position
     * @return true if the {@link table} has such an element with the given position,
     *         false otherwise
     *
     * @author Péter Versényi
     */
    public boolean inBounds(int x, int y) {
        return  (x >= 0 && x < width && y >= 0 && y < height);
    }

    /**
     * Decides whether the given value can be written in the given position or not
     *
     * @param posX the x coordinate of the position
     * @param posY the y coordinate of the position
     * @param value the desired value to the given position
     * @return true if the given value can be written in the given position,
     *         false otherwise
     *
     * @author Péter Versényi
     */
    protected boolean operatorRequirement(int posX, int posY, int value) {

        for (int i = 0; i < width; i++) {
            if (table[posY][i] == value) return false;
        }

        for (int i = 0; i < height; i++) {
            if (table[i][posX] == value) return false;
        }

        for (int i = (posX / cellWidth) * cellWidth; i < (posX / cellWidth) * cellWidth + cellWidth; i++) {
            for (int j = ((posY / cellHeight) * cellHeight); j < (posY / cellHeight) * cellHeight + cellHeight; j++){
                if (table[j][i] == value) return false;
            }
        }

        return true;
    }

    /**
     * Prints the Sudoku to terminal
     *
     * @deprecated
     * @author Péter Versényi
     */

    public void printSudoku() {
        for (int i = 0; i < cellNumbersVertical; i++) {
            for (int j = 0; j < cellHeight; j++) {
                for (int k = 0; k < cellNumbersHorizontal; k++) {
                    for (int l = 0; l < cellWidth; l++) {
                        System.out.print(table[i * cellHeight + j][k * cellWidth + l] + " ");
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * Generates the sudoku
     *
     * @param cellsToRemove the nuber of cells desired to remove
     *
     * @author Péter Versényi
     */
    public void generate(int cellsToRemove) {
        if (isPropertiesSet()) {
            fillBlankTable();
            generateSolved(0, 0);
            generateUnsolved(cellsToRemove);
        }
    }

    /**
     * Generates the solved sudoku
     *
     * This recursive function fills every position
     * one by one.
     * <p>
     *     Note: Solved sudoku means sudoku with
     *     no elements missing.
     * </p>
     * @param x the x coordinate of the position
     * @param y the y coordinate of the position
     *
     * @author Péter Versényi
     */
    protected void generateSolved(int x, int y) {

        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < cellHeight * cellWidth; i++) {
            numbers.add(i + 1);
        }

        Collections.shuffle(numbers);

        for (int i = 0; i < numbers.size(); i++) {
            if (operatorRequirement(x, y, numbers.get(i))) {

                table[y][x] = numbers.get(i);
                if (x == width - 1 && y == height - 1) return;

                generateSolved((x + 1) % width, y + ((x + 1) / width));
                if (table[y + (x + 1) / width][(x + 1) % width] != -1) {
                    return;
                }
            }
        }
        table[y][x] = -1;
    }

    /**
     * Checks whether the given sudoku satisfies the rules or not
     * @param table sudoku to check
     * @return true if the given sudoku satisfies the rules,
     *         false otherwise
     *
     * @author Péter Versényi
     */
    public boolean isValid(int table[][]) {

        boolean[][] row = new boolean[height][width];
        boolean[][] column = new boolean[width][height];
        boolean[][] cells = new boolean[cellNumbersHorizontal * cellNumbersVertical][cellHeight * cellWidth];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (table[j][i] == 0) continue;

                int cellNumber = (i / cellWidth) * cellNumbersHorizontal + (j / cellHeight);
                if (row[j][table[j][i] - 1] || column[i][table[j][i] - 1] || cells[cellNumber][table[j][i] - 1]) {
                    return false;
                }

                row[j][table[j][i] - 1] = true;
                column[i][table[j][i] - 1] = true;
                cells[cellNumber][table[j][i] - 1] = true;
            }
        }

        return true;
    }

    /**
     * This recursive function removes some elements to create an unsolved Sudoku
     *
     * @param cellsLeft number of cells to remove from now on
     * @return true if cellsLeft is 0,
     *         false if the element cannot be removed,
     *         {@link generateUnsolved(cellsLeft - 1)} otherwise
     *
     * @author Péter Versényi
     */
    private boolean generateUnsolved(int cellsLeft) {
        if (cellsLeft == 0) return true;

        List<Integer> cells = new ArrayList<Integer>();

        for (int i = 0; i < width * height; i++) {
            cells.add(i);
        }

        Collections.shuffle(cells);

        for (int i = 0; i < cells.size(); i++) {
            if (table[cells.get(i) / width][cells.get(i) % width] == 0) continue;

            int save = table[cells.get(i) / width][cells.get(i) % width];
            table[cells.get(i) / width][cells.get(i) % width] = 0;

            if (countSolutions() == 1) {

                boolean next = generateUnsolved(cellsLeft - 1);
                if (next) return true;
            }

            table[cells.get(i) / width][cells.get(i) % width] = save;
        }

        return false;
    }

    /**
     * Counts the number of possible solutions with the current state of the {@link table}
     * @return the number of possible solutions
     *
     * @author Péter Versényi
     */
    private int countSolutions() {

        int returnValue = 0;

        List<Integer> emptyCells = new ArrayList<Integer>();
        List<Integer> values = new ArrayList<Integer>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (table[i][j] == 0) {
                    emptyCells.add(i * width + j);
                    values.add(1);
                }
            }
        }

        while (true) {
            int tableCopy[][] = new int[height][width];

            for (int k = 0; k < height; k++) {
                for (int l = 0; l < width; l++) {
                    tableCopy[k][l] = table[k][l];
                }
            }

            int actual = 0;
            for (int j = 0; j < emptyCells.size(); j++) {
                actual = j;
                tableCopy[emptyCells.get(j) / width][emptyCells.get(j) % width] = values.get(j);
                if (!isValid(tableCopy)) break;
            }

            while (true) {
                if (actual == -1) return returnValue;

                if (values.get(actual) == cellWidth * cellHeight) {
                    actual--;
                } else {
                    values.set(actual, values.get(actual) + 1);
                    for (int j = actual + 1; j < values.size(); j++) {
                        values.set(j, 1);
                    }
                    break;
                }
            }

            if (!isValid(tableCopy)) continue;
            returnValue++;
        }
    }
}