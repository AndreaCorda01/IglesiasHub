package me.Creeper96.IglesiasHub;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
  public static Plugin plugin;
  public static Server server;
  public static List<IglesiasHubItem> items = new ArrayList();
  public static Points PlayerPoints;
  
  public void onEnable() {
     plugin = this;
    server = getServer();
    plugin.saveDefaultConfig();
    PlayerPoints = new Points();
    for (int c = 1; c <= 9; ++c)
    {
      if(plugin.getConfig().isSet(""+c))
      {
					items.add(new IglesiasHubItem(c));
				}	
			}
    
     server.getPluginManager().registerEvents(new Events(), this);
     server.getPluginManager().registerEvents(PlayerPoints, this);
   }

   public void onDisable() {
    plugin = this;
    server = getServer();
  }
 }