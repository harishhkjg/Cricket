package com.cricket.serviceimpl;

import com.cricket.entity.Team;
import com.cricket.repo.TeamRepository;
import com.cricket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepo;
    public Team insertTeam(Team team) {

        Team existingTeam = teamRepo.findByTeamname(team.getTeamname());

        return teamRepo.save(team);
    }

    public List<Team> getteam(Team team) {
        return teamRepo.findAll();
    }
}
