package weathersim.fields;

public class MoistureField extends SimulationField {
    // Diffusion
    private static final float TRANSFER_RATE = 0.02f;
    private static final float DIFFUSE_RATE = 0.005f;
    // Seed and target
    private static final float TARGET_AVG = 0.25f;
    private static final float INITIAL_RANGE = 0.1f;
    // Evaporation
    private static final float EVAP_RATE = 0.005f;

    private final TempField tempField;

    public MoistureField(int rows, int cols, TempField tempField) {
        super(rows, cols);
        this.tempField = tempField;

        // Start with moisture around target average
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = TARGET_AVG + ((float) Math.random() - 0.5f) * INITIAL_RANGE;
            }
        }
    }

    @Override
    protected void diffuse() {
        // Source: https://thecodingtrain.com/challenges/180-falling-sand
        // Transfer moisture from cell to cell towards colder regions
        float[][] newGrid = new float[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                newGrid[r][c] = grid[r][c];
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                float currMoisture = grid[r][c];
                float currTemp = tempField.getCell(r, c);

                for (int rdiff = -1; rdiff <= 1; rdiff++) {
                    for (int cdiff = -1; cdiff <= 1; cdiff++) {
                        if (!inBounds(r + rdiff, c + cdiff)) {
                            continue;
                        }
                        if (rdiff == 0 && cdiff == 0) {
                            continue;
                        }

                        float sideT = tempField.getCell(r + rdiff, c + cdiff);
                        float sideM = grid[r + rdiff][c + cdiff];

                        // Move moisture to/from sides
                        float transfer = TRANSFER_RATE * Math.max(0, currTemp - sideT);
                        float diffuse = DIFFUSE_RATE * Math.max(0, currMoisture - sideM);
                        float wanted = currMoisture * (transfer + diffuse);

                        // Moisture value cant go below 0 or above 1
                        float sourceHas = newGrid[r][c];
                        float destRoom = 1 - newGrid[r + rdiff][c + cdiff];
                        float amount = Math.min(wanted, Math.min(sourceHas, destRoom));

                        if (amount > 0) {
                            newGrid[r][c] -= amount;
                            newGrid[r + rdiff][c + cdiff] += amount;
                        }
                    }
                }
            }
        }

        // Copy newGrid to grid, track moisture
        float moistureTotal = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = newGrid[r][c];
                moistureTotal += newGrid[r][c];
            }
        }

        // Add moisture from bottom if below target (evaporation)
        float currentAvg = moistureTotal / (rows * cols);
        if (currentAvg < TARGET_AVG) {
            for (int c = 0; c < cols; c++) {
                grid[rows - 1][c] = Math.min(1f, grid[rows - 1][c] + EVAP_RATE);
            }
        }
    }
}
