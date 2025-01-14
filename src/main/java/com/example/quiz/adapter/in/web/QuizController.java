package com.example.quiz.adapter.in.web;

import com.example.quiz.domain.Grade;
import com.example.quiz.domain.Question;
import com.example.quiz.domain.quiz.Quiz;
import com.example.quiz.domain.quiz.QuizSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuizController {

  private QuizSession quizSession;
  private Question question;

  public QuizController(QuizSession quizSession) {
    this.quizSession = quizSession;
  }

  @Autowired
  public QuizController(Quiz quiz) {
    this.quizSession = quiz.start();
  }

  @GetMapping("/quiz")
  public String askQuestion(Model model) {
    if (quizSession.isFinished()) {
      return "redirect:/result";
    }
    question = quizSession.question();
    final AskQuestionForm askQuestionForm = AskQuestionForm.from(question);
    model.addAttribute("askQuestionForm", askQuestionForm);
    return "quiz";
  }

  @PostMapping("/quiz")
  public String questionResponse(AskQuestionForm askQuestionForm) {
    quizSession.respondWith(askQuestionForm.getSelectedChoice(), question);
    if (quizSession.isFinished()) {
      return "redirect:/result";
    }
    return "redirect:/quiz";
  }

  @GetMapping("/result")
  public String showResult(Model model) {
    final Grade grade = quizSession.grade();
    model.addAttribute("resultView", ResultView.from(grade));
    return "result";
  }

  @PostMapping("/restart")
  public String restart() {
    quizSession.restart();
    return "redirect:/quiz";
  }
}
