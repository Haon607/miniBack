package org.example.wlback.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameIdSequence")
    Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Player> players;
    String data;
}
