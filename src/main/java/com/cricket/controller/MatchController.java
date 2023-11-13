package com.cricket.controller;


import com.cricket.entity.Match;
import com.cricket.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchController {


    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/startmatch")
    public String startMatch(@RequestParam String TeamAId, @RequestParam String TeamBId, @RequestParam int TotalOvers , @RequestParam int TotalPlayers ){
        Match match  = matchService.StartMatch(TeamAId,TeamBId, TotalOvers , TotalPlayers);
        return "Match Data has been inserted: " + match;
    }

    @GetMapping("/getallmatch")
    public List<Match> getAllmatch(){
        return matchService.getAllMatch();
    }

}
