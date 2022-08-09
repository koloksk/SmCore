package pl.smcore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.smcore.Commands.*;
//import pl.smcore.Utils.AutoMobDisable;

//import pl.smcore.Commands.CMDfly;
//import pl.smcore.Commands.CMDinvsee;
//import pl.smcore.Commands.CMDsetslots;

public final class smcore extends JavaPlugin implements Listener {
    private static smcore instance;
    int a;
    int deleted;
    FileConfiguration config = getConfig();
    public String prefix;


    public static smcore getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        a = 0;
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new CMDpanel(), this);
      //  Bukkit.getPluginManager().registerEvents(new AutoMobDisable(this), this);

        this.getCommand("panel").setExecutor(new CMDpanel());
        this.getCommand("podpis").setExecutor(new CMDpodpis());
        //this.getCommand("rtp").setExecutor(new CMDrtp(this));
        //this.getCommand("dajrange").setExecutor(this);
        this.getCommand("pomoc").setExecutor(new CMDpomoc());
        this.getCommand("smcore").setExecutor(new CMDsmcore());

     //~~   new AutoMobDisable(this);

        //this.getCommand("setslots").setExecutor(new CMDsetslots());
        //this.getCommand("invsee").setExecutor(new CMDinvsee());
        //this.getCommand("fly").setExecutor(new CMDfly());

        loadConfig();
        new BukkitRunnable() {
            public void run() {
                    if (a >= 5) {
                        clearitems();
                        Bukkit.broadcastMessage(prefix + "usunieto " + deleted + " itemów!");
                        deleted = 0;
                        a = 0;
                    } else if (a == 4) {
                        Bukkit.broadcastMessage(prefix + "Usuwanie itemow za 1 minute");
                        a++;
                    } else {
                        a++;
                    }

                }
        }.runTaskTimerAsynchronously(this, 40L, 20*60L);
    }

    public void loadConfig() {
        config.options().copyDefaults(true);
        saveConfig();
        prefix = ChatColor.translateAlternateColorCodes('&', getInstance().getConfig().getString("prefix"));
    }


    public void clearitems() {
        for (World w : Bukkit.getWorlds()) {
            for (Entity e : w.getEntities()) {
                if (e.getType() != EntityType.EGG && (e.getType() == EntityType.DROPPED_ITEM || e.getType() == EntityType.ARROW || e.getType() == EntityType.SILVERFISH || e.getType() == EntityType.EXPERIENCE_ORB)) {
                    e.remove();
                    deleted++;
                }
            }
        }
    }

/*    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        if (cmd.getName().equals("dajrange")) {
            if (p.hasPermission("sm.mod")) {
                if (args.length == 2) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + args[0] + " parent addtemp " + args[1] + " 30d");
                } else {
                    p.sendMessage("poprawne uzycie /dajrange nick ranga");
                }
            } else {
                p.sendMessage(prefix + "Nie masz permisi!");
            }
        }
        return true;
    }*/






}
