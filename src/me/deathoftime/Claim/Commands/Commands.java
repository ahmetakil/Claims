package me.deathoftime.Claim.Commands;

import me.deathoftime.Claim.Claim;
import me.deathoftime.Claim.Listeners.InventoryClick;
import me.deathoftime.Claim.Scheduler.InfoDelayed;
import me.deathoftime.Claim.util.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ahmet on 18.2.2017.
 */
public class Commands implements CommandExecutor {


    SettingsManager manager = Claim.getManager();
    static int number = 0;
    OfflinePlayer op;
    static int amount = 0;
    public static boolean canInfo = true;
    static int n = 0;
    static Date date = new Date();
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    static Calendar cal = Calendar.getInstance();
    static Player p;
    public static boolean stopTask = false;
    public static ArrayList<Player> opmod = new ArrayList<>();
    public static boolean opMode = false;
    Economy econ = Claim.getEconomy();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player p = (Player) sender;

        //TODO CONFIG DESTEGI
        if (command.getName().equalsIgnoreCase("claim")) {

            if (args.length == 0) {
                p.sendMessage(ChatColor.GREEN + "/claim menü > "+ChatColor.YELLOW+"claim ekranını açar");
                p.sendMessage(ChatColor.GREEN+" /claim ekle <isim> > "+ChatColor.YELLOW+" claime okuyuncu ekle");
                p.sendMessage(ChatColor.GREEN+"/claim kaldır <isim>  > "+ChatColor.YELLOW+" claimden oyuncu çıkar");
                p.sendMessage(ChatColor.GREEN+"/claim info > "+ChatColor.YELLOW+" yakındaki claimleri gör");
                p.sendMessage(ChatColor.GREEN+"/claim sorgu > "+ChatColor.YELLOW+" Ne kadar zamanın kaldıgını öğren !");
                if(p.isOp()){
                 p.sendMessage(ChatColor.GREEN+"/claim opmod >" +ChatColor.YELLOW+" Claim OP Modu aç !");
                }
                return true;


            }

            //CLAIMLEME
            if (args[0].equalsIgnoreCase("menü")) {

                if(FileUtil.checkWorld(p)) {
                    new Gui();
                    p.openInventory(Gui.getInventory());
                    return true;
                }
                p.sendMessage(ChatColor.RED+"Yanlış Dünya");
                return true;


            }
            else if(args[0].equalsIgnoreCase("onayla")){
                if(Claim.state){
                   if(InventoryClick.withMoney){
                       //PARAYLA
                       econ.withdrawPlayer(p,15000);
                    FileUtil.addNewClaim(p,false);
                    ClaimUtil.stopChunkBorders(InventoryClick.chunk);
                    stopTask = true;
                    return true;
                   }
                   else{
                       //KREDI
                      Integer kredi;
                       try{
                           kredi =  FileUtil.getKredi(p.getName());
                      }catch(NullPointerException e){
                          p.sendMessage(Messages.HATA);
                          return true;
                      }

                      Claim.getKredi().getSettings().getData().set(p.getName(),kredi-InventoryClick.kredi_price);
                      Claim.getKredi().getSettings().saveData();


                   }
                    FileUtil.addNewClaim(p,true);
                    ClaimUtil.stopChunkBorders(InventoryClick.chunk);

                    stopTask = true;
                    return true;

                }
                p.sendMessage(ChatColor.RED+"Mevcut claim istediğiniz bulunmamaktadır !.");
                return true;

            }
            else if(args[0].equalsIgnoreCase("reddet")){
                    if(Claim.state){
                        p.sendMessage(Messages.CLAIM_REDDEDILDI);
                        Claim.state = false;
                        ClaimUtil.stopChunkBorders(InventoryClick.chunk);
                        stopTask = true;

                    }




                return true;
            }
            //INFO
            else if(args[0].equalsIgnoreCase("info")){

                if(canInfo) {
                canInfo = false;
                    ClaimUtil.infoTask(p);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Claim.getPlugin(), new InfoDelayed(), 20 * 10);
                    return true;
                }
                return true;
            }

            //SORGU
            else if(args[0].equalsIgnoreCase("sorgu")) {

            if(!FileUtil.hasCharge(p)){

              p.sendMessage(Messages.CHUNKTA_YETKILI_DEGILSINIZ);
            return true;

              }
                String tarih = FileUtil.getTime(p.getLocation().getChunk());
            //TODO TARIH MESAEJI
                p.sendMessage(ChatColor.GREEN+"Claiminiz "+tarih+" tarihinde sona ericektir ! ");
                return true;

            }

