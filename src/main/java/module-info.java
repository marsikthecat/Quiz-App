module com.example.quiz {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.quiz to javafx.fxml;
    exports com.example.quiz;
    exports com.example.quiz.components;
    opens com.example.quiz.components to javafx.fxml;
    exports com.example.quiz.ui;
    opens com.example.quiz.ui to javafx.fxml;
}