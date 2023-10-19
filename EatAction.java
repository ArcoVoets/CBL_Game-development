class EatAction extends Action {
    public EatAction(UpdateCallback actionCallback) {
        super("Eat", actionCallback);
    }

    @Override
    public boolean runAction(Creature creature) {
        creature.statsContainer.energy.addValue(2);
        return true;
    }
}
