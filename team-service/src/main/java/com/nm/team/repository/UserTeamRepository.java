package com.nm.team.repository;

import java.util.List;

import com.nm.commonpojo.entities.UserTeam;
import com.nm.team.model.UserTeamPoints;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserTeamRepository extends CrudRepository<UserTeam, Integer> {
    List<UserTeam> findAll();

    List<UserTeam> findByMatch_IdAndUser_Id(Integer matchId,Integer userId);

    @Modifying
    @Query(value = "update UserTeam u set u.points=:points where id=:userTeamId")
    void updateUserTeamPoints(@Param("userTeamId") Integer userTeamId,@Param("points") Double points);

    @Query(value = "select new com.nm.team.model.UserTeamPoints(u.userTeam.id as userTeamId,SUM(points) as points) from UserTeamPlayer u where u.match.id=:matchId group by u.userTeam.id")
    List<UserTeamPoints> userTeamPoints(@Param("matchId") Integer matchId);
}
