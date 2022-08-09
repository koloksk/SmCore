package pl.smcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class CMDpanel implements CommandExecutor, Listener {
    Inventory inv = Bukkit.getServer().createInventory(null, 27, "Panel");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        if (cmd.getName().equals("panel")) {
            newInventory(p);
        }
        return true;
    }

    public void newInventory(Player player) {
        inv.clear();
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
        if(e.isCancelled()){return;}
        Player p = (Player) e.getWhoClicked();
        Inventory open = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();

        if (open == null) {

            return;
        }
        if (e.getView().getTitle().equals("Panel")) {
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
}
