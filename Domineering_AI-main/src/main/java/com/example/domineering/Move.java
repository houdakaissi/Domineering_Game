package com.example.domineering;

import javafx.scene.shape.Rectangle;

public class Move extends Rectangle implements Cloneable {
    public Move(int width, int height) {
        super(width, height);
    }


    @Override
    public Move clone() {
        try {
            return (Move) super.clone();
        } catch (CloneNotSupportedException e) {
           throw new AssertionError();
        }
    }
}
