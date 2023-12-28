package com.example.domineering;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public abstract class Position implements Cloneable {

    private Player player;
    private final int[] movesPlayer = {0, 0};
    private int currentPlayer = 1;

    private static final int NUM_SQUARES = 5; // Number of squares in each dimension

    private static final boolean DEBUG = false;

    private GridPane gridPane = new GridPane();


    Position(Player player) {
        this.player = player;
    }


    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getCurrentPlayerType() {
        return player;
    }

    public void setCurrentPlayerType(Player player) {
        this.player = player;
    }

    public boolean isCurrentPlayer(Player player) {
        return this.player == player;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void switchPlayer() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
    }

    public boolean isCurrentPlayer(int player) {
        return currentPlayer == player;
    }

    public int getMovesPlayer(int player) {
        if (player == 1) {
            return movesPlayer[0];
        } else {
            return movesPlayer[1];
        }
    }

    public void incrementMovesPlayer(int player) {
        if (player == 1) {
            movesPlayer[0]++;
        } else {
            movesPlayer[1]++;
        }
    }

    public void setMovesPlayer(int player, int moves) {
        if (player == 1) {
            movesPlayer[0] = moves;
        } else {
            movesPlayer[1] = moves;
        }
    }

    public boolean isDebugMode() {
        return DEBUG;
    }


    public int getNumSquares() {
        return NUM_SQUARES;
    }

    public Move getSquare(int row, int col) {
        int index = row * NUM_SQUARES + col;
        if (index >= 0 && index < gridPane.getChildren().size()) {
            return (Move) gridPane.getChildren().get(index);
        } else {
            // Handle the error, for now, returning null
            throw new IllegalArgumentException("Invalid row and column row= " + row + " col=" + col);
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void reset() {
        currentPlayer = 1;
        movesPlayer[0] = 0;
        movesPlayer[1] = 0;
        for (Node child : gridPane.getChildren()) {
            child.setDisable(false);
            child.getProperties().remove("player");
        }
    }

    @Override
    public Position clone() {
        try {
            Position clonedPosition = (Position) super.clone();
            // Make sure to create a new GridPane instance for the clone
            clonedPosition.gridPane = new GridPane();
            // Clone other mutable fields if needed

            // Copy the children of the original gridPane to the new one
            for (Node child : gridPane.getChildren()) {
                Move originalMove = (Move) child;
                Move clonedMove = originalMove.clone();
                clonedPosition.gridPane.add(clonedMove, GridPane.getColumnIndex(originalMove), GridPane.getRowIndex(originalMove));
            }
            return clonedPosition;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    @Override
    public String toString() {
        return "Position{" +
                "player=" + player +
                ", movesPlayer=" + Arrays.toString(movesPlayer) +
                ", currentPlayer=" + currentPlayer +
                ", gridPane=" + gridPane +
                '}';
    }
}
