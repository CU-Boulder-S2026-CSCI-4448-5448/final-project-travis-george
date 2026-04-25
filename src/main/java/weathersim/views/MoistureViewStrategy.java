package weathersim.views;

import processing.core.PConstants;
import processing.core.PGraphics;
import weathersim.Simulation;

public class MoistureViewStrategy implements IViewStrategy {
    // Dry color (yellow)
    private static final int DRY_R = 255;
    private static final int DRY_G = 240;
    private static final int DRY_B = 200;
    // Wet color (blue)
    private static final int WET_R = 10;
    private static final int WET_G = 120;
    private static final int WET_B = 255;

    @Override
    public void render(PGraphics g, Simulation sim) {
        // Draw grid
        // source: https://processing.org/examples/gameoflife.html
        int rows = sim.getMoistureField().getNumRows();
        int cols = sim.getMoistureField().getNumCols();
        float cellWidth = sim.getSkyboxWidth() / cols;
        float cellHeight = sim.getSkyboxHeight() / rows;

        g.stroke(200);
        g.strokeWeight(0.1f);
        g.rectMode(PConstants.CORNER);
        int dry = g.color(DRY_R, DRY_G, DRY_B);
        int wet = g.color(WET_R, WET_G, WET_B);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float value = sim.getMoistureField().getCell(row, col);
                int cellColor = g.lerpColor(dry, wet, value);
                g.fill(cellColor);
                g.rect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
            }
        }
    }
}
