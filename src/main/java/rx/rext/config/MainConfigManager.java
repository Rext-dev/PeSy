package rx.rext.config;

import org.bukkit.configuration.file.FileConfiguration;

import rx.rext.PeSy;
import rx.rext.data.DataManager;
import rx.rext.utils.msgUtils;

public final class MainConfigManager {
    private final CustomConfig configFile;
    private CustomConfig messagesFile;
    private FileConfiguration messages;
    private final CustomConfig perkFile;
    private final CustomConfig playerData;
    private final PeSy plugin;
    private final DataManager dataManager;

    private String lang;

    public MainConfigManager(PeSy plugin){
        this.plugin = plugin;
        configFile = new CustomConfig("config.yml",null, this.plugin);
        configFile.registerConfig();
        loadConfig();
        perkFile = new CustomConfig("perks.yml","perks", this.plugin);
        perkFile.registerConfig();
        playerData = new CustomConfig("data.yml",null, this.plugin);
        playerData.registerConfig();
        dataManager = new DataManager(plugin, perkFile, playerData);
    }

    public void loadConfig(){
        FileConfiguration config = configFile.getConfig();
        // blockBreak = config.getString("messages.test");
        lang = config.getString("config.lang");
        messagesFile = new CustomConfig(lang+".yml","messages",this.plugin);
        messagesFile.registerConfig();
        messages = messagesFile.getConfig();
        msgUtils.setPREFIX(config.getString("config.prefix"));
        msgUtils.setMessages(messages);
    }

    public void reloadConfig(){
        configFile.reloadConfig();
        dataManager.reload();
        loadConfig();
    }

    public String getLang() {
        return lang;
    }

    public void save(){
        perkFile.saveConfig();
        playerData.saveConfig();
    }

    
}
