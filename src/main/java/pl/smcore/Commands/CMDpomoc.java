package pl.smcore.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.smcore.smcore;

public class CMDpomoc implements CommandExecutor {
    smcore plugin = smcore.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Player only command
        if (sender instanceof Player) {
            Player p = (Player) sender;
            for(String pomoc : plugin.getConfig().getStringList("pomoc"))
            {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', pomoc));
            }
        }
        return true;
    }
}
