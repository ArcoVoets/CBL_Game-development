class Creature {
    CodesContainer codesContainer;
    PropertyContainer statsContainer;
    Environment environment;
    Actions actionsContainer;

    /**
     * Constructor.
     */
    public Creature(PropertyContainer statsContainer, Actions actionsContainer,
        Environment environment) {
        this.statsContainer = statsContainer;
        this.actionsContainer = actionsContainer;
        this.environment = environment;
        this.codesContainer = new CodesContainer(
            new Code[] {
                Code.generateRandomCode(),
                Code.generateRandomCode(),
                Code.generateRandomCode(),
                Code.generateRandomCode(),
                Code.generateRandomCode(),
                Code.generateRandomCode()
            });
    }
}
