package me.deathoftime.Claim.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

/**
 * Created by Ahmet on 26.2.2017.
 */
public class Hologram {

    private Location loc;
    private String name;
    private int claim_id;
    private static ArmorStand physicalEntity;
    private static ArrayList<ArmorStand> holograms = new ArrayList<>();



    private Hologram(Location loc,String name,int claim_id){
        this.claim_id = claim_id;
        this.loc = loc;
        this.name = name;
        physicalEntity = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        holograms.add(physicalEntity);
        physicalEntity.setVisible(false);
        physicalEntity.setVisible(false);
        physicalEntity.setGravity(false);
        physicalEntity.setCustomNameVisible(true);
        physicalEntity.setBasePlate(false);
        physicalEntity.setCanPickupItems(false);

        physicalEntity.setCustomName(ChatColor.YELLOW+"Claim Sahibi : "+ChatColor.WHITE+name+ChatColor.YELLOW+" Claim ID : "
                +ChatColor.WHITE+claim_id);

    }

    public static void destroy() {
        for (ArmorStand x : holograms) {

            x.remove();
        }

    }

}
