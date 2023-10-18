class Environment implements ProgressBarDataProvider {

    private Property temperature;
    private Property luminosity;

    /**
     * Constructor.
     */
    Environment() {
        temperature = new Property("Temperature", 20, -20, 100, "°C");
        luminosity = new Property("Luminosity", 50, 100);
    }

    int calculateTemperatureDamage(Creature creature) {
        if (temperature
            .getValue() > creature.codesContainer.averageHeatResistance) {
            // creature.statsContainer.getProperty("energy").SubtractValue(1);
            return 1;
        } else if (temperature
            .getValue() < creature.codesContainer.averageColdResistance) {
            // creature.statsContainer.getProperty("energy").SubtractValue(1);
            return 1;
        }
        return 0;
    }

    int calculateEnergyProduction(Creature creature) {
        if (luminosity.getValue() > creature.codesContainer.averageLuminosity) {
            // creature.statsContainer.getProperty("luminosity").AddValue(1);
            return 1;
        }
        return 0;
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
