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
        node.setLayoutX(node.getLayoutX() + offsetX);
        node.setLayoutY(node.getLayoutY() + offsetY);
    }

}
