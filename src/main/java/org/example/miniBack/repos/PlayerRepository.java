package org.example.miniBack.repos;

import org.example.miniBack.entities.Player;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends ListCrudRepository<Player, Long> {
}