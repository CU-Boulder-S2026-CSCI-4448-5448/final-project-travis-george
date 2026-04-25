package weathersim.particles;

import weathersim.fields.MoistureField;

import java.util.ArrayList;

// Source: https://processing.org/examples/simpleparticlesystem.html
public class RainParticleSystem {
    public static final float CLOUD_MOISTURE_DRAIN_RATE = 0.0005f;
    public static final float RAIN_THRESHOLD = 0.4f;

    private final MoistureField moistureField;
    private float skyboxWidth;
    private float skyboxHeight;

    ArrayList<RainParticle> particles;

    public RainParticleSystem(float skyboxWidth, float skyboxHeight, MoistureField moistureField) {
        this.moistureField = moistureField;
        this.skyboxWidth = skyboxWidth;
        this.skyboxHeight = skyboxHeight;
        this.particles = new ArrayList<RainParticle>();
    }

    void addParticle(float x, float y, float cellSize) {
        particles.add(new RainParticle(x, y, cellSize));
    }

    public void tick() {
        updateRain();
    }

    public void updateRain() {
        // spawn rain from cells where they are raining
        int rows = moistureField.getNumRows();
        int cols = moistureField.getNumCols();
        float cellWidth = skyboxWidth / cols;
        float cellHeight = skyboxHeight / rows;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float moisture = moistureField.getCell(row, col);
                if (moisture >= RAIN_THRESHOLD) {
                    spawnAt(row, col, cellWidth, cellHeight);
                }
            }
        }

        for (int i = 0; i < particles.size(); i++) {
            RainParticle p = particles.get(i);
            p.update();
            if (p.isDead()) {
                particles.remove(i);
            }
        }
    }

    public void spawnAt(int row, int col, float cellWidth, float cellHeight) {
        // spawn rain
        float x = col * cellWidth;
        float y = row * cellHeight;
        addParticle(x, y, cellWidth);
        // subtract moisture
        moistureField.reduceCell(row, col, CLOUD_MOISTURE_DRAIN_RATE);
    }

    public ArrayList<RainParticle> getParticles() {
        return particles;
    }
}