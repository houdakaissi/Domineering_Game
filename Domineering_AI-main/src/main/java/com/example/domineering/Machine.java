package com.example.domineering;

import java.util.List;

public class Machine extends Agent {
    @Override
    public Move makeMove(Position gamePosition, GameSearch domineeringGameSearch) {
        List<Move> unPlayedMoves = gamePosition.getGridPane().getChildren().stream().filter(node -> {
            Move neighbourSquare = domineeringGameSearch.getNeighbourMove(gamePosition, (Move) node, gamePosition.getCurrentPlayer());
            return !node.isDisable() && neighbourSquare != null && !neighbourSquare.isDisable();
        }).map(node -> (Move) node).toList();

        if (!unPlayedMoves.isEmpty()) {
            return unPlayedMoves.get((int) (Math.random() * unPlayedMoves.size()));
        }
        return null;

    }
}
