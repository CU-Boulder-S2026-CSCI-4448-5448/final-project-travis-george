package weathersim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimulationTest {
    @Test
    void testConstructor() {
        Simulation sim = new Simulation(3, 3, 10f);
        assertNotNull(sim.getTempField());
        assertNotNull(sim.getMoistureField());
        assertNotNull(sim.getRainParticleSystem());
    }

    @Test
    void testUpdate() {
        Simulation sim = new Simulation(3, 3, 10f);
        sim.update();
        assertNotNull(sim.getTempField());
    }
}
