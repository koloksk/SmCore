/*
package pl.smcore.Utils;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.smcore.smcore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class AutoMobDisable implements Listener {
    private final String name = Bukkit.getServer().getClass().getPackage().getName();
    private final String version = name.substring(name.lastIndexOf('.') + 1);
    private Object serverInstance;
    private Field tpsField;
    boolean MobsOn;

    public AutoMobDisable(smcore smcore) {
        try {
            serverInstance = getNMSClass("MinecraftServer").getMethod("getServer").invoke(null);
            tpsField = serverInstance.getClass().getField("recentTps");
        } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        new BukkitRunnable() {
            public void run() {
                if(Bukkit.getOnlinePlayers().size() < 35) {
                    if(getTPS(0) < 19.2){
                        for(World w: Bukkit.getWorlds() ){
                            w.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                        }
                    } else {
                        for(World w: Bukkit.getWorlds() ){
                            w.setGameRule(GameRule.DO_MOB_SPAWNING, true);
                        }
                    }
                } else {
                    for(World w: Bukkit.getWorlds() ){
                        w.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                    }
                }
            }
        }.runTaskTimerAsynchronously(smcore, 20L, 20); //.runTaskTimer(smcore, 0L, 20*10L);
    }
    private Class<?> getNMSClass(String className) {
        try {
            return Class.forName("net.minecraft.server." + version + "." + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public double getTPS(int time) {
        try {
            double[] tps = ((double[]) tpsField.get(serverInstance));
            return tps[time];
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @EventHandler
    public void entitySpawnEvent(CreatureSpawnEvent e) {
        if(e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL || e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.DEFAULT && !MobsOn){
            e.setCancelled(true);
        }
    }

}

 */
