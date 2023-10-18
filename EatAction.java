class EatAction extends Action {
    public EatAction() {
        super("Eat");
    }

    @Override
    public void runAction(Creature creature) {
        creature.statsContainer.properties[0].AddValue(1);
    }
}
