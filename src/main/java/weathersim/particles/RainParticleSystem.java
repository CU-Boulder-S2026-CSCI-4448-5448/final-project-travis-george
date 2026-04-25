package weathersim.particles;

import weathersim.Simulation;
import java.util.ArrayList;

// Source: https://processing.org/examples/simpleparticlesystem.html
public class RainParticleSystem {
    public static final float CLOUD_MOISTURE_DRAIN_RATE = 0.0005f;

    ArrayList<RainParticle> particles;

    public RainParticleSystem() {
        particles = new ArrayList<RainParticle>();
    }

    void addParticle(float x, float y, float cellSize) {
        particles.add(new RainParticle(x, y, cellSize));
    }

    public void tick() {
        for (int i = particles.size()-1; i >= 0; i--) {
            RainParticle p = particles.get(i);
            p.update();
            if (p.isDead()) {
                particles.remove(i);
            }
        }
    }

    public void spawnAt(int row, int col, float cellWidth, float cellHeight, Simulation sim) {
        // spawn rain
        float x = col * cellWidth;
        float y = row * cellHeight;
        addParticle(x, y, cellWidth);
        // subtract moisture
        float moisture = sim.getMoistureField().getCell(row, col);
        sim.getMoistureField().setCell(row, col, Math.max(0.0f, moisture - CLOUD_MOISTURE_DRAIN_RATE));
    }

    public ArrayList<RainParticle> getParticles() {
        return particles;
    }
}