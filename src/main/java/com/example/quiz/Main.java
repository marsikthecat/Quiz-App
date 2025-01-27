package com.example.quiz;

import com.example.quiz.models.QuestionSet;
import com.example.quiz.viewmodel.Viewmodel;
import java.util.Objects;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main Quiz Programm.
 * Main: 69 lines.
 * Viewmodel: 117 lines.
 * Option: 25 lines.
 * Question: 29 lines.
 * QuestionSet: 89 lines.
 * ResultWindow: 50 lines.
 * 379 lines of code + 30 lines CSS.
 */

public class Main extends Application {

  private QuestionSet questionSet = new QuestionSet();
  private Viewmodel viewmodel = new Viewmodel(questionSet);

  @Override
  public void start(Stage stage) {
    VBox content = new VBox();
    content.setAlignment(Pos.CENTER);
    content.styleProperty().bind(viewmodel.backgroundColorProperty());
    Label questionLabel = new Label();
    questionLabel.setStyle("-fx-font-size: 14pt");
    questionLabel.textProperty().bind(viewmodel.questionLabelPropertyProperty());
    content.getChildren().add(questionLabel);
    for (int i = 0; i < 4; i++) {
      Button btn = new Button();
      btn.setId(String.valueOf(i));
      btn.textProperty().bind(viewmodel.stringPropertyList(i));
      VBox.setMargin(btn, new Insets(5));
      btn.getStyleClass().add("custom-button");
      btn.setOnAction(e -> viewmodel.handleAnswer(btn));
      content.getChildren().add(btn);
    }
    viewmodel.terminatedProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        this.questionSet = new QuestionSet();
        this.viewmodel = new Viewmodel(questionSet);
        Stage anotherStage = new Stage();
        start(anotherStage);
      }
    });

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
}