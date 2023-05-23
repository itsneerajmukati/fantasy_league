package com.nm.commonpojo.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

@Entity
@Table(name = "contest_team")
public class ContestTeams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="contest_id")
    private Contest contest;

    @ManyToOne
    @JoinColumn(name="team_id")
    private UserTeam userTeam;
    
    @Column(name="team_rank")
    private Integer rank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public UserTeam getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(UserTeam userTeam) {
        this.userTeam = userTeam;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    
    
    
}
