package com.finalproject.service;

import com.finalproject.entity.League;
import com.finalproject.entity.Team;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LeagueServiceImpl implements LeagueService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addLeague(League league) {
        em.persist(league);
    }

    @Override
    public List<League> getAllLeagues() {
        return em.createNamedQuery("League.findAll", League.class)
                .getResultList();
    }

    @Override
    public void addTeamToLeague(int leagueId, Team team) {
        League league = em.find(League.class, leagueId);
        if(league!=null){
            Team thisTeam = em.createNamedQuery("Team.getById", Team.class)
                    .setParameter("id", team.getId())
                    .getSingleResult();
            if(thisTeam!=null){
                thisTeam.setLeague(league);
            }
        }
    }

}
