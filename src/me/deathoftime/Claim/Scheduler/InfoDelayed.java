package me.deathoftime.Claim.Scheduler;

import me.deathoftime.Claim.Commands.Commands;
import me.deathoftime.Claim.util.ClaimUtil;
import me.deathoftime.Claim.util.Hologram;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ahmet on 26.2.2017.
 */
public class InfoDelayed extends BukkitRunnable {

    public void run(){

            ClaimUtil.stopChunksBorder(ClaimUtil.chunks);
            Commands.canInfo = true;
                Hologram.destroy();

    }

}

