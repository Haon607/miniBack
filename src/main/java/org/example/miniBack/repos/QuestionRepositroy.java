package org.example.miniBack.repos;

import org.example.miniBack.entities.Question;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepositroy extends ListCrudRepository<Question, Long> {
}
