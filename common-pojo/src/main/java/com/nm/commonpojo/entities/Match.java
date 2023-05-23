package com.nm.commonpojo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.nm.commonpojo.view.Views;

@Entity
@Table(name = "sport_match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="team1_id")
    @JsonView(Views.TeamWithoutPlayer.class)
    private SportTeam team1;

    @ManyToOne
    @JoinColumn(name="team2_id")
    @JsonView(Views.TeamWithoutPlayer.class)
    private SportTeam team2;

    private String venue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setTeam1(SportTeam team1) {
        this.team1 = team1;
    }

    public void setTeam2(SportTeam team2) {
        this.team2 = team2;
    }

    

    
}
