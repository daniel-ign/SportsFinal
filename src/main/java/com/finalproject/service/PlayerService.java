package com.finalproject.service;

import com.finalproject.entity.Player;

import java.util.List;

public interface PlayerService {
    void removeFromList(int id);
    void addToList(Player player);
    List<Player> getPlayerList();
    void updateData(Player updatedPlayer, int id);
}
