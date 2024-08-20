package org.example.wlback.repos;

import org.example.wlback.entities.questions.QuestionFirst;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionFirstRepository extends ListCrudRepository<QuestionFirst, Long> {
}
