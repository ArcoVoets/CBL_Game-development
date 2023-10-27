package data;

public class World implements interfaces.World {
    Creature playerCreature;
    Creature[] worldCreatures;

    /**
     * Constructor.
     * 
     * @param playerCreature Player creature to add to the world
     * @param worldCreatures Other creatures in the world
     */
    public World(Creature playerCreature, Creature[] worldCreatures) {
        this.playerCreature = playerCreature;
        this.worldCreatures = worldCreatures;
        playerCreature.setWorld(this);
        for (Creature worldCreature : worldCreatures) {
            worldCreature.setWorld(this);
        }
    }

    public void deleteCreature(Creature creature) {
        Creature[] newWorldCreatures = new Creature[worldCreatures.length - 1];
        int i = 0;
        for (Creature worldCreature : worldCreatures) {
            if (worldCreature != creature) {
                newWorldCreatures[i] = worldCreature;
                i++;
            }
        }
        worldCreatures = newWorldCreatures;
    }

    @Override
    public Creature getPlayerCreature() {
        return playerCreature;
    }

    @Override
    public Creature[] getWorldCreatures() {
        return worldCreatures;
    }
}
