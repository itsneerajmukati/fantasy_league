package com.nm.commonpojo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_team_player")
public class UserTeamPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="player_id")
    private Players player;

    @ManyToOne
    @JoinColumn(name="user_team_id")
    private UserTeam userTeam ;

    @ManyToOne
    @JoinColumn(name="match_id")
    private Match match;

    private Double points;

    private Boolean isCaptain;

    private Boolean isViceCaptain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

    public Boolean getIsCaptain() {
        return isCaptain;
    }


    public void setIsCaptain(Boolean isCaptain) {
        this.isCaptain = isCaptain;
    }


    public Boolean getIsViceCaptain() {
        return isViceCaptain;
    }


    public void setIsViceCaptain(Boolean isViceCaptain) {
        this.isViceCaptain = isViceCaptain;
    }

    public UserTeam getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(UserTeam userTeam) {
        this.userTeam = userTeam;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    
    
}
