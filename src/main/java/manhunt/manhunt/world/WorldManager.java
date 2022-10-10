package manhunt.manhunt.world;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.time.Instant;
import java.time.temporal.ChronoField;

import static manhunt.manhunt.world.WorldConstants.*;

public class WorldManager {
    private static WorldManager instance;
    public static WorldManager getInstance() {
        if(instance == null){
            instance = new WorldManager();
        }
        return instance;
    }
    private WorldManager() {
    }

    private final String filepath = "E:/Development/Minecraft/Manhunt/Server";

    private World currentOverWorld;
    private World currentNetherWorld;
    private World currentEndWorld;

    public void init(){
        currentPrefixWorld = String.valueOf(Instant.now().get(ChronoField.MILLI_OF_SECOND));
        createNewWorld();
    }

    public void createNewWorld() {
        createOverWorld();
        createNetherWorld();
        createEndWorld();
    }

    private void createOverWorld(){
        WorldCreator worldCreator = new WorldCreator(filepath + "/manhunt_worlds/" + WORLD_SUFFIX + currentPrefixWorld);

        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.type(WorldType.NORMAL);

        this.currentOverWorld = worldCreator.createWorld();
    }

    private void createNetherWorld(){
        WorldCreator worldCreator = new WorldCreator(filepath + "/manhunt_worlds/" + NETHER_SUFFIX + currentPrefixWorld);

        worldCreator.environment(World.Environment.NETHER);
        worldCreator.type(WorldType.NORMAL);

        this.currentNetherWorld = worldCreator.createWorld();
    }

    private void createEndWorld(){
        WorldCreator worldCreator = new WorldCreator(filepath + "/manhunt_worlds/" + END_SUFFIX + currentPrefixWorld);

        worldCreator.environment(World.Environment.THE_END);
        worldCreator.type(WorldType.NORMAL);

        this.currentEndWorld = worldCreator.createWorld();
    }

    public World getCurrentOverWorld() {
        return currentOverWorld;
    }

    public World getCurrentEndWorld() {
        return currentEndWorld;
    }

    public World getCurrentNetherWorld() {
        return currentNetherWorld;
    }
}
