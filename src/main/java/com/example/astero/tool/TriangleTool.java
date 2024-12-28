package com.example.astero.tool;

import com.example.astero.controller.DrawingController;
import com.example.astero.controller.ShapeController;
import com.example.astero.model.DraggableShape;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleTool implements DrawingTool {

    private Shape previewShape;
    private double startX, startY;

    @Override
    public void onMousePressed(MouseEvent event, DrawingController controller) {
        startX = event.getX();
        startY = event.getY();

        Polygon triangle = new Polygon(
                startX, startY,
                startX, startY,
                startX, startY
        );
        triangle.setStroke(Color.BLACK);
        triangle.setFill(Color.TRANSPARENT);
        triangle.setPickOnBounds(true);

        previewShape = triangle;
        controller.getDrawingPane().getChildren().add(previewShape);
    }

    @Override
    public void onMouseDragged(MouseEvent event, DrawingController controller) {
        if (previewShape instanceof Polygon) {
            Polygon poly = (Polygon) previewShape;
            double currentX = event.getX();
            double currentY = event.getY();

            double baseX1 = startX;
            double baseY  = startY;
            double baseX2 = currentX;
            double apexX  = (baseX1 + baseX2) / 2.0;
            double apexY  = currentY;

            poly.getPoints().set(0, baseX1);
            poly.getPoints().set(1, baseY);
            poly.getPoints().set(2, baseX2);
            poly.getPoints().set(3, baseY);
            poly.getPoints().set(4, apexX);
            poly.getPoints().set(5, apexY);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event, DrawingController controller) {
        if (previewShape instanceof Polygon) {
            Polygon poly = (Polygon) previewShape;
            double baseWidth = Math.abs(poly.getPoints().get(2) - poly.getPoints().get(0));
            double height    = Math.abs(poly.getPoints().get(5) - poly.getPoints().get(1));
            if (baseWidth < 2 || height < 2) {
                controller.getDrawingPane().getChildren().remove(poly);
            } else {
                DraggableShape ds = new DraggableShape(poly);
                controller.getShapes().add(ds);
                ShapeController.addShapeHandlers(ds, controller);
                controller.selectShape(poly);
            }
        }
        previewShape = null;
    }
}
