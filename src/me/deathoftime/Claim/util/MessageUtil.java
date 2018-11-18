package me.deathoftime.Claim.util;

/**
 * Created by Ahmet on 20.3.2017.
 */
public class MessageUtil {


    static SettingsManager manager = SettingsManager.getInstance();

    public static void addDefault(){

        manager.getConfig().set("PREFIX", "&7[ &2&lS&c&lO &7]");
        manager.getConfig().set("CLAIM_REDDEDILDI","&2Claim Reddedildi");
        manager.getConfig().set("CHUNKTA_YETKILI_DEGILSINIZ","&2Bulundugunuz chunkta yetkili degilsiniz !");
        manager.getConfig().set("EKLE_KULLANIM", "&2/claim ekle <isim>");
        manager.getConfig().set("CLAIM_BULUNAMADI", "&2Claiminiz Bulunamadi");
        manager.getConfig().set("KENDINI_EKLIYEMEZSIN", "&4Kendinizi Claime Ekliyemezsiniz");
        manager.getConfig().set("KULLANMA_YETKINIZ_YOK", "&4Bu komutu kullanma yetkiniz yok");
        manager.getConfig().set("OPMOD_KAPAT", "&2Tekrar /claim opmod yazarak kapatabilirsiniz !");
        manager.getConfig().set("BASARILI", "&2Basarili!");
        manager.getConfig().set("YETERSIZ_BAKIYE", "&2Yetersiz bakiye");
        manager.getConfig().set("CLAIM_SONA_ERME","&2Claiminiz %tarih% tarihinde sona ericektir.");
        manager.getConfig().set("OYUNCU_BULUNAMADI","&2Oyuncu bulunamadi !");
        manager.getConfig().set("LUTFEN_CLAIM_OLUSTURUN","&2Lutfen Claim Olusturun !");

        manager.getConfig().set("HATA", "&4Hata");
        manager.saveConfig();


    }

}
