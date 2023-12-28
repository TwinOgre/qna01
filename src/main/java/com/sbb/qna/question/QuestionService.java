package com.sbb.qna.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> list(){
        List<Question> questionList = this.questionRepository.findAll();
        return questionList;
    }

    public Question getQuestion(Integer id) {
        Optional<Question> oq = this.questionRepository.findById(id);

        if(oq.isEmpty()){
            throw new RuntimeException();
        }

         return oq.get();
    }

    public void create(String subject, String content) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());

        this.questionRepository.save(question);
    }
}
