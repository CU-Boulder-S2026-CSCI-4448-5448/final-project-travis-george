package weathersim;

import processing.core.PApplet;

public class SimulationUI extends PApplet {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;

    private String keyResult = "";

    public static void main(String[] args) {
        // source: https://www.mindevice.net/posts/processing4-java
        PApplet.main("weathersim.SimulationUI");
    }

    public void settings() {
        // Processing - Runs before setup()
        size(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    public void setup() {
        // Processing - Runs once at start

    }

    public void draw() {
        // Processing - Loops constantly after setup()
        background(255);
        fill(0);
        text(keyResult, width/2, height/2);
    }

    public void keyPressed () {
        // Processing - Runs when key pressed
        if(key == 't') {
            keyResult = "'t' key pressed";
        } else if (key == 'm') {
            keyResult = "'m' key pressed";
        } else if (key == 'r') {
            keyResult = "'r' key pressed";
        }
    }
}
