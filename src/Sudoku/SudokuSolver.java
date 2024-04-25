package Sudoku;

public class SudokuSolver {
    private static final int SIZE = 9;
    private int[][] sudoku;

    public SudokuSolver(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    protected void printSudoku(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean checkNumber(int row, int col, int number) {
        // check line
        for (int i = 0; i < SIZE; i++) {
            if (sudoku[row][i] == number || sudoku[i][col] == number){
                return false;
            }
        }

        // check rectangle
        int tempRow = row - row % 3;
        int tempCol = col - col % 3;
        for (int i = tempRow; i < tempRow + 3; i++){
            for (int j = tempCol; j < tempCol + 3; j++){
                if(sudoku[i][j] == number){
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean solveSudoku(){
        for (int i = 0; i < SIZE; i++){                     // Navigating the rows
            for (int j = 0; j < SIZE; j++){                 // Navigating the cols
                if(sudoku[i][j] == 0){                      // Check it is empty
                    for (int k = 1; k <= 9; k++){           // Available numbers for fill to the sudoku
                        if(checkNumber(i, j, k)){           // Check it is available
                            sudoku[i][j] = k;               // Fill with the current number
                            if(solveSudoku()){              // Check it is successful
                                return true;
                            }else {
                                sudoku[i][j] = 0;           // Fill with zero again
                            }
                        }
                    }                                       // The cell is empty but no available numbers
                    return false;
                }
            }
        }
        return true;                                        // Return true
    }
}
