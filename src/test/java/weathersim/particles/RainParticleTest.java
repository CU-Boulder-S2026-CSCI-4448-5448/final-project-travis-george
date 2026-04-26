package weathersim.particles;

import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static weathersim.particles.RainParticle.LIFESPAN;
import static weathersim.particles.RainParticle.LIFESPAN_LOSS_RATE;

public class RainParticleTest {
    @Test
    void testRainParticleStart() {
        RainParticle particle = new RainParticle(10, 100, 1);
        float startingX = particle.getPosition().x;
        float startingY = particle.getPosition().y;
        assertEquals(10.0f, startingX, 1.0f);
        assertEquals(100.0f, startingY, 1.0f);
    }
    @Test
    void testRainParticleUpdate() {
        RainParticle particle = new RainParticle(10, 100, 3);
        float startingLifespan = particle.getLifespan();
        particle.update();
        assertTrue(particle.getLifespan() <startingLifespan);
    }

    @Test
    void testRainParticleDies() {
        RainParticle particle = new RainParticle(0, 0, 1);
        for (float i = LIFESPAN; i > 0; i -= LIFESPAN_LOSS_RATE) {
            particle.update();
        }
        assertTrue(particle.isDead());
    }
}
