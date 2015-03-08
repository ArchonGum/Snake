package com.archon.snake.model;

import android.graphics.Canvas;

/**
 * Created by 文浩 on 2015.3.1.
 */
public class Boundary {

    public void draw(Canvas canvas) {
        System.out.println(this.getClass().getName() + "-->draw");
    }

    public boolean hitSnake() {
        System.out.println(this.getClass().getName() + "-->hitSnake");
        return true;
    }
}
