package com.archon.snake.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.archon.snake.util.Const;

import java.util.Random;

/**
 * Created by 文浩 on 2015.3.1.
 */
public class Food {
    private Point point;
    private Paint paint;
    private boolean isLive;

    public Food() {
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);

        setPoint();

        isLive = true;
    }

    public boolean isEaten() {
        System.out.println(this.getClass().getName() + "-->isEaten");
        return true;
    }

    public void draw(Canvas canvas) {
        if (!isLive) {
            setPoint();
            isLive = true;
        }

        canvas.drawRect(
                point.x * Const.getInstance().cellWidth,
                point.y * Const.getInstance().cellHeight,
                (point.x + 1) * Const.getInstance().cellWidth,
                (point.y + 1) * Const.getInstance().cellHeight,
                paint);
        System.out.println(this.getClass().getName() + "-->draw");
    }

    private void setPoint() {
        Random random = new Random();
        int x = random.nextInt(Const.GAME_WIDTH);
        int y = random.nextInt(Const.GAME_HEIGHT);
        point = new Point(x, y);
    }

    public Point getPoint() {
        return point;
    }

    public void setLive(boolean isLive) {
        this.isLive = isLive;
    }
}

