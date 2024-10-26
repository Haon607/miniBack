package org.example.miniBack.repos;

import org.example.miniBack.entities.Round;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends ListCrudRepository<Round, Long> {
}
