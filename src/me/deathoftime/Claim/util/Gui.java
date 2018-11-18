package me.deathoftime.Claim.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Gui {

    static Inventory inv;

    public Gui() {

        inv = Bukkit.createInventory(null, 9, "Claim Menü");


        ArrayList<String> lore = new ArrayList<>(Arrays.asList(ChatColor.GRAY +
                        " Bulundugun chunka claim atar",
                ChatColor.GRAY + " Claim boyutu 16x16 dır. ",
                ChatColor.GRAY + " Claim atılıcak alanın etrafı yeşil yünle çizilir",
                "  ",
                ChatColor.GOLD + "Claim Fiyatı: " + ChatColor.WHITE + "15000 Lira",
                ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "Claim atmak için tıkla"));

        ArrayList<String> solore = new ArrayList<>(Arrays.asList(ChatColor.GRAY +
                        " Bulundugun chunka claim atar",
                ChatColor.GRAY + " Claim boyutu 16x16 dır. ",
                ChatColor.GRAY + " Claim atılıcak alanın etrafı yeşil yünle çizilir",
                "  ",
                ChatColor.GOLD + "Claim Fiyatı: " + ChatColor.WHITE + "50Kredi",
                ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "Claim atmak için tıkla"));

        ArrayList<String> zaman = new ArrayList<>(Arrays.asList(ChatColor.GRAY +
                        " Claimi 1 ay daha uzat",
                "  ",
                ChatColor.GOLD + "Claim Fiyatı: " + ChatColor.WHITE + "1000 Lira",
                ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "Claimi uzatmak için tıkla"));


        addItem(Material.PAPER, ChatColor.AQUA + "Lira ile claim at", lore, 0);
        addItem(Material.PAPER, ChatColor.AQUA + "So para ile claim at", solore, 1);
        addItem(Material.WATCH, ChatColor.GOLD + "Claim süresini uzat", zaman, 2);


    }


    public void addItem(Material m, String name, List<String> lore, int slot) {

        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(slot, item);

    }

    public static Inventory getInventory() {

        return inv;
    }

    public static ItemStack getItem(int slot) {

        return inv.getItem(slot);
    }
}
