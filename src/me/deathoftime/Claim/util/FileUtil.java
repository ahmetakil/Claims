package me.deathoftime.Claim.util;


import me.deathoftime.Claim.Claim;
import me.deathoftime.Claim.Commands.Commands;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ahmet on 18.2.2017.
 */
public class FileUtil {

    private static SettingsManager manager = Claim.getManager();
    private static int ca = Claim.claims;
    //DAKIKA CINSINDEN TICK


    private static Date date = new Date();
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static Calendar cal = Calendar.getInstance();


    public static void addNewClaim(Player p, boolean isKredi) {

        if (isClaimed(p.getLocation().getChunk())) {
            p.sendMessage(ChatColor.RED + "Bu chunk zaten claimlenmiş");
            return;
        }

        cal.setTime(date);
        if (isKredi) {
            cal.add(Calendar.DAY_OF_MONTH, 90);

        } else if (!isKredi) {
            cal.add(Calendar.DAY_OF_MONTH, 30);
        }
        cal.add(Calendar.HOUR, 1);

        String time = dateFormat.format(cal.getTime());
        ca += 1;
        manager.getData().set("claim_sayisi", ca);
        manager.getData().set("claim" + ca + ".sahip", p.getUniqueId().toString());
        manager.getData().set("claim" + ca + ".sahip_name", p.getName());
        manager.getData().createSection("claim" + ca + ".location");
        manager.getData().set("claim" + ca + ".location.X", p.getLocation().getChunk().getX());
        manager.getData().set("claim" + ca + ".location.Z", p.getLocation().getChunk().getZ());
        manager.getData().set("claim" + ca + ".time", time);
        manager.saveData();
        p.sendMessage(ChatColor.YELLOW + "Bulundugunuz chunk başarıyla claimlenmiştir !");
        Claim.claims = ca;

    }

    private static String getPlayerByChunk(Chunk x) {

        for (int i = 0; i <= ca; i++) {
            if (manager.getData().isConfigurationSection("claim" + i)) {
                if (manager.getData().getInt("claim" + i + ".location.X") == x.getX() &&
                        manager.getData().getInt("claim" + i + ".location.Z") == x.getZ()) {

                    return manager.getData().getString("claim" + i + ".sahip_name");


                }


            }

        }

        return null;
    }


    public static boolean checkWorld(Player p) {

        String world = manager.getConfig().getString("WORLD");

        String pwrold = p.getWorld().getName();
        if (p.getWorld() == null) {

            return true;
        }

        return (world.equalsIgnoreCase(pwrold));


    }


    public static boolean hasCharge(Player p) {

        Chunk chunk = p.getLocation().getChunk();
        if (!FileUtil.isClaimed(chunk)) {
            return false;
        }

        for (int i = 0; i <= ca; i++) {
            if (manager.getData().isConfigurationSection("claim" + i + ".location")) {
                if (manager.getData().getInt("claim" + i + ".location.X") == chunk.getX() &&
                        manager.getData().getInt("claim" + i + ".location.Z") == chunk.getZ()) {
                    if (manager.getData().getString("claim" + i + ".sahip").equalsIgnoreCase(p.getUniqueId().toString())) {
                        return true;

                    } else if (manager.getData().isInt("claim" + i + ".yetkili_sayisi")) {
                        int amount = manager.getData().getInt("claim" + i + ".yetkili_sayisi");
                        for (int k = 0; k <= amount; k++) {
                            if (manager.getData().isConfigurationSection("claim" + i + ".yetkili" + k)) {
                                if (manager.getData().getString("claim" + i + ".yetkili" + k + ".uuid").equalsIgnoreCase(p.getUniqueId().toString())) {
                                    return true;
                                }
                            }
                        }
                    }

                }
            }

        }
        return false;
    }


    public static boolean canInteract(Player p, Block b) {

        if (Commands.opmod.contains(p)) {
            return true;
        }

        Chunk chunk = b.getChunk();
        if (!isClaimed(b.getChunk())) {
            return true;
        }


        for (int i = 0; i <= ca; i++) {
            if (manager.getData().isConfigurationSection("claim" + i + ".location")) {
                if (manager.getData().getInt("claim" + i + ".location.X") == chunk.getX() &&
                        manager.getData().getInt("claim" + i + ".location.Z") == chunk.getZ()) {
                    if (manager.getData().getString("claim" + i + ".sahip").equalsIgnoreCase(p.getUniqueId().toString())) {
                        return true;


                    } else if (manager.getData().isInt("claim" + i + ".yetkili_sayisi")) {
                        int amount = manager.getData().getInt("claim" + i + ".yetkili_sayisi");
                        for (int k = 0; k <= amount; k++) {
                            if (manager.getData().isConfigurationSection("claim" + i + ".yetkili" + k)) {
                                if (manager.getData().getString("claim" + i + ".yetkili" + k + ".uuid").equalsIgnoreCase(p.getUniqueId().toString())) {
                                    return true;
                                }
                            }
                        }
                    }

                }
            }

        }
        return false;
    }


