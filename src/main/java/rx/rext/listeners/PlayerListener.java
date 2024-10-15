package rx.rext.listeners;

import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import rx.rext.PeSy;
import rx.rext.data.DataManager;
import rx.rext.data.playerData;

public class PlayerListener implements Listener {
    private final PeSy plugin;

    public PlayerListener(PeSy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String uuid = event.getPlayer().getUniqueId().toString();
        if (!playerData.getKeys().contains(uuid)) {
            FileConfiguration playerDataFile = playerData.getDataFile();
            playerDataFile.set(uuid + ".stats.exhaust", 0);
            playerDataFile.set(uuid + ".stats.endurance", 0);
        }
    }
    /*
     * @EventHandler
     * public void onChat(AsyncPlayerChatEvent event){
     * Player player = event.getPlayer();
     * String message = event.getMessage();
     * 
     * if(message.toLowerCase().contains("aternos")){
     * event.setCancelled(true);
     * player.sendMessage(msgUtils.text("No escribas cosas feas", true));
     * }
     * }
     * 
     * 
     * 
     * @EventHandler
     * public void onBlockBreak(BlockBreakEvent event){
     * Player player = event.getPlayer();
     * // if player.hasPermissions("miplugin.BreakBLocks")
     * player.sendMessage(msgUtils.text(plugin.getMainConfigManager().getBlockBreak(
     * ),false).replace("%player%", player.getName()));
     * }
     */

    // limitar la vida al 30% -ejemplo
    @EventHandler
    public void regeneravida(EntityRegainHealthEvent event) {
        // I dont mind
        if (event.getEntity() instanceof Player player) {
            String uuid = player.getUniqueId().toString();
            if (playerData.hasPerk("iDontMind", uuid)) {
                int level = playerData.data.getInt(uuid+".perks.iDontMind.level");
                double healthMax = DataManager.perks.getDoubleList("iDontMind.healthMax").get(level-1);
                int health = (int) Math.round(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * healthMax);
                if ((player.getHealth()+event.getAmount()) >= health) {
                    event.setCancelled(true);
                    player.setHealth(health);
                    player.setSaturation(player.getSaturation() + 0.4f);
                }
            }
        }
    }

    // evitar que muera
    @EventHandler
    public void damageEntity(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            String uuid = player.getUniqueId().toString();
            if (playerData.hasPerk("iDontMind", uuid)) {
                if (player.getHealth() <= event.getDamage()) {
                    int level = playerData.data.getInt(uuid+".perks.iDontMind.level");
                    double healthMax = DataManager.perks.getDoubleList("iDontMind.healthMax").get(level-1);
                    int health = (int) Math.round(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * healthMax);
                    event.setCancelled(true);
                    int ticks = DataManager.perks.getInt("iDontMind.time");
                    PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, ticks, 1);
                    player.setHealth(health);
                    player.addPotionEffect(effect);
                    player.setNoDamageTicks(ticks);
                }
            }
        }
    }

    @EventHandler
    public void respawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        if (playerData.hasPerk("iDontMind", uuid)) {
            int level = playerData.data.getInt(uuid+".perks.iDontMind.level");
            double healthMax = DataManager.perks.getDoubleList("iDontMind.healthMax").get(level-1);
            new BukkitRunnable() {
                @Override
                public void run(){
                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * healthMax);
                }
            }.runTaskLater(plugin, 5);
        }
    }

}
