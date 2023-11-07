package com.cricket.controller;

import com.cricket.entity.Team;
import com.cricket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {

        this.teamService = teamService;
    }

    @PostMapping("/addteam")
    public Team insertTeam(@RequestBody Team team){

        return teamService.insertTeam(team);
    }

    @GetMapping("/geteam")
    public List<Team> getteam(Team team)
    {
        return teamService.getteam(team);
    }

}
