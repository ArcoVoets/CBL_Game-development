class Creature {
    PropertyContainer codesContainer;
    PropertyContainer statsContainer;
    Actions actionsContainer;

    /**
     * Constructor.
     */
    public Creature(PropertyContainer codesContainer,
        PropertyContainer statsContainer, Actions actionsContainer) {
        this.codesContainer = codesContainer;
        this.statsContainer = statsContainer;
        this.actionsContainer = actionsContainer;
    }
}
