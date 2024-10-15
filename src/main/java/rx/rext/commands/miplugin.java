package rx.rext.commands;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rx.rext.PeSy;
import rx.rext.data.playerData;
import rx.rext.managers.perkManager;
import rx.rext.utils.msgUtils;

public class miplugin implements CommandExecutor {
    private PeSy plugin;

    public miplugin(PeSy plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if(sender instanceof Player player){
            sender.sendMessage(msgUtils.text("Hola player, this command is working",true));
            // /miplugin args[0]...
            if(args.length >= 1){
                if(args[0].equalsIgnoreCase("bienvenido")){
                    // /cmd bienvenido
                    sender.sendMessage(msgUtils.text("Hola "+ player.getName(),false));
                } else if(args[0].equalsIgnoreCase("get")){
                    // /cmd get autor/version
                    getcmd(sender,args);
                } else if(args[0].equalsIgnoreCase("reload")){
                    subCommandReload(sender);
                } else if(args[0].equalsIgnoreCase("test")){
                    test(sender, player);
                } else if(args[0].equalsIgnoreCase("save")){
                    plugin.getMainConfigManager().save();
                }   
                
                else{
                    help(sender);
                }
            }
        }
        return true;
    }

    public void help(CommandSender sender){
        sender.sendMessage(msgUtils.text("&8----------------------------------",false));

    }

    public void getcmd(CommandSender sender, String[] args){
        if(args.length == 1){
            sender.sendMessage(msgUtils.text("&4A ocurrido un error al usar este comando, procura usar &2/cmd help",true));
            return;
        }
        if(args[1].equalsIgnoreCase("autor")){
            sender.sendMessage(msgUtils.text("Los autores son" + plugin.getDescription().getAuthors(),false));
        }if(args[1].equalsIgnoreCase("version")){
            sender.sendMessage(msgUtils.text("La version es" + plugin.getDescription().getVersion(),false));
        }
    }

    public void subCommandReload(CommandSender sender){
        if (!sender.hasPermission("miplugin.commands.reload")){
            sender.sendMessage(msgUtils.text("&4A ocurrido un error al usar este comando, procura usar &2/cmd help",true));
        } else{
            plugin.getMainConfigManager();
        }
    }

    public void test(CommandSender sender, Player player){
        String uuid = player.getUniqueId().toString();
        String perk = "iDontMind";
        Map<String,Object> dataplayer = playerData.getPerk(uuid, "iDontMind");
        if((boolean) dataplayer.get("hasPerk")){
            int level = (int) dataplayer.get("level");
            player.sendMessage("your level is "+ level);
            if(level==3){
                perkManager.removeLevelPerk(uuid, perk);
                perkManager.removeLevelPerk(uuid, perk);
                perkManager.removeLevelPerk(uuid, perk);
                player.sendMessage("you have: "+playerData.hasPerk(perk, uuid));

            } else{
                perkManager.addLevelPerk(uuid, perk);
                dataplayer = playerData.getPerk(uuid, "iDontMind");
                level = (int) dataplayer.get("level");
                player.sendMessage("your level is "+ level);
            }
        }else{
            player.sendMessage("You haven this perk");
            perkManager.addPerk(uuid, perk);
            if(playerData.hasPerk(perk, uuid)){
                player.sendMessage("You have this perk");
            } else{
                player.sendMessage("youhavent this perk");
            }
        }
    }
    
}

/*
 * public void test(CommandSender sender, Player player){
        World world = player.getWorld();
        Location location = player.getLocation();
        double radio = 30.0;
        player.sendMessage(msgUtils.text("Mundo " + world.getName(), true));
        int villagerCount = 0;
        //get villagers
        for (Entity entity : world.getNearbyEntities(location, radio, radio, radio)){
            if (entity instanceof Villager villager){
                PotionEffect effect = new PotionEffect(PotionEffectType.REGENERATION, 100, 1);
                villager.addPotionEffect(effect);
                villagerCount++;
            }
        }
        player.sendMessage(msgUtils.text("Hay "+ villagerCount, true));
    }

    player.sendMessage(
            msgUtils.text(
                "Effects: " + player.getActivePotionEffects() +
                " MaxHealt: " + player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()
            , true)
        );
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
 */
