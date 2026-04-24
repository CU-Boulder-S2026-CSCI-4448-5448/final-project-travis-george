package weathersim.fields;

import processing.core.PConstants;
import processing.core.PGraphics;

public class ResultField extends SimulationField {
    public static final float CLOUD_THRESHOLD = 0.25f;

    private final MoistureField moistureField;

    public ResultField(MoistureField moistureField) {
        super(moistureField.getNumRows(), moistureField.getNumCols());
        this.moistureField = moistureField;
    }

    @Override
    protected void diffuse() {}

    public void renderClouds(PGraphics g) {
        int rows = getNumRows();
        int cols = getNumCols();
        float cellWidth = (float) g.width / cols;
        float cellHeight = (float) g.height / rows;
        g.rectMode(PConstants.CORNER);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                float moisture = moistureField.getCell(row, col);
                if (moisture >= CLOUD_THRESHOLD) {
                    float x = (col) * cellWidth;
                    float y = (row) * cellHeight;
                    g.fill(255);
                    g.rect(x, y, cellWidth, cellHeight);
                }
            }
        }
    }
}
