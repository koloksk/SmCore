package pl.smcore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
/*import pl.smcore.Utils.Border;
import pl.smcore.Utils.CooldownManager;*/

public class Events implements Listener {
/*    private final CooldownManager cooldownManager = new CooldownManager();


    @EventHandler
    public void pjoinevent(PlayerJoinEvent e) {

        e.setJoinMessage(null);
        Border border = new Border(new Vector(1572.5, 0, -816.5), new Vector(1589.5, 255, -836.5));
        Border border2 = new Border(new Vector(1532.5, 0, -814.5), new Vector(1531.5, 255, -802.5));
        if (border.contains(e.getPlayer().getLocation()) || border2.contains(e.getPlayer().getLocation())) {
            e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
        }
    }*/

    @EventHandler
    public void pquitmessage(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityPortal(EntityPortalEvent event) {
        event.setSearchRadius(15);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerProtal(PlayerPortalEvent event) {
        if (event.getTo().getWorld().getEnvironment() == World.Environment.NETHER) {
            event.setSearchRadius(2);
            event.setCreationRadius(2);
        } else if (event.getTo().getWorld().getEnvironment() == World.Environment.NORMAL && event.getFrom().getWorld().getEnvironment() == World.Environment.NETHER) {
            event.setSearchRadius(16);
            event.setCreationRadius(16);
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void blockmobs(EntitySpawnEvent e) {
        if (e.getEntityType() == EntityType.BAT || e.getEntityType() == EntityType.PHANTOM) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void playerportal(EntityPortalEvent e) {
        if(e.getEntityType() == EntityType.BOAT){
            e.setCancelled(true);
        }
    }


/*    @EventHandler
    public void playerleverplace(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getClickedBlock() != null) {
            if (e.getClickedBlock().getType() == Material.LEVER) {
                int timeLeft = cooldownManager.getCooldown(p.getUniqueId());
                //If the cooldown has expired
                if (timeLeft == 0) {
                    //Use the feature
                    //Start the countdown task
                    cooldownManager.setCooldown(p.getUniqueId(), 2);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            int timeLeft = cooldownManager.getCooldown(p.getUniqueId());
                            cooldownManager.setCooldown(p.getUniqueId(), --timeLeft);
                            if (timeLeft == 0) {
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(smcore.getInstance(), 20, 20);

                } else {
                    //Hasn't expired yet, shows how many seconds left until it does
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED.toString() + "Odczekaj " + timeLeft + " sekund zanim użyjesz dźwigni.");
                }
            }
        }

    }*/
}
