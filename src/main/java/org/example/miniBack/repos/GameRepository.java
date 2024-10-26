package org.example.miniBack.repos;

import org.example.miniBack.entities.Game;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends ListCrudRepository<Game, Long> {
}