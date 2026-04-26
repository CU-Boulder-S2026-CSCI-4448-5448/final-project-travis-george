package weathersim.views;

import processing.core.PConstants;
import processing.core.PGraphics;
import weathersim.Simulation;

public class TempViewStrategy implements IViewStrategy {
    // Cold color (blue)
    private static final int COLD_R = 0;
    private static final int COLD_G = 0;
    private static final int COLD_B = 255;
    // Hot color (red)
    private static final int HOT_R = 255;
    private static final int HOT_G = 0;
    private static final int HOT_B = 0;

    @Override
    public void render(PGraphics g, Simulation sim, float cellSize) {
        // Draw grid
        // source: https://processing.org/examples/gameoflife.html
        int rows = sim.getTempField().getNumRows();
        int cols = sim.getTempField().getNumCols();

        g.stroke(200);
        g.strokeWeight(0.1f);
        g.rectMode(PConstants.CORNER);
        int cold = g.color(COLD_R, COLD_G, COLD_B);
        int hot = g.color(HOT_R, HOT_G, HOT_B);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float value = sim.getTempField().getCell(row, col);
                int cellColor = g.lerpColor(cold, hot, value);
                g.fill(cellColor);
                g.rect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }

    @Override
    public void onMouseDrag(PGraphics g, Simulation sim, float cellSize, int x, int y, boolean coldPaint) {
        int col = (int) (x / cellSize);
        int row = (int) (y / cellSize);
        if (coldPaint) {
            sim.getTempField().paintCold(row, col);
        } else {
            sim.getTempField().paintHot(row, col);
        }
    }
}
