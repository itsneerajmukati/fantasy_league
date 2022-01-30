package com.nm.player.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nm.commonpojo.entities.PlayerMatchPoint;
import com.nm.commonpojo.entities.Score;
import com.nm.player.constant.PointConstant;
import com.nm.player.repository.PlayerMatchPointRepository;
import com.nm.player.service.PlayerMatchPointService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PlayerMatchPointServiceImpl implements PlayerMatchPointService {
    Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    @Autowired
    private PlayerMatchPointRepository playerMatchPointRepository;

    @Override
    public PlayerMatchPoint save(PlayerMatchPoint playerMatchPoint) {
        logger.debug("Inside save method");
        return playerMatchPointRepository.save(playerMatchPoint);
    }
    
    @Override
    public List<PlayerMatchPoint> findAll() {
        return playerMatchPointRepository.findAll();
    }

    @Override
    public String calculatePlayerPoint(Score score) {
        // if run is 0 then do nothing.
        PlayerMatchPoint playerMatchPoint = playerMatchPointRepository.findByPlayer_IdAndMatch_Id(score.getBowler(), score.getMatchId());
        System.out.println(playerMatchPoint!=null?playerMatchPoint.toString():"null");
        List<PlayerMatchPoint> playerMatchPointToUpdate = new ArrayList<>();
        if(score != null && (score.getRun() > 0 || score.getWicket() !=null)) {
            if(score.getWicket()!=null) {
                playerMatchPointToUpdate = calculateWicketPoint(score);
            }else {
                playerMatchPointToUpdate = calculateRunPoint(score);
            }
        }
        playerMatchPointToUpdate.forEach(pmp -> {playerMatchPointRepository.save(pmp);});
        
        return "Completed";
    }

    private List<PlayerMatchPoint> calculateRunPoint(Score score) {
        Double batsmanPoints = 0.0;
        // if 4 or 6 extra point
        if(score.getRun().equals(4)) {
            batsmanPoints+= PointConstant.FOUR_POINT;
        }else if(score.getRun().equals(6)) {
            batsmanPoints+= PointConstant.SIX_POINT;
        }
        // run point always
        if(score.getRun()!=null) {
            batsmanPoints+= (PointConstant.RUN_POINT*score.getRun());
        }
        List<PlayerMatchPoint> playerMatchPointToUpdate = new ArrayList<>();
        PlayerMatchPoint batsman = playerMatchPointRepository.findByPlayer_IdAndMatch_Id(score.getStrikeBatsman(), score.getMatchId());
        batsman.setPoints(batsman.getPoints()+batsmanPoints);
        playerMatchPointToUpdate.add(batsman);
        return playerMatchPointToUpdate;
        
    }

    private List<PlayerMatchPoint> calculateWicketPoint(Score score) {
        Double bowlerPoints = 0.0;
        Double catchPoints = 0.0;
        Double throwPoints = 0.0;
        Double wkPoints = 0.0;
        Double batsmanPoints = 0.0;
        bowlerPoints+=PointConstant.WICKET_POINT;
        if(score.getWicket().equals(Score.WicketType.BOLD)) {
            bowlerPoints+=PointConstant.WICKET_BOLD;
        }else if(score.getWicket().equals(Score.WicketType.LBW)) {
            bowlerPoints+=PointConstant.WICKET_LBW;
        }else if(score.getWicket().equals(Score.WicketType.CATCH)) {
            catchPoints+=PointConstant.WICKET_CATCH;
        }else if(score.getWicket().equals(Score.WicketType.RUNOUT)) {
            throwPoints+=PointConstant.WICKET_THROW;
            wkPoints+=PointConstant.WICKET_THROW_WK;
            batsmanPoints+= (PointConstant.RUN_POINT*score.getRun());
        }
        logger.info("bowlerPoints" + bowlerPoints);
        List<PlayerMatchPoint> playerMatchPointToUpdate = new ArrayList<>();
        PlayerMatchPoint bowler = playerMatchPointRepository.findByPlayer_IdAndMatch_Id(score.getBowler(), score.getMatchId());
        bowler.setPoints(bowler.getPoints()+bowlerPoints);
        playerMatchPointToUpdate.add(bowler);
        if(score.getFielder()!=null) {
            PlayerMatchPoint fielder = playerMatchPointRepository.findByPlayer_IdAndMatch_Id(score.getFielder(), score.getMatchId());
            fielder.setPoints(fielder.getPoints()+catchPoints);
            playerMatchPointToUpdate.add(fielder);
        }
        if(score.getThrower()!=null) {
            PlayerMatchPoint thrower = playerMatchPointRepository.findByPlayer_IdAndMatch_Id(score.getThrower(), score.getMatchId());
            thrower.setPoints(thrower.getPoints()+throwPoints);
            playerMatchPointToUpdate.add(thrower);   
        }
        if(score.getWicketKeeper()!=null) {
            PlayerMatchPoint wk = playerMatchPointRepository.findByPlayer_IdAndMatch_Id(score.getWicketKeeper(), score.getMatchId());
            wk.setPoints(wk.getPoints()+wkPoints);
            playerMatchPointToUpdate.add(wk);
        }
        if(score.getStrikeBatsman()!=null) {
            PlayerMatchPoint batsman = playerMatchPointRepository.findByPlayer_IdAndMatch_Id(score.getStrikeBatsman(), score.getMatchId());
            batsman.setPoints(batsman.getPoints()+batsmanPoints);
            playerMatchPointToUpdate.add(batsman);
        }
        logger.info("size " + playerMatchPointToUpdate.size());
        return playerMatchPointToUpdate;
    }

    @Override
    public Map<String, String> playingElevelPoint(List<PlayerMatchPoint> playerMatchPoints) {
        playerMatchPoints.forEach(playerMatchPoint -> {
            playerMatchPoint.setPoints(2.0);
        });
        for(PlayerMatchPoint playerMatchPoint : playerMatchPoints) {
            playerMatchPointRepository.save(playerMatchPoint);
        }
        Map<String, String> map = new HashMap<>();
        map.put("status", "completed");
        return map;
    }

    @Override
    public List<PlayerMatchPoint> findByMatchId(Integer matchId) {
        return playerMatchPointRepository.findByMatch_Id(matchId);
    }
}
