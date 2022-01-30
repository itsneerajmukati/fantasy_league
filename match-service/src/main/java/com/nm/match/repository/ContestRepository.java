package com.nm.match.repository;

import java.util.List;

import com.nm.commonpojo.entities.Contest;

import org.springframework.data.repository.CrudRepository;

public interface ContestRepository extends CrudRepository<Contest, Integer> {
    List<Contest> findAll();
    List<Contest> findByMatch_Id(Integer matchId);
}
