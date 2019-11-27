package controllers;

import java.util.*;
import javafx.util.*;

public class IrregularSudokuGenerator extends BaseSudokuGenerator {
    public IrregularSudokuGenerator() {}

    /**
     * Generates an irregular sudoku pattern after calling base class's fillBlankTable() method
     *
     * @see controllers.BaseSudokuGenerator#fillBlankTable()
     * @author Péter Versényi
     */
    @Override
    protected void fillBlankTable() {
        super.fillBlankTable();

        pattern = new int[height][width];
        connecting = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                pattern[i][j] = -1;
            }
        }

        generatePattern();
    }

    /**
     * For each empty position calculates how many other empty positions can be reached
     * without touching a non-empty position
     *
     * This is an important part of the {@generatePattern()} method.
     * @param x the x coordinate of the position
     * @param y the y coordinate of the position
     *
     * @author Péter Versényi
     */
    private void calculateConnecting(int x, int y) {

        List<Pair<Integer, Integer>> coords = new ArrayList<Pair<Integer, Integer>>();
        int pos = 0;

        coords.add(new Pair<>(x, y));

        while (pos < coords.size()) {

            int x_ = coords.get(pos).getKey();
            int y_ = coords.get(pos).getValue();

            Pair<Integer, Integer> pair = new Pair<>(x_ + 1, y_);
            if (inBounds(x_ + 1, y_) && pattern[y_][x_ + 1] == -1 && !coords.contains(pair)) {
                coords.add(pair);
            }

            pair = new Pair<>(x_ - 1, y_);
            if (inBounds(x_ - 1, y_) && pattern[y_][x_ - 1] == -1 && !coords.contains(pair)) {
                coords.add(pair);
            }

            pair = new Pair<>(x_, y_ + 1);
            if (inBounds(x_, y_ + 1) && pattern[y_ + 1][x_] == -1 && !coords.contains(pair)) {
                coords.add(pair);
            }

            pair = new Pair<>(x_, y_ - 1);
            if (inBounds(x_, y_ - 1) && pattern[y_ - 1][x_] == -1 && !coords.contains(pair)) {
                coords.add(pair);
            }
            pos++;
        }

        for (int i = 0; i < coords.size(); i++) {
            connecting[coords.get(i).getValue()][coords.get(i).getKey()] = coords.size();
        }
    }

    /**
     * Calls {@link generatePattern(int, Pair<Integer, Integer>, int, List<Pair<Integer, Integer>>)} method
     * for each distinct cell
     *
     * @author Péter Versényi
     */
    private void generatePattern() {
        Pair<Integer, Integer> currentCoord = new Pair<Integer, Integer>(-1, -1);

        for (int i = 0; i < cellWidth * cellHeight; i++) {
            for (int j = 0; j < height; j++) {
                boolean br = false;
                // finds the next empty position
                for (int k = 0; k < width; k++) {
                    if (pattern[j][k] == -1) {
                        currentCoord = new Pair<Integer, Integer>(k, j);
                        br = true;
                        break;
                    }
                }
                if (br) break;
            }
            generatePattern(i, currentCoord, 1, new ArrayList<Pair<Integer, Integer>>());
        }
    }

    /**
     * Generates the rest of the given patter
     *
     * @param currentPattern the pattern which will be generated
     * @param currentCoord current position
     * @param count represents how big is the current pattern currently
     * @param neighbours members of the current pattern
     * @return true, if the generation is successful
     *         false, if otherwise, it will cause a backtrack
     *
     * @author Péter Versényi
     */
    private boolean generatePattern(int currentPattern, Pair<Integer, Integer> currentCoord, int count, List<Pair<Integer, Integer>> neighbours) {

        int y = currentCoord.getValue();
        int x = currentCoord.getKey();

        pattern[y][x] = currentPattern;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                connecting[i][j] = 0;
            }
        }

        List<Pair<Integer, Integer>> stuck = new ArrayList<Pair<Integer, Integer>>();
        int minimumConnection = width * height + 1;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // calculate connecting value for each empty position
                // if it's not calculated yet
                if (pattern[i][j] == -1 && connecting[i][j] == 0) {
                    calculateConnecting(j, i);
                }

                // search for the group with the least empty neighbours
                if (pattern[i][j] == -1 && minimumConnection > connecting[i][j]) {
                    stuck.clear();
                    minimumConnection = connecting[i][j];
                }

                // collect the positions with the least empty neighbours
                if (pattern[i][j] == -1 && minimumConnection == connecting[i][j]) {
                    stuck.add(new Pair<Integer, Integer>(j, i));
                }
            }
        }

        // if the stucked elements can be added to the current pattern
        // add them to it, and increase the value of count
        boolean stuckSolved = false;
        if (minimumConnection + count <= cellWidth * cellHeight) {
            for (int i = 0; i < stuck.size(); i++) {
                pattern[stuck.get(i).getValue()][stuck.get(i).getKey()] = currentPattern;
            }
            count += minimumConnection;
            stuckSolved = true;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (pattern[i][j] == -1 && connecting[i][j] == 0) {
                    calculateConnecting(j, i);
                }

                // after solving the stucked elements
                // connection empty cells + the size of the current pattern
                // should give 0 remainder after division with (cellWidth * cellHeight)
                if (pattern[i][j] == -1 && (connecting[i][j] + count ) % (cellWidth * cellHeight) != 0) {
                    pattern[y][x] = -1;

                    if (stuckSolved) {
                        for (int k = 0; k < stuck.size(); k++) {
                            pattern[stuck.get(k).getValue()][stuck.get(k).getKey()] = -1;
                        }
                    }
                    return false;
                }
            }
        }

        if (count == cellWidth * cellHeight) {
            return true;
        }

        if (inBounds(x + 1, y) && pattern[y][x + 1] == -1) {
            neighbours.add(new Pair<Integer, Integer>(x + 1, y));
        }
        if (inBounds(x - 1, y) && pattern[y][x - 1] == -1) {
            neighbours.add(new Pair<Integer, Integer>(x - 1, y));
        }
        if (inBounds(x, y + 1) && pattern[y + 1][x] == -1) {
            neighbours.add(new Pair<Integer, Integer>(x, y + 1));
        }
        if (inBounds(x, y - 1) && pattern[y - 1][x] == -1) {
            neighbours.add(new Pair<Integer, Integer>(x, y - 1));
        }

        Collections.shuffle(neighbours);

        int position = 0;

        while (position < neighbours.size()) {
            if (pattern[neighbours.get(position).getValue()][neighbours.get(position).getKey()] != -1) {
                position++;
                continue;
            }
            boolean ret = generatePattern(currentPattern, neighbours.get(position), count + 1, neighbours);
            if (ret) return true;
            position++;
        }

        pattern[y][x] = -1;

        if (stuckSolved) {
            for (int k = 0; k < stuck.size(); k++) {
                pattern[stuck.get(k).getValue()][stuck.get(k).getKey()] = -1;
            }
        }
        return false;
    }

    /**
     * Decides whether the given value can be written in the given position or not
     *
     * Uses the pattern to calculate the result.
     *
     * @param posX the x coordinate of the position
     * @param posY the y coordinate of the position
     * @param value the desired value to the given position
     * @return true if the given value can be written in the given position,
     *         false otherwise
     *
     * @author Péter Versényi
     */
    @Override
    protected boolean operatorRequirement(int posX, int posY, int value) {
        for (int i = 0; i < width; i++) {
            if (table[posY][i] == value) return false;
        }

        for (int i = 0; i < height; i++) {
            if (table[i][posX] == value) return false;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (pattern[i][j] == pattern[posY][posX]) {
                    if (table[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks whether the given sudoku satisfies the rules or not
     * @param table sudoku to check
     * @return true if the given sudoku satisfies the rules,
     *         false otherwise
     *
     * @author Péter Versényi
     */
    @Override
    protected boolean isValid(int table[][]) {

        boolean[][] row = new boolean[height][width];
        boolean[][] column = new boolean[width][height];
        boolean[][] cells = new boolean[cellNumbersHorizontal * cellNumbersVertical][cellHeight * cellWidth];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (table[j][i] == 0) continue;

                if (row[j][table[j][i] - 1] || column[i][table[j][i] - 1] || cells[pattern[j][i]][table[j][i] - 1]) {
                    return false;
                }

                row[j][table[j][i] - 1] = true;
                column[i][table[j][i] - 1] = true;
                cells[pattern[j][i]][table[j][i] - 1] = true;
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
    public void printPattern() {
        for (int i = 0; i < cellNumbersVertical; i++) {
            for (int j = 0; j < cellHeight; j++) {
                for (int k = 0; k < cellNumbersHorizontal; k++) {
                    for (int l = 0; l < cellWidth; l++) {
                        System.out.print(pattern[i * cellHeight + j][k * cellWidth + l] + " ");
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public int[][] getPattern() {
        return pattern;
    }

    /**
     * For each empty positions, it contains how many empty
     * positions can be reached without touching a non-empty position
     */
    private int connecting[][];

    /**
     * The irregular pattern
     */
    private int pattern[][];
}