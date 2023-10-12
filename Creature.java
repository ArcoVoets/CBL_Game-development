public class Creature {
    PropertyContainer codesContainer;
    PropertyContainer statsContainer;

    /**
     * Constructor.
     */
    Creature() {
        codesContainer = new PropertyContainer(
            new Property[] {
                new Property("speed", 5, 10),
                new Property("damage", 5, 10),
                new Property("max_energy", 5, 10),
                new Property("heat_resistance", 5, 10),
            });
        statsContainer = new PropertyContainer(
            new Property[] {
                new Property("energy", 10, 10)
            });
    }
}
