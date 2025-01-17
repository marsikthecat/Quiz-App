package com.example.quiz.components;

/**
 * An Option is a possible Answer of a question, it has its content and
 * the boolean that says if the options is correct or not to the corresponding question.
 * <p> </p>
 */

public class Option {

  private final String content;
  private final boolean correct;

  public Option(String content, boolean correct) {
    this.content = content;
    this.correct = correct;
  }

  public String getOptionContent() {
    return content;
  }

  public boolean isCorrect() {
    return correct;
  }
}