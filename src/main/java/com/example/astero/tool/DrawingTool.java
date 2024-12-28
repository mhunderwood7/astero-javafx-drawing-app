package com.example.astero.tool;

import com.example.astero.controller.DrawingController;
import javafx.scene.input.MouseEvent;

public interface DrawingTool {

    void onMousePressed(MouseEvent event, DrawingController controller);

    void onMouseDragged(MouseEvent event, DrawingController controller);

    void onMouseReleased(MouseEvent event, DrawingController controller);
}
