package com.GuillemArbiol.pong.controller;


import com.GuillemArbiol.pong.model.ModelViewOps;
import com.GuillemArbiol.pong.model.Score;
import com.GuillemArbiol.pong.view.View;
import com.GuillemArbiol.pong.model.Model;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;


//import io.reactivex.rxjava3.core.Subscriber;

//import javax.xml.ws.Service.Mode;

public class Controller {

    public ModelViewOps model;
    public View view;

    private final BehaviorSubject<String> textUpdates = BehaviorSubject.createDefault("Press space to start playing");
    private final BehaviorSubject<Score> scoreUpdates = BehaviorSubject.createDefault(Score.of());
    private final BehaviorSubject<Integer> paddleUpdates = BehaviorSubject.createDefault(5);

    public Observable<String> gameTextUpdates() {
        return textUpdates;
    }
    public Observable<Integer> paddleUpdates() {
        return paddleUpdates;
    }
    public Observable<Score> scoreUpdates() {
        return scoreUpdates;
    }

    public Controller(View view, ModelViewOps model){
        this.model = model;
        this.view=view;
    }

    //called by main
    public void start(){
        setListeners();
        playPong();
    }

    //gets mouse position and starts the loop
    public void playPong(){
        model.startGame();
        int x=0;
        while(true){
            //I get the mouse position here and not in the View because it is not related to UI, it changes too fast
            //and it doesn't need processing here, so I think it's easier and doesn't cause problems
            x = MouseInfo.getPointerInfo().getLocation().x;
            model.setMouseX(x);
            model.play();

            try {
                Thread.sleep(3);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    private void setListeners(){
        model.scoreUpdates().subscribe(view::setScore);
        model.paddleUpdates().subscribe(view::setPaddlePosition);
        model.setOnBallMovedListener(view::setBallPosition);
    }




}
