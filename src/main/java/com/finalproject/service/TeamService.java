package com.finalproject.service;

import com.finalproject.entity.Player;
import com.finalproject.entity.Team;
import com.finalproject.entity.TeamUpdateDto;

import java.util.List;

public interface TeamService {
    void addTeamToList(Team team);
    void addPlayerToTeam(int teamId, Player player);
    Team updateTeamName(TeamUpdateDto teamUpdateDto, Team teamToUpdate);
    List<Team> getAllTeams();
    void removeTeamFromList(int id);
    Team getById(int id);
}
