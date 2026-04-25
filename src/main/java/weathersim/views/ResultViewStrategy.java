package weathersim.views;

import processing.core.PConstants;
import processing.core.PGraphics;
import weathersim.Simulation;

public class ResultViewStrategy implements IViewStrategy {
    public static final float CLOUD_THRESHOLD = 0.325f;
    // Sky color
    private static final int SKY_R = 145;
    private static final int SKY_G = 205;
    private static final int SKY_B = 255;
    // White cloud
    private static final int WHITE_R = 255;
    private static final int WHITE_G = 255;
    private static final int WHITE_B = 255;
    private static final int WISPY_A = 50;
    private static final int SOLID_A = 255;
    // Storm cloud
    private static final int STORM_R = 60;
    private static final int STORM_G = 70;
    private static final int STORM_B = 90;
    private static final int STORM_A = 255;
    // Transition point from white to storm clouds
    private static final float CLOUD_MIDPOINT = 0.25f;

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

        g.noStroke();
        g.rectMode(PConstants.CORNER);
        int wispy = g.color(WHITE_R, WHITE_G, WHITE_B, WISPY_A);
        int white = g.color(WHITE_R, WHITE_G, WHITE_B, SOLID_A);
        int storm = g.color(STORM_R, STORM_G, STORM_B, STORM_A);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float moisture = sim.getMoistureField().getCell(row, col);
                if (moisture >= CLOUD_THRESHOLD) {
                    float x = col * cellWidth;
                    float y = row * cellHeight;
                    float intensity = invLerp(CLOUD_THRESHOLD, 1, moisture);
                    int cloudColor;
                    if (intensity < CLOUD_MIDPOINT) {
                        // White clouds transparency
                        cloudColor = g.lerpColor(wispy, white, invLerp(0, CLOUD_MIDPOINT, intensity));
                    } else {
                        // Storm clouds darkness
                        cloudColor = g.lerpColor(white, storm, invLerp(CLOUD_MIDPOINT, 1, intensity));
                    }
                    g.fill(cloudColor);
                    g.rect(x, y, cellWidth, cellHeight);
                }
            }
        }
    }

    private static float invLerp(float from, float to, float value) {
        // Source: https://www.ronja-tutorials.com/post/047-invlerp_remap/
        return (value - from) / (to - from);
    }
}
