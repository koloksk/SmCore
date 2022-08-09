package pl.smcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.smcore.smcore;

public class CMDsmcore implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1 && args[0].equals("reload") && sender.hasPermission("*")){
            smcore.getInstance().reloadConfig();
            sender.sendMessage(smcore.getInstance().prefix + "Pomyslnie przeladowano.");
        }
        return true;
    }
}
