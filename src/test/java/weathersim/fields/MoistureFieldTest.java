package weathersim.fields;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoistureFieldTest {
    @Test
    void testConstructor() {
        MoistureField moistureField = new MoistureField(2, 3, new TempField(2, 3));
        assertEquals(2, moistureField.getNumRows());
        assertEquals(3, moistureField.getNumCols());
    }

    @Test
    void testDiffuse() {
        TempField tempField = new TempField(3, 3);
        MoistureField moistureField = new MoistureField(3, 3, tempField);
        tempField.setCell(0, 0, 0f); // cold spot to move moisture towards 

        float totalBefore = 0;
        for (int r = 0; r < moistureField.getNumRows(); r++) {
            for (int c = 0; c < moistureField.getNumCols(); c++) {
                totalBefore += moistureField.getCell(r, c);
            }
        }

        moistureField.tick();
        moistureField.tick();
        moistureField.tick();

        float totalAfter = 0;
        for (int r = 0; r < moistureField.getNumRows(); r++) {
            for (int c = 0; c < moistureField.getNumCols(); c++) {
                totalAfter += moistureField.getCell(r, c);
            }
        }
        // moisture moved around but still the same amount
        assertEquals(totalBefore, totalAfter, 0.1f);
    }
}
