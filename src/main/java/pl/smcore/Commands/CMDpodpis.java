package pl.smcore.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.smcore.smcore;

public class CMDpodpis implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = smcore.getInstance().prefix;
        Player p = (Player) sender;
        if (p.hasPermission("sm.svip")) {
            ItemStack item = p.getInventory().getItemInMainHand();

            if (item.getType() != Material.AIR && item.getType() != Material.NAME_TAG) {
                if(item.getAmount() == 1) {
                    ItemMeta im = item.getItemMeta();
                    im.setDisplayName("§6Podpis " + p.getDisplayName());
                    item.setItemMeta(im);
                } else {
                    p.sendMessage(prefix + "możesz podpisać tylko jeden przedmiot na raz!");

                }
            } else {
                p.sendMessage(prefix + "musisz trzymac item w rece!");
            }

        } else {
            p.sendMessage(prefix + "Nie masz permisi!");
        }
        return true;

    }
}
