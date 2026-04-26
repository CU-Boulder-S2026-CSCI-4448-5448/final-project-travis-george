package weathersim.particles;

import org.junit.jupiter.api.Test;
import weathersim.fields.MoistureField;
import weathersim.fields.TempField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static weathersim.particles.RainParticleSystem.RAIN_THRESHOLD;

public class RainParticleSystemTest {
    @Test
    void testSimpleStart() {
        MoistureField moistureField = new MoistureField(2, 2, new TempField(2, 2));
        RainParticleSystem rainSystem = new RainParticleSystem(10f, moistureField);

        rainSystem.spawnAt(1, 1);
        assertEquals(1, rainSystem.getParticles().size());
        rainSystem.spawnAt(0, 0);
        assertEquals(2, rainSystem.getParticles().size());
    }

    @Test
    void testRainReducesMoisture() {
        MoistureField moistureField = new MoistureField(2, 2, new TempField(2, 2));
        moistureField.setCell(1, 1, RAIN_THRESHOLD); // above rain threshold
        moistureField.setCell(0, 0, RAIN_THRESHOLD);
        moistureField.setCell(1, 0, RAIN_THRESHOLD);

        RainParticleSystem rainSystem = new RainParticleSystem(10f, moistureField);
        rainSystem.tick(); // particles created here b/c above cloud threshold
        assertEquals(3, rainSystem.getParticles().size());
        assertEquals(RAIN_THRESHOLD - RainParticleSystem.CLOUD_MOISTURE_DRAIN_RATE, moistureField.getCell(1, 1));
        assertEquals(RAIN_THRESHOLD - RainParticleSystem.CLOUD_MOISTURE_DRAIN_RATE, moistureField.getCell(0, 0));
        assertEquals(RAIN_THRESHOLD - RainParticleSystem.CLOUD_MOISTURE_DRAIN_RATE, moistureField.getCell(1, 0));
    }
}
