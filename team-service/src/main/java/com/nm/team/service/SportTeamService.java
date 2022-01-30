package com.nm.team.service;

import java.util.List;

import com.nm.commonpojo.entities.SportTeam;

import org.springframework.stereotype.Component;

@Component
public interface SportTeamService {

    public SportTeam save(SportTeam sportTeam);

    public List<SportTeam> findAll();

    public SportTeam findById(Integer id);
    
}
