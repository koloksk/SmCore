package pl.smcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CMDchat implements CommandExecutor, Listener {
    boolean chat = true;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       if (sender.hasPermission("sm.chat")) {
           if (args.length == 1) {
               if(args[0].equals("c") || args[0].equals("clear")){
                   for (Player p : Bukkit.getOnlinePlayers()) {
                       if (!p.hasPermission("clearchat.clearchat.bypass"))
                           clearchat(p);
                   }
               } else if(args[0].equals("off")){
                    chat = false;
               }

           }
       } else {
           sender.sendMessage("nie masz permissi");
       }
       return true;
    }
    public void clearchat(Player p){
        for (int i = 0; i < 100; i++)
            p.sendMessage("");
    }

    @EventHandler
    public void playerchatevent(AsyncPlayerChatEvent e){
        if(!chat){
            if(!e.getPlayer().hasPermission("sm.bypass") || !e.getPlayer().isOp()){
                e.setCancelled(true);
            }
        }
    }
}