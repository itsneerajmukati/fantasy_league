package com.nm.player.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.nm.commonpojo.entities.PlayerMatchPoint;
import com.nm.commonpojo.entities.UserTeamPlayer;
import com.nm.player.repository.PlayerMatchPointRepository;
import com.nm.player.repository.UserTeamPlayerRepository;
import com.nm.player.service.UserTeamPlayerService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Component
public class UserTeamPlayerServiceImpl implements UserTeamPlayerService {
    Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    @Autowired
    private UserTeamPlayerRepository userTeamPlayerRepository;

    @Autowired
    private PlayerMatchPointRepository playerMatchPointRepository;


    @Override
    public UserTeamPlayer save(UserTeamPlayer userTeamPlayer) {
        logger.debug("Inside save method");
        return userTeamPlayerRepository.save(userTeamPlayer);
    }

    @Override
    public List<UserTeamPlayer> createBulk(List<UserTeamPlayer> userTeamPlayers) {
        List<UserTeamPlayer> userTeamPlayersList = new ArrayList<>();
        for(UserTeamPlayer userTeamPlayer : userTeamPlayers) {
            userTeamPlayer.setPoints(0.0);
            userTeamPlayersList.add(save(userTeamPlayer));
        }
        return userTeamPlayersList;
    }
    
    @Override
    public List<UserTeamPlayer> findAll() {
        return userTeamPlayerRepository.findAll();
    }

    @Override
    @Transactional
    public void updatePlayerPointsInAllTeam(Integer matchId) {
        List<PlayerMatchPoint> playerMatchPoints = playerMatchPointRepository.findByMatch_Id(matchId);
        playerMatchPoints.forEach(
            (playerMatchPoint) -> {
                userTeamPlayerRepository.updatePointsByPlayerIdAndMatchId(playerMatchPoint.getPlayer().getId(), matchId, playerMatchPoint.getPoints());
            }
        );
    }

}
