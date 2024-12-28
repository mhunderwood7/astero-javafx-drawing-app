package com.example.astero.controller;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import com.example.astero.model.DraggableShape;
import javafx.scene.shape.Shape;

public class ShapeController {

    public static void addShapeHandlers(DraggableShape shapeModel, DrawingController drawingController) {
        shapeModel.getNode().setOnMousePressed(e -> onMousePressed(shapeModel, drawingController, e));
        shapeModel.getNode().setOnMouseDragged(e -> onMouseDragged(shapeModel, drawingController, e));
        shapeModel.getNode().setOnMouseReleased(e -> onMouseReleased(shapeModel, drawingController, e));
    }

    private static void onMousePressed(DraggableShape shapeModel, DrawingController controller, MouseEvent event) {
        if (controller.isSelectMode()) {
            controller.selectShape(shapeModel.getNode());
            shapeModel.setDragStartX(event.getX());
            shapeModel.setDragStartY(event.getY());
            shapeModel.setBeingDragged(true);
            shapeModel.getNode().setCursor(Cursor.MOVE);
        }
        event.consume();
    }

    private static void onMouseDragged(DraggableShape shapeModel, DrawingController controller, MouseEvent event) {
        if (controller.isSelectMode() && shapeModel.isBeingDragged()) {
            double offsetX = event.getX() - shapeModel.getDragStartX();
            double offsetY = event.getY() - shapeModel.getDragStartY();
            shapeModel.move(offsetX, offsetY);
        }
        event.consume();
    }

    private static void onMouseReleased(DraggableShape shapeModel, DrawingController controller, MouseEvent event) {
        shapeModel.setBeingDragged(false);
        shapeModel.getNode().setCursor(Cursor.DEFAULT);
        event.consume();
    }
}
