package data;

import interfaces.ProgressBarData;
import interfaces.ProgressBarDataProvider;

/**
 * Environment with temperature and luminosity.
 */
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

    /**
     * Calculates the total damage from the environment temperature.
     * .
     * 
     * @param creature The creature to calculate the damage for
     * @return The total damage from the environment temperature
     */
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

    /**
     * Calculates the energy production from the environment luminosity.
     * 
     * @param creature The creature to calculate the energy production for
     * @return The energy production from the environment luminosity
     */
    int calculateEnergyProduction(Creature creature) {
        if (luminosity
            .getValue() >= creature.codesContainer.averageLightSensitivity) {
            return 1;
        }
        return 0;
    }

    /**
     * Updates the environment stats.
     */
    void updateEnvironmentStats() {
        temperature.addValue((int) ((Math.random() - 0.5) * 20));
        luminosity.addValue((int) ((Math.random() - 0.5) * 20));
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
