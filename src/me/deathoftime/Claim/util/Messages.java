package me.deathoftime.Claim.util;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * Created by Ahmet on 22.2.2017.
 */


public class Messages {

    static SettingsManager manager = SettingsManager.getInstance();


    public static String PREFIX;
    public static String CLAIM_REDDEDILDI;
    public static String CHUNKTA_YETKILI_DEGILSINIZ;
    public static String EKLE_KULLANIM;
    public static String CLAIM_BULUNAMADI;
    public static String KENDINI_EKLIYEMEZSIN;
    public static String KULLANMA_YETKINIZ_YOK;
    public static String OPMOD_KAPALI;
    public static String BASARILI;
    public static String YETERSIZ_BAKIYE;
    public static String OYUNCU_BULUNAMADI;
    public static String LUTFEN_CLAIM_OLUSTURUN;
    public static String HATA;


    public static void load() {

        try {
            PREFIX = ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("PREFIX"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Prefix yüklenirken bir sorun oluştu !");
            return;
        }

        try {
            CLAIM_REDDEDILDI = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("CLAIM_REDDEDILDI"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "2 yüklenirken bir sorun oluştu !");
            return;
        }

        try {
            CHUNKTA_YETKILI_DEGILSINIZ = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("CHUNKTA_YETKILI_DEGILSINIZ"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "3 bir sorun oluştu !");
            return;
        }

        try {
            EKLE_KULLANIM = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("EKLE_KULLANIM"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "4 bir sorun oluştu !");
            return;
        }

        try {
            CLAIM_BULUNAMADI = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("CLAIM_BULUNAMADI"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "5 bir sorun oluştu !");
            return;
        }

        try {
            KENDINI_EKLIYEMEZSIN = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("KENDINI_EKLIYEMEZSIN"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "6 bir sorun oluştu !");
            return;
        }

        try {
            LUTFEN_CLAIM_OLUSTURUN = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("LUTFEN_CLAIM_OLUSTURUN"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "7 bir sorun oluştu !");
            return;
        }

        try {
            OPMOD_KAPALI = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("OPMOD_KAPAT"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "8 bir sorun oluştu !");
            return;
        }

        try {
            BASARILI = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("BASARILI"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "9 yüklenirken bir sorun oluştu !");
            return;
        }

        try {
            YETERSIZ_BAKIYE = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("YETERSIZ_BAKIYE"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "10 yüklenirken bir sorun oluştu !");
            return;
        }

        try {
            OYUNCU_BULUNAMADI = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("OYUNCU_BULUNAMADI"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "11 yüklenirken bir sorun oluştu !");
            return;
        }

        try {
            HATA = PREFIX + ChatColor.translateAlternateColorCodes('&', manager.getConfig().getString("HATA"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "12 yüklenirken bir sorun oluştu !");
            return;
        }


    }
}





