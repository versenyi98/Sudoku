package test;

import org.junit.Test;
import static org.junit.Assert.*;

import controllers.*;

public class BaseSudokuGeneratorTest {

    @Test
    public void isProperiesSetTest() {
        BaseSudokuGenerator generator = new BaseSudokuGenerator();

        assertFalse(generator.isPropertiesSet());

        generator.setCellWidth(6);
        generator.setCellHeight(6);
        generator.setCellNumbersHorizontal(6);
        generator.setCellNumbersVertical(6);

        assertTrue(generator.isPropertiesSet());
    }

    @Test
    public void inBoundsTest() {
        BaseSudokuGenerator generator = new BaseSudokuGenerator();
        assertFalse(generator.inBounds(0, 0));

        generator.setCellWidth(6);
        generator.setCellHeight(6);
        generator.setCellNumbersHorizontal(6);
        generator.setCellNumbersVertical(6);

        assertTrue(generator.inBounds(0, 0));
        assertTrue(generator.inBounds(5, 5));
        assertFalse(generator.inBounds(-1, 0));
        assertFalse(generator.inBounds(36, 0));
    }

    @Test
    public void isValidTest() {
        BaseSudokuGenerator generator = new BaseSudokuGenerator();
        generator.setCellWidth(3);
        generator.setCellHeight(3);
        generator.setCellNumbersHorizontal(3);
        generator.setCellNumbersVertical(3);

        int table1[][] = {
                {1, 2, 3, 4, 5, 6, 7 , 8, 9},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0}};

        int table2[][] = {
                {1, 2, 3, 4, 5, 6, 7 , 8, 9},
                {0, 0, 0, 0, 0, 0, 0 , 0, 1},
                {0, 0, 0, 0, 0, 0, 0 , 0, 2},
                {0, 0, 0, 0, 0, 0, 0 , 0, 3},
                {0, 0, 0, 0, 0, 0, 0 , 0, 4},
                {0, 0, 0, 0, 0, 0, 0 , 0, 5},
                {0, 0, 0, 0, 0, 0, 0 , 0, 6},
                {0, 0, 0, 0, 0, 0, 0 , 0, 7},
                {0, 0, 0, 0, 0, 0, 0 , 0, 8}};

        int table3[][] = {
                {1, 8, 7, 0, 0, 0, 0 , 0, 0},
                {2, 9, 6, 0, 0, 0, 0 , 0, 0},
                {3, 4, 5, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 1, 6, 7, 0 , 0, 0},
                {0, 0, 0, 2, 5, 8, 0 , 0, 0},
                {0, 0, 0, 3, 4, 9, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 1 , 5, 6},
                {0, 0, 0, 0, 0, 0, 2 , 4, 7},
                {0, 0, 0, 0, 0, 0, 3 , 9, 8}};

        int table4[][] = {
                {1, 2, 3, 4, 5, 6, 7 , 8, 1},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0}};

        int table5[][] = {
                {1, 2, 3, 4, 5, 6, 7 , 8, 9},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 0 , 0, 9}};

        int table6[][] = {
                {1, 8, 7, 0, 0, 0, 0 , 0, 0},
                {2, 9, 6, 0, 0, 0, 0 , 0, 0},
                {3, 4, 5, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 1, 6, 7, 0 , 0, 0},
                {0, 0, 0, 2, 4, 8, 0 , 0, 0},
                {0, 0, 0, 3, 4, 9, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 1 , 5, 6},
                {0, 0, 0, 0, 0, 0, 2 , 4, 7},
                {0, 0, 0, 0, 0, 0, 3 , 9, 8}};

        assertTrue(generator.isValid(table1));
        assertTrue(generator.isValid(table2));
        assertTrue(generator.isValid(table3));
        assertFalse(generator.isValid(table4));
        assertFalse(generator.isValid(table5));
        assertFalse(generator.isValid(table6));
    }
}