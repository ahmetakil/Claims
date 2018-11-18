package me.deathoftime.Claim.Scheduler;

import me.deathoftime.Claim.Claim;
import me.deathoftime.Claim.util.FileUtil;
import me.deathoftime.Claim.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ahmet on 21.2.2017.
 */
public class RemovingRunnable extends BukkitRunnable {

    SettingsManager manager = SettingsManager.getInstance();


    @Override
    public void run() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        for(int i = 0; i<= Claim.claims; i++){
            if(manager.getData().isConfigurationSection("claim"+i)){
                if(manager.getData().isString("claim"+i+".time")){
                    String f = dateFormat.format(date);
                    if(manager.getData().getString("claim"+i+".time").equalsIgnoreCase(f)){

                        String name = manager.getData().getString("claim"+i+".sahip_name");
                        Bukkit.broadcastMessage(ChatColor.RED.toString()+name+" isimli oyuncunun "+i+" nolu arsasının claimi sona ermiştir !");
                        FileUtil.removeClaim(i);
                        Claim.claims -= 1;
                        manager.getData().set("claim_sayisi",Claim.claims);
                        manager.saveData();
                    }

                }

            }

        }
    }
}
