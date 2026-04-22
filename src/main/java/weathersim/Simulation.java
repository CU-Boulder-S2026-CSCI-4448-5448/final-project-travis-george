package weathersim;

import weathersim.fields.TempField;

public class Simulation {
    private final TempField tempField;

    public Simulation(int rows, int cols) {
        this.tempField = null;
    }

    public void update() {
        tempField.tick();
    }
}
