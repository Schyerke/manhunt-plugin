package manhunt.manhunt.persistent;

import com.google.gson.Gson;
import org.bukkit.entity.Player;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
    private static FileHelper instance;
    public static FileHelper getInstance() {
        if(instance == null){
            instance = new FileHelper();
        }
        return instance;
    }
    private FileHelper(){
    }

    public static final String filename = "playerStatistics.json";
    public static final String path = "E://Development/Minecraft/Manhunt/Dev";
    public static final String fullpath = path + filename;


    // writes the player statistics to a json file
    public boolean savePlayerStatistics(PlayerWrapper playerWrapper){
        Gson gson = new Gson();
        String json = gson.toJson(playerWrapper);
        try {
            FileWriter fileWriter = new FileWriter(fullpath);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // add a new playerwrapper to the file
    public boolean addPlayerWrapper(PlayerWrapper playerWrapper){
        Gson gson = new Gson();
        String json = gson.toJson(playerWrapper);
        try {

            FileWriter fileWriter = new FileWriter(fullpath);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // reads the player statistics from a json file
    public PlayerWrapper loadPlayerStatistics(Player player)  {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader(fullpath);
            PlayerWrapper[] playerWrappers = gson.fromJson(fileReader, PlayerWrapper[].class);
            fileReader.close();
            for(PlayerWrapper pw : playerWrappers){
                if(pw.getPlayer().getDisplayName().equals(player.getDisplayName())){
                    return pw;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
