package weathersim;

import weathersim.fields.MoistureField;
import weathersim.fields.ResultField;
import weathersim.fields.TempField;

public class Simulation {
    private final TempField tempField;
    private final MoistureField moistureField;
    private final ResultField resultField;

    public Simulation(int rows, int cols) {
        this.tempField = new TempField(rows, cols);
        this.moistureField = new MoistureField(rows, cols, tempField);
        this.resultField = new ResultField(moistureField);
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

    public ResultField getResultField() {
        return this.resultField;
    }
}
