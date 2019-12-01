package test;

import org.junit.Test;
import static org.junit.Assert.*;

import controllers.XSudokuGenerator;

public class XSudokuGeneratorTest {
    /* ----------------------------------------------------
     * --- tests inherited from BaseSudokuGeneratorTest ---
     * ---------------------------------------------------- */
    @Test
    public void isPropertiesSetTest() {
        XSudokuGenerator generator = new XSudokuGenerator();

        assertFalse(generator.isPropertiesSet());

        generator.setCellWidth(6);
        generator.setCellHeight(6);
        generator.setCellNumbersHorizontal(6);
        generator.setCellNumbersVertical(6);

        assertTrue(generator.isPropertiesSet());
    }

    @Test
    public void inBoundsTest() {
        XSudokuGenerator generator = new XSudokuGenerator();
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

    /* ------------------------------------------
     * --- tests specific to XSudokuGenerator ---
     * ------------------------------------------ */

    @Test
    public void widthHeightMustEqualTest()
    {
        XSudokuGenerator generator = new XSudokuGenerator();

        generator.setCellWidth(3);
        generator.setCellHeight(3);
        generator.setCellNumbersHorizontal(2);
        generator.setCellNumbersVertical(3);

        assertThrows("Width-height equals test fails when w < h.", IndexOutOfBoundsException.class, () -> generator.generate(10));

        generator.setCellNumbersHorizontal(3);
        generator.setCellNumbersVertical(2);

        assertThrows("Width-height equals test fails when w > h.", IndexOutOfBoundsException.class, () -> generator.generate(10));

        generator.setCellNumbersHorizontal(3);
        generator.setCellNumbersVertical(3);

        try
        {
            generator.generate(10);
        }
        catch (IndexOutOfBoundsException ex)
        {
            fail("Width-Height equals test fails on equal width and height.");
        }
    }

    @Test
    public void isValidTest() {
        XSudokuGenerator generator = new XSudokuGenerator();
        generator.setCellWidth(3);
        generator.setCellHeight(3);
        generator.setCellNumbersHorizontal(3);
        generator.setCellNumbersVertical(3);

        int[][] table1 = {
                {1, 2, 3, 4, 5, 6, 7 , 8, 9},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0}};

        int[][] table2 = {
                {1, 2, 3, 4, 5, 6, 7 , 8, 9},
                {0, 0, 0, 0, 0, 0, 0 , 0, 1},
                {0, 0, 0, 0, 0, 0, 0 , 0, 2},
                {0, 0, 0, 0, 0, 0, 0 , 0, 3},
                {0, 0, 0, 0, 0, 0, 0 , 0, 4},
                {0, 0, 0, 0, 0, 0, 0 , 0, 5},
                {0, 0, 0, 0, 0, 0, 0 , 0, 6},
                {0, 0, 0, 0, 0, 0, 0 , 0, 7},
                {0, 0, 0, 0, 0, 0, 0 , 0, 8}};

        int[][] table3 = {
                {1, 8, 7, 0, 0, 0, 0 , 0, 0},
                {2, 9, 6, 0, 0, 0, 0 , 0, 0},
                {3, 4, 5, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 1, 6, 7, 0 , 0, 0},
                {0, 0, 0, 2, 5, 8, 0 , 0, 0},
                {0, 0, 0, 3, 4, 9, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 1 , 5, 6},
                {0, 0, 0, 0, 0, 0, 2 , 4, 7},
                {0, 0, 0, 0, 0, 0, 3 , 9, 8}};

        int[][] table4 = {
                {1, 2, 3, 4, 5, 6, 7 , 8, 1},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0}};

        int[][] table5 = {
                {1, 2, 3, 4, 5, 6, 7 , 8, 9},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 9}};

        int[][] table6 = {
                {1, 8, 7, 0, 0, 0, 0 , 0, 0},
                {2, 9, 6, 0, 0, 0, 0 , 0, 0},
                {3, 4, 5, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 1, 6, 7, 0 , 0, 0},
                {0, 0, 0, 2, 4, 8, 0 , 0, 0},
                {0, 0, 0, 3, 4, 9, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 1 , 5, 6},
                {0, 0, 0, 0, 0, 0, 2 , 4, 7},
                {0, 0, 0, 0, 0, 0, 3 , 9, 8}};

        int[][] table7 = {
                {1, 8, 7, 0, 0, 0, 0 , 0, 0},
                {2, 9, 6, 0, 0, 0, 0 , 0, 0},
                {3, 4, 5, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 2, 5, 7, 0 , 0, 0},
                {0, 0, 0, 1, 6, 8, 0 , 0, 0},
                {0, 0, 0, 3, 9, 4, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 3 , 5, 6},
                {0, 0, 0, 0, 0, 0, 2 , 7, 4},
                {0, 0, 0, 0, 0, 0, 1 , 9, 8}};

        int[][] table8 = {
                {1, 8, 7, 0, 0, 0, 0, 0, 3},
                {2, 9, 6, 0, 0, 0, 0, 1, 0},
                {3, 4, 5, 0, 0, 0, 8, 0, 0},
                {0, 0, 0, 2, 5, 7, 0, 0, 0},
                {0, 0, 0, 1, 6, 8, 0, 0, 0},
                {0, 0, 0, 3, 9, 4, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 3, 5, 6},
                {0, 5, 0, 0, 0, 0, 2, 7, 4},
                {4, 0, 0, 0, 0, 0, 1, 9, 8}};

        int[][] table9 = {
                {1, 8, 7, 0, 0, 0, 0, 0, 9},
                {2, 9, 6, 0, 0, 0, 0, 1, 0},
                {3, 4, 5, 0, 0, 0, 8, 0, 0},
                {0, 0, 0, 2, 5, 7, 0, 0, 0},
                {0, 0, 0, 1, 6, 8, 0, 0, 0},
                {0, 0, 0, 3, 9, 4, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 3, 5, 6},
                {0, 5, 0, 0, 0, 0, 2, 7, 4},
                {4, 0, 0, 0, 0, 0, 1, 9, 8}};

        assertTrue(generator.isValid(table1));
        assertTrue(generator.isValid(table2));
        assertFalse(generator.isValid(table3));
        assertFalse(generator.isValid(table4));
        assertFalse(generator.isValid(table5));
        assertFalse(generator.isValid(table6));
        assertTrue(generator.isValid((table7)));
        assertFalse(generator.isValid(table8));
        assertTrue(generator.isValid(table9));
    }
}
