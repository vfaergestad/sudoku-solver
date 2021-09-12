package sudoku.solver;

import static java.lang.System.exit;

public class Solver {

    private int MAX;
    private final Board board;
    private boolean solved;
    private static Solver solverInstance;

    private Solver(int size){
        board = Board.getBoardInstance(size);
        solved = false;
        MAX = size;
    }

    public static Solver getSolverInstance(int size){
        if (solverInstance == null){
            solverInstance = new Solver(size);
        } return solverInstance;
    }

    public void printBoard(){
        board.printBoard();
        System.out.println(board);
    }

    private int getRowFromPos(int pos){
        return pos/10;
    }

    private int getColumnFromPos(int pos){
        while (pos >= 10){
            pos = pos - 10;
        } return pos;
    }

    private int getPosInGrid(int row, int column){
        boolean topRow = false;
        boolean middleRow = false;
        boolean bottomRow = false;
        boolean leftColumn = false;
        boolean middleColumn = false;
        boolean rightColumn = false;

        if (row == 0 || row == 3 || row == 6) topRow = true;
        if (row == 1 || row == 4 || row == 7) middleRow = true;
        if (row == 2 || row == 5 || row == 8) bottomRow = true;
        if (column == 0 || column == 3 || column == 6) leftColumn = true;
        if (column == 1 || column == 4 || column == 7) middleColumn = true;
        if (column == 2 || column == 5 || column == 8) rightColumn = true;

        if (topRow && leftColumn) return 1;
        if (topRow && middleColumn) return 2;
        if (topRow && rightColumn) return 3;
        if (middleRow && leftColumn) return 4;
        if (middleRow && middleColumn) return 5;
        if (middleRow && rightColumn) return 6;
        if (bottomRow && leftColumn) return 7;
        if (bottomRow && middleColumn) return 8;
        if (bottomRow && rightColumn) return 9;
        return 0;
    }

    private boolean checkGrid(int row, int column, int num){
        int posInGrid = getPosInGrid(row, column);
        int middleRow = 0;
        int middleColumn = 0;
        switch (posInGrid) {
            case 1 -> {
                middleRow = row + 1;
                middleColumn = column + 1;
            }
            case 2 -> {
                middleRow = row + 1;
                middleColumn = column;
            }
            case 3 -> {
                middleRow = row + 1;
                middleColumn = column - 1;
            }
            case 4 -> {
                middleRow = row;
                middleColumn = column + 1;
            }
            case 5 -> {
                middleRow = row;
                middleColumn = column;
            }
            case 6 -> {
                middleRow = row;
                middleColumn = column - 1;
            }
            case 7 -> {
                middleRow = row - 1;
                middleColumn = column + 1;
            }
            case 8 -> {
                middleRow = row - 1;
                middleColumn = column;
            }
            case 9 -> {
                middleRow = row - 1;
                middleColumn = column - 1;
            }
        }

        if (board.getTile(middleRow - 1, middleColumn - 1) == num) return true;
        if (board.getTile(middleRow - 1, middleColumn) == num) return true;
        if (board.getTile(middleRow - 1, middleColumn + 1) == num) return true;
        if (board.getTile(middleRow, middleColumn - 1) == num) return true;
        if (board.getTile(middleRow, middleColumn) == num) return true;
        if (board.getTile(middleRow, middleColumn + 1) == num) return true;
        if (board.getTile(middleRow + 1, middleColumn - 1) == num) return true;
        if (board.getTile(middleRow + 1, middleColumn) == num) return true;
        if (board.getTile(middleRow + 1, middleColumn + 1) == num) return true;
        return false;

    }

    private boolean legalNumber(int num, int row, int column){
        for (int i = 0; i < MAX; ++i) {
            if (board.getTile(row, i) == num) return false;
            if (board.getTile(i, column) == num) return false;
            if (checkGrid(row, column, num)) return false;

        } return true;
    }

    public boolean solve(int pos){
        if (getColumnFromPos(pos) == 9){
            pos++;
        }
        //System.out.println(pos);
        if (pos > 88){
            printBoard();
            solved = true;
        } else {
            int row = getRowFromPos(pos);
            int column = getColumnFromPos(pos);
            if (board.getTile(row, column) == 0){
                for (int num = 1; num <= MAX; ++num) {
                    if (legalNumber(num, row, column)){
                        board.setTile(row, column, num);

                        solve(pos + 1);
                        if (!solved){
                            board.setTile(row, column, 0);
                        }
                    }
                }
            } else solve(pos + 1);
        } return solved;
    }

}
