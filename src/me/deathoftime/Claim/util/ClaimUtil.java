package me.deathoftime.Claim.util;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Ahmet on 23.2.2017.
 */
public class ClaimUtil {

    static int height;
    public static ArrayList<Chunk> chunks = new ArrayList<>();
    private static Chunk chunk;
    private static Player p;

    public static void showChunkBorders(Chunk x, Player p, byte data) {
        ClaimUtil.p = p;
        height = p.getLocation().getBlockY() + 2;
        chunks.add(x);

        for (int i = 0; i < 16; i++) {

            Block b1 = x.getBlock(i, height, 0);
            Block b2 = x.getBlock(0, height, i);
            Block b3 = x.getBlock(15, height, i);
            Block b4 = x.getBlock(i, height, 15);

            p.sendBlockChange(b1.getLocation(), Material.WOOL, data);
            p.sendBlockChange(b2.getLocation(), Material.WOOL, data);
            p.sendBlockChange(b3.getLocation(), Material.WOOL, data);
            p.sendBlockChange(b4.getLocation(), Material.WOOL, data);

        }


    }

    public static void stopChunkBorders(Chunk x) {


        for (int i = 0; i < 16; i++) {
            Block b1 = x.getBlock(i, height, 0);
            Block b2 = x.getBlock(0, height, i);
            Block b3 = x.getBlock(15, height, i);
            Block b4 = x.getBlock(i, height, 15);

            b1.getState().update();
            b2.getState().update();
            b3.getState().update();
            b4.getState().update();


        }
    }

    public static void stopChunksBorder(ArrayList<Chunk> chunks) {


        for (Chunk x : chunks) {
            for (int i = 0; i < 16; i++) {
                Block b1 = x.getBlock(i, height, 0);
                Block b2 = x.getBlock(0, height, i);
                Block b3 = x.getBlock(15, height, i);
                Block b4 = x.getBlock(i, height, 15);

                b1.getState().update();
                b2.getState().update();
                b3.getState().update();
                b4.getState().update();


            }

        }

    }


    public static void infoTask(Player p) {

        ArrayList<Chunk> closeChunks = getClaimsClose(p);
        int height = p.getLocation().getBlockY() + 1;

        for (Chunk x : closeChunks) {

            if (FileUtil.isClaimed(x)) {
                Integer claim_id = FileUtil.getClaimIDChunk(x);
                if (claim_id == null) {
                    continue;
                }

                String name = FileUtil.getPlayerByChunk(x);
                Location loc = x.getBlock(5, height, 5).getLocation();
                ClaimUtil.showChunkBorders(x, p, (byte) 14);
                new Hologram(loc, name, claim_id);


            }

        }

    }

    private static ArrayList<Chunk> getClaimsClose(Player p) {

        ArrayList<Chunk> claimedChunks = new ArrayList<>();
        Chunk origChunk = p.getLocation().getChunk();

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                chunk = p.getWorld().getChunkAt(origChunk.getX() + x, origChunk.getZ() + z);
                if (FileUtil.isClaimed(chunk)) {

                    claimedChunks.add(chunk);


                }
            }
        }
        return claimedChunks;
    }


}