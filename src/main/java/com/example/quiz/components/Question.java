package com.example.quiz.components;

import java.util.ArrayList;
import java.util.List;

/**
 * A Question object that has the question itself and the options in the arraylist.
 */
public class Question {

  private final String question;
  private final List<String> options;
  private int correctOptionIndex;

  /**
   * Constructor for Question.
   */
  public Question(String question) {
    options = new ArrayList<>();
    this.question = question;
  }

  public String getQuestionContent() {
    return question;
  }

  /**
   * Adds an option to the question.
   */
  public void addOptions(List<String> option) {
    options.addAll(option);
  }

  public List<String> getOptions() {
    return options;
  }

  public int getCorrectOptionIndex() {
    return correctOptionIndex;
  }

  /**
   * Sets the index of the correct option.
   */
  public void setCorrectOptionIndex(int correctOptionIndex) {
    if (correctOptionIndex < 0 || correctOptionIndex >= options.size()) {
      throw new IndexOutOfBoundsException("Invalid index for correct option.");
    }
    this.correctOptionIndex = correctOptionIndex;
  }
}