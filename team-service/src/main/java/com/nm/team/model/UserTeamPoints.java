package com.nm.team.model;

public class UserTeamPoints {
    
    Integer userTeamId;
    Double points;
    public Integer getUserTeamId() {
        return userTeamId;
    }
    public void setUserTeamId(Integer userTeamId) {
        this.userTeamId = userTeamId;
    }
    public Double getPoints() {
        return points;
    }
    public void setPoints(Double points) {
        this.points = points;
    }
    public UserTeamPoints(Integer userTeamId, Double points) {
        this.userTeamId = userTeamId;
        this.points = points;
    }

    
}
