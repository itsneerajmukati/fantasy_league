package com.nm.player.controller;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.nm.commonpojo.entities.Players;
import com.nm.commonpojo.view.Views;
import com.nm.player.service.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    public Players creatPlayer(@RequestBody Players players) {
        return playerService.save(players);
    }

    @PostMapping(path = "/create-bulk", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Players> creatPlayers(@RequestBody List<Players> players) {
        List<Players> playersList = new ArrayList<>();
        for(Players players2 : players) {
            playersList.add(playerService.save(players2));
        }
        return playersList;
    }

    @GetMapping("/findAll")
    @JsonView(Views.Public.class)
    public List<Players> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/find-by-team-id")
    @JsonView(Views.Public.class)
    public List<Players> getPlayersByTeamId(@RequestParam("teamId") Integer teamId) {
        return playerService.getPlayersByTeamId(teamId);
    }

}
