package com.vadimksn.game.tetris.controller;

public enum Direction {
    LEFT(-1), RIGHT(1);

    private int x;

    Direction(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
