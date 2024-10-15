package rx.rext;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import rx.rext.commands.miplugin;
import rx.rext.config.MainConfigManager;
import rx.rext.listeners.EntityListeners;
import rx.rext.listeners.NoDamagePets;
import rx.rext.listeners.PlayerListener;
import rx.rext.listeners.spawnMob;
import rx.rext.utils.cmdSender;
import rx.rext.utils.msgUtils;


public class PeSy extends JavaPlugin{
    private final String version = getDescription().getVersion();
    private MainConfigManager mainConfigManager;

    @Override
    public void onEnable(){
        cmdSender.setSender(getServer().getConsoleSender());
        mainConfigManager = new MainConfigManager(this);
        registerCommands();
        registerEvents();
        PlaceholderStart();
        
        cmdSender.sender.sendMessage(msgUtils.text("&2El Plugin se ha activado exitosamente &e- &3"+version,true));
    }

    @Override
    public void onDisable(){
        mainConfigManager.save();
        cmdSender.sender.sendMessage(msgUtils.text("&4El Plugin se ha desactivado",true));
    }

    public void registerCommands(){
        this.getCommand("miplugin").setExecutor(new miplugin(this));
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new NoDamagePets(this), this);
        getServer().getPluginManager().registerEvents(new spawnMob(), this);
        getServer().getPluginManager().registerEvents(new EntityListeners(), this);
        //getServer().getPluginManager().registerEvents(new sprintPerk(), this);
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }

    public void PlaceholderStart(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            //enable listeners
            // ADD A UTIL FOR DEPENDENCIES LIKE dependencyCheck.get("PLACEHOLDER")
            cmdSender.sender.sendMessage(msgUtils.text("Enabling PlaceholderAPI support", true));
        } else {
            getLogger().warning("You not have placeholder api, this plugin wit not use this support");
        }
    }
}
