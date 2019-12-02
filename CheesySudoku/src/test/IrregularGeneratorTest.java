package test;

import controllers.IrregularSudokuGenerator;
import controllers.XSudokuGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class IrregularGeneratorTest {
    /* ----------------------------------------------------
     * --- tests inherited from BaseSudokuGeneratorTest ---
     * ---------------------------------------------------- */
    @Test
    public void isPropertiesSetTest() {
        IrregularSudokuGenerator generator = new IrregularSudokuGenerator();

        assertFalse(generator.isPropertiesSet());

        generator.setCellWidth(6);
        generator.setCellHeight(6);
        generator.setCellNumbersHorizontal(6);
        generator.setCellNumbersVertical(6);

        assertTrue(generator.isPropertiesSet());
    }

    @Test
    public void inBoundsTest() {
        IrregularSudokuGenerator generator = new IrregularSudokuGenerator();
        assertFalse(generator.inBounds(0, 0));

        generator.setCellWidth(3);
        generator.setCellHeight(3);
        generator.setCellNumbersHorizontal(3);
        generator.setCellNumbersVertical(3);

        assertTrue(generator.inBounds(0, 0));
        assertTrue(generator.inBounds(5, 5));
        assertFalse(generator.inBounds(-1, 0));
        assertFalse(generator.inBounds(9, 0));
    }

    @Test
    public void isValidTest() {
        IrregularSudokuGenerator generator = new IrregularSudokuGenerator();
        generator.setCellWidth(3);
        generator.setCellHeight(3);
        generator.setCellNumbersHorizontal(3);
        generator.setCellNumbersVertical(3);

        generator.generate(20);
        int[][] table1 = new int[9][9];

        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                table1[row][col] = generator.getTableParam(row, col);
            }
        }
        assertTrue(generator.isValid(table1));

        int table2[][] = {
                {1, 1, 1, 4, 5, 6, 7, 8, 9},
                {1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 9}};

        int table3[][] = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        int table4[][] = {
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 4, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 5, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 6, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 7, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 8, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 9}};

        int table5[][] = {
                {1, 4, 8, 2, 5, 6, 3, 7, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        assertFalse(generator.isValid(table2));
        assertTrue(generator.isValid(table3));
        assertTrue(generator.isValid(table4));
        assertTrue(generator.isValid(table5));
    }
}
