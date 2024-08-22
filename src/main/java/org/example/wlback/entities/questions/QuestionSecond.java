package org.example.wlback.entities.questions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSecond {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questionSecondIdSequence")
    Long id;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Answer> answers;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Connection> connections;
}
