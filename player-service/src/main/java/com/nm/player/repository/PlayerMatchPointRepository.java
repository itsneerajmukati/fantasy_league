package com.nm.player.repository;

import java.util.List;

import com.nm.commonpojo.entities.PlayerMatchPoint;

import org.springframework.data.repository.CrudRepository;

public interface PlayerMatchPointRepository extends CrudRepository<PlayerMatchPoint, Integer> {
    List<PlayerMatchPoint> findAll();

    PlayerMatchPoint findByPlayer_IdAndMatch_Id(Integer playerId, Integer matchId);
    
    List<PlayerMatchPoint> findByMatch_Id(Integer matchId);
}
