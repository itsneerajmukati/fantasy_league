package com.nm.match.service;

import java.util.List;

import com.nm.commonpojo.entities.Match;

import org.springframework.stereotype.Component;

@Component
public interface MatchService {

    public Match save(Match match);
    public List<Match> findAll();
    public Match findById(Integer matchId);
    
}
