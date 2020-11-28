/*
package pl.smcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDfly implements CommandExecutor {
//dodac wiadomosci
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("sm.fly")){
                if(args.length == 0) {
                    p.setAllowFlight(!p.getAllowFlight());
                } else if(args.length == 1) {
                    Player a = Bukkit.getPlayer(args[0]);
                    assert a != null;
                    a.setAllowFlight(!p.getAllowFlight());

                }
                //  tutaj wiadomosc
            }
        }
        return  false;
    }
}
*/