package weathersim.views;

import processing.core.PConstants;
import processing.core.PGraphics;
import weathersim.Simulation;

public class ResultViewStrategy implements IViewStrategy{
    public static final float CLOUD_THRESHOLD = 0.25f;

    @Override
    public void render(PGraphics g, Simulation sim) {
        drawSky(g);
        drawClouds(g, sim);
    }

    private void drawSky(PGraphics g) {
        g.fill(145, 205, 255);
        g.rectMode(PConstants.CORNER); // draws from corner
        g.rect(0, 0, g.width, g.height);
    }

    private void drawClouds(PGraphics g, Simulation sim) {
        int rows = sim.getMoistureField().getNumRows();
        int cols = sim.getMoistureField().getNumCols();
        float cellWidth = (float) g.width / cols;
        float cellHeight = (float) g.height / rows;
        g.rectMode(PConstants.CORNER);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float moisture = sim.getMoistureField().getCell(row, col);
                if (moisture >= CLOUD_THRESHOLD) {
                    float x = (col) * cellWidth;
                    float y = (row) * cellHeight;
                    g.fill(255);
                    g.rect(x, y, cellWidth, cellHeight);
                }
            }
        }
    }
}
