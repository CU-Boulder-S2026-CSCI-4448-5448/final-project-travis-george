package weathersim.views;

import processing.core.PGraphics;
import weathersim.Simulation;

public class TempViewStrategy implements IViewStrategy {

    @Override
    public void render(PGraphics g, Simulation sim) {
//        g.background(100);
        g.fill(0);
        g.text("keyResult", g.width/2, g.height/2);
    }
}