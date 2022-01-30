package com.nm.team.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.nm.commonpojo.entities.SportTeam;
import com.nm.commonpojo.view.Views;
import com.nm.team.service.SportTeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sport-team")
public class SportTeamController {

    @Autowired
    private SportTeamService sportTeamService;
    
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SportTeam create(@RequestBody SportTeam sportTeam) {
        return sportTeamService.save(sportTeam);
    }

    @GetMapping("/findAll")
    @JsonView(Views.Public.class)
    public List<SportTeam> findAll() {
        return sportTeamService.findAll();
    }

    @GetMapping("/find-by-id")
    @JsonView(Views.Public.class)
    public SportTeam findById(@RequestParam("id") Integer id) {
        return sportTeamService.findById(id);
    }

}
