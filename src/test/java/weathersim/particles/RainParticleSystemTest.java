package weathersim.particles;

import org.junit.jupiter.api.Test;
import weathersim.fields.MoistureField;
import weathersim.fields.TempField;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RainParticleSystemTest {
    @Test
    void testSimpleStart() {
        MoistureField moistureField = new MoistureField(2, 2, new TempField(2, 2));
        moistureField.setCell(1, 1, 0.8f);
        RainParticleSystem rainSystem = new RainParticleSystem(100, 100, moistureField);
        rainSystem.spawnAt(1,1, 5, 5);
        rainSystem.spawnAt(0,0, 5, 5);
        assertEquals(1, rainSystem.getParticles().getFirst().getPosition().x);
        assertEquals(1, rainSystem.getParticles().getFirst().getPosition().y);


        assertEquals(1, rainSystem.getParticles().getLast().getPosition().x);
        assertEquals(1, rainSystem.getParticles().getLast().getPosition().y);
    }


}
