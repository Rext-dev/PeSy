package rx.rext.listeners;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

import rx.rext.utils.msgUtils;

public class spawnMob implements Listener{

    @EventHandler
    public void twinsSpawns(EntityBreedEvent event){
        if(event.getBreeder() instanceof Player player){
            LivingEntity child = event.getEntity();
            Random random = new Random();
            double probability = 0.10;
            double value = random.nextDouble();
            if (value <= probability){
                child.copy(child.getLocation());
                player.sendMessage(msgUtils.text("Genial, tuviste un gemelo", false));
            }
        }
    }
}
