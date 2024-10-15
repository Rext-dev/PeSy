package rx.rext.listeners;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import rx.rext.PeSy;

public class NoDamagePets implements Listener{
    
    private final PeSy plugin;

    public NoDamagePets(PeSy plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void preventDamagePets(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Tameable){
            Player player = (Player) event.getDamager();
            Tameable entity = (Tameable) event.getEntity();
            // String message = msgUtils.text("You are a: " + player.getName(), true);
            // sender.sendMessage(message);
            // message = msgUtils.text("You damage a: " + event.getEntity().getName(), true);
            // sender.sendMessage(message);
            if(entity.isTamed()){
                if(entity.getOwner().getUniqueId() == player.getUniqueId()){
                    event.setCancelled(true);
                    // message = msgUtils.text("You are the owner, cancelling damage", true);
                    // sender.sendMessage(message);
                }
            }
        } else if(event.getDamager() instanceof Projectile && event.getEntity() instanceof Tameable){
            Projectile proyectil = (Projectile) event.getDamager();
            Tameable entity = (Tameable) event.getEntity();
            if(proyectil.getShooter() instanceof Player player){
                if(entity.isTamed()){
                    if(entity.getOwner().getUniqueId() == player.getUniqueId()){
                        event.setCancelled(true);
                        // message = msgUtils.text("You are the owner, cancelling damage", true);
                        // sender.sendMessage(message);
                    }
                }
            }
        }
    }
    
}
