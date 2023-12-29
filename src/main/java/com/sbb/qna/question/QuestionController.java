package com.sbb.qna.question;

import com.sbb.qna.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model){

        List<Question> questionList = this.questionService.list();
        model.addAttribute("questionList", questionList);

        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
    @GetMapping("/create")
    public String create(QuestionForm questionForm){
        return "question_form";
    }
    @PostMapping("/create")
    public String create(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
}
