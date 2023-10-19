class World {
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
}
