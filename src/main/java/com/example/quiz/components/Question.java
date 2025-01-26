package com.example.quiz.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Question object that has the question itself and the options in the arraylist.
 */
public class Question {

  private final String question;
  private final List<Option> options = new ArrayList<>();

  public Question(String question) {
    this.question = question;
  }

  public String getQuestionContent() {
    return question;
  }

  public List<Option> getOptions() {
    Collections.shuffle(options);
    return options;
  }

  public void addOption(Option o) {
    options.add(o);
  }
}