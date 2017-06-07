package com.vadimksn.game.tetris.model;

import java.util.Random;

public class Figure {

    public static Tile[][] getRandomFigure() {
        int i = new Random().nextInt(TileColor.values().length);

        switch (i) {
            case 0:
                return new Tile[][]{
                        {new Tile(0, 4, TileColor.O.getColor()), new Tile(0, 5, TileColor.O.getColor())},
                        {new Tile(1, 4, TileColor.O.getColor()), new Tile(1, 5, TileColor.O.getColor())}
                };

            case 1:
                return new Tile[][]{
                        {new Tile(0, 3, TileColor.I.getColor()), new Tile(0, 4, TileColor.I.getColor()),
                                new Tile(0, 5, TileColor.I.getColor()), new Tile(0, 6, TileColor.I.getColor())}
                };
            case 2:
                return new Tile[][]{
                        {null, new Tile(0, 5, TileColor.T.getColor()), null},
                        {new Tile(1, 4, TileColor.T.getColor()), new Tile(1, 5, TileColor.T.getColor()), new Tile(1, 6, TileColor.T.getColor())}
                };
            case 3:
                return new Tile[][]{
                        {new Tile(0, 4, TileColor.Z.getColor()), new Tile(0, 5, TileColor.Z.getColor()), null},
                        {null, new Tile(1, 5, TileColor.Z.getColor()), new Tile(1, 6, TileColor.Z.getColor())}
                };
            case 4:
                return new Tile[][]{
                        {null, new Tile(0, 5, TileColor.S.getColor()), new Tile(0, 6, TileColor.S.getColor())},
                        {new Tile(1, 4, TileColor.S.getColor()), new Tile(1, 5, TileColor.S.getColor()), null}
                };
            case 5:
                return new Tile[][]{
                        {new Tile(0, 4, TileColor.J.getColor()), null, null},
                        {new Tile(1, 4, TileColor.J.getColor()), new Tile(1, 5, TileColor.J.getColor()), new Tile(1, 6, TileColor.J.getColor())}
                };
            case 6:
                return new Tile[][]{
                        {null, null, new Tile(0, 6, TileColor.L.getColor())},
                        {new Tile(1, 4, TileColor.L.getColor()), new Tile(1, 5, TileColor.L.getColor()), new Tile(1, 6, TileColor.L.getColor())}
                };
        }
        return null;
    }

}
