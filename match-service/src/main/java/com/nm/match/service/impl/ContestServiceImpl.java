package com.nm.match.service.impl;

import java.util.List;

import com.nm.commonpojo.entities.Contest;
import com.nm.commonpojo.entities.ContestTeams;
import com.nm.match.repository.ContestRepository;
import com.nm.match.repository.ContestTeamsRepository;
import com.nm.match.service.ContestService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class ContestServiceImpl implements ContestService {
    
    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private ContestTeamsRepository contestTeamsRepository;
    
    Logger logger = LogManager.getLogger(ContestServiceImpl.class);

    @Override
    public Contest save(Contest contest) {
        logger.debug("Inside save method");
        return contestRepository.save(contest);
    }

    @Override
    public List<Contest> findAll() {
        return contestRepository.findAll();
    }

    @Override
    public List<Contest> findByMatchId(Integer matchId) {
        return contestRepository.findByMatch_Id(matchId);
    }

    @Override
    public ContestTeams joinContest(ContestTeams contesttTeams) {
        return contestTeamsRepository.save(contesttTeams);
    }

    @Override
    public List<ContestTeams> findContestTeamsByContestId(Integer contestId) {
        return contestTeamsRepository.findByContest_Id(contestId);
    }

    @Override
    @Transactional
    public void updateRankByContestId(Integer contestId) {
        contestTeamsRepository.updateRankByContestId(contestId);
    }
}
