package com.GuillemArbiol.pong.model;

public class Score {

    private int hits;
    private int miss;

    private Score(int hits, int miss) {
        this.hits = hits;
        this.miss = miss;
    }


    public int getHits() {
        return hits;
    }

    public int getMiss() {
        return miss;
    }

    public static Score of(){
        return new Score(0, 0);
    }
    static Score incrementHits(Score current){
        return new Score(current.hits + 1, current.miss);
    }
    static Score incrementMiss(Score current){
        return new Score(current.hits, current.miss +1);
    }
}
