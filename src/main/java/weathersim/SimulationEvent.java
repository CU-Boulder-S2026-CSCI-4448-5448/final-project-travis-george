package weathersim;

public class SimulationEvent {
    // source is what changed (temperature, moisture, etc)
    private final String source;
    // 2D [row][col] grid of density values (temperature, moisture, etc)
    private final float[][] densityGrid;

    public SimulationEvent(String source, float[][] densityGrid) {
        this.source = source;
        this.densityGrid = densityGrid;
    }

    public String getSource() {
        return source;
    }

    public float[][] getDensityGrid() {
        return densityGrid;
    }
}
