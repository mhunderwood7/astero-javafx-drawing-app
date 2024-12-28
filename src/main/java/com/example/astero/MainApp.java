package com.example.astero;

import com.example.astero.controller.DrawingController;
import com.example.astero.view.ToolbarBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        DrawingController drawingController = new DrawingController();

        BorderPane root = new BorderPane();
        root.setCenter(drawingController.getDrawingPane());

        root.setTop(ToolbarBuilder.build(drawingController));

        Scene scene = new Scene(root, 900, 600);
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DELETE:
                    drawingController.deleteSelectedShape();
                    break;
                default:
                    break;
            }
        });

        primaryStage.setTitle("Astero Shape Drawing Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
