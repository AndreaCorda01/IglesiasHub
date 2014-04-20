/*    */ package me.Creeper96.IglesiasHub;
/*    */ 
/*    */ import java.util.ArrayList;
import java.util.List;

import org.bukkit.Server;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class Main extends JavaPlugin
/*    */ {
/*    */   public static Plugin plugin;
/*    */   public static Server server;
/* 15 */   public static List<IglesiasHubItem> items = new ArrayList();
/*    */ 
/*    */   public void onEnable() {
/* 18 */     plugin = this;
/* 19 */     server = getServer();
/* 20 */     plugin.saveDefaultConfig();
/* 21 */     for (int c = 1; c <= 9; ++c)
/*    */     {
/* 23 */       if(plugin.getConfig().isSet(""+c))
/* 24 */        {
					items.add(new IglesiasHubItem(c));
				}	
			}
/*    */     
/* 26 */     server.getPluginManager().registerEvents(new Events(), this);
/*    */   }
/*    */ 
/*    */   public void onDisable() {
/* 30 */     plugin = this;
/* 31 */     server = getServer();
/*    */   }
/*    */ }