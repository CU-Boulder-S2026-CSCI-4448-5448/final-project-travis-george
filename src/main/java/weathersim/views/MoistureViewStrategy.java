package weathersim.views;

import processing.core.PGraphics;
import weathersim.Simulation;

public class MoistureViewStrategy implements IViewStrategy {
    @Override
    public void render(PGraphics g, Simulation sim) {
        // Draw grid
        // source: https://processing.org/examples/gameoflife.html
        int rows = sim.getMoistureField().getNumRows();
        int cols = sim.getMoistureField().getNumCols();
        int cellSize = g.width / cols;

        g.stroke(200);
        g.strokeWeight(0.1f);
        int dry = g.color(255, 240, 150); // yellow
        int wet = g.color(20, 20, 120); // blue
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float value = sim.getMoistureField().getCell(row, col);
                int cellColor = g.lerpColor(dry, wet, value);
                g.fill(cellColor);
                g.rect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }
}
