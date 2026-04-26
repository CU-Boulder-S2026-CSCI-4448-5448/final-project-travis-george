package weathersim.views;

import processing.core.PGraphics;
import weathersim.Simulation;

public interface IViewStrategy {
    void render(PGraphics g, Simulation sim, float cellSize);

    default void onMouseDrag(PGraphics g, Simulation sim, float cellSize, int x, int y, boolean coldPaint) {
        return;
    }
}
