package data;

/**
 * CreatureActionRunner class with methods to run actions, based on TOPSIS.
 */
public class CreatureActionRunner {
    Actions actions;
    World world;

    void setActions(Actions actions) {
        this.actions = actions;
    }

    void setWorld(World world) {
        this.world = world;
    }

    void chooseAction() {
        if (DecisionMaking.chooseToEat(actions.creature)) {
            Actions.selectedCreature = DecisionMaking.creatureToEat(
                actions.creature, world.worldCreatures);
            actions.getAction(EatAction.class).execute();
        } else {
            Actions.selectedCreature = DecisionMaking
                .creatureToPair(actions.creature, world.worldCreatures);
            actions.getAction(PairAction.class).execute();

        }
    }
}
