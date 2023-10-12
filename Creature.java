public class Creature {
    PropertyContainer codesContainer;
    PropertyContainer statsContainer;

    Creature() {
        codesContainer = new PropertyContainer(new Property[0]);
        statsContainer = new PropertyContainer(new Property[0]);

        // TESTING
        codesContainer = new PropertyContainer(
                new Property[] {
                        new Property("speed", 1),
                        new Property("damage", 3)
                });
        statsContainer = new PropertyContainer(
                new Property[] {
                        new Property("energy", 10)
                });
    }
}
