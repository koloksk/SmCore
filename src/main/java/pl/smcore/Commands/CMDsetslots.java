package pl.smcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;

public class CMDsetslots implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("sm.mod")) {
            if (args.length == 1) {
                Bukkit.setMaxPlayers(Integer.parseInt(args[0]));
                p.sendMessage("Ustawiono sloty na " + args[0]);
                Properties properties = new Properties();
                File propertiesFile = new File("server.properties");
                try {
                    InputStream is = new FileInputStream(propertiesFile);
                    try {
                        properties.load(is);
                        is.close();
                    } catch (Throwable throwable) {
                        try {
                            is.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                        throw throwable;
                    }
                    String maxPlayers = Integer.toString(Bukkit.getServer().getMaxPlayers());
                    if (properties.getProperty("max-players").equals(maxPlayers))
                        return false;
                    Bukkit.getLogger().info("Saving max players to server.properties...");
                    properties.setProperty("max-players", maxPlayers);
                    OutputStream os = new FileOutputStream(propertiesFile);
                    try {
                        properties.store(os, "Minecraft server properties");
                        os.close();
                    } catch (Throwable throwable) {
                        try {
                            os.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                        throw throwable;
                    }
                    p.sendMessage("Zapisano zmiany w server.properties");
                } catch (IOException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Error while saving max players in server properties", e);
                }


            } else {
                p.sendMessage("Poprawne uzycie /setslots <ilosc>");
            }
        }


        return true;
    }
}

