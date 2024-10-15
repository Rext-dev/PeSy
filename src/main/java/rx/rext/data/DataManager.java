package rx.rext.data;

import org.bukkit.configuration.file.FileConfiguration;

import rx.rext.PeSy;
import rx.rext.config.CustomConfig;

public class DataManager {
    private PeSy plugin;
    public static FileConfiguration perks;
    private CustomConfig perkFile;
    private CustomConfig dataFile;

    public DataManager(PeSy plugin, CustomConfig perkFile, CustomConfig data){
        this.perkFile = perkFile;
        perks = perkFile.getConfig();
        playerData.setData(data.getConfig());
        dataFile = data;
    }

    public void reload(){
        perkFile.reloadConfig();
    }

    public void saveData(){
        dataFile.saveConfig();
        perkFile.saveConfig();
    }
    
}
