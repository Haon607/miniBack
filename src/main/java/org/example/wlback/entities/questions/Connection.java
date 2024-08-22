package org.example.wlback.entities.questions;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "connectionIdSequence")
    Long id;
    String explanation;
    Byte groupNumber;
}
