package com.nm.team.service.impl;

import java.util.List;

import com.nm.commonpojo.entities.UserTeam;
import com.nm.team.model.UserTeamPoints;
import com.nm.team.repository.UserTeamRepository;
import com.nm.team.service.UserTeamService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
public class UserTeamServiceImpl implements UserTeamService {

    Logger logger = LogManager.getLogger(SportTeamServiceImpl.class);

    @Autowired
    private UserTeamRepository userTeamRepository;

    @Override
    public UserTeam save(UserTeam userTeam) {
        logger.debug("Inside save method");
        return userTeamRepository.save(userTeam);
    }

    @Override
    public List<UserTeam> findAll() {
        return userTeamRepository.findAll();
    }

    @Override
    public List<UserTeam> findTeamByMatchId(Integer matchId, Integer userId) {
        return userTeamRepository.findByMatch_IdAndUser_Id(matchId,userId);
    }
    
    @Override
    @Transactional
    public void updateUserTeamPoints(Integer matchId) {
        List<UserTeamPoints> userTeamPoints = userTeamRepository.userTeamPoints(matchId);
        userTeamPoints.forEach(userTeam -> {
            userTeamRepository.updateUserTeamPoints(userTeam.getUserTeamId(),userTeam.getPoints());
        });
        
    }
}
