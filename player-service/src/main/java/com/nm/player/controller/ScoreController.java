package com.nm.player.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;
import com.nm.commonpojo.entities.PlayerMatchPoint;
import com.nm.commonpojo.entities.Score;
import com.nm.commonpojo.view.Views;
import com.nm.player.service.PlayerMatchPointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("score")
public class ScoreController {
    
    @Autowired
    private PlayerMatchPointService playerMatchPointService;

    @PostMapping(path = "calculate-point", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> calculatePlayerPoint(@RequestBody Score score) {
        playerMatchPointService.calculatePlayerPoint(score);
        Map<String,String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }

    @PostMapping(path = "playing-eleven-point", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> playingElevelPoint(@RequestBody List<PlayerMatchPoint> playerMatchPoints) {
        return playerMatchPointService.playingElevelPoint(playerMatchPoints);
    }

    @GetMapping("/findAll")
    @JsonView(Views.Public.class)
    public List<PlayerMatchPoint> findAll() {
        return playerMatchPointService.findAll();
    }

    @GetMapping("/find-by-match")
    @JsonView(Views.Public.class)
    public List<PlayerMatchPoint> findByMatchId(@RequestParam("id") Integer matchId) {
        return playerMatchPointService.findByMatchId(matchId);
    }
}
