package rx.rext.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class playerData {
    public static FileConfiguration data;

    public static void setData(FileConfiguration data) {
        playerData.data = data;
    }

    public static String getData(String path){
        return data.getString(path);
    }
    public static Set<String> getKeys(){
        return data.getKeys(false);
    }

    public static FileConfiguration getDataFile(){
        return data;
    }

    public static Map<String, Double> getStatus(String uuid){
        Map<String, Double> effectsMap = new HashMap<>();
        Double exhaust = data.getDouble(uuid+".stats.exhaust");
        Double endurance = data.getDouble(uuid+".stats.endurance");
        effectsMap.put("exhaust", exhaust);
        effectsMap.put("endurance", endurance);
        return effectsMap;
    }

    public static void setStatus(String uuid, Map<String, Double> data){
        getDataFile().set(uuid+".stats.exhaust", data.get("exhaust"));
        getDataFile().set(uuid+".stats.endurance", data.get("endurance"));
    }

    public static boolean hasPerk(String Perk, String uuid){
        return data.contains(uuid+".perks."+Perk);
    }

    public static boolean hasEnablePerk(String Perk, String uuid){
        return data.contains(uuid+".perksEnable."+Perk);
    }

    public static Map<String, Object> getPerk(String uuid,String perk){
        Map<String, Object> perkInfo = new HashMap<>();
        boolean hasPerk = hasPerk(perk, uuid);
        if(hasPerk){
            boolean enablePerk = hasEnablePerk(perk, uuid);
            int level = data.getInt(uuid+".perks."+perk+".level");
            perkInfo.put("level",level);
            if(enablePerk){
                ConfigurationSection perkEnableInfo = data.getConfigurationSection(uuid+".perksEnable."+perk);
                for (String key : perkEnableInfo.getKeys(false)) {
                    Object value = perkEnableInfo.get(key);
                    perkInfo.put(key, value);
                }
            }
        }
        perkInfo.put("hasPerk", hasPerk);
        return perkInfo;
    }
}
