package com.archon.snake.controller;

import android.view.MotionEvent;

import com.archon.snake.model.Boundary;
import com.archon.snake.model.Food;
import com.archon.snake.model.Snake;
import com.archon.snake.util.Const;

/**
 * Created by 文浩 on 2015.3.1.
 */
public class Controller implements Snake.SnakeListener{
    private Snake snake;
    private Food food;
    private Boundary boundary;

    public Controller(Snake snake, Food food, Boundary boundary) {
        this.snake = snake;
        this.food = food;
        this.boundary = boundary;
        init();
    }

    private void init() {
        snake.setSnakeListener(this);
    }

    @Override
    public void snakeMoved() {
        System.out.println(this.getClass().getName() + "-->snakeMoved");
        gameLogic();
    }

    private void gameLogic() {
        if (snake.eatFood(food)) {
            food.setLive(false);
            snake.grow();
        }
    }

    public void onTouch(MotionEvent event) {
        int direction = getKeyEvent(event);
        if (direction < 0) return;

        switch (direction) {
            case Const.UP:
                snake.setCtrlDirection(Const.UP);
                break;
            case Const.DOWN:
                snake.setCtrlDirection(Const.DOWN);
                break;
            case Const.LEFT:
                snake.setCtrlDirection(Const.LEFT);
                break;
            case Const.RIGHT:
                snake.setCtrlDirection(Const.RIGHT);
                break;
            default:
                break;
        }
    }

    private int getKeyEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (x > 1.0/3 * Const.getInstance().screenSize.x
                && x < 2.0/3 * Const.getInstance().screenSize.x
                && y < 1.0/2 * Const.getInstance().screenSize.y)
        {

            return Const.UP;

        }else if (x > 1.0/3 * Const.getInstance().screenSize.x
                && x < 2.0/3 * Const.getInstance().screenSize.x
                && y > 1.0/2 * Const.getInstance().screenSize.y)
        {

            return Const.DOWN;

        }else if (x < 1.0/3 * Const.getInstance().screenSize.x) {

            return Const.LEFT;

        }else if (x > 2.0/3 * Const.getInstance().screenSize.x) {

            return Const.RIGHT;

        }
        return -1;
    }
}
