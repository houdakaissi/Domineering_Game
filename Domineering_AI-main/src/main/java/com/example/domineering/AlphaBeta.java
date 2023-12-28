package com.example.domineering;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class AlphaBeta extends Agent {

    private static final int MAX_DEPTH = 5;
    
    

    
  
     @Override
    public Move makeMove(Position gamePosition, GameSearch domineeringGameSearch) {
        // transform position to board
        // -1 = program, 1 = human, 0 = blank
        AlphaBetaMove alphaBetaAgentMove = new AlphaBetaMove(gamePosition);


        AlphaBetaMove alphaBetaAgentMoveResult = alphaBeta(
                alphaBetaAgentMove,
                MAX_DEPTH,
                Float.NEGATIVE_INFINITY,
                Float.POSITIVE_INFINITY,
                true,
                domineeringGameSearch
        ).getAlphaBetaAgentMove();
       
         alphaBetaAgentMoveResult = alphaBetaAgentMoveResult != null ? alphaBetaAgentMoveResult  : null;
        //System.out.println("new move" + alphaBetaAgentMoveResult);
        //return the element of the board with the move
       // return gamePosition.getSquare(alphaBetaAgentMoveResult.getRow(), alphaBetaAgentMoveResult.getCol());
     
         if (alphaBetaAgentMoveResult != null) {
             System.out.println("new move " + alphaBetaAgentMoveResult);
             // Return the element of the board with the move
             return gamePosition.getSquare(alphaBetaAgentMoveResult.getRow(), alphaBetaAgentMoveResult.getCol());
         } else {
             // Handle the case when alphaBetaAgentMoveResult is null
             // You might want to log an error, throw an exception, or handle it in a way that makes sense for your application.
             System.err.println("Error: AlphaBetaAgentMoveResult is null");
             ; // or handle it accordingly
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Game Over");
             alert.setHeaderText(null);
             alert.setContentText("Player machine wins");
 

             alert.showAndWait();
             return  null;
             
         }
     
     }
////////////////
    private AlphaBetaAgentMoveEvaluation alphaBeta(AlphaBetaMove position, int depth, float alpha, float beta, boolean maximizingPlayer, GameSearch domineeringGameSearch) {
        int currentPlayer = maximizingPlayer ? 2 : 1;

        if (depth == 0 || wonPosition(position, currentPlayer)) {
            float evaluation = positionEvaluation(position, domineeringGameSearch, currentPlayer);
            return new AlphaBetaAgentMoveEvaluation(position, evaluation); // Use null for Move in case of leaf nodes
        }

        AlphaBetaMove[] possibleMoves = domineeringGameSearch.possibleMoves(position, currentPlayer);


        // searching for the best move
        AlphaBetaAgentMoveEvaluation bestMove = new AlphaBetaAgentMoveEvaluation(null, maximizingPlayer ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY);

        if (maximizingPlayer) {
            float maxEval = Float.NEGATIVE_INFINITY;
            for (AlphaBetaMove possiblePosition : possibleMoves) {
                AlphaBetaAgentMoveEvaluation possiblePositionEval = alphaBeta(possiblePosition, depth - 1, alpha, beta, false, domineeringGameSearch);
                if (possiblePositionEval == null) continue;
                float eval = possiblePositionEval.getEvaluation();
                if (eval > maxEval) {
                    maxEval = eval;
                    bestMove = new AlphaBetaAgentMoveEvaluation(possiblePositionEval.getAlphaBetaAgentMove(), eval);
                }
                alpha = Math.max(alpha, maxEval);
                if (beta <= alpha) {
                    break;
                }
            }
        } else {
            float minEval = Float.POSITIVE_INFINITY;
            for (AlphaBetaMove possiblePosition : possibleMoves) {
                AlphaBetaAgentMoveEvaluation possiblePositionEval = alphaBeta(possiblePosition, depth - 1, alpha, beta, true, domineeringGameSearch);
                if (possiblePositionEval == null) continue;
                float eval = possiblePositionEval.getEvaluation();
                if (eval < minEval) {
                    minEval = eval;
                    bestMove = new AlphaBetaAgentMoveEvaluation(possiblePositionEval.getAlphaBetaAgentMove(), eval);
                }
                beta = Math.min(beta, minEval);
                if (beta <= alpha) {
                    break;
                }
            }
        }
        return bestMove;
    }

    private boolean wonPosition(AlphaBetaMove position, int player) {
        int numSquares = position.getNumSquares();
        for (int row = 0; row < numSquares; row++) {
            for (int col = 0; col < numSquares; col++) {
                if (player == 1 && col < numSquares - 1 && position.isDisabled(row, col) && position.isDisabled(row, col + 1))
                    return false;
                if (player == 2 && row < numSquares - 1 && position.isDisabled(row, col) && position.isDisabled(row + 1, col))
                    return false;
                
            }
        }

        return true;
    }

//heuristiques
    private float positionEvaluation(AlphaBetaMove pos, GameSearch domineeringGameSearch, int player) {
        int playerMoves = domineeringGameSearch.possibleMoves(pos, player) != null ? domineeringGameSearch.possibleMoves(pos, player).length : 0;
        int opponentMoves = domineeringGameSearch.possibleMoves(pos, 3 - player) != null ? domineeringGameSearch.possibleMoves(pos, 3 - player).length : 0;

        float evaluation = playerMoves - opponentMoves;

        // Add evaluation based on horizontal domination
        for (int i = 0; i < pos.getNumSquares(); i++) {
            for (int j = 0; j < pos.getNumSquares() -1; j++) {
                // j=4 i=4  , 4*5+4 = 24
                if (pos.isDisabled(i, j) && pos.isDisabled(i, j + 1)) {
                    evaluation += (float) (player == 1 ? 0.1 : -0.1);
                }
            }
        }

        // Add evaluation based on vertical domination
        for (int i = 0; i < pos.getNumSquares() - 1; i++) {
            for (int j = 0; j < pos.getNumSquares(); j++) {
                if (pos.isDisabled(i, j) && pos.isDisabled(i + 1, j)) {
                    evaluation += (float) (player == 1 ? 0.1 : -0.1);
                }
            }
        }

        return evaluation;
    }


}
