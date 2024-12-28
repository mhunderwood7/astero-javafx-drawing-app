package com.example.astero.tool;

import com.example.astero.controller.DrawingController;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

public class SelectTool implements DrawingTool {

    @Override
    public void onMousePressed(MouseEvent event, DrawingController controller) {
        if (!(event.getTarget() instanceof Shape)) {
            controller.deselectShape();
        }
    }

    @Override
    public void onMouseDragged(MouseEvent event, DrawingController controller) {
    }

    @Override
    public void onMouseReleased(MouseEvent event, DrawingController controller) {
    }
}
