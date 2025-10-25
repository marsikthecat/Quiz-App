package com.example.quiz.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Set of questions.
 * Here the questions and options are filled.
 */
public class QuestionSet extends ArrayList<Question> {

  private int index;
  private int correct;

  /**
   * Constructor that loads question-data from a CSV file.
   */
  public QuestionSet() {
    try {
      loadQuestions();
    } catch (IOException e) {
      System.err.println("Error loading questions: " + e.getMessage());
    }
  }

  private void loadQuestions() throws IOException {
    File questionsFile = new File("Questions.csv");
    if (!questionsFile.exists()) {
      try {
        if (questionsFile.createNewFile()) {
          BufferedWriter writer = new BufferedWriter(new FileWriter(questionsFile));
          writer.write("What is the capital of France?, Paris;true, London:false, "
                  + "Berlin:false, Madrid:false\n");
          writer.close();
        }
      } catch (IOException e) {
        e.getCause();
      }
    }
    BufferedReader reader = new BufferedReader(new FileReader("Questions.csv"));
    String line;
    while ((line = reader.readLine()) != null) {
      Question question = getQuestion(line);
      question.scuffleOptions();
      add(question);
    }
    scuffleAndStart();
    reader.close();
  }

  private static Question getQuestion(String line) {
    String[] splitLine = line.split(",");
    String questionText = splitLine[0];
    Question question = new Question(questionText);
    for (int i = 1; i < 5; i++) {
      String optionTextWithBoolean = splitLine[i];
      String[] splitOptionTextWithBoolean = optionTextWithBoolean.split(";");
      String optionText = splitOptionTextWithBoolean[0];
      boolean isCorrect = Boolean.parseBoolean(splitOptionTextWithBoolean[1]);
      question.addOption(new Option(optionText, isCorrect));
    }
    return question;
  }

  /**
   * Shuffles the questions and resets the index and correct count.
   */
  public void scuffleAndStart() {
    correct = 0;
    index = 0;
    Collections.shuffle(this);
  }

  /**
   * Returns the next question in the set.
   */
  public Question readNextQuestion() {
    Question question = get(index);
    index++;
    return question;
  }

  public boolean isComplete() {
    return index == size();
  }

  public int getCorrect() {
    return correct;
  }

  /**
   * Increments the count of correct answers.
   */
  public void incrementCorrect() {
    correct++;
  }
}