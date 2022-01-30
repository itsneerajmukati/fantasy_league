package com.nm.player.repository;

import java.util.List;

import com.nm.commonpojo.entities.Players;
import com.nm.commonpojo.entities.SportTeam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Players, Integer> {
    List<Players> findAll();

    @Query("select p from Players p where ?1 MEMBER OF p.sportTeams")
    List<Players> getPlayersByTeamId(SportTeam teamId);
}
