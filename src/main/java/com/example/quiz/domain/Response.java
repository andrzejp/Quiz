package com.example.quiz.domain;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Response {
  private final ResponseStatus status;
  private final Question question;

  public Response(String responseText, Question question) {
    this.question = question;
    this.status = statusFor(responseText, question);
  }

  public Question question() {
    return question;
  }

  public ResponseStatus status() {
    return this.status;
  }

  private ResponseStatus statusFor(String responseText, Question question) {
    if (question.isCorrectAnswer(responseText)) {
      return ResponseStatus.CORRECT;
    } else {
      return ResponseStatus.INCORRECT;
    }
  }

  @Override
  public boolean equals(Object other) {
    return reflectionEquals(this, other);
  }

  @Override
  public int hashCode() {
    return reflectionHashCode(this);
  }
}
