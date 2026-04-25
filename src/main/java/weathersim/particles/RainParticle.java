package weathersim.particles;

import processing.core.PVector;
import java.util.Random;

// Source: https://processing.org/examples/simpleparticlesystem.html
public class RainParticle {
    PVector position;
    PVector velocity;
    PVector acceleration;
    float lifespan;

    Random random = new Random();

    // change randomness to cellsize
    RainParticle(float x, float y, float cellSize) {
        acceleration = new PVector(0f, 0.4f);
        velocity = new PVector((float) random.nextFloat(-0.5f, 0.5f), 0); // more visually smooth

        position = new PVector(
                x + random.nextFloat(-cellSize / 2.0f, cellSize / 2.0f),
                y + random.nextFloat(-cellSize / 2.0f, cellSize / 2.0f)
        ); // random spawn within cell for visual smoothness only

        lifespan = 255.0f;
    }

    void update() {
        velocity.add(acceleration);
        position.add(velocity);
        lifespan -= 4;
    }

    boolean isDead() {
        if (lifespan < 0.0) {
            return true;
        } else {
            return false;
        }
    }

    public float getLifespan() {
        return lifespan;
    }

    public PVector getPosition() {
        return position;
    }
}