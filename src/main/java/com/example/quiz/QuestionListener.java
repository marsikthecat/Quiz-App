package com.example.quiz;

import com.example.quiz.components.Question;
import com.example.quiz.components.QuestionSet;
import com.example.quiz.ui.ResultWindow;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

/**
 * QuestionListener class that listens to question changes and manages the game state.
 * It updates the UI properties based on the current question and handles answer checking.
 */

public class QuestionListener {

  private final QuestionSet questionSet;
  private final ObjectProperty<Question> currentQuestion = new SimpleObjectProperty<>();
  private final StringProperty questionTextProperty = new SimpleStringProperty();
  private final StringProperty option1Property = new SimpleStringProperty();
  private final StringProperty option2Property = new SimpleStringProperty();
  private final StringProperty option3Property = new SimpleStringProperty();
  private final StringProperty option4Property = new SimpleStringProperty();
  private final ObjectProperty<String> backgroundColorProperty =
          new SimpleObjectProperty<>("-fx-background-color: white");
  private final BooleanProperty gameFinished = new SimpleBooleanProperty(false);

  /**
   * Constructor that initializes the QuestionListener with a QuestionSet.
   * It sets the initial question and updates the properties accordingly.
   */
  public QuestionListener(QuestionSet questionSet) {
    this.questionSet = questionSet;
    setQuestionAndUpdateProperties();
  }

  public StringProperty getQuestionTextProperty() {
    return questionTextProperty;
  }

  public StringProperty getOption1Property() {
    return option1Property;
  }

  public StringProperty getOption2Property() {
    return option2Property;
  }

  public StringProperty getOption3Property() {
    return option3Property;
  }

  public StringProperty getOption4Property() {
    return option4Property;
  }

  public ObjectProperty<String> getBackgroundColorProperty() {
    return backgroundColorProperty;
  }

  /**
   * .
   */
  public BooleanProperty gameFinishedProperty() {
    return gameFinished;
  }

  /**
   * Sets the current question from the QuestionSet and updates the UI properties.
   * This method is called to refresh or initialize the question and options displayed in the UI.
   */
  public void setQuestionAndUpdateProperties() {
    currentQuestion.set(questionSet.readNextQuestion());
    questionTextProperty.set(currentQuestion.get().getQuestionContent());
    option1Property.set(currentQuestion.get().getOptions().get(0));
    option2Property.set(currentQuestion.get().getOptions().get(1));
    option3Property.set(currentQuestion.get().getOptions().get(2));
    option4Property.set(currentQuestion.get().getOptions().get(3));
  }

  /**
     * Checks the answer selected by the user.
     * If the answer is correct, it updates the background color and increments the correct count.
     * If the answer is incorrect, it changes the background color to indicate failure.
     * After a short pause, it either shows the result window or sets the next question.
     */
  public void checkAnswer(int i) {
    if (i == currentQuestion.get().getCorrectOptionIndex()) {
      backgroundColorProperty.set("-fx-background-color: #bcffbc;");
      questionSet.incrementCorrect();
    } else {
      backgroundColorProperty.set("-fx-background-color: #ffb0b0;");
    }
    PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
    pause.setOnFinished(e -> {
      if (questionSet.isComplete()) {
        gameFinished.set(true);
        Platform.runLater(() -> {
          ResultWindow resultWindow = new ResultWindow(questionSet.getCorrect(),
                  questionSet.size());
          resultWindow.showAndWait();
          if (resultWindow.isRetry()) {
            gameFinished.set(false);
            questionSet.scuffleAndStart();
            setQuestionAndUpdateProperties();
          } else {
            System.exit(0);
          }
        });
      } else {
        setQuestionAndUpdateProperties();
      }
      backgroundColorProperty.set("-fx-background-color: white");
    });
    pause.play();
  }
}