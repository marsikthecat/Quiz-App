package com.example.quiz.components;

import java.util.ArrayDeque;

/**
 * Set of Questions.
 * Here the questions and the options are filled.
 */

public class QuestionSet {

  private final ArrayDeque<Question> questionQueue;
  private static final int size = 7;

  public QuestionSet() {
    questionQueue = new ArrayDeque<>();
    addQuestions();
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
  }
  public Question getNextQuestion() {
    return questionQueue.pollFirst();
  }

  public boolean isEmpty() {
    return questionQueue.isEmpty();
  }

  public static int getSize() {
    return size;
  }
}