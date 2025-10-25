package com.example.quiz.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Question object that has the question itself and the options in the arraylist.
 */
public class Question {

  private final String question;
  private final List<Option> options;

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
  public void addOption(Option option) {
    options.add(option);
  }

  /**
   * Shuffles the options, so that they are not shown in the order of the .csv file.
   */
  public void scuffleOptions() {
    Collections.shuffle(options);
  }

  public List<Option> getOptions() {
    return options;
  }
}