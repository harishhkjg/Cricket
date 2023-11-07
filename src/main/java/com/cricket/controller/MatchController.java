package com.cricket.controller;


import com.cricket.entity.Match;
import com.cricket.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {


    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/startmatch")
    public ResponseEntity<Match> startMatch(@RequestParam String TeamAId, @RequestParam String TeamBId, @RequestParam int Over , @RequestParam int PlayerSize ){
        Match match  = matchService.StartMatch(TeamAId,TeamBId, Over , PlayerSize);
        return ResponseEntity.status(HttpStatus.CREATED).body(match);
    }

    @GetMapping("/getallmatch")
    public List<Match> getAllmatch(){
        return matchService.getAllMatch();
    }

}
