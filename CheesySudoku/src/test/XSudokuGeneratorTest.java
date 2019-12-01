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

        assertThrows(IndexOutOfBoundsException.class, () -> generator.generate(10));

        generator.setCellNumbersHorizontal(3);
        generator.setCellNumbersVertical(2);

        assertThrows(IndexOutOfBoundsException.class, () -> generator.generate(10));
    }
}
