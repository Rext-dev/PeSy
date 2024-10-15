package rx.rext.utils;

import org.bukkit.configuration.file.FileConfiguration;

import net.md_5.bungee.api.ChatColor;

// https://mcutils.com/color-text-generator
public class msgUtils {
    public static String PREFIX = "&9[&6RextP&9]";
    public static FileConfiguration messages;


    public static String text(String text, boolean prefix){
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX);
        sb.append("&f");
        sb.append(text);
        return ChatColor.translateAlternateColorCodes('&', sb.toString());
    }

    public static void setPREFIX(String pREFIX) {
        PREFIX = pREFIX;
    }

    public static void setMessages(FileConfiguration messages) {
        msgUtils.messages = messages;
    }
    public static String getText(String path){
        return messages.getString(path);
    }
}
