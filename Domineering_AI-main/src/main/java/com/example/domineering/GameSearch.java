package com.example.domineering;

public abstract class GameSearch {


    public abstract boolean wonPosition(Position p);


    public abstract AlphaBetaMove[] possibleMoves(AlphaBetaMove p, int player);

    public abstract Move getNeighbourMove(Position position, Move move, int player);

    public abstract Move makeMove(Position gamePosition, GameSearch gameSearch);
}
