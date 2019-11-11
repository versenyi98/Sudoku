package controllers;

import java.util.*;
import javafx.util.*;

public class IrregularSudokuGenerator extends BaseSudokuGenerator {
    public IrregularSudokuGenerator() {}

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

    private void generatePattern() {
        Pair<Integer, Integer> currentCoord = new Pair<Integer, Integer>(-1, -1);

        for (int i = 0; i < cellWidth * cellHeight; i++) {
            for (int j = 0; j < height; j++) {
                boolean br = false;
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

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(pattern[i][j]);
            }
            System.out.println();
        }
    }

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
                if (pattern[i][j] == -1 && connecting[i][j] == 0) {
                    calculateConnecting(j, i);
                }
                if (pattern[i][j] == -1 && minimumConnection > connecting[i][j]) {
                    stuck.clear();
                    minimumConnection = connecting[i][j];
                }
                if (pattern[i][j] == -1 && minimumConnection == connecting[i][j]) {
                    stuck.add(new Pair<Integer, Integer>(j, i));
                }
            }
        }

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

    public int[][] getPattern() {
        return pattern;
    }

    private int connecting[][];
    private int pattern[][];
}