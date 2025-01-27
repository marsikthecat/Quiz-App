module com.example.quiz {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.quiz to javafx.fxml;
    exports com.example.quiz;
    exports com.example.quiz.models;
    opens com.example.quiz.models to javafx.fxml;
    exports com.example.quiz.utils;
    opens com.example.quiz.utils to javafx.fxml;
}