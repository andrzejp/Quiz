package com.example.quiz.adapter.out.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

  @OneToOne(cascade = CascadeType.ALL)
  private MultipleChoiceDto multipleChoiceDto;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String text;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Answer> answers = new ArrayList<>();

  public List<Answer> getAnswers() {
    return answers;
  }

  public void addAnswer(Answer answer) {
    answer.setQuestion(this);
    answers.add(answer);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public MultipleChoiceDto getMultipleChoiceDto() {
    return multipleChoiceDto;
  }

  public void setMultipleChoiceDto(
      MultipleChoiceDto multipleChoiceDto) {
    this.multipleChoiceDto = multipleChoiceDto;
  }
}
