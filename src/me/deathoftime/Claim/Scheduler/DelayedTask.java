package me.deathoftime.Claim.Scheduler;

import me.deathoftime.Claim.Claim;
import me.deathoftime.Claim.Commands.Commands;
import me.deathoftime.Claim.Listeners.InventoryClick;
import me.deathoftime.Claim.util.ClaimUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ahmet on 23.2.2017.
 */
public class DelayedTask extends BukkitRunnable {



    @Override
    public void run(){
       if(!Commands.stopTask) {
           Claim.state = false;
           InventoryClick.getPlayer().sendMessage(ChatColor.RED + "Zamanınız doldu");
           InventoryClick.getPlayer().closeInventory();
           ClaimUtil.stopChunkBorders(InventoryClick.chunk);
           Commands.stopTask = false;
       }
    }

}
