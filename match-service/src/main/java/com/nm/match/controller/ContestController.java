package com.nm.match.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonView;
import com.nm.commonpojo.entities.Contest;
import com.nm.commonpojo.entities.ContestTeams;
import com.nm.commonpojo.view.Views;
import com.nm.match.service.ContestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contest")
public class ContestController {

    @Autowired
    private ContestService contestService;
    
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Contest creatContest(@RequestBody Contest contest) {
        return contestService.save(contest);
    }

    @GetMapping("/find-all")
    @JsonView(Views.Public.class)
    public List<Contest> findAll() {
        return contestService.findAll();
    }

    @GetMapping("/find-by-match")
    @JsonView(Views.Public.class)
    public List<Contest> findByMatchId(@QueryParam("matchId") Integer matchId) {
        return contestService.findByMatchId(matchId);
    }

    @PostMapping(path = "/join-contest", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ContestTeams creatContest(@RequestBody ContestTeams contestTeams) {
        return contestService.joinContest(contestTeams);
    }

    @GetMapping("/find-by-contest")
    @JsonView(Views.Public.class)
    public List<ContestTeams> findContestTeamsByContest(@QueryParam("contestId") Integer contestId) {
        return contestService.findContestTeamsByContestId(contestId);
    }

    @GetMapping("/update-rank")
    public Map<String,String> updateRankByContestId(@QueryParam("contestId") Integer contestId) {
        contestService.updateRankByContestId(contestId);
        Map<String,String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }
}
