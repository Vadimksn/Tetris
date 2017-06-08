package com.vadimksn.game.tetris.controller;

import com.vadimksn.game.tetris.model.Figure;
import com.vadimksn.game.tetris.model.Tile;
import com.vadimksn.game.tetris.view.GamePanel;

public class GameController {
    private static final GameController INSTANCE = new GameController();
    private Tile currentShape[][];
    private Tile gameMas[][];

    private GameController() {
        gameMas = new Tile[GamePanel.ROW_COUNT][GamePanel.COLUMNS_COUNT];
        currentShape = Figure.getRandomFigure();
    }

    public void start() {
        if (!gameOver()) {
            if (canShapeStepDown(currentShape)) {
                stepDownShape(currentShape);
            } else {
                writeShapeToGameMas(currentShape);
                setCurrentShape(Figure.getRandomFigure());
            }
        }else writeShapeToGameMas(currentShape);
    }

    public boolean gameOver() {
        for (int x = 3; x < 7; x++) {
            if (!isTileEmpty(gameMas[1][x])) return true;
        }
        return false;
    }

    public void writeShapeToGameMas(Tile shape[][]) {
        for (Tile[] aShape : shape) {
            for (Tile tile : aShape) {
                if (tile != null) {
                    gameMas[tile.getY()][tile.getX()] = tile;
                }
            }
        }
    }

    public boolean canShapeStepDown(Tile shape[][]) {
        return !isShapeTouchGround(shape) && !isShapeTouchDownAnotherShape(shape);
    }

    public boolean isShapeTouchGround(Tile shape[][]) {
        for (int y = shape.length - 1; y > -1; y--) {
            for (int x = 0; x < shape[y].length; x++) {
                Tile tile = shape[y][x];
                if (!isTileEmpty(tile) && tile.getY() == GamePanel.ROW_COUNT - 1) return true;
            }
        }
        return false;
    }

    public boolean isShapeTouchDownAnotherShape(Tile shape[][]) {
        for (int y = shape.length - 1; y > -1; y--) {
            for (int x = 0; x < shape[y].length; x++) {
                Tile ourTile = shape[y][x];
                if (!isTileEmpty(ourTile) && !isTileEmpty(gameMas[ourTile.getY() + 1][ourTile.getX()])) return true;
            }
        }
        return false;
    }

    public boolean isTileEmpty(Tile tile) {
        return tile == null;
    }


    public boolean isShapeTouchWall(Direction direction, Tile shape[][]) {
        switch (direction) {
            case LEFT:
                for (Tile[] shapeY : shape) {
                    Tile tile = shapeY[0];
                    if (tile != null && tile.getX() == 0)
                        return true;
                }
                break;
            case RIGHT:
                for (Tile[] shapeY : shape) {
                    Tile tile = shapeY[shapeY.length - 1];
                    if (tile != null && tile.getX() == GamePanel.COLUMNS_COUNT - 1)
                        return true;
                }
                break;
        }
        return false;
    }

    /**
     * Check if we can move our current shape LEFT or RIGHT.
     */
    public boolean canMoveShape(Direction direction, Tile shape[][]) {
        if (isShapeTouchWall(direction, shape) || isShapeTochAnotherShape(direction, shape)) {
            return false;

        }
        return true;
    }

    /**
     * Check if our current shape touch LEFT or RIGHT another shape.
     */
    public boolean isShapeTochAnotherShape(Direction direction, Tile shape[][]) {
        switch (direction) {
            case LEFT:
                for (Tile[] shapeY : shape) {
                    Tile ourTile = shapeY[0];
                    if (!isTileEmpty(ourTile) && !isTileEmpty(gameMas[ourTile.getY()][ourTile.getX() + direction.getX()]))
                        return true;
                }
            case RIGHT:
                for (Tile[] shapeY : shape) {
                    Tile ourTile = shapeY[shapeY.length - 1];
                    if (!isTileEmpty(ourTile) && !isTileEmpty(gameMas[ourTile.getY()][ourTile.getX() + direction.getX()]))
                        return true;
                }
        }
        return false;
    }

    /**
     * Move our current shape LEFT or RIGHT.
     */
    public void move(Direction direction, Tile shape[][]) {
        for (Tile shapeY[] : shape) {
            for (Tile tile : shapeY) {
                if (!isTileEmpty(tile)) tile.setX(tile.getX() + direction.getX());
            }
        }
    }

    public void stepDownShape(Tile shape[][]) {
        for (Tile[] shapeY : shape) {
            for (Tile tile : shapeY) {
                if (tile != null) {
                    stepDownTile(tile);
                }
            }
        }
    }

    public void stepDownTile(Tile tile) {
        tile.setY(tile.getY() + 1);
    }

    public static GameController getINSTANCE() {
        return INSTANCE;
    }

    public Tile[][] getGameMas() {
        return gameMas;
    }

    public void setGameMas(Tile[][] gameMas) {
        this.gameMas = gameMas;
    }

    public Tile[][] getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(Tile[][] currentShape) {
        this.currentShape = currentShape;
    }
}
