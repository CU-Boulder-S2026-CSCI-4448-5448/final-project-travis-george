package weathersim;

import processing.core.PApplet;
import java.util.List;

import weathersim.commands.CommandFactory;
import weathersim.views.IViewStrategy;

public class SimulationUI extends PApplet {
    // source: https://processing.org/reference
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;

    private Simulation sim;

    // Layout
    private final int CELL_SIZE = 10;
    private final int PANEL_HEIGHT = 90;
    private final int PANEL_COLOR = color(200, 200, 255);
    private final int BLACK = color(0);
    private final int WHITE = color(255);

    // Main Icons
    private char activeKey = 'r';
    private final List<Character> KEYS = List.of('r', 't', 'm');
    private final List<String> LABELS = List.of("Result", "Temp", "Moisture");
    private final int TEXT_SIZE = 16;
    private final int ICON_TOP_MARGIN = 30;
    private final int ICON_WIDTH = 125;
    private final int ICON_HEIGHT = 30;
    private final int ICON_ACTIVE_COLOR = color(0, 255, 150);
    private final int ICON_INACTIVE_COLOR = color(255, 255, 255);

    // Paint Icons
    private boolean coldPaint = false;
    private final int PAINT_ICON_WIDTH = 60;
    private final int PAINT_ICON_HEIGHT = 20;
    private final int PAINT_ICON_STROKE_ACTIVE = 3;
    private final int PAINT_TEXT_SIZE = 12;
    private final int COLD_COLOR = color(0, 0, 255);
    private final int HOT_COLOR = color(255, 0, 0);

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
        textSize(TEXT_SIZE);
        textAlign(CENTER, CENTER);

        int rows = (height - PANEL_HEIGHT) / CELL_SIZE;
        int cols = width / CELL_SIZE;
        float skyboxWidth = width;
        float skyboxHeight = height - PANEL_HEIGHT;
        sim = new Simulation(rows, cols, skyboxWidth, skyboxHeight);

        commandFactory.newResultViewCommand(this).execute();
    }

    public void draw() {
        // Processing - Loops constantly after setup()
        // clear screen
        background(WHITE);

        // Update Sim
        sim.update();

        // Render current view
        viewStrategy.render(g, sim);

        // Render info panel
        drawInfoPanel();
    }

    public void drawInfoPanel() {
        strokeWeight(0);
        fill(PANEL_COLOR);
        rectMode(CORNER);
        rect(0, height - PANEL_HEIGHT, width, PANEL_HEIGHT);

        // Control Icons
        fill(ICON_INACTIVE_COLOR);
        rectMode(CENTER);
        int iconY = height - PANEL_HEIGHT + ICON_TOP_MARGIN;
        for (int i = 0; i < KEYS.size(); i++) {
            if (KEYS.get(i) == activeKey) {
                fill(ICON_ACTIVE_COLOR);
            } else {
                fill(ICON_INACTIVE_COLOR);
            }
            int x = (width / (KEYS.size() + 1)) * (i + 1);
            rect(x, iconY, ICON_WIDTH, ICON_HEIGHT);
            fill(BLACK);
            text(LABELS.get(i) + " [" + KEYS.get(i) + "]", x, iconY);
        }

        // Paint Icons (under Temp icon)
        int paintY = iconY + ICON_HEIGHT;
        int tempX = (width / (KEYS.size() + 1)) * 2;
        int coldX = tempX - PAINT_ICON_WIDTH / 2;
        int hotX = tempX + PAINT_ICON_WIDTH / 2;

        stroke(WHITE);
        textSize(PAINT_TEXT_SIZE);

        // Cold brush
        fill(COLD_COLOR);
        strokeWeight(coldPaint ? PAINT_ICON_STROKE_ACTIVE : 0);
        rect(coldX, paintY, PAINT_ICON_WIDTH, PAINT_ICON_HEIGHT);
        fill(WHITE);
        text("Cold [c]", coldX, paintY);

        // Hot brush
        fill(HOT_COLOR);
        strokeWeight(!coldPaint ? PAINT_ICON_STROKE_ACTIVE : 0);
        rect(hotX, paintY, PAINT_ICON_WIDTH, PAINT_ICON_HEIGHT);
        fill(WHITE);
        text("Hot [h]", hotX, paintY);

        textSize(TEXT_SIZE);
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
        } else if (key == 'c') {
            coldPaint = true;
        } else if (key == 'h') {
            coldPaint = false;
        }
    }

    public void mouseDragged() {
        // Processing - Runs when mouse dragged
        viewStrategy.onMouseDrag(g, sim, mouseX, mouseY, coldPaint);
    }

    public void setViewStrategy(IViewStrategy viewStrategy) {
        this.viewStrategy = viewStrategy;
    }
}
