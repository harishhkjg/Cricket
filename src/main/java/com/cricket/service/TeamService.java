package com.cricket.service;

import com.cricket.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    public Team insertTeam(Team team);

    public List<Team> getteam(Team team);
}
