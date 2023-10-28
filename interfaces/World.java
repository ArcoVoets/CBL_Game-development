package interfaces;

/**
 * World interface with a list of creatures in the world and the player
 * creature.
 */
public interface World {
    public Creature getPlayerCreature();

    public Creature[] getWorldCreatures();
}
