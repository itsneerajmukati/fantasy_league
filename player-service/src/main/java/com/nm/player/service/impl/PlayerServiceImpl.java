package com.nm.player.service.impl;

import java.util.List;

import com.nm.commonpojo.entities.Players;
import com.nm.commonpojo.entities.SportTeam;
import com.nm.player.repository.PlayerRepository;
import com.nm.player.service.PlayerService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
public class PlayerServiceImpl implements PlayerService {

    Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Players save(Players user) {
        logger.debug("Inside save method");
        return playerRepository.save(user);
    }
    
    @Override
    public List<Players> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<Players> getPlayersByTeamId(Integer teamId) {
        SportTeam sportTeam = new SportTeam();
        sportTeam.setId(teamId);
        return playerRepository.getPlayersByTeamId(sportTeam);
    }

}
