package controllers;

public class XSudokuGenerator extends BaseSudokuGenerator {
    public XSudokuGenerator() {}

    /**
     * This method decides whether or not a given value can be placed in a given cell.
     * It does NOT actually put the value in the cell.
     * @param posX The column of the cell.
     * @param posY The row of the cell.
     * @param value The numerical value to be placed in the cell.
     * @return A boolean value indicating whether or not the given value can be placed in the given cell.
     */
    @Override
    protected boolean operatorRequirement(int posX, int posY, int value) {
        // check if the value could be placed in a basic sudoku
        if(!super.operatorRequirement(posX, posY, value))
            return false;

        // we are in the main diagonal
        if(posX == posY) {
            for(int i = 0; i < this.width; i ++) {
                if(this.table[i][i] == value) {
                    return false;
                }
            }
        }

        // we are in the subsidiary diagonal
        if(posX + posY == this.width - 1) {
            for(int i = 0; i < this.width; i ++) {
                if(this.table[i][this.width - i - 1] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected boolean isValid(int[][] table) {
        if(!super.isValid(table))
            return false;

        // this check relies on the behaviour that arrays of the primitive type "boolean" default every item to "false"
        // DO NOT replace with the reference type "Boolean" unless you add Array.fill(array, false)
        boolean[] mainDiagonalCheck = new boolean[this.width];
        boolean[] subsidiaryDiagonalCheck = new boolean[this.width];
        for(int i = 0; i < this.width; i ++) {
            // only if this number is set. Unset number is 0.
            if(table[i][i] != 0) {
                if(mainDiagonalCheck[table[i][i] - 1]) { // we have already encountered this number in the main diagonal
                    return false;
                }
                mainDiagonalCheck[table[i][i] - 1] = true;
            }

            // only if this number is set. Unset number is 0.
            if(table[i][this.width - i - 1] != 0) {
                if(subsidiaryDiagonalCheck[table[i][this.width - i - 1] - 1]) {
                    return false;
                }
                subsidiaryDiagonalCheck[table[i][this.width - i - 1] - 1] = true;
            }
        }

        return true;
    }
}
