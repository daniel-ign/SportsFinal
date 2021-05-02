package com.finalproject.service;

import com.finalproject.entity.Player;
import com.finalproject.entity.Team;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PlayerServiceImpl implements PlayerService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void removeFromList(int id) {
        Player deletedPlayer = em.createNamedQuery("Player.getById",Player.class)
                .setParameter("id",id)
                .getSingleResult();
        em.remove(deletedPlayer);
    }

    @Override
    public void addToList(Player player) {
        em.persist(player);
    }

    @Override
    public List<Player> getPlayerList() {
        List<Player> playerList = em.createNamedQuery("Player.findAll",Player.class)
                .getResultList();
        return playerList;
    }

    @Override
    public void updateData(Player updatedPlayer, int id) {
        Player player = em.createNamedQuery("Player.getById",Player.class)
                .setParameter("id",id)
                .getSingleResult();

        em.merge(player);
        player.setFirstName(updatedPlayer.getFirstName());
        player.setLastName(updatedPlayer.getLastName());
        player.setDateOfBirth(updatedPlayer.getDateOfBirth());
        player.setTeam(updatedPlayer.getTeam());
        em.flush();
    }
}
