package weathersim.views;

import processing.core.PGraphics;
import weathersim.Simulation;

public class TempViewStrategy implements IViewStrategy {

    @Override
    public void render(PGraphics g, Simulation sim) {
        // Draw grid
        // source: https://processing.org/examples/gameoflife.html
        int rows = sim.getTempField().getNumRows();
        int cols = sim.getTempField().getNumCols();
        int cellSize = g.width / cols;

        g.stroke(200);
        g.strokeWeight(0.1f);
        int cold = g.color(0, 0, 255); // blue
        int hot = g.color(255, 0, 0); // red
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
    public void onMouseDrag(PGraphics g, Simulation sim, int x, int y) {
        int cellSize = g.width / sim.getTempField().getNumCols();
        int col = x / cellSize;
        int row = y / cellSize;
        sim.getTempField().paint(row, col);
    }
}