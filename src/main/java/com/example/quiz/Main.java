package com.example.quiz;

import com.example.quiz.components.Question;
import com.example.quiz.components.QuestionSet;
import com.example.quiz.ui.ResultWindow;
import java.util.Objects;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main Quiz Programm.
 * Main: 102 lines.
 * Option: 25 lines.
 * Question: 31 lines.
 * QuestionSet: 81 lines.
 * ResultWindow: 50 lines.
 * 289 lines of code + 30 lines CSS.
 */

public class Main extends Application {

  private QuestionSet questionSet = new QuestionSet();
  private int correct = 0;

  @Override
  public void start(Stage stage) {
    VBox content = new VBox();
    content.setAlignment(Pos.CENTER);
    showQuestion(content, questionSet.getNextQuestion());
    Scene scene = new Scene(content, 380, 240);
    scene.getStylesheets().add(
            Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
    stage.setTitle("Quiz");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private void showQuestion(VBox content, Question question) {
    content.setStyle("-fx-background-color: white");
    content.getChildren().clear();
    Label label = new Label(question.getQuestionContent());
    label.setStyle("-fx-font-size: 14pt;");
    content.getChildren().add(label);
    question.getOptions().forEach(option -> {
      Button btn = new Button(option.getOptionContent());
      btn.getStyleClass().add("custom-button");
      if (option.isCorrect()) {
        btn.setId("correct");
      }
      VBox.setMargin(btn, new Insets(5, 0, 0, 5));
      btn.setOnAction(e -> handleAnswer(content, btn));
      content.getChildren().add(btn);
    });
  }

  private void handleAnswer(VBox content, Button btn) {
    if (btn.getId() != null) {
      content.setStyle("-fx-background-color: #bcffbc;");
      correct++;
    } else {
      content.setStyle("-fx-background-color: #ffb0b0;");
    }
    PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
    pause.setOnFinished(e -> {
      if (!questionSet.isEmpty()) {
        showQuestion(content, questionSet.getNextQuestion());
      } else {
        endGame(content);
      }
    });
    pause.play();
  }

  private void endGame(VBox content) {
    Platform.runLater(() -> {
      ResultWindow resultWindow = new ResultWindow(correct, questionSet.getSize());
      content.setDisable(true);
      resultWindow.showAndWait();
      if (resultWindow.isRetry()) {
        content.setDisable(false);
        correct = 0;
        questionSet = new QuestionSet();
        showQuestion(content, questionSet.getNextQuestion());
      } else {
        System.exit(0);
      }
    });
  }
}