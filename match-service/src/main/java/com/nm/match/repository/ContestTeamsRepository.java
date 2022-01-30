package com.nm.match.repository;

import java.util.List;

import com.nm.commonpojo.entities.ContestTeams;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContestTeamsRepository extends CrudRepository<ContestTeams, Integer>{
    List<ContestTeams> findAll();
    List<ContestTeams> findByContest_Id(Integer contestId);
    
    @Modifying
    @Query(value = "update contest_team c set rank=select x.rank from (SELECT c.id,RANK() OVER (ORDER BY points DESC) as rank FROM contest_team c inner join user_team u on c.team_id=u.id) x where x.id=c.id and c.contest_id=:contestId",nativeQuery = true)
    void updateRankByContestId(@Param("contestId") Integer contestId);
    
}
