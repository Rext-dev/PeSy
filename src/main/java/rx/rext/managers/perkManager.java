package rx.rext.managers;

import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

import rx.rext.data.playerData;

public class perkManager {

    public static void addPerk(String uuid, String perk){
        if(!playerData.hasPerk(perk, uuid)){
            FileConfiguration config = playerData.getDataFile();
            config.set(uuid+".perks."+perk+".level", 1);
        }
    }

    public static void removePerk(String uuid, String perk){
        if(playerData.hasPerk(perk, uuid)){
            FileConfiguration config = playerData.getDataFile();
            config.set(uuid+".perks."+perk, null);
            if(playerData.hasEnablePerk(perk, uuid)){
                config.set(uuid+".perksEnable."+perk, null);
            }
        }
    }
    
    public static void addLevelPerk(String uuid, String perk){
        if(playerData.hasPerk(perk, uuid)){
            Map<String,Object> data = playerData.getPerk(uuid, perk);
            int level = (int) data.get("level");
            if(level<3){
                level++;
                FileConfiguration config = playerData.getDataFile();
                config.set(uuid+".perks."+perk+".level", level);
            }
        }
    }

    public static void removeLevelPerk(String uuid, String perk){
        if(playerData.hasPerk(perk, uuid)){
            Map<String,Object> data = playerData.getPerk(uuid, perk);
            int level = (int) data.get("level");
            if(level>1){
                level--;
                FileConfiguration config = playerData.getDataFile();
                config.set(uuid+".perks."+perk+".level", level);
            }else{
                removePerk(uuid, perk);
            }
        }
    }

    
}
