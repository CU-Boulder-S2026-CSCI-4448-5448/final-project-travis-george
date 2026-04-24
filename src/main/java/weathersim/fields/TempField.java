package weathersim.fields;

public class TempField extends SimulationField {
    private static final float TOP_TEMP = 0f;
    private static final float BOTTOM_TEMP = 0.8f;
    private static final float HOT_PAINT_VALUE = 0.7f;
    private static final float COLD_PAINT_VALUE = 0.0f;
    private static final int BRUSH_SIZE = 2;
    private static final float DIFFUSION_RATE = 0.2f;

    private final float diffusionRate;

    public TempField(int rows, int cols) {
        super(rows, cols);
        this.diffusionRate = DIFFUSION_RATE;

        // Setup temp field with gradient
        for (int r = 0; r < rows; r++) {
            float percentage = ((float) r / rows);
            float value = lerp(TOP_TEMP, BOTTOM_TEMP, percentage);
            for (int c = 0; c < cols; c++) {
                grid[r][c] = value;
            }
        }
    }

    @Override
    protected void diffuse() {
        // Source: https://thecodingtrain.com/challenges/132-fluid-simulation/
        float[][] newGrid = new float[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Top row always cold source
                if (r == 0) {
                    newGrid[r][c] = TOP_TEMP;
                    continue;
                }
                // Bottom row always hot source
                if (r == rows - 1) {
                    newGrid[r][c] = BOTTOM_TEMP;
                    continue;
                }

                float current = grid[r][c];

                // sets neighbors to current point
                float up = current;
                float down = current;
                float left = current;
                float right = current;

                // updates neighbors if not on the edge of grid
                if (r > 0) {
                    up = grid[r - 1][c];
                }
                if (r < rows - 1) {
                    down = grid[r + 1][c];
                }
                if (c > 0) {
                    left = grid[r][c - 1];
                }
                if (c < cols - 1) {
                    right = grid[r][c + 1];
                }

                float avg = (float) ((up + down + left + right) / 4);
                newGrid[r][c] = current + diffusionRate * (avg - current);
            }
        }

        // copies newGrid to grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = newGrid[r][c];
            }
        }
    }

    public void paintHot(int row, int col) {
        paint(row, col, HOT_PAINT_VALUE);
    }

    public void paintCold(int row, int col) {
        paint(row, col, COLD_PAINT_VALUE);
    }

    private void paint(int row, int col, float value) {
        // Set paint values around the target cell
        for (int r = -BRUSH_SIZE; r <= BRUSH_SIZE; r++) {
            for (int c = -BRUSH_SIZE; c <= BRUSH_SIZE; c++) {
                if (inBounds(row + r, col + c)) {
                    grid[row + r][col + c] = value;
                }
            }
        }
    }

    private float lerp(float start, float end, float amount) {
        // Matching the function of Processing's lerp()
        // source: https://c.algorithmexamples.com/web/misc/lerp.html
        return start + amount * (end - start);
    }
}
