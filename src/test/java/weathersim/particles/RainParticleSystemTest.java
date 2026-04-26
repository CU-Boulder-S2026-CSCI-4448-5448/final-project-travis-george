package weathersim.particles;

import org.junit.jupiter.api.Test;
import weathersim.fields.MoistureField;
import weathersim.fields.TempField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static weathersim.views.ResultViewStrategy.CLOUD_THRESHOLD;

public class RainParticleSystemTest {
    @Test
    void testSimpleStart() {
        MoistureField moistureField = new MoistureField(2, 2, new TempField(2, 2));
        float cellWidth = 5;
        float cellHeight = 5;
        RainParticleSystem rainSystem = new RainParticleSystem(100, 100, moistureField);

        // asserts rain particles are created at expected location within random creation tolerances
        rainSystem.spawnAt(1,1, cellWidth, cellHeight);
        assertEquals(5, rainSystem.getParticles().getFirst().getPosition().x, cellWidth / 2.0f);
        assertEquals(5, rainSystem.getParticles().getFirst().getPosition().y, cellHeight / 2.0f);
        rainSystem.spawnAt(0,0, cellWidth, cellHeight);
        assertEquals(0, rainSystem.getParticles().getLast().getPosition().x, cellWidth / 2.0f);
        assertEquals(0, rainSystem.getParticles().getLast().getPosition().y, cellHeight / 2.0f);
    }

    @Test
    void testTick() {
        MoistureField moistureField = new MoistureField(2, 2, new TempField(2, 2));
        moistureField.setCell(1, 1, CLOUD_THRESHOLD*2); // above cloud threshold
        moistureField.setCell(0, 0, CLOUD_THRESHOLD*2);
        moistureField.setCell(1, 0, CLOUD_THRESHOLD*2);

        float cellWidth = 5;
        float cellHeight = 5;
        RainParticleSystem rainSystem = new RainParticleSystem(100, 100, moistureField);
        rainSystem.tick(); // particles created here b/c above cloud threshold
        assertEquals(3, rainSystem.getParticles().size());
        assertEquals(CLOUD_THRESHOLD*2 - RainParticleSystem.CLOUD_MOISTURE_DRAIN_RATE, moistureField.getCell(1, 1), 0.0001f);
    }
}
