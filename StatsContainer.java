public class StatsContainer implements ProgressBarDataProvider {
    public Property energy = new Property("Energy", 15, 20);

    public ProgressBarData[] getProgressBarData() {
        return new ProgressBarData[] {
            new ProgressBarData(energy.getKey(), energy.getMinValue(),
                energy.getMaxValue(), energy.getValue())
        };
    }
}
