package com.nm.team.service;

import java.util.List;

import com.nm.commonpojo.entities.UserTeam;

import org.springframework.stereotype.Component;

@Component
public interface UserTeamService {

    public UserTeam save(UserTeam userTeam);

    public List<UserTeam> findAll();

    public List<UserTeam> findTeamByMatchId(Integer matchId,Integer userId);

    public void updateUserTeamPoints(Integer matchId);
    
}
