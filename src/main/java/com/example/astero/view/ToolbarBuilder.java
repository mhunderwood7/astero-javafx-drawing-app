package com.example.astero.view;

import com.example.astero.controller.DrawingController;
import com.example.astero.tool.SelectTool;
import com.example.astero.tool.RectangleTool;
import com.example.astero.tool.CircleTool;
import com.example.astero.tool.TriangleTool;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;

public class ToolbarBuilder {

    public static ToolBar build(DrawingController drawingController) {
        Label createLabel = new Label("Create:");
        createLabel.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222;" +
                        "-fx-padding: 4;"
        );

        ToggleButton rectBtn = new ToggleButton("Rectangle");
        rectBtn.setOnAction(e -> drawingController.setCurrentTool(new RectangleTool()));

        ToggleButton circleBtn = new ToggleButton("Circle");
        circleBtn.setOnAction(e -> drawingController.setCurrentTool(new CircleTool()));

        ToggleButton triBtn = new ToggleButton("Triangle");
        triBtn.setOnAction(e -> drawingController.setCurrentTool(new TriangleTool()));

        ToggleGroup creationGroup = new ToggleGroup();
        rectBtn.setToggleGroup(creationGroup);
        circleBtn.setToggleGroup(creationGroup);
        triBtn.setToggleGroup(creationGroup);

        HBox creationTools = new HBox(5, rectBtn, circleBtn, triBtn);

        Label editLabel = new Label("Edit:");
        editLabel.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222;" +
                        "-fx-padding: 4;"
        );

        ToggleButton selectBtn = new ToggleButton("Select");
        selectBtn.setOnAction(e -> drawingController.setCurrentTool(new SelectTool()));
        selectBtn.setSelected(true);

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> drawingController.deleteSelectedShape());

        HBox editingTools = new HBox(5, selectBtn, deleteBtn);

        return new ToolBar(
                createLabel,
                creationTools,
                new Separator(),
                editLabel,
                editingTools
        );
    }
}
