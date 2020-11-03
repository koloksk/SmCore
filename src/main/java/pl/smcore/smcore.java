package pl.smcore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.Collections;

public final class smcore extends JavaPlugin implements Listener {
    private static smcore instance;

    int a;
    int deleted;
    String sm = "§4§lSM §7";
    Inventory inv = Bukkit.getServer().createInventory(null, 27, "Panel");
    public static smcore getInstance(){
        return instance;
    }
    @Override
    public void onEnable() {
        a = 0;
        Bukkit.getPluginManager().registerEvents(this, this);
        BukkitScheduler scheduler = getServer().getScheduler();
        this.getCommand("panel").setExecutor(this);
        this.getCommand("podpis").setExecutor(this);
        this.getCommand("rtp").setExecutor(new rtp(this));
        this.getCommand("dajrange").setExecutor(this);
        loadConfig();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (a >= 5) {
                    clearitems();
                    Bukkit.broadcastMessage(sm + "usunieto " + deleted + " itemów!");
                    deleted = 0;
                    a = 0;
                } else if (a == 4) {
                    Bukkit.broadcastMessage(sm + "Usuwanie itemow za 1 minute");
                    a++;
                } else {
                    a++;
                }

            }
        }, 40L, 60 * 20L);
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @EventHandler
    public void blockmobs(EntitySpawnEvent e) {
        if (e.getEntityType() == EntityType.BAT) {
            e.setCancelled(true);
        }
    }

    public void clearitems() {
        for (World w : Bukkit.getWorlds()) {
            for (Entity e : w.getEntities()) {
                if (e.getType() != EntityType.EGG && (e.getType() == EntityType.DROPPED_ITEM || e.getType() == EntityType.BOAT || e.getType() == EntityType.ARROW || e.getType() == EntityType.MINECART || e.getType() == EntityType.EXPERIENCE_ORB)) {
                    e.remove();
                    deleted++;
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equals("podpis")) {
            if (p.hasPermission("sm.svip")) {
                ItemStack item = p.getInventory().getItemInMainHand();

                if (item.getType() != Material.AIR && item.getType() != Material.NAME_TAG) {
                    ItemMeta im = item.getItemMeta();
                    im.setDisplayName("§6Podpis " + p.getDisplayName());
                    item.setItemMeta(im);
                } else {
                    p.sendMessage(sm + "musisz trzymac item w rece!");
                }

            } else {
                p.sendMessage(sm + "Nie masz permisi!");
            }
        } else if (cmd.getName().equals("panel")) {
            newInventory(p);
        } else if (cmd.getName().equals("dajrange")){
            if (p.hasPermission("sm.mod")) {
                if (args.length == 2) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user "+args[0]+ " parent addtemp "+args[1]+" 30d");

                } else {
                    p.sendMessage("poprawne uzycie /dajrange nick ranga");
                }
            } else {
                p.sendMessage(sm + "Nie masz permisi!");
            }


        }
            return true;
    }
    @EventHandler
    public void playerleverplace(BlockPlaceEvent e){
        if(e.getBlock().getType() == Material.LEVER){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void pjoinevent(PlayerJoinEvent e) {

        e.setJoinMessage(null);
        Border border = new Border(new Vector(1572.5,0,-816.5), new Vector(1589.5,255,-836.5));
        if(border.contains(e.getPlayer().getLocation())) {
            e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
        }
    }

    @EventHandler
    public void pquitmessage(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    public void newInventory(Player player) {

        inv.setItem(13, createitem(Material.OAK_SAPLING, "§b§lSpawn", "§7Teleportuje na spawn"));
        inv.setItem(15, createitem(Material.DIAMOND, "§b§lSklep", "§7Otwiera sklep"));
        inv.setItem(22, createitem(Material.NAME_TAG, "§b§lWarpy Graczy", "§7Warpy Graczy"));
        inv.setItem(11, createitem(Material.BUCKET, "§b§lKosz", "§7Kosz"));
        inv.setItem(4, createitem(Material.PAPER, "§b§lAukcje", "§7Aukcje"));

        player.openInventory(inv);
    }

    public ItemStack createitem(Material m, String name, String lore) {
        ItemStack i = new ItemStack(m);
        ItemMeta imeta = i.getItemMeta();
        imeta.setDisplayName(name);
        imeta.setLore(Collections.singletonList(lore));
        i.setItemMeta(imeta);
        return i;

    }

    @EventHandler
    public void InvenClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        Inventory open = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        if (open == null) {
            return;
        }
        if (open == inv) {

            e.setCancelled(true);

            if (item == null || !item.hasItemMeta()) {
                return;
            }

            if (e.getSlot() == 13) {
                p.performCommand("spawn");
            } else if (e.getSlot() == 15) {
                p.performCommand("sklep");
            } else if (e.getSlot() == 22) {
                p.performCommand("pwarp");
            } else if (e.getSlot() == 11) {
                p.performCommand("trash");
            } else if (e.getSlot() == 4) {
                p.performCommand("ah");
            }
        }

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

}
