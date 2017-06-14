package com.vadimksn.game.tetris.model;

import java.util.Random;

public class Figure {

    public static Tile[][] getRandomFigure() {
        int i = new Random().nextInt(TileColor.values().length);
        return getFigure(TileColor.values()[i]);
    }

    public static Tile[][] getFigure(TileColor tileColor) {

        switch (tileColor) {
            case O:
                return new Tile[][]{
                        {new Tile(0, 4, TileColor.O.getColor()), new Tile(0, 5, TileColor.O.getColor())},
                        {new Tile(1, 4, TileColor.O.getColor()), new Tile(1, 5, TileColor.O.getColor())}
                };

            case I:
                return new Tile[][]{
                        {new Tile(0, 3, TileColor.I.getColor()), new Tile(0, 4, TileColor.I.getColor()),
                                new Tile(0, 5, TileColor.I.getColor()), new Tile(0, 6, TileColor.I.getColor())}
                };
            case T:
                return new Tile[][]{
                        {null, new Tile(0, 5, TileColor.T.getColor()), null},
                        {new Tile(1, 4, TileColor.T.getColor()), new Tile(1, 5, TileColor.T.getColor()), new Tile(1, 6, TileColor.T.getColor())}
                };
            case Z:
                return new Tile[][]{
                        {new Tile(0, 4, TileColor.Z.getColor()), new Tile(0, 5, TileColor.Z.getColor()), null},
                        {null, new Tile(1, 5, TileColor.Z.getColor()), new Tile(1, 6, TileColor.Z.getColor())}
                };
            case S:
                return new Tile[][]{
                        {null, new Tile(0, 5, TileColor.S.getColor()), new Tile(0, 6, TileColor.S.getColor())},
                        {new Tile(1, 4, TileColor.S.getColor()), new Tile(1, 5, TileColor.S.getColor()), null}
                };
            case J:
                return new Tile[][]{
                        {new Tile(0, 4, TileColor.J.getColor()), null, null},
                        {new Tile(1, 4, TileColor.J.getColor()), new Tile(1, 5, TileColor.J.getColor()), new Tile(1, 6, TileColor.J.getColor())}
                };
            case L:
                return new Tile[][]{
                        {null, null, new Tile(0, 6, TileColor.L.getColor())},
                        {new Tile(1, 4, TileColor.L.getColor()), new Tile(1, 5, TileColor.L.getColor()), new Tile(1, 6, TileColor.L.getColor())}
                };
        }
        return null;
    }

}
