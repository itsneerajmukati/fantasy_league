package com.nm.match.service.impl;

import java.util.List;

import com.nm.commonpojo.entities.Match;
import com.nm.match.repository.MatchRepository;
import com.nm.match.service.MatchService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;
    
    Logger logger = LogManager.getLogger(MatchServiceImpl.class);

    @Override
    public Match save(Match match) {
        logger.debug("Inside save method");
        return matchRepository.save(match);
    }

    @Override
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public Match findById(Integer matchId) {
        return matchRepository.findById(matchId).get();
    }

}
