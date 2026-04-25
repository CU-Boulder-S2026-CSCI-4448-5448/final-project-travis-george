package weathersim.fields;

public abstract class SimulationField {
    protected final float[][] grid;
    protected final int rows;
    protected final int cols;

    protected SimulationField(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new float[rows][cols];
    }

    public final void tick() {
        diffuse();
    }

    protected abstract void diffuse();

    protected boolean inBounds(int row, int col) {
        return (row >= 0 && row < rows && col >= 0 && col < cols);
    }

    public int getNumCols() {
        return cols;
    }

    public int getNumRows() {
        return rows;
    }

    public float getCell(int row, int col) {
        if (inBounds(row, col)) {
            return this.grid[row][col];
        }
        return 0.0f;
    }

    public void setCell(int row, int col, float value) { // removes cloud moisture during rain
        if (inBounds(row, col)) {
            this.grid[row][col] = value;
        }
    }

    public void reduceCell(int row, int col, float value) {
        if (inBounds(row, col)) {
            this.grid[row][col] = Math.max(0.0f, this.grid[row][col] - value);
        }
    }
}
