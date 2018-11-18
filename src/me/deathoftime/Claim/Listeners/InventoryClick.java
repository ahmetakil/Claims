package me.deathoftime.Claim.Listeners;

import me.deathoftime.Claim.Claim;
import me.deathoftime.Claim.Scheduler.DelayedTask;
import me.deathoftime.Claim.util.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ahmet on 23.2.2017.
 */
public class InventoryClick implements Listener {

    Economy econ = Claim.getEconomy();
    static Player p;
    public static Chunk chunk;
    SettingsManager manager = SettingsManager.getInstance();
    public static boolean withMoney;
    public static int kredi_price = 50;


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){



        if(!e.getInventory().getName().equalsIgnoreCase("Claim Menü")){
            return;
        }
        if(e.getCurrentItem() == null){
            return;
        }
        if(!e.getCurrentItem().hasItemMeta()){
            return;
        }
        if(e.getCurrentItem().getItemMeta().hasLore()){
            e.setCancelled(true);
            p = (Player) e.getWhoClicked();
            chunk = p.getLocation().getChunk();

            //LIRA ILE CLAIM ALMA
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Gui.getItem(0).getItemMeta().getDisplayName())){


                if(FileUtil.isClaimed(p.getLocation().getChunk())){
                    p.sendMessage(ChatColor.RED+"Bu chunk zaten claimlenmiş !");
                    return;

                }
                Double bal = econ.getBalance(p);
                int price = 15_000;

                if(bal>=price){
                    p.sendMessage(ChatColor.GREEN+"30 saniye süre içinde /claim onayla veya /claim reddet yazın !");
                    Claim.state = true;
                    p.closeInventory();
                    ClaimUtil.showChunkBorders(chunk,p,(byte) 5);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Claim.getPlugin(),new DelayedTask(),10*20L);
                    withMoney = true;

                    return;

                }else{
                    p.sendMessage(Messages.YETERSIZ_BAKIYE);
                    p.closeInventory();
                    return;
                }

            }
            //boolean parayla true> para false> kredi

            //KREDİ İLE

            if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Gui.getItem(1).getItemMeta().getDisplayName())){


                if(FileUtil.isClaimed(chunk)){
                    p.sendMessage(ChatColor.RED+"Bu chunk zaten claimlenmiş !");
                    return;

                }
                int kredi;
                try{
                    kredi = FileUtil.getKredi(p.getName());
                }catch(NullPointerException exception){
                    p.sendMessage("Bir sorun oluştu!");
                    return;
                }


                if(kredi>=kredi_price){
                    withMoney = false;
                    //TODO ONAYLA REDDET MESAJI
                    p.sendMessage(ChatColor.GREEN+"30 saniye süre içinde /claim onayla veya /claim reddet yazın !");
                    Claim.state = true;
                    p.closeInventory();
                    chunk =  p.getLocation().getChunk();
                    ClaimUtil.showChunkBorders(chunk,p,(byte) 5);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Claim.getPlugin(),new DelayedTask(),10*20L);


                    return;

                }else{
                    p.sendMessage(Messages.YETERSIZ_BAKIYE);
                    p.closeInventory();
                    return;
                }

            }


            //ZAMAN UZATMA
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Gui.getItem(2).getItemMeta().getDisplayName())){


                if(!FileUtil.hasClaim(p)) return;
                 int number = -1;
                Date date = null;

                try{
                      number = FileUtil.getClaimNumber(p);
                }catch(NullPointerException exception){
                    exception.printStackTrace();
                }
                if(manager.getData().isConfigurationSection("claim"+number)){
            String time = manager.getData().getString("claim"+number+".time");
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    try {
                         date = format.parse(time);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);

                    cal.add(Calendar.DAY_OF_MONTH,10);
                    String finalTime = format.format(cal.getTime());
                    p.sendMessage(ChatColor.GREEN+"10 Gün zaman eklenmiştir !.");
                    econ.withdrawPlayer(p,1000);
                    manager.getData().set("claim"+number+".time",finalTime);
                    manager.saveData();

                }




            }



        }

    }

    public static Player getPlayer(){

        return p;

    }
}
