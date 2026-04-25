package weathersim.fields;

public class TempField extends SimulationField {
    // Brush
    private static final float HOT_PAINT_VALUE = 0.7f;
    private static final float COLD_PAINT_VALUE = 0.0f;
    private static final int BRUSH_SIZE = 2;
    // Diffusion
    private static final float DIFFUSION_RATE = 0.25f;
    private static final float HORIZONTAL_BIAS = 10.0f;
    private static final float GRADIENT_FALLOFF = 0.98f;

    public TempField(int rows, int cols) {
        super(rows, cols);

        // Setup temp field with gradient
        float temp = HOT_PAINT_VALUE;
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = temp;
            }
            temp *= GRADIENT_FALLOFF;
        }
    }

    @Override
    protected void diffuse() {
        // Source: https://thecodingtrain.com/challenges/132-fluid-simulation/
        float[][] newGrid = new float[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Bottom row always hot source
                if (r == rows - 1) {
                    newGrid[r][c] = HOT_PAINT_VALUE;
                    continue;
                }

                float current = grid[r][c];

                // sets neighbors to current point
                float up = current;
                float down = current;
                float left = current;
                float right = current;

                // updates neighbors if not on the edge of grid
                if (inBounds(r-1,c)) {
                    up = grid[r - 1][c];
                }
                if (inBounds(r+1,c)) {
                    down = grid[r + 1][c];
                }
                if (inBounds(r,c-1)) {
                    left = grid[r][c - 1];
                }
                if (inBounds(r,c+1)) {
                    right = grid[r][c + 1];
                }

                // Diffuses more to the sides than up and down
                float avg = (HORIZONTAL_BIAS * (left + right) + (up + down)) / (2 * HORIZONTAL_BIAS + 2);
                newGrid[r][c] = current + DIFFUSION_RATE * (avg - current);
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
}
