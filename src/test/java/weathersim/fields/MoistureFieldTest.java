package weathersim.fields;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoistureFieldTest extends SimulationField {
    private final TempField tempField;

    public MoistureFieldTest(int rows, int cols, TempField tempField) {
        super(rows, cols);
        this.tempField = tempField;
    }

    @Disabled
    void testConstructor() {
        MoistureFieldTest moistureField = new MoistureFieldTest(2, 3, tempField);
        assertEquals(2, moistureField.getNumRows());
        assertEquals(3, moistureField.getNumCols());
    }

    protected void diffuse() {
    }
}

