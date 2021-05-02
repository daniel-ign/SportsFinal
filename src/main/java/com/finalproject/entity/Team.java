package com.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
@NamedQuery(name="Team.getById", query = "SELECT t FROM Team t WHERE t.id=:id")
@NamedQuery(name = "Team.getByName", query = "SELECT t from Team t where t.teamName = :teamName")
public class Team implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_team")
    private int id;

    @Column(unique = true)
    private String teamName;

    //Below attribute will contain the date of the next game
    private String nextGame;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Player> playerList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "league_id_fk")
    private League league;

}
