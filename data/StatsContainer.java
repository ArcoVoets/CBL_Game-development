package data;

import interfaces.ProgressBarData;
import interfaces.ProgressBarDataProvider;

public class StatsContainer implements ProgressBarDataProvider {
    public Property energy = new Property("Energy", 15, 25);

    public ProgressBarData[] getProgressBarData() {
        return new ProgressBarData[] {
            new ProgressBarData(energy.getKey(), energy.getMinValue(),
                energy.getMaxValue(), energy.getValue())
        };
    }

    public Property getEnergy() {
        return energy;
    }
}
