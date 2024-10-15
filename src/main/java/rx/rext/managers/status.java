package rx.rext.managers;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import rx.rext.PeSy;
import rx.rext.data.playerData;

public class status extends BukkitRunnable {
    private final PeSy plugin;
    
    public status(PeSy plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()){
            String uuid = player.getUniqueId().toString();
            Map<String,Double> data = playerData.getStatus(uuid);
            

        }
    }
    
    
}
