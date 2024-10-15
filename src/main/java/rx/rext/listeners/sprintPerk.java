package rx.rext.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class sprintPerk implements Listener{
    
    @EventHandler
    public void toogleSplint(PlayerToggleSprintEvent event){
        Player player = event.getPlayer();
        if(event.isSprinting()){
            PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 60, 4);
            player.addPotionEffect(effect);
        } else{
            player.removePotionEffect(PotionEffectType.SPEED);
        }
        
    }
}
