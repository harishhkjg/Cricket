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
        int individualruns = 0;
        List<String> EachBall = new ArrayList<>();

        List<String> batsmanNamedata = new ArrayList<>();
        for (int ball = 1; ball <= totalBalls && wicketCount<Playersize; ball++) {
            int StoreBalls = RandomBallGenerator();
            if (StoreBalls == 7) {
                wicketCount++;
                batsmanNamedata.add(BatsmanName(wicketCount,BattingTeam,individualruns));
                EachBall.add("W");
                individualruns=0;
            } else {
                InningsScore += StoreBalls;
                individualruns += StoreBalls;
                EachBall.add(String.valueOf(StoreBalls));
            }
        }
        batsmanNamedata.add(BatsmanName(wicketCount+1,BattingTeam,individualruns));


        match.addTeamResult(BattingTeam.getTeamname(), InningsScore, wicketCount, EachBall,batsmanNamedata);
    }

    private int RandomBallGenerator() {

        return (int) (Math.random() * 8);
    }

    private String BatsmanName(int wicketCount, Team battingTeam,int individualruns)
    {

        String teamname = battingTeam.getTeamname();
        System.out.println(teamname);
        List<String> batsmanName = new ArrayList<>();
        List<Integer> batsmanScore = new ArrayList<>();
        if(teamname.equalsIgnoreCase("india")) {
            switch (wicketCount) {
                case 1:
                    batsmanName.add("Shubman Gill");
                    batsmanScore.add(individualruns);
                    break;
                case 2:
                    batsmanName.add("Rohit Sharma");
                    batsmanScore.add(individualruns);
                    break;
                case 3:
                    batsmanName.add("Virat Kohli");
                    batsmanScore.add(individualruns);
                    break;
                case 4:
                    batsmanName.add("shreyas Iyer");
                    batsmanScore.add(individualruns);
                    break;
                case 5:
                    batsmanName.add("KL Rahul");
                    batsmanScore.add(individualruns);
                    break;
                case 6:
                    batsmanName.add("Suryakumar Yadav");
                    batsmanScore.add(individualruns);
                    break;
                case 7:
                    batsmanName.add("Jadeja");
                    batsmanScore.add(individualruns);
                    break;
                case 8:
                    batsmanName.add("Kuldeep Yadav");
                    batsmanScore.add(individualruns);
                    break;
                case 9:
                    batsmanName.add("Mohammad Shami");
                    batsmanScore.add(individualruns);
                    break;
                case 10:
                    batsmanName.add("Jasprit Bumrah");
                    batsmanScore.add(individualruns);
                    break;
                case 11:
                    batsmanName.add("Mohammad Siraj");
                    batsmanScore.add(individualruns);
                    break;
            }

            return batsmanName.toString() +"score:" + batsmanScore;
        }
        else if(teamname.equalsIgnoreCase("nz")) {
            switch (wicketCount) {

                case 1:
                    batsmanName.add("Devon Conway");
                    batsmanScore.add(individualruns);
                    break;
                case 2:
                    batsmanName.add("Rachin Ravindra");
                    batsmanScore.add(individualruns);
                    break;
                case 3:
                    batsmanName.add("young");
                    batsmanScore.add(individualruns);
                    break;
                case 4:
                    batsmanName.add("Kane Wiliamson");
                    batsmanScore.add(individualruns);
                    break;
                case 5:
                    batsmanName.add("Dary Mitchell");
                    batsmanScore.add(individualruns);
                    break;
                case 6:
                    batsmanName.add("Glenn Philips");
                    batsmanScore.add(individualruns);
                    break;
                case 7:
                    batsmanName.add("Tom Latham");
                    batsmanScore.add(individualruns);
                    break;
                case 8:
                    batsmanName.add("Mitchell Santner");
                    batsmanScore.add(individualruns);
                    break;
                case 9:
                    batsmanName.add("Tim Southee");
                    batsmanScore.add(individualruns);
                    break;
                case 10:
                    batsmanName.add("Trent Boult");
                    batsmanScore.add(individualruns);
                    break;
                case 11:
                    batsmanName.add("Matt Henry");
                    batsmanScore.add(individualruns);

                    break;
            }

            return batsmanName.toString() +"score:" + batsmanScore;
        }
        return batsmanName.toString() +"score:" + batsmanScore;

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
