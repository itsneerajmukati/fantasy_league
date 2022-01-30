package com.nm.commonpojo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.nm.commonpojo.view.Views;

@Entity
@Table(name = "player")
public class Players {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String type;

    private Integer point;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
        name = "player_sport_team", 
        joinColumns = @JoinColumn(name = "player_id",referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "sport_team_id", referencedColumnName = "id"))
    @JsonView(Views.Team.class)
    private Set<SportTeam> sportTeams = new HashSet<>();

   
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public Integer getPoint() {
        return point;
    }


    public void setPoint(Integer point) {
        this.point = point;
    }

    public Set<SportTeam> getSportTeams() {
        return sportTeams;
    }


    public void setSportTeams(Set<SportTeam> sportTeams) {
        this.sportTeams = sportTeams;
    }


}
