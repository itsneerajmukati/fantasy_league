package com.nm.player.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.annotation.JsonView;
import com.nm.commonpojo.entities.UserTeamPlayer;
import com.nm.commonpojo.view.Views;
import com.nm.player.service.UserTeamPlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-team-player")
public class UserTeamPlayerController {

    @Autowired
    private UserTeamPlayerService userTeamPlayerService;
    
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserTeamPlayer create(@RequestBody UserTeamPlayer userTeamPlayer) {
        return userTeamPlayerService.save(userTeamPlayer);
    }

    @PostMapping(path = "/create-bulk", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<UserTeamPlayer> createBulk(@RequestBody List<UserTeamPlayer> userTeamPlayers) {
        return userTeamPlayerService.createBulk(userTeamPlayers);
    }

    @GetMapping("/findAll")
    @JsonView(Views.Public.class)
    public List<UserTeamPlayer> findAll() {
        return userTeamPlayerService.findAll();
    }

    @GetMapping("/update-players-point")
    public Map<String,String> updatePlayerPointsInAllTeam(@RequestParam("matchId") Integer matchId) {
        userTeamPlayerService.updatePlayerPointsInAllTeam(matchId);
        Map<String,String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }
    
}
