package com.cricket.service;

import com.cricket.entity.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {

   public Match StartMatch(String teamAId, String teamBId, int over, int playerSize);

   public List<Match> getAllMatch();
}
