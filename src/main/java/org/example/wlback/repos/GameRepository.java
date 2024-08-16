package org.example.wlback.repos;

import org.example.wlback.entities.Game;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends ListCrudRepository<Game, Long> {
}