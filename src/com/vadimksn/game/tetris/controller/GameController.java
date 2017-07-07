package com.vadimksn.game.tetris.controller;

import com.vadimksn.game.tetris.model.Figure;
import com.vadimksn.game.tetris.model.Player;
import com.vadimksn.game.tetris.model.Tile;
import com.vadimksn.game.tetris.view.GamePanel;
import com.vadimksn.game.tetris.view.LeftPanel;
import com.vadimksn.game.tetris.view.RightPanel;

import java.io.*;

public class GameController {
    private static final GameController INSTANCE = new GameController();
    private final int[] SCORES = {100, 300, 700, 1500};
    private final int gameSpeed = 500;
    private final int acceleration = 20;
    private Tile[][] currentShape;
    private Tile[][] nextShape0;
    private Tile[][] nextShape1;
    private Tile[][] nextShape2;
    private Tile[][] gameMas;
    private Player[] highScores;
    private String highScoresPath = "src\\com\\vadimksn\\game\\tetris\\HighScore";
    private boolean isRecord;
    private boolean isPaused;
    private boolean isGameOver;
    private boolean isGameRunning;
    private int score;
    private int lines;
    private int level;


    private GameController() {
        initHighScores();
        resetGame();
    }

    public void resetGame() {
        gameMas = new Tile[GamePanel.ROW_COUNT][GamePanel.COLUMNS_COUNT];
        currentShape = Figure.getRandomFigure();
        nextShape0 = Figure.getRandomFigure();
        nextShape1 = Figure.getRandomFigure();
        nextShape2 = Figure.getRandomFigure();
        isRecord = false;
        isPaused = false;
        isGameRunning = false;
        isGameOver = false;
        score = 0;
        lines = 0;
        level = 1;


    }

    public void start() {
        if (!isGameOver && isGameRunning) {
            if (!isPaused) {
                if (canShapeStepDown(currentShape)) {
                    stepDownShape(currentShape);
                } else {
                    writeShapeToGameMas(currentShape);
                    scanAndClearRows(currentShape, gameMas);
                    setCurrentShape(nextShape0);
                    updateNextShapes();
                    if (gameOver()) {
                        isGameOver = true;
                        for (int i = 0; i < highScores.length; i++) {
                            if (highScores[i].getScore() < score) {
                                if (i < highScores.length - 1) {
                                    System.arraycopy(highScores, i, highScores, i + 1, highScores.length - i - 1);
                                }
                                highScores[i] = new Player("", score);
                                isRecord = true;
                                saveHighScoresToFile();
                                break;
                            }
                        }
//                        isGameRunning = false;
                    }
                }
            }
        } else writeShapeToGameMas(currentShape);
    }

    public void updateNextShapes() {
        nextShape0 = nextShape1;
        nextShape1 = nextShape2;
        nextShape2 = Figure.getRandomFigure();
        LeftPanel.getINSTANCE().repaint();
    }

