public class World {
    Creature playerCreature;
    Creature[] worldCreatures;

    public World(Creature playerCreature, Creature[] worldCreatures) {
        this.playerCreature = playerCreature;
        this.worldCreatures = worldCreatures;
    }
}
