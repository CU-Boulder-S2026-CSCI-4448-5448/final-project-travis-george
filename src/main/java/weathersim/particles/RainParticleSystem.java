package weathersim.particles;

import weathersim.fields.MoistureField;

import java.util.ArrayList;
import java.util.Random;

// Source: https://processing.org/examples/simpleparticlesystem.html
public class RainParticleSystem {
    public static final float CLOUD_MOISTURE_DRAIN_RATE = 0.0005f;
    public static final float RAIN_THRESHOLD = 0.4f;
    private static final Random RANDOM = new Random();

    private final MoistureField moistureField;
    private float cellSize;

    private final ArrayList<RainParticle> particles;

    public RainParticleSystem(float cellSize, MoistureField moistureField) {
        this.moistureField = moistureField;
        this.cellSize = cellSize;
        this.particles = new ArrayList<RainParticle>();
    }

    void addParticle(float x, float y) {
        particles.add(new RainParticle(x, y));
    }

    public void tick() {
        createRain();
        updateRain();
    }

    private void createRain() {
        // spawn rain from cells where they are raining
        int rows = moistureField.getNumRows();
        int cols = moistureField.getNumCols();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float moisture = moistureField.getCell(row, col);
                if (moisture >= RAIN_THRESHOLD) {
                    spawnAt(row, col);
                }
            }
        }
    }

    private void updateRain() {
        for (int i = 0; i < particles.size(); i++) {
            RainParticle p = particles.get(i);
            p.update();
            if (p.isDead()) {
                particles.remove(i);
            }
        }
    }

    public void spawnAt(int row, int col) {
        // spawn rain
        float x = col * cellSize + RANDOM.nextFloat(-cellSize / 2f, cellSize / 2f);
        float y = row * cellSize + RANDOM.nextFloat(-cellSize / 2f, cellSize / 2f);
        addParticle(x, y);
        // subtract moisture
        moistureField.reduceCell(row, col, CLOUD_MOISTURE_DRAIN_RATE);
    }

    public ArrayList<RainParticle> getParticles() {
        return particles;
    }
}