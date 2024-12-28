package com.example.astero.controller;

import com.example.astero.model.DraggableShape;
import com.example.astero.tool.DrawingTool;
import com.example.astero.tool.SelectTool;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class DrawingController {

    private final Pane drawingPane;
    private DrawingTool currentTool;

    private final List<DraggableShape> shapes = new ArrayList<>();

    private DraggableShape selectedShape = null;

    public DrawingController() {
        drawingPane = new Pane();
        drawingPane.setPrefSize(800, 600);

        this.currentTool = new SelectTool();

        drawingPane.setOnMousePressed(e -> {
            if (currentTool != null) {
                currentTool.onMousePressed(e, this);
            }
        });
        drawingPane.setOnMouseDragged(e -> {
            if (currentTool != null) {
                currentTool.onMouseDragged(e, this);
            }
        });
        drawingPane.setOnMouseReleased(e -> {
            if (currentTool != null) {
                currentTool.onMouseReleased(e, this);
            }
        });
    }

    public Pane getDrawingPane() {
        return drawingPane;
    }

    public List<DraggableShape> getShapes() {
        return shapes;
    }

    public void setCurrentTool(DrawingTool tool) {
        this.currentTool = tool;
    }

    public DrawingTool getCurrentTool() {
        return currentTool;
    }

    public boolean isSelectMode() {
        return (currentTool instanceof SelectTool);
    }

    public void selectShape(Shape shape) {
        deselectShape();
        for (DraggableShape ds : shapes) {
            if (ds.getNode() == shape) {
                selectedShape = ds;
                shape.setStrokeWidth(3);
                shape.setStroke(Color.BLUE);
                break;
            }
        }
    }

    public void deselectShape() {
        if (selectedShape != null) {
            Shape node = selectedShape.getNode();
            node.setStroke(selectedShape.getOriginalStroke());
            node.setStrokeWidth(selectedShape.getOriginalStrokeWidth());
            node.getStrokeDashArray().clear();
            selectedShape = null;
        }
    }

    public void deleteSelectedShape() {
        if (selectedShape != null) {
            drawingPane.getChildren().remove(selectedShape.getNode());
            shapes.remove(selectedShape);
            selectedShape = null;
        }
    }
}