    public boolean gameOver() {
        if (currentShape.length == 1) {
            for (int i = 3; i < 7; i++) {
                if (!isTileEmpty(gameMas[0][i])) return true;
            }
        } else if (currentShape.length == 2 && currentShape[0].length == 2) {
            for (int i = 0; i < 2; i++) {
                for (int j = 4; j < 6; j++) {
                    if (!isTileEmpty(gameMas[i][j])) return true;
                }
            }
        } else if (currentShape[0].length == 3) {
            if (Figure.isSecondRowIsFull(currentShape)) {
                for (int i = 4; i < 7; i++) {
                    if (!isTileEmpty(gameMas[1][i])) return true;
                }
                for (int i = 0; i < 3; i++) {
                    if (!isTileEmpty(currentShape[0][i])) {
                        Tile tile = currentShape[0][i];
                        if (!isTileEmpty(gameMas[tile.getY()][tile.getX()])) return true;
                    }
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    if (!isTileEmpty(gameMas[i][5])) return true;
                }
                if (!isTileEmpty(currentShape[0][0])) {
                    if (!isTileEmpty(gameMas[0][4])) return true;
                    else if (!isTileEmpty(gameMas[1][6])) return true;

                } else if (!isTileEmpty(currentShape[1][0])) {
                    if (!isTileEmpty(gameMas[1][4])) return true;
                    else if (!isTileEmpty(gameMas[0][6])) return true;
                }
            }
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

    public void scanAndClearRows(Tile shape[][], Tile tiles[][]) {
        int firstY = 0;
        int lastY = 0;
        int countFilledRows = -1;

        for (Tile tile : shape[0]) {
            if (!isTileEmpty(tile)) {
                firstY = tile.getY();
            }
        }
        for (Tile tile : shape[shape.length - 1]) {
            if (!isTileEmpty(tile)) {
                lastY = tile.getY();
            }
        }
        for (int y = firstY; y < lastY + 1; y++) {
            boolean rowIsFull = true;
            for (int x = 0; x < tiles[y].length; x++) {
                if (isTileEmpty(tiles[y][x])) {
                    rowIsFull = false;
                }
            }
            if (rowIsFull) {
                clearRow(y, tiles);
                countFilledRows++;
                lines++;
            }
        }
        if (countFilledRows > -1) {
            level = 1 + lines / 10;
            score += SCORES[countFilledRows];
            if (gameSpeed + acceleration - acceleration * level >= 20) {
                GamePanel.getINSTANCE().getTimer().setDelay(gameSpeed + acceleration - acceleration * level);
            }
            RightPanel.getINSTANCE().repaint();
        }
    }

    public void clearRow(int y, Tile tiles[][]) {
        Tile mas[] = new Tile[GamePanel.COLUMNS_COUNT];
        tiles[y] = mas;
        for (int y1 = 0; y1 < y; y1++) {
            for (Tile tile : tiles[y1]) {
                if (!isTileEmpty(tile)) tile.setY(tile.getY() + 1);
            }
        }
        System.arraycopy(tiles, 0, tiles, 1, y);
        Tile mas1[] = new Tile[GamePanel.COLUMNS_COUNT];
        tiles[0] = mas1;
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

    public static boolean isTileEmpty(Tile tile) {
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

    public void rotate(Tile shape[][]) {

        if (shape.length == 1 && shape[0][1].getY() < GamePanel.ROW_COUNT - 2 && shape[0][1].getY() > 0 &&
                isTileEmpty(gameMas[shape[0][1].getY() + 1][shape[0][1].getX()]) &&
                isTileEmpty(gameMas[shape[0][1].getY() + 2][shape[0][1].getX()]) &&
                isTileEmpty(gameMas[shape[0][1].getY() - 1][shape[0][1].getX()])) {
            int y = -1;
            int x = shape[0][1].getX();
            Tile newShape[][] = new Tile[4][1];
            for (Tile tile : shape[0]) {
                tile.setX(x);
                tile.setY(tile.getY() + y);
                newShape[y + 1][0] = tile;
                y++;
            }
            setCurrentShape(newShape);

        } else if (shape.length == 4 && shape[1][0].getX() < GamePanel.COLUMNS_COUNT - 2 && shape[1][0].getX() > 0 &&
                isTileEmpty(gameMas[shape[1][0].getY()][shape[1][0].getX() - 1]) &&
                isTileEmpty(gameMas[shape[1][0].getY()][shape[1][0].getX() + 1]) &&
                isTileEmpty(gameMas[shape[1][0].getY()][shape[1][0].getX() + 2])) {
            int y = shape[1][0].getY();
            int x = -1;
            Tile newShape[][] = new Tile[1][4];
            for (Tile[] shapeY : shape) {
                for (Tile tile : shapeY) {
                    tile.setY(y);
                    tile.setX(tile.getX() + x);
                    newShape[0][x + 1] = tile;
                    x++;
                }
            }
            setCurrentShape(newShape);
        } else if (shape[0].length == 3) {
            if (
                    (Figure.isSecondRowIsFull(shape) && shape[1][1].getY() < GamePanel.ROW_COUNT - 1
                            && isTileEmpty(gameMas[shape[1][1].getY() + 1][shape[1][1].getX()])
                            && isTileEmpty(gameMas[shape[1][1].getY() - 1][shape[1][1].getX()])
                            && ((!isTileEmpty(shape[0][0])
                            && isTileEmpty(gameMas[shape[1][1].getY() - 1][shape[1][1].getX() + 1]))
                            || !isTileEmpty(shape[0][1])
                            || (!isTileEmpty(shape[0][2])
                            && isTileEmpty(gameMas[shape[1][1].getY() + 1][shape[1][1].getX() + 1])))
                    )
                            || (!isTileEmpty(shape[1][0]) && !isTileEmpty(shape[1][1]) && isTileEmpty(shape[1][2])
                            && shape[1][1].getY() < GamePanel.ROW_COUNT - 1
                            && isTileEmpty(gameMas[shape[1][1].getY()][shape[1][1].getX() + 1])
                            && isTileEmpty(gameMas[shape[1][1].getY() + 1][shape[1][1].getX() + 1]))

                            || (isTileEmpty(shape[1][0]) && !isTileEmpty(shape[1][1]) && !isTileEmpty(shape[1][2])
                            && shape[1][1].getY() < GamePanel.ROW_COUNT - 1
                            && isTileEmpty(gameMas[shape[1][1].getY() + 1][shape[1][1].getX()])
                            && isTileEmpty(gameMas[shape[1][1].getY() - 1][shape[1][1].getX() + 1]))
                    ) {
                int y = 0;
                int x = 2;
                int j = 1;
                int i = 0;
                Tile newShape[][] = new Tile[3][2];
                for (Tile[] shapeY : shape) {
                    moveTiles(x, y, i, j, shapeY, newShape);
                    i = 0;
                    j--;
                    x = 1;
                    y = -1;
                }
                setCurrentShape(newShape);
            } else if (Figure.isFirstRowIsFull(shape) && shape[0][1].getY() > 0
                    && isTileEmpty(gameMas[shape[0][1].getY() - 1][shape[0][1].getX()])
                    && isTileEmpty(gameMas[shape[0][1].getY() + 1][shape[0][1].getX()])
                    && ((!isTileEmpty(shape[1][0])
                    && isTileEmpty(gameMas[shape[0][1].getY() - 1][shape[0][1].getX() - 1]))
                    || !isTileEmpty(shape[1][1])
                    || (!isTileEmpty(shape[1][2])
                    && isTileEmpty(gameMas[shape[0][1].getY() + 1][shape[0][1].getX() - 1])))) {
                int y = -1;
                int x = 1;
                int j = 1;
                int i = 0;
                Tile newShape[][] = new Tile[3][2];
                for (Tile[] shapeY : shape) {
                    moveTiles(x, y, i, j, shapeY, newShape);
                    x = 0;
                    y = -2;
                    i = 0;
                    j--;
                }
                setCurrentShape(newShape);
            }
        } else if (shape.length == 3) {
            if (Figure.isFirstColumnIsFull(shape)
                    && shape[1][0].getX() > 0
                    && isTileEmpty(gameMas[shape[1][0].getY()][shape[1][0].getX() + 1])
                    && isTileEmpty(gameMas[shape[1][0].getY()][shape[1][0].getX() - 1])

                    && ((!isTileEmpty(shape[0][1])
                    && isTileEmpty(gameMas[shape[1][0].getY() + 1][shape[1][0].getX() + 1]))
                    || !isTileEmpty(shape[1][1])
                    || (!isTileEmpty(shape[2][1])
                    && isTileEmpty(gameMas[shape[1][0].getY() + 1][shape[1][0].getX() - 1])))) {
                int y = 1;
                int x = 1;
                int j = 2;
                int i = 0;
                Tile newShape[][] = new Tile[2][3];
                for (Tile[] shapeY : shape) {
                    for (Tile tile : shapeY) {
                        if (!isTileEmpty(tile)) {
                            tile.setX(tile.getX() + x);
                            tile.setY(tile.getY() + y);
                            newShape[i][j] = tile;
                        }
                        y++;
                        x--;
                        i++;
                    }
                    if (y == 3) {
                        y = 0;
                    } else if (y == 2) y = -1;
                    x++;
                    i = 0;
                    j--;
                }
                setCurrentShape(newShape);
            } else if (Figure.isSecondColumnIsFull(shape)
                    && shape[1][1].getX() < GamePanel.COLUMNS_COUNT - 1
                    && isTileEmpty(gameMas[shape[1][1].getY()][shape[1][1].getX() + 1])
                    && isTileEmpty(gameMas[shape[1][1].getY()][shape[1][1].getX() - 1])
                    && ((!isTileEmpty(shape[0][0])
                    && isTileEmpty(gameMas[shape[1][1].getY() - 1][shape[1][1].getX() + 1]))
                    || !isTileEmpty(shape[1][0])
                    || (!isTileEmpty(shape[2][0])
                    && isTileEmpty(gameMas[shape[1][1].getY() - 1][shape[1][1].getX() - 1])))) {
                int y = 0;
                int x = 2;
                int j = 2;
                int i = 0;
                Tile newShape[][] = new Tile[2][3];
                for (Tile[] shapeY : shape) {
                    for (Tile tile : shapeY) {
                        if (!isTileEmpty(tile)) {
                            tile.setX(tile.getX() + x);
                            tile.setY(tile.getY() + y);
                            newShape[i][j] = tile;
                        }
                        y++;
                        i++;
                        x--;
                    }
                    if (y == 2) {
                        y = -1;
                    } else if (y == 1) y = -2;
                    x++;
                    i = 0;
                    j--;
                }
                setCurrentShape(newShape);

            } else if (!isTileEmpty(shape[1][0]) && shape[1][0].getX() > 0
                    && ((!isTileEmpty(shape[0][0]) && isTileEmpty(shape[2][0])
                    && isTileEmpty(gameMas[shape[1][0].getY()][shape[1][0].getX() - 1])
                    && isTileEmpty(gameMas[shape[1][0].getY() - 1][shape[1][0].getX() + 1]))

                    || isTileEmpty(shape[0][0]) && !isTileEmpty(shape[2][0])
                    && isTileEmpty(gameMas[shape[1][0].getY() - 1][shape[1][0].getX() - 1])
                    && isTileEmpty(gameMas[shape[1][0].getY() - 1][shape[1][0].getX()]))) {
                int y = 1;
                int x = -1;
                int j = 0;
                int i = 1;
                Tile newShape[][] = new Tile[2][3];
                for (Tile[] shapeY : shape) {
                    for (Tile tile : shapeY) {
                        if (!isTileEmpty(tile)) {
                            tile.setX(tile.getX() + x);
                            tile.setY(tile.getY() + y);
                            newShape[i][j] = tile;
                        }
                        x--;
                        y--;
                        i--;
                    }
                    y++;
                    x = x + 3;
                    i = 1;
                    j++;
                }
                setCurrentShape(newShape);
            }
        }
    }

    private void initHighScores() {
        try (FileInputStream fileInputStream = new FileInputStream(highScoresPath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            highScores = (Player[]) objectInputStream.readObject();
        } catch (EOFException e) {
            highScores = new Player[8];
            for (int i = 0; i < highScores.length; i++) {
                highScores[i] = new Player("---", 0);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveHighScoresToFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(highScoresPath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveTiles(int x, int y, int i, int j, Tile[] tiles, Tile[][] newShape) {
        for (Tile tile : tiles) {
            if (!isTileEmpty(tile)) {
                tile.setX(tile.getX() + x);
                tile.setY(tile.getY() + y);
                newShape[i][j] = tile;
            }
            x--;
            y++;
            i++;
        }
    }

    /**
     * Check if we can move our current shape LEFT or RIGHT.
     */
    public boolean canMoveShape(Direction direction, Tile shape[][]) {
        if (isShapeTouchWall(direction, shape) || isShapeTouchAnotherShape(direction, shape)) {
            return false;
        }
        return true;
    }

    /**
     * Check if our current shape touch LEFT or RIGHT another shape.
     */
    public boolean isShapeTouchAnotherShape(Direction direction, Tile shape[][]) {
        switch (direction) {
            case LEFT:
                for (Tile[] shapeY : shape) {
                    for (Tile tile : shapeY) {
                        if (!isTileEmpty(tile) && !isTileEmpty(gameMas[tile.getY()][tile.getX() + direction.getX()]))
                            return true;
                    }
                }
            case RIGHT:
                for (Tile[] shapeY : shape) {
                    for (int x = shapeY.length - 1; x > -1; x--) {
                        if (!isTileEmpty(shapeY[x])) {
                            Tile tile = shapeY[x];
                            if (!isTileEmpty(gameMas[tile.getY()][tile.getX() + direction.getX()]))
                                return true;
                        }
                    }
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Tile[][] getNextShape0() {
        return nextShape0;
    }

    public void setNextShape0(Tile[][] nextShape0) {
        this.nextShape0 = nextShape0;
    }

    public Tile[][] getNextShape1() {
        return nextShape1;
    }

    public void setNextShape1(Tile[][] nextShape1) {
        this.nextShape1 = nextShape1;
    }

    public Tile[][] getNextShape2() {
        return nextShape2;
    }

    public void setNextShape2(Tile[][] nextShape2) {
        this.nextShape2 = nextShape2;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public boolean isRecord() {
        return isRecord;
    }

    public void setRecord(boolean record) {
        isRecord = record;
    }

    public Player[] getHighScores() {
        return highScores;
    }

    public void setHighScores(Player[] highScores) {
        this.highScores = highScores;
    }
}
