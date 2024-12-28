package com.example.astero.tool;

import com.example.astero.controller.DrawingController;
import com.example.astero.model.DraggableShape;
import com.example.astero.controller.ShapeController;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleTool implements DrawingTool {

    private Shape previewShape;
    private double startX, startY;

    @Override
    public void onMousePressed(MouseEvent event, DrawingController controller) {
        startX = event.getX();
        startY = event.getY();

        Rectangle rect = new Rectangle(startX, startY, 0, 0);
        rect.setStroke(Color.BLACK);
        rect.setFill(Color.TRANSPARENT);
        rect.setPickOnBounds(true);

        previewShape = rect;
        controller.getDrawingPane().getChildren().add(previewShape);
    }

    @Override
    public void onMouseDragged(MouseEvent event, DrawingController controller) {
        if (previewShape instanceof Rectangle) {
            Rectangle rect = (Rectangle) previewShape;
            double currentX = event.getX();
            double currentY = event.getY();
            rect.setX(Math.min(startX, currentX));
            rect.setY(Math.min(startY, currentY));
            rect.setWidth(Math.abs(currentX - startX));
            rect.setHeight(Math.abs(currentY - startY));
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event, DrawingController controller) {
        if (previewShape instanceof Rectangle) {
            Rectangle rect = (Rectangle) previewShape;
            if (rect.getWidth() < 2 || rect.getHeight() < 2) {
                controller.getDrawingPane().getChildren().remove(rect);
            } else {
                DraggableShape ds = new DraggableShape(rect);
                controller.getShapes().add(ds);
                ShapeController.addShapeHandlers(ds, controller);
                controller.selectShape(rect);
            }
        }
        previewShape = null;
    }
}
