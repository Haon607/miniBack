package org.example.wlback.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameIdSequence")
    Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Player> players;
    String data;
}
//TODO next step -> controller with add player to game, following problem: how does the game know a new player has been added