package com.example.quiz.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ResultWindow class to display the result of the quiz.
 * It shows the number of correct answers and provides options to retry or close.
 */

public class ResultWindow extends Stage {

  private boolean retry = false;

  /**
   * Constructor for ResultWindow.
   */

  public ResultWindow(int correct, int total) {
    Label resultLabel = new Label("Correct: " + correct + " / " + total);
    Button tryAgain = new Button("Try Again");
    Button close = new Button("Close");
    tryAgain.setOnAction(e -> {
      retry = true;
      this.close();
    });
    close.setOnAction(e -> this.close());

    VBox root = new VBox(resultLabel, tryAgain, close);
    Scene scene = new Scene(root, 250, 100);
    this.setScene(scene);
  }

  public boolean isRetry() {
    return retry;
  }
}