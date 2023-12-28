package com.example.domineering;

public class AlphaBetaAgentMoveEvaluation {
    AlphaBetaMove move;
    float evaluation;

    public AlphaBetaAgentMoveEvaluation(AlphaBetaMove move, float evaluation) {
        this.move = move;
        this.evaluation = evaluation;
    }

    public AlphaBetaMove getAlphaBetaAgentMove() {
        return move;
    }

    public float getEvaluation() {
        return evaluation;
    }
}