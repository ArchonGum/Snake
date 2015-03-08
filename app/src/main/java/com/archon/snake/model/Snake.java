package com.archon.snake.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.archon.snake.util.Const;

import java.util.LinkedList;

/**
 * Created by 文浩 on 2015.3.1.
 */
public class Snake {
    private LinkedList<Point> body;
    private Point tail;
    private Paint paint;
    private int direction;
    private int ctrlDirection;
    private boolean isLive;

    public Snake() {
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        direction = Const.RIGHT;
        ctrlDirection = Const.RIGHT;
        createSnake();
    }

    private void createSnake() {
        body = new LinkedList<>();
        for (int i=0; i<3; i++) {
            body.addFirst(new Point(
                    Const.GAME_WIDTH/2 + i,
                    Const.GAME_HEIGHT/2
            ));
        }
    }

    public void move() {
        System.out.println(this.getClass().getName() + "-->move");
        //Drop tail.
        tail = body.removeLast();

        if (direction + ctrlDirection != 3) {
            direction = ctrlDirection;
        }

        //Add Head.
        Point oldHead = body.getFirst();
        int newX = oldHead.x;
        int newY = oldHead.y;
        switch (direction) {
            case Const.UP:
                newY = oldHead.y - 1;
                if (newY < 0) newY = Const.GAME_HEIGHT -1;
                break;
            case Const.DOWN:
                newY = oldHead.y + 1;
                if (newY >= Const.GAME_HEIGHT) newY = 0;
                break;
            case Const.LEFT:
                newX = oldHead.x - 1;
                if (newX < 0) newX = Const.GAME_WIDTH -1;
                break;
            case Const.RIGHT:
                newX = oldHead.x + 1;
                if (newX >= Const.GAME_WIDTH) newX = 0;
                break;
        }
        body.addFirst(new Point(newX, newY));

        callSnakeMoved();
    }

    public void grow() {
        System.out.println(this.getClass().getName() + "-->grow");
        body.addLast(tail);
    }

    public boolean eatFood(Food food) {
        System.out.println(this.getClass().getName() + "-->eatFood");
        return body.getFirst().equals(food.getPoint());
    }

    public boolean hitBoundary() {
        System.out.println(this.getClass().getName() + "-->hitBoundary");
        return true;
    }

    private boolean hitSelf() {
        System.out.println(this.getClass().getName() + "-->hitBoundary");
        return true;
    }

    public void draw(Canvas canvas) {
        System.out.println(this.getClass().getName() + "-->draw");
        for (Point p : body) {
            canvas.drawRect(
                    p.x * Const.getInstance().cellWidth,
                    p.y * Const.getInstance().cellHeight,
                    (p.x + 1) * Const.getInstance().cellWidth,
                    (p.y + 1) * Const.getInstance().cellHeight,
                    paint
            );
        }

        move();
    }

    public void setCtrlDirection(int ctrlDirection) {
        System.out.println(this.getClass().getName() + "-->setDirection");
        this.ctrlDirection = ctrlDirection;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean isLive) {
        this.isLive = isLive;
    }






    /**
     * Interface definition for a callback to be invoked when the method move() is called.
     */
    public interface SnakeListener {
        void snakeMoved();
    }

    static SnakeListener mSnakeListener;

    public void setSnakeListener(SnakeListener l) {
        if (l != null) {
            mSnakeListener = l;
        }
    }

    private void callSnakeMoved() {
        if (mSnakeListener != null) {
            mSnakeListener.snakeMoved();
        }
    }
}
