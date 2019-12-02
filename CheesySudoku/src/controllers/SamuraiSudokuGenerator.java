package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for generating a special game mode for Sudoku, the Samurai Sudoku.
 * Currently supporting only a "hard coded" version => no difficutlies except easy
 */
public class SamuraiSudokuGenerator extends BaseSudokuGenerator {

    /**
     * An array containing the 5 individual tables.
     */
    private int tables[][][];


    @Override
    public void setCellWidth(int width) {
        super.setCellWidth(3);
    }


    @Override
    public void setCellHeight(int height) {
        super.setCellHeight(3);
    }


    @Override
    public void setCellNumbersHorizontal(int horizontal) {
        super.setCellNumbersHorizontal(2);
    }


    @Override
    public void setCellNumbersVertical(int vertical) {
        super.setCellNumbersHorizontal(2);
    }

    /**
     * Fills the 5 blank tables.
     */
    @Override
    protected void fillBlankTable() {
        tables = new int[5][cellHeight][cellWidth];
        for (int k = 0; k < 5; k++) {
            tables[k] = new int[cellHeight][cellWidth];
            for (int i = 0; i < cellHeight; i++) {
                for (int j = 0; j < cellWidth; j++) {
                    tables[k][i][j] = -1;
                }
            }
        }
    }

    /**
     * Prints the SamuraiSudoku to the console.
     */
    public void printSudoku() {
        // Magic numbers: 2 = middle Sudoku table
        // < 2 (the tables above)
        // > 2 (the tables below)
        printTablesTop(0, 1);
        printTable(2);
        printTablesBottom(3, 4);
    }

    /**
     * Helper function which prints 2 tables which are above the middle one, in the same row.
     * @param tableIndex the first table to be printed.
     * @param tableIndex2 the second table to be printed, next to the first one.
     */
    private void printTablesTop(int tableIndex, int tableIndex2) {
        for (int j = 0; j < cellHeight; j++) {
            for (int l = 0; l < cellWidth; l++) {
                System.out.printf("%3d ", tables[tableIndex][j][l]);
                if (l == cellWidth - 1)
                    System.out.printf("%3s", (j == cellHeight - 1) ? tables[2][0][1] + " " : " ");
            }
            for (int k = 0; k < cellWidth; k++) {
                System.out.printf("%3d ", tables[tableIndex2][j][k]);
            }
        }
    }
    /**
     * Helper function which prints 2 tables which are below the middle one, in the same row.
     * @param tableIndex the first table to be printed.
     * @param tableIndex2 the second table to be printed, next to the first one.
     */
    private void printTablesBottom(int tableIndex, int tableIndex2) {
        for (int j = 0; j < cellHeight; j++) {
            for (int l = 0; l < cellWidth; l++) {
                System.out.printf("%3d ",tables[tableIndex][j][l]);
                if (l == cellWidth - 1)
                    System.out.printf("%3s", (j == 0) ? tables[2][2][1] + " ": " ");
            }
            for (int k = 0; k < cellWidth; k++) {
                System.out.printf("%3d ", tables[tableIndex2][j][k]);
            }
            System.out.println();
        }
    }

    /**
     * Helper function which prints a part of the middle table.
     * @param tableIndex the table which is going to get printed to the console.
     */
    private void printTable(int tableIndex) {
        for (int j = 1; j < cellHeight - 1; j++) {
            for (int l = 0; l < cellWidth; l++) {
                if (l == 0) {
                    printRow();
                }
                if (l == cellWidth - 1)
                    System.out.print(" ");
                System.out.printf(tables[tableIndex][j][l] + " ");
            }
            if (j != cellHeight - 1)
                System.out.println();
        }
    }

    private void printRow() {
        for (int i = 0; i < 9; i++) {
            System.out.print(" ");
        }
    }

    /**
     * Based on which table we look at, checks if a given value can be written.
     * @param tableIndex the index of the table.
     * @param posX the x position of the cell.
     * @param posY the y position of the cell.
     * @param value the value to be written.
     * @return true if can be written.
     */
    protected boolean operatorRequirement(int tableIndex, int posX, int posY, int value) {
        BaseSudokuGenerator generator;
        if (tableIndex == 2) {
            generator = new XSudokuGenerator();
        } else {
            generator = new BaseSudokuGenerator();
        }
        generator.setCellHeight(3);
        generator.setCellWidth(3);
        generator.setCellNumbersHorizontal(1);
        generator.setCellNumbersVertical(1);
        generator.setTable(tables[tableIndex]);
        return generator.operatorRequirement(posX, posY, value);
    }

