package com.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
@NamedQuery(name = "Player.delete",query = "DELETE FROM Player p WHERE p.id=:id")
@NamedQuery(name = "Player.getById",query = "SELECT p FROM Player p WHERE p.id=:id")
@NamedQuery(name = "Player.getByTeam", query = "SELECT p FROM Player p WHERE p.team=:team")
public class Player implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private Date signedUpDate;
    private String dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "team_id_fk")
    private Team team;

    @PrePersist
    void createdAt() {
        this.signedUpDate = new Date();
    }

    public Player(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}
