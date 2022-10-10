package manhunt.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Constants {
    public static final Manhunt PLUGIN = Manhunt.getPlugin(Manhunt.class);
    public static final Location SPAWN_LOCATION = new Location(Bukkit.getWorld("world"), -40 , 62, -46);
    public static final int INVINCIBILITY_TIME_SECONDS = 30;
}
