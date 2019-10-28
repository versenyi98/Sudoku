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

    private void fillBlankTable() {
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

    public void generate(int cellsToRemove) {
        fillBlankTable();
        generateSolved(0, 0);
        generateUnsolved(cellsToRemove);
    }

    private void generateSolved(int x, int y) {

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

    private boolean isValid(int table[][]) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = j + 1; k < width; k++) {
                    if (table[i][j] == table[i][k] && table[i][j] != 0) return false;
                }
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = j + 1; k < height; k++) {
                    if (table[j][i] == table[k][i] && table[j][i] != 0) return false;
                }
            }
        }

        for (int i = 0; i < cellNumbersVertical; i++) {
            for (int j = 0; j < cellNumbersHorizontal; j++) {
                List<Integer> cellItems = new ArrayList<Integer>();
                for (int k = 0; k < cellWidth; k++) {
                    for (int l = 0; l < cellHeight; l++) {
                        cellItems.add(table[i * cellHeight + l][j * cellWidth + k]);
                    }
                }
                Collections.sort(cellItems);

                for (int k = 0; k < cellItems.size() - 1; k++) {
                    if (cellItems.get(k) == cellItems.get(k + 1) && cellItems.get(k) != 0) return false;
                }
            }
        }

        return true;
    }

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