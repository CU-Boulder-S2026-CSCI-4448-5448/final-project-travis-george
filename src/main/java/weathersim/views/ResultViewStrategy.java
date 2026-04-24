package weathersim.views;

import processing.core.PConstants;
import processing.core.PGraphics;
import weathersim.Simulation;

public class ResultViewStrategy implements IViewStrategy{

    @Override
    public void render(PGraphics g, Simulation sim) {
        drawSky(g);
        sim.getResultField().renderClouds(g);
    }

    private void drawSky(PGraphics g) {
        g.fill(145, 205, 255);
        g.rectMode(PConstants.CORNER); // draws from corner
        g.rect(0, 0, g.width, g.height);
    }

}
