package com.nm.player.service;

import java.util.List;

import com.nm.commonpojo.entities.Players;

import org.springframework.stereotype.Component;

@Component
public interface PlayerService {

    public Players save(Players players);

    public List<Players> findAll();

    public List<Players> getPlayersByTeamId(Integer teamId);
    
}
