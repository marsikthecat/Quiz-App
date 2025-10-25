package com.example.quiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Admin Panel for managing quiz questions.
 * Allows adding and removing questions from a CSV file.
 */

public class Admin extends Stage {
  private final ListView<String> questionList;

  /**
   * Constructor for Admin panel.
   * Initializes the UI components and loads existing questions from the CSV file.
   */

  public Admin() throws IOException {
    setTitle("Admin Panel");
    questionList = new ListView<>();
    reloadFile();
    Label label = new Label("Add Question:");
    label.setStyle("-fx-font-size: 13pt;");
    TextField questionField = new TextField();
    questionField.setPromptText("Enter question text");
    TextField optionsField = new TextField();
    optionsField.setPromptText("Enter options and their truthiness "
            + "('Banana;true'), seperated by commas");
    Button addButton = new Button("Add Question");
    addButton.setOnAction(e -> {
      try {
        addQuestion(questionField.getText(), optionsField.getText().split(","));
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    questionList.setCellFactory(param -> new ListCell<>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
              setText(null);
            } else {
              setText(item);
              setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                  Dialog<ButtonType> dialog = new Dialog<>();
                  dialog.setTitle("Remove Question");
                  dialog.setContentText("Are you sure you want to remove this question?");
                  dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                  ButtonType result = dialog.showAndWait().orElse(ButtonType.NO);
                  if (result == ButtonType.YES) {
                    int index = questionList.getItems().indexOf(item);
                    try {
                      removeQuestion(index);
                      reloadFile();
                    } catch (IOException ex) {
                      System.err.println("Error removing question: " + ex.getMessage());
                    }
                  }
                }
              });
            }
        }
    });
    VBox allContent = new VBox(10);
    allContent.setPadding(new Insets(6));
    allContent.getChildren().addAll(label, questionField, optionsField,
            addButton, questionList);
    Scene scene = new Scene(allContent, 400, 500);
    this.setScene(scene);
  }

  /**
   * Adds a new question to the CSV file.
   */
  public void addQuestion(String questionText, String[] options)
          throws IOException {
    Exception e = checkInput(questionText, options);
    if (e != null) {
      Dialog<ButtonType> dialog = new Dialog<>();
      dialog.setTitle("Input Error");
      dialog.setContentText(e.getMessage());
      dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
      dialog.showAndWait();
      return;
    }
    StringBuilder sb = new StringBuilder();
    sb.append(questionText);
    for (String option : options) {
      String[] optionSplit = option.split(";");
      String optionText = optionSplit[0];
      String isCorrect = optionSplit[1];
      sb.append(", ").append(optionText).append(";").append(isCorrect);
    }
    sb.append("\n");
    BufferedWriter writer = new BufferedWriter(new FileWriter("Questions.csv", true));
    writer.write(sb.toString());
    writer.close();
    reloadFile();
  }

  private Exception checkInput(String questionText, String[] options) {
    if (questionText == null || questionText.isEmpty()) {
      return new IllegalArgumentException("Question text cannot be empty");
    }
    if (options == null || options.length != 4) {
      return new IllegalArgumentException("A question needs 4 Options");
    }
    return null;
  }

  /**
   * Removes a question from the CSV file by its index.
   */
  public void removeQuestion(int questionId) throws IOException {
    List<String> lines = new ArrayList<>();
    String line;
    try (BufferedReader reader = new BufferedReader(new FileReader("Questions.csv"))) {
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      System.err.println("Error reading questions: " + e.getMessage());
    }
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Questions.csv"))) {
      writer.write("");
      for (int i = 0; i < lines.size(); i++) {
        if (i != questionId) {
          writer.write(lines.get(i));
          writer.newLine();
        }
      }
    }
    reloadFile();
  }

  /**
   * Clears the current list and repopulates it with the contents of the file.
   */
  public void reloadFile() throws IOException {
    questionList.getItems().clear();
    String line;
    try (BufferedReader reader = new BufferedReader(new FileReader("Questions.csv"))) {
      while ((line = reader.readLine()) != null) {
        questionList.getItems().add(line);
      }
    } catch (IOException e) {
      System.err.println("Error reading questions: " + e.getMessage());
    }
  }
}