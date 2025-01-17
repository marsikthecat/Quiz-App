package com.example.quiz.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Window that shows the result after the quiz is done.
 * Send its attribute to the Main to check if user wants to continue or quit.
 */

public class ResultWindow extends Stage {

  private boolean retry = false;

  /**
   * Constructor.
   */

  public ResultWindow(int a, int size) {
    this.setTitle("Ergebnis");
    this.initStyle(StageStyle.DECORATED);
    this.initModality(Modality.APPLICATION_MODAL);
    VBox content = new VBox();
    content.setSpacing(10);
    content.setAlignment(Pos.CENTER);
    Label l = new Label();
    l.setStyle("-fx-font-size: 17");
    l.setText("Du hast " + a + " von " + size + " Antworten richtig");
    Button repeatButton = new Button("Nochmal versuchen");
    Button closeButton = new Button("Quiz beenden");
    closeButton.setOnAction(e -> this.close());
    repeatButton.setOnAction(e -> {
      retry = true;
      this.close();
    });
    content.getChildren().addAll(l, closeButton, repeatButton);
    Scene scene = new Scene(content, 280, 120);
    this.setScene(scene);
  }

  public boolean isRetry() {
    return retry;
  }
}