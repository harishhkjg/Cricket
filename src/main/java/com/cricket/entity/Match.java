package com.cricket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Match")
public class Match {
    @Id
    private String id;
    private String teamA;
    private String teamB;

    private List<TeamResults> teamResults;
    private String winner;

    public void addTeamResult(String battingTeam, int totalScore, int wickets, List<String> ballByBall,List<String> batsmanName) {
        if (teamResults == null) {
            teamResults = new ArrayList<>();
        }
        TeamResults teamresult = new TeamResults(battingTeam, totalScore, wickets, ballByBall,batsmanName);
        teamResults.add(teamresult);
    }
}
