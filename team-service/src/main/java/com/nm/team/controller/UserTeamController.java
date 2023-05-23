package com.nm.team.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.annotation.JsonView;
import com.nm.commonpojo.entities.UserTeam;
import com.nm.commonpojo.view.Views;
import com.nm.team.service.UserTeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-team")
public class UserTeamController {

    @Autowired
    private UserTeamService userTeamService;
    
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserTeam create(@RequestBody UserTeam userTeam) {
        return userTeamService.save(userTeam);
    }

    @GetMapping("/findAll")
    @JsonView(Views.Public.class)
    public List<UserTeam> findAll() {
        return userTeamService.findAll();
    }

    @GetMapping("/by-match-id")
    @JsonView(Views.Public.class)
    public List<UserTeam> findTeamByMatchId(@RequestParam("mathcId") Integer matchId,@RequestParam("userId") Integer userId) {
        return userTeamService.findTeamByMatchId(matchId,userId);
    }

    @GetMapping("/update-team-point")
    public Map<String,String> updateUserTeamPoints(@RequestParam("matchId") Integer matchId) {
        userTeamService.updateUserTeamPoints(matchId);
        Map<String,String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }
    
}
