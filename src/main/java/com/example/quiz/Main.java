package com.example.quiz;

import com.example.quiz.components.QuestionSet;
import com.example.quiz.ui.ResultWindow;
import java.util.Objects;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main Quiz Programm.
 * Option: 6 lines.
 * Question: 44 lines.
 * QuestionSet: 102 lines.
 * ResultWindow: 43 lines.
 * Admin: 169 lines.
 * Main: 104 lines.
 * QuestionListener: 124 lines.
 * Total: 692 lines.
 */

public class Main extends Application {

  private final QuestionSet questionSet = new QuestionSet();
  private final QuestionListener questionListener = new QuestionListener(questionSet);
  private final ResultWindow resultWindow = new ResultWindow();

  @Override
  public void start(Stage stage) {
    StackPane root = new StackPane();
    VBox content = new VBox();
    content.setAlignment(Pos.CENTER);
    content.setStyle("-fx-background-color: white");

    Label label = new Label();
    label.textProperty().bind(questionListener.getQuestionTextProperty());
    label.setStyle("-fx-font-size: 14pt;");
    content.getChildren().add(label);
    content.disableProperty().bind(questionListener.gameFinishedProperty());
    content.styleProperty().bind(questionListener.getBackgroundColorProperty());

    questionListener.gameFinishedProperty().addListener((obs, oldVal, newVal) -> {
      if (newVal) {
        resultWindow.init(questionSet.getCorrect(), questionSet.size(), questionListener);
        resultWindow.toFront();
      }
      if (!newVal) {
        content.toFront();
      }
    });

    for (int i = 0; i < 4; i++) {
      Button optionBtn = new Button();
      optionBtn.getStyleClass().add("custom-button");
      VBox.setMargin(optionBtn, new Insets(5, 0, 0, 5));
      optionBtn.textProperty().bind(
        switch (i) {
          case 0 -> questionListener.getOption1Property();
          case 1 -> questionListener.getOption2Property();
          case 2 -> questionListener.getOption3Property();
          case 3 -> questionListener.getOption4Property();
          default -> throw new IllegalStateException("Unexpected value: " + i);
        });
      int finalI = i;
      optionBtn.setOnAction(e -> {
        if (!questionListener.getSubmittedProperty().get()) {
          questionListener.checkAnswer(finalI);
        }
      });
      content.getChildren().add(optionBtn);
    }
    root.getChildren().addAll(content, resultWindow);
    content.toFront();
    Scene scene = new Scene(root, 380, 240);
    scene.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.A) {
        try {
          Admin admin = new Admin();
          admin.show();
          stage.close();
        } catch (Exception ex) {
          System.err.println("Error opening admin panel: " + ex.getMessage());
        }
        e.consume();
      }
    });
    scene.getStylesheets().add(
            Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
    stage.setTitle("Quiz");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}