package com.nm.team.repository;

import java.util.List;

import com.nm.commonpojo.entities.SportTeam;

import org.springframework.data.repository.CrudRepository;

public interface SportTeamRepository extends CrudRepository<SportTeam, Integer> {
    List<SportTeam> findAll();
}
