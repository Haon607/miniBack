package org.example.miniBack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameIdSequence")
    Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Player> players;
    String route;
    @Column(length = 10000)
    String data;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    List<Round> rounds;
}
