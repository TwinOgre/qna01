package com.sbb.qna.answer;

import com.sbb.qna.answer.Answer;
import com.sbb.qna.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
