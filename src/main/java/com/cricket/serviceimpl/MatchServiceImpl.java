package com.cricket.serviceimpl;

import com.cricket.entity.Match;
import com.cricket.entity.Team;
import com.cricket.entity.TeamResults;
import com.cricket.repo.MatchRepository;
import com.cricket.repo.TeamRepository;
import com.cricket.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepo;

    @Autowired
    private TeamRepository teamRepo;

    public List<Match> getAllMatch() {

        return matchRepo.findAll();
    }


    @Override
    public Match StartMatch(String teamAId, String teamBId, int Overs, int PlayerSize) {

        Team teamA = teamRepo.findById(teamAId).get();
        Team teamB = teamRepo.findById(teamBId).get();

        Match match = new Match();
        match.setTeamA(teamA.getTeamname());
        match.setTeamB(teamB.getTeamname());

        int AllBalls = Overs*6;

        StartInnings(match, teamA, teamB, AllBalls, PlayerSize);
        StartInnings(match, teamB, teamA, AllBalls, PlayerSize);

        int teamAScore = IndividualTeamScore(match, teamA.getTeamname());
        int teamBScore = IndividualTeamScore(match, teamB.getTeamname());

        String MatchWinner = "";
        if(teamAScore > teamBScore)
        {
            MatchWinner = teamA.getTeamname();
        }
        else if(teamBScore > teamAScore)
        {
            MatchWinner = teamB.getTeamname();
        }
        else
        {
            MatchWinner = "Match Drawn";
        }

        match.setWinner(MatchWinner);

        matchRepo.save(match);
        return match;

        
    }
    private void StartInnings(Match match, Team BattingTeam, Team BowlingTeam, int totalBalls, int Playersize) {
        int InningsScore = 0;
        int wicketCount = 0;
        List<String> EachBall = new ArrayList<>();

        List<String> batsmanNamedata = new ArrayList<>();
        for (int ball = 1; ball <= totalBalls && wicketCount<Playersize; ball++) {
            int StoreBalls = RandomBallGenerator();
            if (StoreBalls == 7) {
                wicketCount++;
                batsmanNamedata.add(BatsmanName(wicketCount,BattingTeam));
                EachBall.add("W");
            } else {
                InningsScore += StoreBalls;
                EachBall.add(String.valueOf(StoreBalls));
            }
        }


        match.addTeamResult(BattingTeam.getTeamname(), InningsScore, wicketCount, EachBall,batsmanNamedata);
        match.addTeamResult(BattingTeam.getTeamname(), InningsScore, wicketCount, EachBall);
    }

    private int RandomBallGenerator() {

        return (int) (Math.random() * 8);
    }

    private String BatsmanName(int wicketCount, Team battingTeam)
    {

        String teamname = battingTeam.getTeamname();
        System.out.println(teamname);
        List<String> batsmanName = new ArrayList<>();

        if(teamname.equalsIgnoreCase("india")) {
            switch (wicketCount) {
                case 1:
                    batsmanName.add("Shubman Gill");

                    break;
                case 2:
                    batsmanName.add("Rohit Sharma");

                    break;
                case 3:
                    batsmanName.add("Virat Kohli");

                    break;
                case 4:
                    batsmanName.add("shreyas Iyer");

                    break;
                case 5:
                    batsmanName.add("KL Rahul");

                    break;
                case 6:
                    batsmanName.add("Suryakumar Yadav");

                    break;
                case 7:
                    batsmanName.add("Jadeja");

                    break;
                case 8:
                    batsmanName.add("Kuldeep Yadav");

                    break;
                case 9:
                    batsmanName.add("Mohammad Shami");

                    break;
                case 10:
                    batsmanName.add("Jasprit Bumrah");

                    break;
                case 11:
                    batsmanName.add("Mohammad Siraj");

                    break;
            }

            return batsmanName.toString();
        }
        else if(teamname.equalsIgnoreCase("nz")) {
            switch (wicketCount) {

                case 1:
                    batsmanName.add("Devon Conway");

                    break;
                case 2:
                    batsmanName.add("Rachin Ravindra");

                    break;
                case 3:
                    batsmanName.add("young");

                    break;
                case 4:
                    batsmanName.add("Kane Wiliamson");

                    break;
                case 5:
                    batsmanName.add("Dary Mitchell");

                    break;
                case 6:
                    batsmanName.add("Glenn Philips");

                    break;
                case 7:
                    batsmanName.add("Tom Latham");

                    break;
                case 8:
                    batsmanName.add("Mitchell Santner");

                    break;
                case 9:
                    batsmanName.add("Tim Southee");

                    break;
                case 10:
                    batsmanName.add("Trent Boult");

                    break;
                case 11:
                    batsmanName.add("Matt Henry");

                    break;
            }

            return batsmanName.toString();
        }
        return batsmanName.toString();

    }

    private int IndividualTeamScore(Match match, String teamname) {
        int totalScore = 0 ;
        for(TeamResults teamResults : match.getTeamResults()){
            if(teamname.equals(teamResults.getBattingTeam())){
                totalScore += teamResults.getTotalScore();
            }
        }
        return  totalScore;
    }
}
