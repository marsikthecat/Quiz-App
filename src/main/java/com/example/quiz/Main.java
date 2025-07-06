package com.example.quiz;

import com.example.quiz.components.QuestionSet;
import java.util.Objects;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main Quiz Programm.
 * Question: 51 lines.
 * QuestionSet: 85 lines.
 * Main: 83 lines.
 * ResultWindow: 40 lines.
 * QuestionListener: 123 lines.
 * Admin: 153 lines.
 * Total: 535 lines.
 */

public class Main extends Application {

  private final QuestionSet questionSet = new QuestionSet();
  private final QuestionListener questionListener = new QuestionListener(questionSet);

  @Override
  public void start(Stage stage) {
    VBox content = new VBox();
    content.setAlignment(Pos.CENTER);
    content.setStyle("-fx-background-color: white");

    Label label = new Label();
    label.textProperty().bind(questionListener.getQuestionTextProperty());
    label.setStyle("-fx-font-size: 14pt;");
    content.getChildren().add(label);
    content.disableProperty().bind(questionListener.gameFinishedProperty());
    content.styleProperty().bind(questionListener.getBackgroundColorProperty());

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
      optionBtn.setOnAction(e -> questionListener.checkAnswer(finalI));
      content.getChildren().add(optionBtn);
    }
    Scene scene = new Scene(content, 380, 240);
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