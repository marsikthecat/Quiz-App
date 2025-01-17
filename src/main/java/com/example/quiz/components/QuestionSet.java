package com.example.quiz.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Set of Questions.
 * Here the questions and the options are filled.
 */

public class QuestionSet {

  private final List<Question> questionList;
  private static final int size = 7;

  public QuestionSet() {
    questionList = new ArrayList<>();
    addQuestions();
  }

  private void addQuestions() {
    Question q1 = new Question("Was machen Sachen?");
    q1.addOption(new Option("Sachen machen", true));
    q1.addOption(new Option("Gar Nichts", false));
    q1.addOption(new Option("Sachen lachen", false));
    q1.addOption(new Option("Pupsen", false));
    Question q2 = new Question("Was passiert mit Wasser bei 100 Grad?");
    q2.addOption(new Option("Verdursten", false));
    q2.addOption(new Option("Verhungern", false));
    q2.addOption(new Option("Verdampfen", true));
    q2.addOption(new Option("Pupsen", false));
    Question q3 = new Question("Wann war der erste Weltkrieg?");
    q3.addOption(new Option("1912 bis 1916", false));
    q3.addOption(new Option("1814 bis 1818", false));
    q3.addOption(new Option("1955 bis 1975", false));
    q3.addOption(new Option("1914 bis 1918", true));
    Question q4 = new Question("Wann gefriert Wasser?");
    q4.addOption(new Option("Bei 0 Grad", true));
    q4.addOption(new Option("Bei 100 Grad", false));
    q4.addOption(new Option("Bei -22 Grad", false));
    q4.addOption(new Option("Gar Nicht", false));
    Question q5 = new Question("Was ist die Hauptstadt von Deutschland?");
    q5.addOption(new Option("Düsseldorf", false));
    q5.addOption(new Option("Berlin", true));
    q5.addOption(new Option("Bonn", false));
    q5.addOption(new Option("Hamburg", false));
    Question q6 = new Question("In welcher Staatsform leben wir?");
    q6.addOption(new Option("Diktatur", false));
    q6.addOption(new Option("Monarchie", false));
    q6.addOption(new Option("Demokratie", true));
    q6.addOption(new Option("Autokratie", false));
    Question q7 = new Question("Mit was bezahlt man in England?");
    q7.addOption(new Option("Mit Euro", false));
    q7.addOption(new Option("Mit Pfund", true));
    q7.addOption(new Option("Mit Dollar", false));
    q7.addOption(new Option("Mit Kokosnüsse", false));
    questionList.addAll(List.of(q1, q2, q3, q4, q5, q6, q7));
  }

  public Iterator<Question> getQuestion() {
    return questionList.iterator();
  }

  public static int getSize() {
    return size;
  }
}