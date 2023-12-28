package com.example.domineering;

import com.example.domineering.Agent.*;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Domineering extends GameSearch {


    @Override
    public boolean wonPosition(Position position) {
        int numSquares = position.getNumSquares();
        for (int row = 0; row < numSquares; row++) {
            for (int col = 0; col < numSquares; col++) {
                Move move = position.getSquare(row, col);
                Move neighbourMove = getNeighbourMove(position, move, position.getCurrentPlayer());
                if (move != null && !move.isDisable() && neighbourMove != null && !neighbourMove.isDisable())
                    return false;
            }
        }

        return true;
    }


    @Override
    public AlphaBetaMove[] possibleMoves(AlphaBetaMove position, int player) {
        System.out.println(position);
        int numSquares = position.getNumSquares();

        // List to store possible positions
        List<AlphaBetaMove> possiblePositions = new ArrayList<>();

        // Iterate through each square in the grid
       
        for (int row = 0; row < numSquares; row++) {//////////////
            for (int col = 0; col < numSquares; col++) {
            	
                // Check if the square is empty
               // if (!position.isDisabled(row, col)) {
                	 
                    // Check if the adjacent square is also empty based on the player type
                    boolean isValidMove;
                    if (player == 2) {
                        isValidMove = (col < numSquares -1) && !position.isDisabled(row, col + 1);
                    } 
                    else {
                        isValidMove = (row < numSquares -1) && !position.isDisabled(row + 1, col);
                    }

                    if (isValidMove) {
                        // Create a new position with the move applied and add it to the list
                        AlphaBetaMove newPosition = createNewPosition(position, row, col, player);
                        possiblePositions.add(newPosition);
                    }
               // }
                
            }
           
            	 
            
        }
       // System.out.println("fffffffffffffffffffff"+possiblePositions);
        return possiblePositions.toArray(new AlphaBetaMove[0]);
        
        
        
        
    }

    // Helper method to create a new position with a move applied
    private AlphaBetaMove createNewPosition(AlphaBetaMove position, int row, int col, int player) {
        AlphaBetaMove newPosition = position.clone();
        //apply move
        newPosition.setMove(row, col, player);
        newPosition.setRow(row);
        newPosition.setCol(col);
        if (player == 1)
            newPosition.setMove(row + 1, col, player);
        else
            newPosition.setMove(row, col +1, player);

        return newPosition;
    }


    public Move getNeighbourMove(Position position, Move move, int player) {
        if (move == null || move.getProperties() == null || !move.getProperties().containsKey("row") || !move.getProperties().containsKey("col")) {
            return null;
        }

        int currentPlayer = position.getCurrentPlayer();
        int clickedSquareRow = (int) move.getProperties().get("row");
        int clickedSquareCol = (int) move.getProperties().get("col");

        // check the current Player
        if (currentPlayer == 1)
            return (Move) position.getGridPane().getChildren().stream().filter(node -> GridPane.getRowIndex(node) == clickedSquareRow + 1 && GridPane.getColumnIndex(node) == clickedSquareCol && !node.isDisable()).map(node -> (Rectangle) node).findFirst().orElse(null);
        else
            return (Move) position.getGridPane().getChildren().stream().filter(node -> GridPane.getRowIndex(node) == clickedSquareRow && GridPane.getColumnIndex(node) == clickedSquareCol + 1 && !node.isDisable()).map(node -> (Rectangle) node).findFirst().orElse(null);
    }


    @Override
    public Move makeMove(Position gamePosition, GameSearch gameSearch) {
        Agent agent = switch (gamePosition.getCurrentPlayerType()) {
            case HUMAN -> new Human();
           // case MINIMAX -> new MinMaxAgent();
            case RANDOM -> new Machine();
            case ALPHA_BETA -> new AlphaBeta();
        };
        return agent.makeMove(gamePosition, gameSearch);
    }

}
