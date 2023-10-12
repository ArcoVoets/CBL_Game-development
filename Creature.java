public class Creature {
    PropertyContainer codesContainer;
    PropertyContainer statsContainer;

    /**
     * Constructor.
     */
    Creature() {
        codesContainer = new PropertyContainer(new Property[0]);
        statsContainer = new PropertyContainer(new Property[0]);

        // TESTING
        codesContainer = new PropertyContainer(
                new Property[] {
                        new Property("speed", 1, 10),
                        new Property("damage", 3, 10)
                });
        statsContainer = new PropertyContainer(
                new Property[] {
                        new Property("energy", 10, 10)
                });
    }
}
