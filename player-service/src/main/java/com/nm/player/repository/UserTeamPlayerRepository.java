package com.nm.player.repository;

import java.util.List;

import com.nm.commonpojo.entities.UserTeamPlayer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserTeamPlayerRepository extends CrudRepository<UserTeamPlayer, Integer> {
    List<UserTeamPlayer> findAll();

    @Modifying
    @Query(value = "update user_team_player set points= case when is_captain=true then points+(?3*2) when is_vice_captain=true then points+(?3*1.5) else points+?3 end where player_id=?1 and match_id=?2",nativeQuery = true)
    void updatePointsByPlayerIdAndMatchId(Integer playerId, Integer matchId, Double points);

}
