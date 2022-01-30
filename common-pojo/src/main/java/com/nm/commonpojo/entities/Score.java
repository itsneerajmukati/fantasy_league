package com.nm.commonpojo.entities;

public class Score {
    private Integer run;
    private WicketType wicket;
    private Integer strikeBatsman;
    private Integer fielder;
    private Integer bowler;
    private Integer thrower;
    private Integer wicketKeeper;
    private Integer matchId;

    public enum WicketType {
        BOLD,
        CATCH,
        RUNOUT,
        LBW
    }

    public Integer getRun() {
        return run;
    }

    public void setRun(Integer run) {
        this.run = run;
    }

    public WicketType getWicket() {
        return wicket;
    }

    public void setWicket(WicketType wicket) {
        this.wicket = wicket;
    }

    public Integer getStrikeBatsman() {
        return strikeBatsman;
    }

    public void setStrikeBatsman(Integer strikeBatsman) {
        this.strikeBatsman = strikeBatsman;
    }

    public Integer getFielder() {
        return fielder;
    }

    public void setFielder(Integer fielder) {
        this.fielder = fielder;
    }

    public Integer getBowler() {
        return bowler;
    }

    public void setBowler(Integer bowler) {
        this.bowler = bowler;
    }

    public Integer getThrower() {
        return thrower;
    }

    public void setThrower(Integer thrower) {
        this.thrower = thrower;
    }

    public Integer getWicketKeeper() {
        return wicketKeeper;
    }

    public void setWicketKeeper(Integer wicketKeeper) {
        this.wicketKeeper = wicketKeeper;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    

    
}