package weathersim.views;

import processing.core.PConstants;
import processing.core.PGraphics;
import weathersim.Simulation;

public class ResultViewStrategy implements IViewStrategy {
    public static final float CLOUD_THRESHOLD = 0.25f;
    // Sky color (light blue)
    private static final int SKY_R = 145;
    private static final int SKY_G = 205;
    private static final int SKY_B = 255;
    // Cloud color (white)
    private static final int CLOUD_R = 255;
    private static final int CLOUD_G = 255;
    private static final int CLOUD_B = 255;

    @Override
    public void render(PGraphics g, Simulation sim) {
        drawSky(g, sim);
        drawClouds(g, sim);
    }

    private void drawSky(PGraphics g, Simulation sim) {
        g.background(SKY_R, SKY_G, SKY_B);
    }

    private void drawClouds(PGraphics g, Simulation sim) {
        int rows = sim.getMoistureField().getNumRows();
        int cols = sim.getMoistureField().getNumCols();
        float cellWidth = sim.getSkyboxWidth() / cols;
        float cellHeight = sim.getSkyboxHeight() / rows;

        g.rectMode(PConstants.CORNER);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float moisture = sim.getMoistureField().getCell(row, col);
                if (moisture >= CLOUD_THRESHOLD) {
                    float x = (col) * cellWidth;
                    float y = (row) * cellHeight;
                    g.fill(CLOUD_R, CLOUD_G, CLOUD_B);
                    g.rect(x, y, cellWidth, cellHeight);
                }
            }
        }
    }
}
