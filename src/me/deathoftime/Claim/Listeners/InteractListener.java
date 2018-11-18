package me.deathoftime.Claim.Listeners;

import me.deathoftime.Claim.util.FileUtil;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Ahmet on 18.2.2017.
 */
public class InteractListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Block block = e.getBlock();
        Player p = e.getPlayer();
        Chunk chunk = block.getChunk();

        if(!FileUtil.canInteract(p,block)) {
            e.setCancelled(true);
            return;


        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        if(e.getAction() == Action.LEFT_CLICK_AIR){
            return;
        }
        Block block = e.getClickedBlock();
        if(block == null){
            return;
        }
        Player p = e.getPlayer();

        if(!FileUtil.canInteract(p,block)) {
            e.setCancelled(true);
            return;


        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Block block = e.getBlock();
        Player p = e.getPlayer();
        Chunk chunk = block.getChunk();

        if(!FileUtil.canInteract(p,block)) {
            e.setCancelled(true);
            return;


        }
    }
}
