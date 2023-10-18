class Creature {
    CodesContainer codesContainer;
    PropertyContainer statsContainer;
    Actions actionsContainer;

    /**
     * Constructor.
     */
    public Creature(PropertyContainer statsContainer,
        Actions actionsContainer) {
        this.statsContainer = statsContainer;
        this.actionsContainer = actionsContainer;
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
