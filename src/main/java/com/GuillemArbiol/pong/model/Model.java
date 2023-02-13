package com.GuillemArbiol.pong.model;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;


public class Model implements ModelViewOps{

    public int paddle;
    //private final BehaviorSubject<String> textUpdates = BehaviorSubject.createDefault("Press space to start playing");
    //BehaviorSubject similar to observable, more concrete
    private final BehaviorSubject<Score> scoreUpdates = BehaviorSubject.createDefault(Score.of());
    private final BehaviorSubject<Integer> paddleUpdates = BehaviorSubject.createDefault(5);
    private final BallModel ballModel = new BallModel();
    private OnBallMovedListener listener;
    private int mouseX = 0;
    int[] difs = new int[2];

    private int initPosX = 0;

    //trying to do a different listener for the ball movement
    public interface OnBallMovedListener {
        void onMoved(int newX, int newY);
    }

    @Override
    public String titleText() {
        return "Pong Game";
    }

    //Observable returns BehaviorSubject
    @Override public Observable<Integer> paddleUpdates() {
        return paddleUpdates;
    }
    @Override public Observable<Score> scoreUpdates() {
        return scoreUpdates;
    }
    @Override public void setOnBallMovedListener(OnBallMovedListener listener) {
        this.listener = listener;
    }


    //calculates the new ball and paddle position, called by controller
    @Override
    public void play(){
        difs = calculateBallPosition(difs[0], difs[1]);
        calculatePaddlePosition(initPosX, mouseX);
        initPosX = mouseX;
            listener.onMoved(ballModel.ballX, ballModel.ballY);
            try {
                Thread.sleep(3);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }


    private void calculatePaddlePosition(int initPosX, int x){
        int difm = x - initPosX;

        if(difm >= 0){
            paddle = Math.min(460, paddle + difm);

            paddleUpdates.onNext(paddle);
        }else{
            paddle = Math.max(0, paddle + difm);
            paddleUpdates.onNext(paddle);
        }

    }

    private int[] calculateBallPosition(int difx, int dify){

        if( ballModel.ballX == 0 ){
            System.out.print("difx=1");
            difx = 1;
        }

        //right side touch
        if( ballModel.ballX == 600 ){
            System.out.print("difx=-1");
            difx = -1;
        }

        //top superior
        if(ballModel.ballY == 34){
            System.out.print("bally=34");
            if(ballModel.ballX < paddle || ballModel.ballX > paddle+150){
                scoreUpdates.onNext(Score.incrementMiss(scoreUpdates.getValue()));
                startGame();
                difx = 1;
                dify = -1;
            }else{
                dify = 1;
                scoreUpdates.onNext(Score.incrementHits(scoreUpdates.getValue()));
            }
        }

        //bottom border
        if(ballModel.ballY == 382){
            dify = -1;
        }

        //
        ballModel.ballX += difx* ballModel.ballVelocityX;
        ballModel.ballY += dify* ballModel.ballVelocityY;
        int[] difs = new int[2];
        difs[0]=difx;
        difs[1]=dify;
        return difs;
    }


    public void setMouseX(int x){
        this.mouseX = x;
    }

    /*@Override
    public Observable<String> gameTextUpdates() {
        return textUpdates;
    }*/
    public void startGame() {
        //textUpdates.onNext("");
        ballModel.ballX = 286;
        ballModel.ballY = 328;
        paddle = 228;
        difs[0] = 1;
        difs[1] = -1;
    }

}

