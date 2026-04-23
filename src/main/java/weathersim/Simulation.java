package weathersim;

import weathersim.fields.TempField;

public class Simulation {
    private final TempField tempField;

    public Simulation(int rows, int cols) {
        this.tempField = new TempField(rows, cols);
    }

    public void update() {
        tempField.tick();
    }

    public TempField getTempField() {
        return this.tempField;
    }
}
