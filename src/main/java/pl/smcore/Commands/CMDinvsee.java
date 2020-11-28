/*
package pl.smcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDinvsee implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("sm.invsee")) {
                if (args.length == 1) {
                    Player t = p.getServer().getPlayer(args[0]);
                    if (t != null) {
                        p.openInventory(t.getInventory());
                        p.sendMessage("Inventory of " + t.getName() + " opened");
                    } else {
                        p.sendMessage("Target not found");
                    }
                } else {
                    p.sendMessage("/invsee [name]");
                }
            } else{
                //wiadomosc nie masz permissi
            }


        }
        return false;
    }
}



 */