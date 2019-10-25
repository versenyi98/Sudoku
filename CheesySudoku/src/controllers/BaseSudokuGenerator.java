package controllers;

import java.util.*;

public class BaseSudokuGenerator {

    public BaseSudokuGenerator() {}

    private int table[][];

    private int cellWidth = -1;
    private int cellHeight = -1;
    private int cellNumbersHorizontal = -1;
    private int cellNumbersVertical = -1;
    private int width = -1;
    private int height = -1;

    public int getCellWidth() { return cellWidth; }
    public int getCellHeight() { return cellHeight; }
    public int getCellNumbersHorizontal() { return cellNumbersHorizontal; }
    public int getCellNumbersVertical() { return cellNumbersVertical; }

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

    private boolean isPropertiesSet() {
        return (cellHeight > 0 && cellHeight > 0 && cellNumbersHorizontal > 0 && cellNumbersVertical > 0);
    }

    public void fillBlankTable() {
        if (isPropertiesSet()) {

            table = new int[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width ; j++) {
                    table[i][j] = -1;
                }
            }
        }
    }

    private boolean operatorRequirement(int posX, int posY, int value) {

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

    public void printSudoku() {
        for (int i = 0; i < cellNumbersHorizontal; i++) {
            for (int j = 0; j < cellHeight; j++) {
                for (int k = 0; k < cellNumbersVertical; k++) {
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

    public void generate() {
        generateRec(0, 0);
    }

    public void generateRec(int x, int y) {

        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < cellHeight * cellWidth; i++) {
            numbers.add(i + 1);
        }

        Collections.shuffle(numbers);

        for (int i = 0; i < numbers.size(); i++) {
            if (operatorRequirement(x, y, numbers.get(i))) {

                table[y][x] = numbers.get(i);
                if (x == width - 1 && y == height - 1) return;

                generateRec((x + 1) % width, y + ((x + 1) / width));
                if (table[y + (x + 1) / width][(x + 1) % width] != -1) {
                    return;
                }
            }
        }
        table[y][x] = -1;
    }
}