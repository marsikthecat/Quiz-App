package com.example.quiz;

import com.example.quiz.components.Question;
import com.example.quiz.components.QuestionSet;
import com.example.quiz.ui.ResultWindow;
import java.util.Iterator;
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
 * Main: 173 lines.
 * Option: 26 lines.
 * Question: 30 lines.
 * QuestionSet: 68 lines.
 * ResultWindow: 50 lines.
 * 347 lines of code.
 * <p> </p>
 */

public class Main extends Application {

  private Iterator<Question> questionIterator;
  private int correct = 0;

  @Override
  public void start(Stage stage) {
    VBox content = new VBox();
    content.setAlignment(Pos.CENTER);
    questionIterator = new QuestionSet().getQuestion();
    showQuestion(content, questionIterator.next());
    Scene scene = new Scene(content, 380, 240);
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
      btn.setId(String.valueOf(option.isCorrect()));
      styleButton(btn);
      VBox.setMargin(btn, new Insets(5, 0, 0, 5));
      btn.setOnAction(e -> handleAnswer(content, btn));
      content.getChildren().add(btn);
    });
  }

  private void handleAnswer(VBox content, Button btn) {
    content.setStyle(decideBackground(btn));
    PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
    pause.setOnFinished(e -> {
      if (questionIterator.hasNext()) {
        showQuestion(content, questionIterator.next());
      } else {
        endGame(content);
      }
    });
    pause.play();
  }

  private String decideBackground(Button btn) {
    if (Boolean.parseBoolean(btn.getId())) {
      correct++;
      return "-fx-background-color: #bcffbc;";
    } else {
      return "-fx-background-color: #ffb0b0;";
    }
  }

  private void endGame(VBox content) {
    Platform.runLater(() -> {
      ResultWindow resultWindow = new ResultWindow(correct, QuestionSet.getSize());
      content.setDisable(true);
      resultWindow.showAndWait();
      if (resultWindow.isRetry()) {
        content.setDisable(false);
        correct = 0;
        questionIterator = new QuestionSet().getQuestion();
        showQuestion(content, questionIterator.next());
      }
    });
  }

  void styleButton(Button btn) {
    btn.setStyle(
            "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), "
                    + "linear-gradient(#fcfcfc 0%, #e6e6e6 20%, #d6d6d6 100%), "
                    + "linear-gradient(#dddddd 0%, #f6f6f6 50%); "
                    + "-fx-background-insets: 0, 1, 2; "
                    + "-fx-background-radius: 8, 7, 6; "
                    + "-fx-padding: 10 20 10 20; "
                    + "-fx-font-size: 13px; "
                    + "-fx-font-family: 'Arial'; "
                    + "-fx-text-fill: #333333; "
                    + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 5, 0.5, 0, 1); "
                    + "-fx-border-color: #b5b5b5; "
                    + "-fx-border-radius: 8, 7, 6; "
                    + "-fx-border-width: 1px; "
                    + "-fx-cursor: hand; "
                    + "-fx-pref-width: 150px;"
    );
    btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: linear-gradient(#e6e6e6, #c2c2c2), "
                    + "linear-gradient(#f2f2f2 0%, #cccccc 20%, #bfbfbf 100%), "
                    + "linear-gradient(#cccccc 0%, #e0e0e0 50%); "
                    + "-fx-background-insets: 0, 1, 2; "
                    + "-fx-background-radius: 8, 7, 6; "
                    + "-fx-padding: 10 20 10 20; "
                    + "-fx-font-size: 13px; "
                    + "-fx-font-family: 'Arial'; "
                    + "-fx-text-fill: #000000; "
                    + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 5, 0.5, 0, 1); "
                    + "-fx-border-color: #b5b5b5; "
                    + "-fx-border-radius: 8, 7, 6; "
                    + "-fx-border-width: 1px; "
                    + "-fx-cursor: hand; "
                    + "-fx-pref-width: 150px;"
    ));
    btn.setOnMousePressed(e -> btn.setStyle(
            "-fx-background-color: linear-gradient(#d6d6d6, #a8a8a8), "
                    + "linear-gradient(#e6e6e6 0%, #b8b8b8 20%, #a0a0a0 100%), "
                    + "linear-gradient(#b8b8b8 0%, #d0d0d0 50%); "
                    + "-fx-background-insets: 0, 1, 2; "
                    + "-fx-background-radius: 8, 7, 6; "
                    + "-fx-padding: 10 20 10 20; "
                    + "-fx-font-size: 13px; "
                    + "-fx-font-family: 'Arial'; "
                    + "-fx-text-fill: #000000; "
                    + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 5, 0.5, 0, 1); "
                    + "-fx-border-color: #b5b5b5; "
                    + "-fx-border-radius: 8, 7, 6; "
                    + "-fx-border-width: 1px; "
                    + "-fx-cursor: hand; "
                    + "-fx-pref-width: 150px;"
    ));
    btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), "
                    + "linear-gradient(#fcfcfc 0%, #e6e6e6 20%, #d6d6d6 100%), "
                    + "linear-gradient(#dddddd 0%, #f6f6f6 50%); "
                    + "-fx-background-insets: 0, 1, 2; "
                    + "-fx-background-radius: 8, 7, 6; "
                    + "-fx-padding: 10 20 10 20; "
                    + "-fx-font-size: 13px; "
                    + "-fx-font-family: 'Arial'; "
                    + "-fx-text-fill: #333333; "
                    + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 5, 0.5, 0, 1); "
                    + "-fx-border-color: #b5b5b5; "
                    + "-fx-border-radius: 8, 7, 6; "
                    + "-fx-border-width: 1px; "
                    + "-fx-cursor: hand; "
                    + "-fx-pref-width: 150px;"
    ));
  }
}