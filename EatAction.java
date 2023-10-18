class EatAction extends Action {
    public EatAction() {
        super("Eat");
    }

    @Override
    public void runAction(Creature creature) {
        creature.statsContainer.getProperty("energy").AddValue(2);
    }
}
