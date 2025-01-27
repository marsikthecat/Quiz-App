package com.example.quiz.viewmodel;

import com.example.quiz.models.Option;
import com.example.quiz.models.Question;
import com.example.quiz.models.QuestionSet;
import com.example.quiz.utils.ResultWindow;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * viewModel.
 */
public class Viewmodel {

  private final List<StringProperty> stringPropertyButtons = new ArrayList<>();
  private final ObjectProperty<Question> questionProperty = new SimpleObjectProperty<>();
  private final StringProperty questionLabelProperty = new SimpleStringProperty();
  private final StringProperty backgroundColorProperty = new SimpleStringProperty();
  private final BooleanProperty terminated = new SimpleBooleanProperty();
  private final QuestionSet questionSet;
  private int correct = 0;

  /**
   * initializes all properties and fills the list with stringProperties.
   */

  public Viewmodel(QuestionSet questionSet) {
    this.questionSet = questionSet;
    for (int i = 0; i < 4; i++) {
      stringPropertyButtons.add(new SimpleStringProperty());
    }
    questionLabelProperty.set("");
    backgroundColorProperty.set("-fx-background-color: white");
    terminated.set(false);
    showQuestion();
  }

  /**
   * inserts the content of the new question to the label.
   * Puts all the option strings to the buttons.
   */

  public void showQuestion() {
    Question nextQuestion = questionSet.getNextQuestion();
    questionProperty.set(nextQuestion);
    questionLabelProperty.set(nextQuestion.getQuestionContent());
    List<Option> options = nextQuestion.getOptions();
    for (int i = 0; i < options.size(); i++) {
      stringPropertyButtons.get(i).set(
              options.get(i).getOptionContent());
    }
  }

  /**
   * checks if the index of the clicked button is the index of the right option.
   * continues the game if the QuestionSet is not empty.
   */

  public void handleAnswer(Button clickedBtn) {
    int clickedIndex = Integer.parseInt(clickedBtn.getId());
    List<Option> options = questionProperty.get().getOptions();
    if (options.get(clickedIndex).isCorrect()) {
      backgroundColorProperty.set("-fx-background-color: #bcffbc;");
      correct++;
    } else {
      backgroundColorProperty.set("-fx-background-color: #ffb0b0;");
    }
    PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
    pause.setOnFinished(e -> {
      if (questionSet.isEmpty()) {
        endGame();
      } else {
        backgroundColorProperty.set("-fx-background-color: white");
        showQuestion();
      }
    });
    pause.play();
  }

  private void endGame() {
    Platform.runLater(() -> {
      ResultWindow resultWindow = new ResultWindow(correct, questionSet.getSize());
      resultWindow.showAndWait();
      if (resultWindow.isRetry()) {
        terminated.set(true);
      } else {
        System.exit(0);
      }
    });
  }

  public StringProperty questionLabelPropertyProperty() {
    return questionLabelProperty;
  }

  public StringProperty stringPropertyList(int indexFromBtn) {
    return stringPropertyButtons.get(indexFromBtn);
  }

  public StringProperty backgroundColorProperty() {
    return backgroundColorProperty;
  }

  public BooleanProperty terminatedProperty() {
    return terminated;
  }
}