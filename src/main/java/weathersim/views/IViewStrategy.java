package weathersim.views;

import processing.core.PGraphics;
import weathersim.Simulation;

public interface IViewStrategy {
    void render(PGraphics g, Simulation sim);
}
