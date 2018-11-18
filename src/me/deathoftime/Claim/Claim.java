package me.deathoftime.Claim;

import me.deathoftime.Claim.Commands.Commands;
import me.deathoftime.Claim.Listeners.ArmorStandManipulateListener;
import me.deathoftime.Claim.Listeners.InteractListener;
import me.deathoftime.Claim.Listeners.InventoryClick;
import me.deathoftime.Claim.Scheduler.RemovingRunnable;
import me.deathoftime.Claim.util.Messages;
import me.deathoftime.Claim.util.SettingsManager;
import me.deathoftime.Kredi.Kredi;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ahmet on 18.2.2017.
 */
public class Claim extends JavaPlugin {

    public static SettingsManager manager = SettingsManager.getInstance();
   public static int claims;
    public static Economy economy = null;
    public static boolean state = false;
    static Plugin p;

    public void onEnable(){
        p = this;
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Claim Plugini by"+ChatColor.YELLOW+" DeathOfTime"+ChatColor.GREEN+"  aktif !");
        manager.setup(this);
        claims = manager.getData().getInt("claim_sayisi");
        if(!(setupEconomy())){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[HATA] Ekonomi Başlatlamadı lütfen Vaultun kurulu olduguna emin olunuz. ");
            return;

        }
        registerComamands();
        registerListener();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this,new RemovingRunnable(),20,20*20);
        Messages.load();

    }

    public static SettingsManager getManager(){
        return manager;

    }

    public void registerListener(){
        Bukkit.getPluginManager().registerEvents(new InteractListener(),this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(),this);
        Bukkit.getPluginManager().registerEvents(new ArmorStandManipulateListener(),this);

    }

    public void registerComamands(){
        Bukkit.getPluginCommand("claim").setExecutor(new Commands());

    }

    private boolean setupEconomy(){

        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public static Economy getEconomy(){
        return economy;
    }

    public static Plugin getPlugin(){
        return p;
    }

    public static Kredi getKredi(){

        return (Kredi) Bukkit.getServer().getPluginManager().getPlugin("Kredi");

    }

}
