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
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roundIdSequence")
    Long id;
    String name;
    String rules;
    String route;
    Boolean large;
    Integer minPlayerCount;
    Integer maxPlayerCount;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Question> questions;
    String data;
}
