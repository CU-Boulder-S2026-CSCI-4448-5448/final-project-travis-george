package weathersim.views;

import processing.core.PGraphics;
import weathersim.Simulation;

public class ResultViewStrategy implements IViewStrategy{

    @Override
    public void render(PGraphics g, Simulation sim) {
        g.fill(0);
        g.text("Result View", g.width/2, g.height/2);
    }
}
