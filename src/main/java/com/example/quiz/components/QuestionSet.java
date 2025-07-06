package com.example.quiz.components;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Set of Questions.
 * Here the questions and options are filled.
 */

public class QuestionSet extends ArrayList<Question> {

  private int index;
  private int correct;

  /**
     * Constructor that loads questions from a CSV file.
     * The file should be in the format: question;option1;option2;option3;option4;correctIndex
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
          writer.write("What is the capital of France?,Paris,London,Berlin,Madrid,0\n");
          writer.close();
        }
      } catch (IOException e) {
        e.getCause();
      }
    }
    BufferedReader reader = new BufferedReader(new FileReader("Questions.csv"));
    String line;
    while ((line = reader.readLine()) != null) {
      String[] splitLine = line.split(",");
      String questionText = splitLine[0];
      String option1 = splitLine[1];
      String option2 = splitLine[2];
      String option3 = splitLine[3];
      String option4 = splitLine[4];
      int correctIndex = Integer.parseInt(splitLine[5]);
      Question question = new Question(questionText);
      question.addOptions(List.of(option1, option2, option3, option4));
      question.setCorrectOptionIndex(correctIndex);
      add(question);
    }
    scuffleAndStart();
    reader.close();
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