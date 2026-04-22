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
}
