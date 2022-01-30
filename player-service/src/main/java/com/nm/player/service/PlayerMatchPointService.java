package com.nm.player.service;

import java.util.List;
import java.util.Map;

import com.nm.commonpojo.entities.PlayerMatchPoint;
import com.nm.commonpojo.entities.Score;

import org.springframework.stereotype.Component;

@Component
public interface PlayerMatchPointService {

    public PlayerMatchPoint save(PlayerMatchPoint playerMatchPoint);

    public List<PlayerMatchPoint> findAll();

    public String calculatePlayerPoint(Score score);

    public Map<String, String> playingElevelPoint(List<PlayerMatchPoint> playerMatchPoints);

    public List<PlayerMatchPoint> findByMatchId(Integer matchId);
    
}
