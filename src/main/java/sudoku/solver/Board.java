package sudoku.solver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sudoku.Main;

import java.util.ArrayList;

public class Board {

    private int MAX;
    private int[][] board;
    private static Board boardInstance;

    private Board(int size){
        MAX = size;
        board = new int[MAX][MAX];
        fillBoard();
    }

    public static Board getBoardInstance(int size) {
        if (boardInstance == null){
            boardInstance = new Board(size);
        }
        return boardInstance;
    }

    public void setTile(int row, int column, int num){
        board[row][column] = num;
    }

    public int getTile(int row, int column){
        return board[row][column];
    }

    public String getTileAsString(int row, int column){
        return String.format("%d", board[row][column]);
    }

    private void fillBoard(){
        /*board[0][4] = 6;
        board[0][6] = 4;
        board[0][7] = 2;
        board[1][0] = 5;
        board[1][5] = 7;
        board[1][7] = 8;
        board[2][2] = 7;
        board[3][0] = 6;
        board[3][1] = 1;
        board[3][6] = 5;
        board[4][4] = 8;
        board[4][7] = 3;
        board[5][1] = 5;
        board[5][2] = 8;
        board[5][5] = 9;
        board[6][0] = 4;
        board[6][4] = 2;
        board[7][2] = 1;
        board[7][3] = 4;
        board[7][8] = 6;
        board[8][0] = 2;
        board[8][2] = 6;
        board[8][8] = 9;*/
        board = Main.getBoardSetup();

    }

    public ObservableList<Integer> getBoardAsList(){
        ArrayList<Integer> boardList = new ArrayList<>();
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                boardList.add(getTile(i, j));
            }
        } return FXCollections.observableList(boardList);
    }

    public void printBoard(){
        System.out.println("- | 0  1  2  3  4  5  6  7  8  ");
        System.out.println("------------------------------");
        for (int i = 0; i < MAX; ++i) {
            System.out.print(i + " | ");
            for (int j = 0; j < MAX; ++j) {
                System.out.print(board[i][j] + "  ");
            } System.out.print("\n");
        }
        System.out.println("\n");
    }

}
