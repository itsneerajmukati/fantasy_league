package com.nm.match.repository;

import java.util.List;

import com.nm.commonpojo.entities.Match;

import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Integer> {
    List<Match> findAll();
}
