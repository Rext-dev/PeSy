package rx.rext.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityListeners implements Listener{

    @EventHandler
    public void mitosis(EntityDeathEvent event){
        if(event.getEntity() instanceof Monster){
            LivingEntity entidad = event.getEntity();
            Random random = new Random();
            double probability = 0.05;
            double value = random.nextDouble();
            if (value <= probability) {
                // Duplicar el monstruo
                LivingEntity newEntity = (LivingEntity) entidad.getWorld().spawnEntity(entidad.getLocation(), entidad.getType());

                // Duplicar las estadísticas (vida y daño)
                double originalHealth = entidad.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                double originalDamage = entidad.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();

                newEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(originalHealth * 2);
                newEntity.setHealth(originalHealth * 2);
                newEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(originalDamage * 2);
            }
        }
    }

    @EventHandler
    public void onWitherSpawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Wither wither) {

            // Duplicar las estadísticas del Wither (vida y daño)
            double originalHealth = wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            double originalDamage = wither.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();

            wither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(originalHealth * 2);
            wither.setHealth(originalHealth * 2);
            wither.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(originalDamage * 2);

            // Añadir 3 rosas de Wither al loot
            ItemStack witherRoses = new ItemStack(Material.WITHER_ROSE, 3);
            wither.getWorld().dropItemNaturally(wither.getLocation(), witherRoses);
        }
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof WitherSkull) {
            // Duplicar el daño causado por la cabeza de Wither
            double originalDamage = event.getDamage();
            event.setDamage(originalDamage * 2);
        }
    }
}
