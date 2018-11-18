package me.deathoftime.Claim.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

/**
 * Created by Ahmet on 27.2.2017.
 */
public class ArmorStandManipulateListener implements Listener {

    @EventHandler
    public void onArmorStandManipulate(PlayerArmorStandManipulateEvent e){

        if(!e.getRightClicked().isVisible()){

            e.setCancelled(true);
        }


    }

}
