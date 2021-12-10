package com.example.quiz.adapter.out.repository.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionJpaRepository extends JpaRepository<QuestionDto, Long>
{
  Optional<QuestionDto> findByText(String userName);
}