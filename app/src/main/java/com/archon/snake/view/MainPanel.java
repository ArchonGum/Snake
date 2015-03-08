package com.archon.snake.view;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.archon.snake.controller.Controller;
import com.archon.snake.model.Boundary;
import com.archon.snake.model.Food;
import com.archon.snake.model.Snake;
import com.archon.snake.util.Const;

/**
 * Created by 文浩 on 2015.3.1.
 */
public class MainPanel extends SurfaceView implements Callback, Runnable{
    private Activity mActivity;
    private GameInfo mGameInfo;
    private static SurfaceHolder mHolder;
    private static Canvas mCanvas;

    static class GameInfo {
        public Snake snake;
        public Food food;
        public Boundary boundary;
        public Controller controller;

        public GameInfo() {
            snake = new Snake();
            food = new Food();
            boundary = new Boundary();
            controller = new Controller(snake, food, boundary);
        }
    }

    public MainPanel(Activity activity) {
        super(activity);
        mActivity = activity;
        init();
    }

    private void init() {
        setFocusable(true);
        mHolder = this.getHolder();
        mHolder.addCallback(this);

        Point p = new Point();
        mActivity.getWindowManager().getDefaultDisplay().getSize(p);
        Const.setInstance(p);

        setGameInfo();
    }

    public void setGameInfo() {
        if (mGameInfo == null) {
            mGameInfo = new GameInfo();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mGameInfo.snake.setLive(true);
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mGameInfo.snake.setLive(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGameInfo.controller.onTouch(event);
        return super.onTouchEvent(event);
    }

    private void display() {
        mCanvas = mHolder.lockCanvas();
        if (mCanvas != null) {
            try {
                mCanvas.drawRGB(0, 0, 0);
                mGameInfo.boundary.draw(mCanvas);
                mGameInfo.food.draw(mCanvas);
                mGameInfo.snake.draw(mCanvas);
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @Override
    public void run() {
        while (mGameInfo.snake.isLive()) {
            long start = System.currentTimeMillis();

            display();

            long end = System.currentTimeMillis();
            if (end - start < Const.EACH_FRAME_LAST) {
                try {
                    Thread.sleep(Const.EACH_FRAME_LAST - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
