package weathersim;

import processing.core.PApplet;
import java.util.List;

import weathersim.commands.CommandFactory;
import weathersim.views.IViewStrategy;

public class SimulationUI extends PApplet {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;

    private Simulation sim;

    // Control Key
    private char activeKey = 'r';
    private List<Character> keys = List.of('r', 't', 'm');
    private List<String> labels = List.of("Result", "Temp", "Moisture");

    // UI Constants
    private final int CELL_SIZE = 10;
    private final int PANEL_COLOR = color(200, 200, 255);
    private final int ICON_ACTIVE_COLOR = color(0, 255, 150);
    private final int ICON_INACTIVE_COLOR = color(255, 255, 255);
    private final int PANEL_HEIGHT = 75;
    private final int ICON_WIDTH = 125;
    private final int ICON_HEIGHT = 40;
    private IViewStrategy viewStrategy;
    private final CommandFactory commandFactory = new CommandFactory();

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
        // source: https://processing.org/reference
        textSize(16);
        textAlign(CENTER, CENTER);

        int rows = (height - PANEL_HEIGHT) / CELL_SIZE;
        int cols = width / CELL_SIZE;
        sim = new Simulation(rows, cols);

        commandFactory.newResultViewCommand(this).execute();
    }

    public void draw() {
        // Processing - Loops constantly after setup()
        background(255);

        // Update simulation and render results
        sim.update();
        viewStrategy.render(g, sim);

        fill(0);
        // Bottom Panel
        strokeWeight(0);
        fill(PANEL_COLOR);
        rectMode(CORNER);
        rect(0, height - PANEL_HEIGHT, width, PANEL_HEIGHT);

        // Control Icons
        fill(ICON_INACTIVE_COLOR);
        rectMode(CENTER);
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i) == activeKey) {
                fill(ICON_ACTIVE_COLOR);
            } else {
                fill(ICON_INACTIVE_COLOR);
            }
            int x = (width / 4) * (i + 1);
            int y = height - (PANEL_HEIGHT / 2);
            rect(x, y, ICON_WIDTH, ICON_HEIGHT);
            fill(0);
            text(labels.get(i) + " [" + keys.get(i) + "]", x, y);
        }
    }

    public void keyPressed() {
        // Processing - Runs when key pressed
        if (key == 'r') {
            activeKey = 'r';
            commandFactory.newResultViewCommand(this).execute();
        } else if (key == 't') {
            activeKey = 't';
            commandFactory.newTempViewCommand(this).execute();
        } else if (key == 'm') {
            activeKey = 'm';
            commandFactory.newMoistureViewCommand(this).execute();
        }
    }

    public void mouseDragged() {
        // Processing - Runs when mouse dragged
        viewStrategy.onMouseDrag(g, sim, mouseX, mouseY);
    }

    public void setViewStrategy(IViewStrategy viewStrategy) {
        this.viewStrategy = viewStrategy;
    }

}
