package com.example.quiz.models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Set of Questions.
 * Here the questions and the options are filled.
 */

public class QuestionSet {

  private ArrayDeque<Question> questionQueue = new ArrayDeque<>();
  private int size = 0;

  public QuestionSet() {
    addQuestions();
    this.size = questionQueue.size();
  }

  private void addQuestions() {
    Question q1 = new Question("Was machen Sachen?");
    q1.addOption(new Option("Sachen machen", true));
    q1.addOption(new Option("Gar Nichts", false));
    q1.addOption(new Option("Sachen lachen", false));
    q1.addOption(new Option("Pupsen", false));
    questionQueue.addFirst(q1);
    Question q2 = new Question("Was passiert mit Wasser bei 100 Grad?");
    q2.addOption(new Option("Verdursten", false));
    q2.addOption(new Option("Verhungern", false));
    q2.addOption(new Option("Verdampfen", true));
    q2.addOption(new Option("Pupsen", false));
    questionQueue.addFirst(q2);
    Question q3 = new Question("Wann war der erste Weltkrieg?");
    q3.addOption(new Option("1912 bis 1916", false));
    q3.addOption(new Option("1814 bis 1818", false));
    q3.addOption(new Option("1955 bis 1975", false));
    q3.addOption(new Option("1914 bis 1918", true));
    questionQueue.addFirst(q3);
    Question q4 = new Question("Wann gefriert Wasser?");
    q4.addOption(new Option("Bei 0 Grad", true));
    q4.addOption(new Option("Bei 100 Grad", false));
    q4.addOption(new Option("Bei -22 Grad", false));
    q4.addOption(new Option("Gar Nicht", false));
    questionQueue.addFirst(q4);
    Question q5 = new Question("Was ist die Hauptstadt von Deutschland?");
    q5.addOption(new Option("Düsseldorf", false));
    q5.addOption(new Option("Berlin", true));
    q5.addOption(new Option("Bonn", false));
    q5.addOption(new Option("Hamburg", false));
    questionQueue.addFirst(q5);
    Question q6 = new Question("In welcher Staatsform leben wir?");
    q6.addOption(new Option("Diktatur", false));
    q6.addOption(new Option("Monarchie", false));
    q6.addOption(new Option("Demokratie", true));
    q6.addOption(new Option("Autokratie", false));
    questionQueue.addFirst(q6);
    Question q7 = new Question("Mit was bezahlt man in England?");
    q7.addOption(new Option("Mit Euro", false));
    q7.addOption(new Option("Mit Pfund", true));
    q7.addOption(new Option("Mit Dollar", false));
    q7.addOption(new Option("Mit Kokosnüsse", false));
    questionQueue.addFirst(q7);
    scuffleEveryThing(q1, q2, q3, q4, q5, q6, q7);
  }

  public Question getNextQuestion() {
    return questionQueue.pollFirst();
  }

  public boolean isEmpty() {
    return questionQueue.isEmpty();
  }

  private void scuffleEveryThing(Question ... questions) {
    for (Question question : questions) {
      Collections.shuffle(question.getOptions());
    }
    List<Question> tempQuestionQueue = new ArrayList<>(questionQueue);
    Collections.shuffle(tempQuestionQueue);
    this.questionQueue = new ArrayDeque<>(tempQuestionQueue);
  }

  public int getSize() {
    return size;
  }
}