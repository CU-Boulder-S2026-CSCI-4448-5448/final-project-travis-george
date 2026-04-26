package weathersim.particles;

import processing.core.PVector;
import java.util.Random;

// Source: https://processing.org/examples/simpleparticlesystem.html
public class RainParticle {
    private static final float GRAVITY = 0.4f;
    private static final float VELOCITY_DIFF = 0.5f;
    private static final float LIFESPAN = 255.0f;
    private static final float LIFESPAN_LOSS_RATE = 4f;

    private final PVector position;
    private final PVector velocity;
    private final PVector acceleration;
    private float lifespan;

    private static final Random RANDOM = new Random();

    // change randomness to cellsize
    RainParticle(float x, float y) {
        position = new PVector(x, y);
        velocity = new PVector(RANDOM.nextFloat(-VELOCITY_DIFF, VELOCITY_DIFF), 0);
        acceleration = new PVector(0f, GRAVITY);
        lifespan = LIFESPAN;
    }

    void update() {
        velocity.add(acceleration);
        position.add(velocity);
        lifespan -= LIFESPAN_LOSS_RATE;
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