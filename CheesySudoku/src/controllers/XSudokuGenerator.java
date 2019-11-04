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
}
