package com.example.quiz.ui;

import com.example.quiz.QuestionListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Displays the result of the quiz and provides options to retry or close.
 */

public class ResultWindow extends VBox {

  private final Label resultLabel = new Label();
  private final Button tryAgain = new Button();
  private final Button close = new Button();

  /**
   * Constructor for ResultWindow.
   */
  public ResultWindow() {
    HBox btnBar = new HBox();
    btnBar.getChildren().addAll(tryAgain, close);
    btnBar.setAlignment(Pos.CENTER);
    btnBar.setSpacing(15);
    getChildren().addAll(resultLabel, btnBar);
    setAlignment(Pos.CENTER);
    setSpacing(15);
  }

  /**
   * Constructor for ResultWindow.
   */
  public void init(int correct, int total, QuestionListener questionListener) {
    resultLabel.setText("Correct: " + correct + " / " + total);
    tryAgain.setText("Try Again");
    close.setText("Close");
    tryAgain.setOnAction(e -> questionListener.reset());
    close.setOnAction(e -> System.exit(0));
  }
}