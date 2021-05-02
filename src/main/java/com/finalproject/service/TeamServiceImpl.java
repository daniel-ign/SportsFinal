package com.finalproject.service;

import com.finalproject.entity.Player;
import com.finalproject.entity.Team;
import com.finalproject.entity.TeamUpdateDto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TeamServiceImpl implements TeamService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addTeamToList(Team team) {
        em.persist(team);
    }

    @Override
    public void addPlayerToTeam(int teamId, Player player) {
        Team team = em.createNamedQuery("Team.getById", Team.class)
                .setParameter("id", teamId).getSingleResult();

        if(team!=null){
            Player thisPlayer = em.find(Player.class, player.getId());
            if(thisPlayer!=null){
                thisPlayer.setTeam(team);
            }
        }
    }

    @Override
    public Team updateTeamName(TeamUpdateDto teamUpdateDto, Team teamToUpdate) {
        if(teamUpdateDto.getTeamName() != null){
            teamToUpdate.setTeamName(teamUpdateDto.getTeamName());
        }
        if(teamUpdateDto.getNextGame() != null){
            teamToUpdate.setNextGame(teamUpdateDto.getNextGame());
        }
        em.merge(teamToUpdate);
        return teamToUpdate;
    }

    @Override
    public List<Team> getAllTeams() {
        return em.createNamedQuery("Team.findAll", Team.class)
                .getResultList();
    }

    @Override
    public void removeTeamFromList(int id) {
        Team thisTeam = em.createNamedQuery("Team.getById", Team.class)
                .setParameter("id", id)
                .getSingleResult();
        em.remove(thisTeam);
    }

    @Override
    public Team getById(int id) {
        return em.find(Team.class, id);
    }
}
