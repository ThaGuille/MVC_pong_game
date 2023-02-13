package com.GuillemArbiol.pong.model;

import com.GuillemArbiol.pong.model.Model.OnBallMovedListener;
import io.reactivex.Observable;

public interface ModelViewOps {

    String titleText();

    Observable<Score> scoreUpdates();
    Observable<Integer> paddleUpdates();

    //Observable<String> gameTextUpdates();

    void setOnBallMovedListener(OnBallMovedListener listener);

    void setMouseX(int x);
    void startGame();
    void play();
}
