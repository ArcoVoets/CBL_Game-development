package data;

import interfaces.*;

public class Environment implements ProgressBarDataProvider {

    private Property temperature;
    private Property luminosity;

    /**
     * Constructor.
     */
    public Environment() {
        temperature = new Property("Temperature", 20, -20, 100, "°C");
        luminosity = new Property("Luminosity", 50, 100);
    }

    int calculateTemperatureDamage(Creature creature) {
        int totalTemperatureDamage = 0;
        if (temperature
            .getValue() > creature.codesContainer.averageHeatResistance) {
            totalTemperatureDamage++;
        }
        if (temperature
            .getValue() < creature.codesContainer.averageColdResistance) {
            totalTemperatureDamage++;
        }
        return totalTemperatureDamage;
    }

    int calculateEnergyProduction(Creature creature) {
        if (luminosity
            .getValue() >= creature.codesContainer.averageLightSensitivity) {
            return 1;
        }
        return 0;
    }

    void updateEnvironmentStats() {
        temperature.addValue((int) ((Math.random() - 0.5) * 5));
        luminosity.addValue((int) ((Math.random() - 0.5) * 5));
    }

    @Override
    public ProgressBarData[] getProgressBarData() {
        return new ProgressBarData[] {
            new ProgressBarData(temperature.getKey(), temperature.getMinValue(),
                temperature.getMaxValue(),
                temperature.getValue(), "°C"),
            new ProgressBarData(luminosity.getKey(), luminosity.getMinValue(),
                luminosity.getMaxValue(),
                luminosity.getValue())
        };
    }
}
