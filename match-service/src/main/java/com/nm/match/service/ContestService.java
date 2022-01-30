package com.nm.match.service;
import java.util.List;

import com.nm.commonpojo.entities.Contest;
import com.nm.commonpojo.entities.ContestTeams;

import org.springframework.stereotype.Component;

@Component
public interface ContestService {
    public Contest save(Contest contest);
    public List<Contest> findAll();
    public List<Contest> findByMatchId(Integer matchId);
    public ContestTeams joinContest(ContestTeams contest);
    public List<ContestTeams> findContestTeamsByContestId(Integer contestId);
    public void updateRankByContestId(Integer contestId);
    
}
