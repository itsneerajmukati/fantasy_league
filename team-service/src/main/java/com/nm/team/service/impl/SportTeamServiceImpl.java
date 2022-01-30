package com.nm.team.service.impl;

import java.util.List;

import com.nm.commonpojo.entities.SportTeam;
import com.nm.team.repository.SportTeamRepository;
import com.nm.team.service.SportTeamService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
public class SportTeamServiceImpl implements SportTeamService {

    Logger logger = LogManager.getLogger(SportTeamServiceImpl.class);

    @Autowired
    private SportTeamRepository sportTeamRepository;

    @Override
    public SportTeam save(SportTeam user) {
        logger.debug("Inside save method");
        return sportTeamRepository.save(user);
    }

    @Override
    public List<SportTeam> findAll() {
        return sportTeamRepository.findAll();
    }

    @Override
    public SportTeam findById(Integer id) {
        return sportTeamRepository.findById(id).get();
    }

}
