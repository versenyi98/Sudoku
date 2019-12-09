package test;

import controllers.BaseSudokuGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SamuraiSudokuGeneratorTest {

    @Test
    public void isPropertiesSetTest() {
        BaseSudokuGenerator mock = new BaseSudokuGenerator();

        assertFalse(mock.isPropertiesSet());

        mock.setCellWidth(3);
        mock.setCellHeight(3);
        mock.setCellNumbersHorizontal(3);
        mock.setCellNumbersVertical(3);

        assertTrue(mock.isPropertiesSet());
    }

    @Test
    public void inBoundsTest() {
        BaseSudokuGenerator mock = new BaseSudokuGenerator();
        assertFalse(mock.inBounds(0, 0));

        mock.setCellWidth(3);
        mock.setCellHeight(3);
        mock.setCellNumbersHorizontal(3);
        mock.setCellNumbersVertical(3);

        assertTrue(mock.inBounds(0, 0));
        assertTrue(mock.inBounds(2, 2));
        assertTrue(mock.inBounds(0, 1));
        assertFalse(mock.inBounds(-1, -1));
        assertFalse(mock.inBounds(136, 0));
        assertFalse(mock.inBounds(136, 244));
        assertFalse(mock.inBounds(136, -9));


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

        int table7[][] = {
                {1, 8, 7, 0, 0, 0, 0 , 0, 0},
                {2, 9, 6, 0, 0, 0, 0 , 0, 0},
                {3, 4, 5, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 1, 6, 7, 0 , 0, 0},
                {0, 0, 0, 2, 4, 8, 0 , 0, 0},
                {0, 0, 0, 3, 4, 9, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 1 , 5, 6},
                {0, 0, 0, 0, 0, 0, 2 , 4, 7},
                {3, 0, 0, 0, 0, 0, 3 , 9, 8}};

        int table8[][] = {
                {1, 8, 7, 0, 0, 0, 0 , 0, 0},
                {2, 9, 6, 0, 0, 0, 0 , 0, 0},
                {3, 4, 5, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 1, 6, 7, 0 , 0, 0},
                {0, 0, 0, 2, 4, 8, 0 , 0, 0},
                {0, 0, 0, 3, 4, 9, 0 , 0, 0},
                {0, 0, 0, 0, 0, 0, 1 , 5, 6},
                {0, 0, 0, 2, 0, 0, 2 , 4, 7},
                {0, 0, 0, 0, 0, 0, 3 , 9, 8}};

        int table9[][] = {
                {1, 8, 7, 0, 0, 0, 0 , 0, 0},
                {2, 9, 6, 0, 0, 0, 0 , 0, 0},
                {3, 4, 5, 0, 0, 0, 0 , 0, 0},
                {0, 0, 0, 1, 6, 7, 0 , 0, 0},
                {0, 0, 0, 2, 4, 8, 0 , 0, 0},
                {0, 0, 0, 3, 4, 9, 1 , 0, 0},
                {0, 0, 0, 0, 0, 0, 1 , 5, 6},
                {0, 0, 0, 2, 0, 0, 2 , 4, 7},
                {0, 0, 0, 0, 0, 0, 3 , 9, 8}};

        assertTrue(generator.isValid(table1));
        assertTrue(generator.isValid(table2));
        assertTrue(generator.isValid(table3));
        assertFalse(generator.isValid(table4));
        assertFalse(generator.isValid(table5));
        assertFalse(generator.isValid(table6));
        assertFalse(generator.isValid(table7));
        assertFalse(generator.isValid(table8));
        assertFalse(generator.isValid(table9));
    }

}
