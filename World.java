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
}
