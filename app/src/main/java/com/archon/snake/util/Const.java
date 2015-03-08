package com.archon.snake.util;

import android.graphics.Point;

/**
 * Created by 文浩 on 2015.3.3.
 */
public class Const {
    public static final int GAME_WIDTH = 32;
    public static final int GAME_HEIGHT = 18;
    public static final int EACH_FRAME_LAST = 200;

    public static final int UP = 0;
    public static final int DOWN = 3;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    public Point screenSize;
    public int cellWidth;
    public int cellHeight;

    private static Const instance;

    private Const() {};

    private Const(Point p) {
        screenSize = p;
        cellWidth = p.x / Const.GAME_WIDTH;
        cellHeight = p.y / Const.GAME_HEIGHT;
    }

    public static void setInstance(Point p) {
        if (p != null) {
            instance = new Const(p);
        }
    }

    public static Const getInstance() {
        return instance;
    }
}
