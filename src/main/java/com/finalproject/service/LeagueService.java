package com.finalproject.service;

import com.finalproject.entity.League;
import com.finalproject.entity.Team;

import java.util.List;

public interface LeagueService {
    void addLeague(League league);
    List<League> getAllLeagues();
    void addTeamToLeague(int leagueId, Team team);
}
