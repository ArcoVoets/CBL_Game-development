class PairAction extends Action {
    public PairAction() {
        super("Pair");
    }

    @Override
    public void runAction(Creature creature) {
        creature.statsContainer.properties[0].SubtractValue(1);
    }
}
