package weathersim;

import weathersim.fields.MoistureField;
import weathersim.fields.TempField;

public class Simulation {
    private final TempField tempField;
    private final MoistureField moistureField;
    private final float skyboxWidth;
    private final float skyboxHeight;

    public Simulation(int rows, int cols, float skyboxWidth, float skyboxHeight) {
        this.tempField = new TempField(rows, cols);
        this.moistureField = new MoistureField(rows, cols, tempField);
        this.skyboxWidth = skyboxWidth;
        this.skyboxHeight = skyboxHeight;
    }

    public void update() {
        tempField.tick();
        moistureField.tick();
    }

    public TempField getTempField() {
        return this.tempField;
    }

    public MoistureField getMoistureField() {
        return this.moistureField;
    }

    public float getSkyboxWidth() {
        return this.skyboxWidth;
    }

    public float getSkyboxHeight() {
        return this.skyboxHeight;
    }
}
