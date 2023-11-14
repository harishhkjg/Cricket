package com.cricket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamResults {

    private String battingTeam;
    private int totalScore;
    private int wickets;
    private List<String> ballByBall;
    
    private List<String> batsmanName;
}
