package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SamuraiSudokuGenerator extends BaseSudokuGenerator {

   
    public SamuraiSudokuGenerator() {}
    
    @Override
    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
        this.width = (cellWidth + 1) * cellNumbersHorizontal;
    }
    
    @Override
    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
        this.height = (cellHeight + 1) * cellNumbersVertical;
    }
    
    @Override
    public void setCellNumbersHorizontal(int cellNumbersHorizontal) {
        this.cellNumbersHorizontal = cellNumbersHorizontal;
        this.width = (cellWidth + 1) * cellNumbersHorizontal;
    }
    
    @Override
    public void setCellNumbersVertical(int cellNumbersVertical) {
        this.cellNumbersVertical = cellNumbersVertical;
        this.height = (cellHeight + 1) * cellNumbersVertical;
    }
    
    @Override
    public void printSudoku() {
        super.fillBlankTable();
        for (int i = 0; i < cellNumbersVertical; i++) {
            for (int j = 0; j < cellHeight; j++) {
                for (int k = 0; k < cellNumbersHorizontal; k++) {
                    for (int l = 0; l < cellWidth; l++) {
                        int y = i * cellHeight + j;
                        int x = k * cellWidth + l;
                        if ((k % cellWidth == 0 && k > 0) || ((i % cellHeight == 0) && i > 0))
                            System.out.print(" ");
                        else 
                            System.out.print(table[y][x] + " ");
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    @Override
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


    public void generate(int cellsToRemove) {
        if (isPropertiesSet()) {
            fillBlankTable();
            generateSolved(0, 0);
            generateUnsolved(cellsToRemove);
        }
    }

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

    @Override
    protected boolean isValid(int table[][]) {

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
    
