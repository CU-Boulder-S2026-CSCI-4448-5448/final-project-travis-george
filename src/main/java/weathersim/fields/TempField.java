package weathersim.fields;

public class TempField extends SimulationField {
    private static final float PAINT_VALUE = 1.0f;
    private static final int BRUSH_SIZE = 1;
    private static final float DIFFUSION_RATE = 0.1f;

    private final float diffusionRate;

    public TempField(int rows, int cols) {
        super(rows, cols);
        this.diffusionRate = DIFFUSION_RATE;
    }

    @Override
    protected void diffuse() {
        // Source: https://thecodingtrain.com/challenges/132-fluid-simulation/

        float[][] newGrid = new float[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
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

    public void paint(int row, int col) {
        // Set paint values around the target cell
        for (int r = -BRUSH_SIZE; r <= BRUSH_SIZE; r++) {
            for (int c = -BRUSH_SIZE; c <= BRUSH_SIZE; c++) {
                if (inBounds(row + r, col + c)) {
                    grid[row + r][col + c] = PAINT_VALUE;
                }
            }
        }
    }
}
