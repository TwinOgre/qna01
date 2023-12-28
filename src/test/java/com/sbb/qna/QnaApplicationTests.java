package com.sbb.qna;

import com.sbb.qna.answer.Answer;
import com.sbb.qna.answer.AnswerRepository;
import com.sbb.qna.question.Question;
import com.sbb.qna.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QnaApplicationTests {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerRepository;


	@Test
	void testJpa() {
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}
	@Test
	void test01(){
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?2");
		q1.setContent("sbb에 대해서 알고 싶습니다.2");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
	}

	@Test
	void test02(){
		List<Question> questionList = this.questionRepository.findAll();
		assertEquals(2, questionList.size());
	}

	@Test
	void test03(){
		Optional<Question> optionalQuestion = this.questionRepository.findById(1);
		if(optionalQuestion.isPresent()){
			Question question = optionalQuestion.get();
			assertEquals(1, question.getId());
		}

	}

	@Test
	void test04(){
		Question question = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		if(question != null){
			assertEquals("sbb가 무엇인가요?", question.getSubject());
		}
	}
	@Test
	void test06(){
		List<Question> questionList = this.questionRepository.findBySubjectLike("sbb%");
		assertEquals(1, questionList.size());
	}

	@Test
	void test07(){
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}

	@Test
	void test08(){
		assertEquals(1, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(7);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(0, this.questionRepository.count());
	}

	@Test
	void test09(){
		Answer answer = new Answer();
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		answer.setContent("answerContent");
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(q);
		this.answerRepository.save(answer);
	}
	@Test
	void test10(){
		Optional<Answer> optionalAnswer = this.answerRepository.findById(1);
		if(optionalAnswer.isPresent()){
			Answer answer = optionalAnswer.get();
			assertEquals(1, answer.getId());
		}
	}
	@Test
	void test11(){
		Optional<Answer> oa = this.answerRepository.findById(2);
		assertTrue(oa.isPresent());
		Answer answer = oa.get();
		answer.setContent("수정된 내용2");
		this.answerRepository.save(answer);
	}
	@Test
	void test12(){
		assertEquals(2, this.answerRepository.count());
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer answer = oa.get();
		this.answerRepository.delete(answer);
		assertEquals(1, this.answerRepository.count());
	}
}
