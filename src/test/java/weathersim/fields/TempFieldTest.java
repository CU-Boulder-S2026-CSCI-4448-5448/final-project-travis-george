package weathersim.fields;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static weathersim.fields.TempField.COLD_PAINT_VALUE;
import static weathersim.fields.TempField.HOT_PAINT_VALUE;

public class TempFieldTest {
    @Test
    void testConstructor() {
        TempField tempField = new TempField(2, 3);
        assertEquals(2, tempField.getNumRows());
        assertEquals(3, tempField.getNumCols());
    }

    @Test
    void testHotPaint() {
        TempField tempField = new TempField(4, 4);
        tempField.paintHot(2, 2);
        assertEquals(HOT_PAINT_VALUE, tempField.getCell(2,2));
    }

    @Test
    void testColdPaint() {
        TempField tempField = new TempField(4, 4);
        tempField.paintCold(2, 2);
        assertEquals(COLD_PAINT_VALUE, tempField.getCell(2,2));
    }

    @Test
    void testDiffuse() {
        TempField tempField = new TempField(3, 3);
        float neighborBefore = tempField.getCell(0, 1);
        tempField.setCell(1, 1, 1.0f); // hot center
        tempField.tick();
        // center got cooler, sides got warmer
        assertTrue(tempField.getCell(1, 1) < 1.0f);
        assertTrue(tempField.getCell(0, 1) > neighborBefore);
    }
}