            //CLAIME OYUNCU EKLEME
            else if (args[0].equalsIgnoreCase("ekle")) {

                if (args.length == 1) {
                    p.sendMessage(Messages.EKLE_KULLANIM);
                    return true;
                }

                if (!FileUtil.hasClaim(p)) {
                    p.sendMessage(Messages.EKLE_KULLANIM);
                    return true;
                }
                Player target = Bukkit.getServer().getPlayer(args[1]);
                if (target == null) {
                    p.sendMessage(Messages.OYUNCU_BULUNAMADI);
                    return true;
                }

                if (!target.isOnline()) {

                    p.sendMessage(Messages.OYUNCU_BULUNAMADI);
                    return true;
                }
                try {
                    number = FileUtil.getClaimNumber(p);
                } catch (NullPointerException e) {

                    p.sendMessage(Messages.LUTFEN_CLAIM_OLUSTURUN);
                    return true;

                }

                try {
                    amount = manager.getData().getInt("claim" + number + ".yetkili_sayisi");
                } catch (Exception e) {
                    Bukkit.broadcastMessage("H");
                    return true;
                }
                if(p.getName().equalsIgnoreCase(args[1])){
                    p.sendMessage(Messages.KENDINI_EKLIYEMEZSIN);
                    return true;
                }

                for (int k = 0; k <= amount; k++) {
                    if (manager.getData().isString("claim" + number + ".yetkili" + k)) {
                        String uuid = manager.getData().getString("claim" + number + ".yetkili" + k);
                        if (target.getUniqueId().toString().equalsIgnoreCase(uuid)) {
                            //TODO ZATEN EKLI
                            p.sendMessage(ChatColor.RED.toString() + target.getName() + " isimli oyuncu zaten claime ekli");
                            return true;

                        }


                    }


                }
                amount += 1;
                manager.getData().set("claim" + number + ".yetkili_sayisi", amount);
                manager.getData().set("claim"+number+".yetkili"+amount+".name",target.getName());
                manager.getData().set("claim" + number + ".yetkili" + amount+".uuid", target.getUniqueId().toString());
                manager.saveData();
                p.sendMessage(ChatColor.YELLOW + "Başarılı !");
                return true;


                //KALDIRMA KISMI////

            } else if (args[0].equalsIgnoreCase("kaldır")) {

                if (args.length == 1) {
                    p.sendMessage(ChatColor.RED + "/claim kaldir <isim>");
                    return true;
                }

                if (!FileUtil.hasClaim(p)) {
                    p.sendMessage(Messages.LUTFEN_CLAIM_OLUSTURUN);
                    return true;
                }
                String name = args[1];

               try{
                    n = FileUtil.getClaimNumber(p);
               }catch(NullPointerException e){
                   p.sendMessage(Messages.LUTFEN_CLAIM_OLUSTURUN);
                   return true;
               }

                if (manager.getData().isInt("claim" + n + ".yetkili_sayisi")) {

                    int amount = manager.getData().getInt("claim" + n + ".yetkili_sayisi");
                    for (int k = 0; k <= amount; k++) {
                        if (manager.getData().isString("claim" + n + ".yetkili" + k+".name")) {
                            if (manager.getData().getString("claim" + n + ".yetkili" + k+".name").equalsIgnoreCase(name)) {

                                manager.getData().set("claim" + n + ".yetkili"+k, null);
                                manager.saveData();
                                //TODO BAŞARIYLA KALDIRILDI
                                p.sendMessage(ChatColor.GREEN.toString() + args[1] + " isimli oyuncu başarıyla kaldırıldı");

                                return true;


                            }
                        }
                    }
                    p.sendMessage(ChatColor.RED.toString()+args[1]+" isimli oyuncu bulunamadı !");
                    return true;
                }
            }

            //OP MOD
            else if(args[0].equalsIgnoreCase("opmod")){
                if(opMode){
                    opMode = false;
                    opmod.remove(p);
                    p.sendMessage(Messages.OPMOD_KAPALI);
                    return true;
                }
                else if(!p.isOp()){
                    p.sendMessage(Messages.KULLANMA_YETKINIZ_YOK);
                    return true;
                }
                opmod.add(p);
                opMode = true;
                p.sendMessage(Messages.BASARILI);
                return true;


            }

            else {
                p.sendMessage(Messages.HATA);
                return true;
            }
            return true;
        }
        return true;
    }
}