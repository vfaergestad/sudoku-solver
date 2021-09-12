#include <iostream>
#include "stdio.h"
#include <Windows.h>
using namespace std;

int const MAX = 9;

int board[MAX][MAX];

bool solved = false;

void fillBoard(){
    board[0][4] = 6;
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
    board[8][8] = 9;

}

void printBoard(){
    cout << "- | 0  1  2  3  4  5  6  7  8  \n";
    cout << "------------------------------";
    for (int i = 0; i < MAX; ++i) {
        cout << "\n" << i << " | ";
        for (int j = 0; j < MAX; ++j) {
            cout << board[i][j] << "  ";
        }
    } cout << "\n\n";
}

int getRowFromPos(int pos){
    return pos/10;
}

int getColumnFromPos(int pos){
    while (pos >= 10){
        pos = pos - 10;
    } return pos;
}

int getPosInGrid(int row, int column){
    bool topRow = false;
    bool middleRow = false;
    bool bottomRow = false;
    bool leftColumn = false;
    bool middleColumn = false;
    bool rightColumn = false;

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

bool checkGrid(int row, int column, int num){
    int posInGrid = getPosInGrid(row, column);
    int middleRow;
    int middleColumn;
    switch (posInGrid) {
        case 1:
            middleRow = row + 1;
            middleColumn = column + 1;
            break;
        case 2:
            middleRow = row + 1;
            middleColumn = column;
            break;
        case 3:
            middleRow = row + 1;
            middleColumn = column - 1;
            break;
        case 4:
            middleRow = row;
            middleColumn = column + 1;
            break;
        case 5:
            middleRow = row;
            middleColumn = column;
            break;
        case 6:
            middleRow = row;
            middleColumn = column - 1;
            break;
        case 7:
            middleRow = row - 1;
            middleColumn = column + 1;
            break;
        case 8:
            middleRow = row - 1;
            middleColumn = column;
            break;
        case 9:
            middleRow = row - 1;
            middleColumn = column - 1;
            break;
    }

    if (board[middleRow - 1][middleColumn - 1] == num) return true;
    if (board[middleRow - 1][middleColumn] == num) return true;
    if (board[middleRow - 1][middleColumn + 1] == num) return true;
    if (board[middleRow][middleColumn - 1] == num) return true;
    if (board[middleRow][middleColumn] == num) return true;
    if (board[middleRow][middleColumn + 1] == num) return true;
    if (board[middleRow + 1][middleColumn - 1] == num) return true;
    if (board[middleRow + 1][middleColumn] == num) return true;
    if (board[middleRow + 1][middleColumn + 1] == num) return true;
    return false;

}

bool legalNumber(int num, int row, int column){
    for (int i = 0; i < MAX; ++i) {
        if (board[row][i] == num) return false;
        if (board[i][column] == num) return false;
        if (checkGrid(row, column, num)) return false;

    } return true;
}

void solve(int pos){
    if (getColumnFromPos(pos) == 9){
        pos++;
    }
    if (pos > 88){
        printBoard();
        solved = true;
    } else {
        int row = getRowFromPos(pos);
        int column = getColumnFromPos(pos);
        if (board[row][column] == 0){
            for (int num = 1; num <= MAX; ++num) {
                if (legalNumber(num, row, column)){
                    board[row][column] = num;
                    printBoard();
                    solve(pos + 1);
                    board[row][column] = 0;
                    printBoard();
                }
            }
        } else solve(pos + 1);
    }
}


int main() {
    fillBoard();
    printBoard();
    solve(0);

    if (!solved){
        cout << "Found no solution.";
    }

    return 0;
}