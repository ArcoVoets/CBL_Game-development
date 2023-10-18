class Environment extends PropertyContainer {
    Environment(Property[] properties) {
        super(properties);
    }

    int calculateDamage(Creature creature) {
        if (getProperty("temperature").getValue() > creature.codesContainer
            .getProperty("heat resistance").getValue()) {
            creature.statsContainer.getProperty("energy").SubtractValue(1);
        } else if (getProperty("temperature")
            .getValue() < creature.codesContainer
                .getProperty("cold resistance").getValue()) {
            creature.statsContainer.getProperty("energy").SubtractValue(1);
        }
        return 0;
    }
}
