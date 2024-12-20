package org.example.miniBack.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerIdSequence")
    Long id;
    String name;
    @Column(length = 10000)
    String input;
    String color;
    Long totalScore;
}
