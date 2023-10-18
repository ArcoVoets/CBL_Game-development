class EatAction extends Action {
    public EatAction() {
        super("Eat");
    }

    @Override
    public boolean runAction(Creature creature) {
        creature.statsContainer.getProperty("energy").addValue(2);
        return true;
    }
}
