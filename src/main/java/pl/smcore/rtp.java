package pl.smcore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class rtp implements CommandExecutor {

    private final CooldownManager cooldownManager = new CooldownManager();

    private final Plugin plugin;

    public rtp(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Player only command
        if (sender instanceof Player) {
            Player p = (Player) sender;
            int timeLeft = cooldownManager.getCooldown(p.getUniqueId());
            //If the cooldown has expired
            if (timeLeft == 0) {
                //Use the feature
                p.sendMessage(ChatColor.GREEN + "teleportacja!");
                p.performCommand("tpr");
                //Start the countdown task
                cooldownManager.setCooldown(p.getUniqueId(), 15);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        int timeLeft = cooldownManager.getCooldown(p.getUniqueId());
                        cooldownManager.setCooldown(p.getUniqueId(), --timeLeft);
                        if (timeLeft == 0) {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(this.plugin, 20, 20 * 60);

            } else {
                //Hasn't expired yet, shows how many seconds left until it does
                p.sendMessage(ChatColor.RED.toString() + "Odczekaj " + timeLeft + " minut przed nastepna teleportacja.");
            }
        } else {
            sender.sendMessage("Player-only command");
        }

        return true;
    }
}
