package com.example.domineering;


import java.util.Arrays;

public class AlphaBetaMove implements Cloneable {

    int boardSize;
    int[][] board;
    int row, col;

    public AlphaBetaMove(Position position) {
        this.boardSize = position.getNumSquares();
        board = new int[position.getNumSquares()][position.getNumSquares()];

        for (int i = 0; i < position.getNumSquares(); i++) {
            for (int j = 0; j < position.getNumSquares(); j++) {
                boolean isDisabled = position.getSquare(i, j).isDisable();
                if (isDisabled) {
                    board[i][j] = position.getCurrentPlayer() == 1 ? -1 : 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }

    }


    public int getNumSquares() {
        return boardSize;
    }

    public boolean isDisabled(int row, int col) {
        return board[row][col] != 0;
    }

    @Override
    public String toString() {
        return "AlphaBetaAgentMove{" +
                "boardSize=" + boardSize +
                ", board=" + Arrays.deepToString(board) +
                '}';
    }

    @Override
    public AlphaBetaMove clone() {
        try {
            AlphaBetaMove clone = (AlphaBetaMove) super.clone();
            clone.board = new int[boardSize][boardSize];
            for (int i = 0; i < boardSize; i++) {
                clone.board[i] = board[i].clone();
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    public void setMove(int row, int col, int player) {
        board[row][col] = player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

}