    @Override
    public void generate(int cellsToRemove) {
        fillBlankTable();
        generateSolved(0, 0);
        generateUnsolved(cellsToRemove);
    }

    @Override
    protected void generateSolved(int x, int y) {

        List<Integer> numbers = new ArrayList<Integer>();
        for (int k = 0; k < tables.length; k++) {
            for (int i = 0; i < cellHeight * cellWidth; i++) {
                numbers.add(i + 1);
            }

            Collections.shuffle(numbers);


            for (int i = 0; i < numbers.size(); i++) {
                if (operatorRequirement(k, x, y, numbers.get(i))) {

                    tables[k][y][x] = numbers.get(i);
                    // Check if the first table's last cell is the one getting updated, if so
                    // update the middle table's first cell
                    if (k == 0 && y == cellHeight - 1 && x == cellWidth - 1) {
                        tables[2][0][0] = numbers.get(i);
                    }
                    // Check if the second table's last cell in the first column is the one getting updated, if so
                    // update the middle table's last cell in the first row
                    if (k == 1 && y == cellHeight - 1 && x == 0) {
                        tables[2][0][2] = numbers.get(i);
                    }
                    // Check if the fourth table's last cell in the first row is the one getting updated, if so
                    // update the middle table's first cell in the last row
                    if (k == 3 && y == 0 && x == cellWidth - 1) {
                        tables[2][2][0] = numbers.get(i);
                    }
                    // Check if the fifth table's first cell in the first row is the one getting updated, if so
                    // update the middle table's last cell in the first row
                    if (k == 4 && y == 0 && x == 0) {
                        tables[2][0][2] = numbers.get(i);
                    }
                    // Check if the middle table is getting updated, if so , update the correct table position around it
                    if (k == 2 && x != 1 && y != 1) {
                        if (x == 0 && y == 0) {
                            tables[0][2][2] = numbers.get(i);
                        } else if (x == 0 && y == 2) {
                            tables[3][0][2] = numbers.get(i);
                        } else if (x == 2 && y == 0) {
                            tables[2][2][0] = numbers.get(i);
                        } else if (x == 2 && y == 2) {
                            tables[4][0][2] = numbers.get(i);
                        }
                    }

                    generateSolved((x + 1) % cellWidth, y + ((x + 1) / cellHeight));
                    if (tables[k][y + (x + 1) / cellHeight][(x + 1) % cellWidth] != -1) {
                        return;
                    }
                }
            }
            tables[k][y][x] = -1;

        }
    }

    private boolean generateUnsolved(int cellsLeft) {
        if (cellsLeft == 0) return true;

        for (int k = 0; k < tables.length; k++) {
            List<Integer> cells = new ArrayList<Integer>();

            for (int i = 0; i < cellWidth * cellHeight; i++) {
                cells.add(i);
            }

            Collections.shuffle(cells);

            for (int i = 0; i < cells.size(); i++) {
                if (tables[k][cells.get(i) / cellWidth][cells.get(i) % cellWidth] == 0) continue;

                int save = tables[k][cells.get(i) / cellWidth][cells.get(i) % cellWidth];
                tables[k][cells.get(i) / cellWidth][cells.get(i) % cellWidth] = 0;

                if (countSolutions() == 1) {

                    boolean next = generateUnsolved(cellsLeft - 1);
                    if (next) return true;
                }

                tables[k][cells.get(i) / width][cells.get(i) % width] = save;
            }
        }
        return false;
    }

    /**
     * Not implemented yet
     * @return 1
     */
    private int countSolutions() {
        return 1;
    }

/*
    public static void main(String[] args) {
        SamuraiSudokuGenerator gen = new SamuraiSudokuGenerator();
        gen.setCellHeight(3);
        gen.setCellWidth(3);
        gen.setCellNumbersHorizontal(2);
        gen.setCellNumbersVertical(2);
        gen.fillBlankTable();
        gen.generate(0);
        gen.printSudoku();
    }
 */

}
