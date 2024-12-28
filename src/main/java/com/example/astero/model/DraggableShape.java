package com.example.astero.model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class DraggableShape {

    private final Shape node;
    private double dragStartX;
    private double dragStartY;
    private boolean beingDragged;

    private final Paint originalStroke;
    private final double originalStrokeWidth;

    public DraggableShape(Shape node) {
        this.node = node;
        this.originalStroke = node.getStroke();
        this.originalStrokeWidth = node.getStrokeWidth();
    }

    public Shape getNode() {
        return node;
    }

    public double getDragStartX() {
        return dragStartX;
    }

    public void setDragStartX(double dragStartX) {
        this.dragStartX = dragStartX;
    }

    public double getDragStartY() {
        return dragStartY;
    }

    public void setDragStartY(double dragStartY) {
        this.dragStartY = dragStartY;
    }

    public boolean isBeingDragged() {
        return beingDragged;
    }

    public void setBeingDragged(boolean beingDragged) {
        this.beingDragged = beingDragged;
    }

    public Paint getOriginalStroke() {
        return originalStroke;
    }

    public double getOriginalStrokeWidth() {
        return originalStrokeWidth;
    }

    public void move(double offsetX, double offsetY) {
        if (node instanceof javafx.scene.shape.Rectangle) {
            javafx.scene.shape.Rectangle rect = (javafx.scene.shape.Rectangle) node;
            rect.setX(rect.getX() + offsetX);
            rect.setY(rect.getY() + offsetY);
        } else if (node instanceof javafx.scene.shape.Circle) {
            javafx.scene.shape.Circle circle = (javafx.scene.shape.Circle) node;
            circle.setCenterX(circle.getCenterX() + offsetX);
            circle.setCenterY(circle.getCenterY() + offsetY);
        } else if (node instanceof javafx.scene.shape.Polygon) {
            javafx.scene.shape.Polygon poly = (javafx.scene.shape.Polygon) node;
            for (int i = 0; i < poly.getPoints().size(); i += 2) {
                double oldX = poly.getPoints().get(i);
                double oldY = poly.getPoints().get(i + 1);
                poly.getPoints().set(i, oldX + offsetX);
                poly.getPoints().set(i + 1, oldY + offsetY);
            }
        }
    }
}
