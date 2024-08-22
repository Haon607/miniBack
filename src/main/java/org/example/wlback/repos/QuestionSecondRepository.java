package org.example.wlback.repos;

import org.example.wlback.entities.questions.QuestionSecond;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSecondRepository extends ListCrudRepository<QuestionSecond, Long> {
}
