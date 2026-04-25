package weathersim.views;

import processing.core.PConstants;
import processing.core.PGraphics;
import weathersim.Simulation;
import weathersim.particles.RainParticle;
import java.util.ArrayList;

public class ResultViewStrategy implements IViewStrategy{
    public static final float CLOUD_THRESHOLD = 0.25f;
    public static final float RAIN_THRESHOLD = 0.39f;

    @Override
    public void render(PGraphics g, Simulation sim) {
        drawSky(g);
        drawClouds(g, sim);
        drawRain(g, sim);
    }

    private void drawSky(PGraphics g) {
        g.fill(145, 205, 255);
        g.rectMode(PConstants.CORNER);
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

    public void drawRain(PGraphics g, Simulation sim) {
        // spawn rain from cells where they are raining
        int rows = sim.getMoistureField().getNumRows();
        int cols = sim.getMoistureField().getNumCols();
        float cellWidth = (float) g.width / cols;
        float cellHeight = (float) g.height / rows;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float moisture = sim.getMoistureField().getCell(row, col);
                if (moisture >= RAIN_THRESHOLD) {
                    sim.getRainParticleSystem().spawnAt(row, col, cellWidth, cellHeight, sim);
                }
            }
        }

        // draw rain from rain list
        ArrayList<RainParticle> particles = sim.getRainParticleSystem().getParticles();
        for (int i = particles.size()-1; i >= 0; i--) {
            RainParticle p = particles.get(i);
            g.stroke(0,0,205, p.getLifespan());
            g.fill(0,0,205, p.getLifespan());
            g.ellipse(p.getPosition().x, p.getPosition().y, 3, 8);
        }
    }
}
