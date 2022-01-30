package com.nm.match.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.nm.commonpojo.entities.Match;
import com.nm.commonpojo.view.Views;
import com.nm.match.service.MatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;
    
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Team.class)
    public Match creatMatch(@RequestBody Match match) {
        return matchService.save(match);
    }

    @GetMapping("/find-all")
    @JsonView(Views.TeamWithoutPlayer.class)
    public List<Match> findAll() {
        return matchService.findAll();
    }

    @GetMapping("/find-by-id")
    @JsonView(Views.TeamWithoutPlayer.class)
    public Match findById(@RequestParam("matchId") Integer matchId) {
        return matchService.findById(matchId);
    }
    
}
