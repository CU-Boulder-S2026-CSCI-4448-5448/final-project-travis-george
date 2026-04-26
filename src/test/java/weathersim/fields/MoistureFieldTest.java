package weathersim.fields;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoistureFieldTest {
    @Test
    void testConstructor() {
        MoistureField moistureField = new MoistureField(2, 3, new TempField(2, 3));
        assertEquals(2, moistureField.getNumRows());
        assertEquals(3, moistureField.getNumCols());
    }

    @Test
    void testDiffuse() {
        MoistureField moistureField = new MoistureField(3, 3, new TempField(3, 3));
        moistureField.tick();
        float value = moistureField.getCell(1, 1);
        assertTrue(value >= 0f);
        assertTrue(value <= 1f);
    }
}
