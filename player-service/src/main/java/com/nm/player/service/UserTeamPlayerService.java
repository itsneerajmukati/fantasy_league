package com.nm.player.service;

import java.util.List;

import com.nm.commonpojo.entities.UserTeamPlayer;

import org.springframework.stereotype.Component;

@Component
public interface UserTeamPlayerService {

    public UserTeamPlayer save(UserTeamPlayer userTeamPlayer);

    public List<UserTeamPlayer> createBulk(List<UserTeamPlayer> userTeamPlayers);

    public List<UserTeamPlayer> findAll();

    public void updatePlayerPointsInAllTeam(Integer matchId);
    
}
