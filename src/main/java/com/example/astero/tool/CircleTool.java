package com.example.astero.tool;

import com.example.astero.controller.DrawingController;
import com.example.astero.model.DraggableShape;
import com.example.astero.controller.ShapeController;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleTool implements DrawingTool {

    private Shape previewShape;
    private double startX, startY;

    @Override
    public void onMousePressed(MouseEvent event, DrawingController controller) {
        startX = event.getX();
        startY = event.getY();

        Circle circle = new Circle(startX, startY, 0);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.TRANSPARENT);
        circle.setPickOnBounds(true);

        previewShape = circle;
        controller.getDrawingPane().getChildren().add(previewShape);
    }

    @Override
    public void onMouseDragged(MouseEvent event, DrawingController controller) {
        if (previewShape instanceof Circle) {
            Circle circle = (Circle) previewShape;
            double currentX = event.getX();
            double currentY = event.getY();
            double radius = Math.hypot(currentX - startX, currentY - startY);
            circle.setCenterX(startX);
            circle.setCenterY(startY);
            circle.setRadius(radius);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event, DrawingController controller) {
        if (previewShape instanceof Circle) {
            Circle circle = (Circle) previewShape;
            if (circle.getRadius() < 2) {
                controller.getDrawingPane().getChildren().remove(circle);
            } else {
                DraggableShape ds = new DraggableShape(circle);
                controller.getShapes().add(ds);
                ShapeController.addShapeHandlers(ds, controller);
                controller.selectShape(circle);
            }
        }
        previewShape = null;
    }
}
