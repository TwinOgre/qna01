package com.sbb.qna.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String detail(@PathVariable("id") Integer id, Model model){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
    @GetMapping("/create")
    public String create(){

        return "question_form";
    }
    @PostMapping("/create")
    public String create(@RequestParam("subject")String subject, @RequestParam("content") String content){
//        Question question = this.questionService.getQuestion(id);
        this.questionService.create(subject, content);
        return "redirect:/question/list";
    }
}