    public static String getTime(Chunk x) {

        for (int i = 0; i <= ca; i++) {

            if (manager.getData().isConfigurationSection("claim" + i)) {

                if (manager.getData().getInt("claim" + i + ".location.X") == x.getX() &&
                        manager.getData().getInt("claim" + i + ".location.Z") == x.getZ()) {

                    return manager.getData().getString("claim" + i + ".time");

                }

            }


        }
        return null;
    }

    public static boolean isClaimed(Chunk chunk) {

        for (int i = 0; i <= ca; i++) {
            if (manager.getData().isConfigurationSection("claim" + i)) {

                if (manager.getData().getInt("claim" + i + ".location.X") == chunk.getX()
                        && manager.getData().getInt("claim" + i + ".location.Z") == chunk.getZ()) {
                    return true;

                }

            }

        }
        return false;
    }

    public static Integer getClaimNumber(Player p) {

        for (int i = 0; i <= ca; i++) {

            if (manager.getData().isConfigurationSection("claim" + i)) {
                String uuid = manager.getData().getString("claim" + i + ".sahip");

                if (p.getUniqueId().toString().equalsIgnoreCase(uuid)) {
                    if (manager.getData().getInt("claim" + i + ".location.X") == p.getLocation().getChunk().getX()
                            && manager.getData().getInt("claim" + i + ".location.Z") == p.getLocation().getChunk().getZ()) {


                        return i;
                    }

                }
                if (manager.getData().isInt("claim" + i + ".yetkili_sayisi")) {
                    int c = manager.getData().getInt("claim" + i + ".yetkili_sayisi");
                    for (int k = 0; k <= c; k++) {

                        if (manager.getData().isString("claim" + i + ".yetkili" + k + ".uuid")) {


                            if (manager.getData().getString("claim" + i + ".yetkili" + k + ".uuid").equalsIgnoreCase(p.getUniqueId().toString())) {

                                return i;
                            }

                        }

                    }

                }
            }


        }


        return null;
    }

    public static Integer getClaimID(String playerName, Player target) {

        Chunk x = target.getLocation().getChunk();

        for (int i = 0; i <= ca; i++) {

            if (manager.getData().isConfigurationSection("claim" + i)) {
                String name = manager.getData().getString("claim" + i + ".sahip_name");
                int state = i;
                if (playerName.equalsIgnoreCase(name)) {
                    if (manager.getData().getInt("claim" + state + ".location.X") == x.getX()
                            && manager.getData().getInt("claim" + state + ".location.Z") == x.getZ()) {

                        return state;

                    }
                }


            }


        }
        return null;
    }

    public static boolean hasClaim(Player p) {

        int cn = 0;
        try {
            cn = getClaimNumber(p);
        } catch (NullPointerException e) {
            p.sendMessage(ChatColor.RED + "Bir sorun oldu !.");
            return false;
        }
        return manager.getData().isConfigurationSection("claim" + cn);

    }

    private static Integer getClaimIDChunk(Chunk x) {

        int chunkX = x.getX();
        int chunkZ = x.getZ();

        if (!isClaimed(x)) return null;

        for (int i = 0; i <= Claim.claims; i++) {
            if (manager.getData().isConfigurationSection("claim" + i)) {
                if (manager.getData().getInt("claim" + i + ".location.X") == chunkX
                        && manager.getData().getInt("claim" + i + ".location.Z") == chunkZ) {

                    return i;

                }

            }


        }
        return null;

    }

    public static void removeClaim(int id) {

        if (manager.getData().isConfigurationSection("claim" + id)) {
            manager.getData().set("claim" + id, null);
            manager.saveData();

        }

    }

    public static Integer getKredi(String name){

       if(Claim.getKredi().getSettings().getData().isInt(name)) {

           return (Integer) Claim.getKredi().getSettings().getData().get(name);

       }
         return null;
    }

}
