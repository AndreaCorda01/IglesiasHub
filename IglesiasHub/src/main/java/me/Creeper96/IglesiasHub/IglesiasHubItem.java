package me.Creeper96.IglesiasHub;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.PlayerInventory;
 import org.bukkit.inventory.meta.ItemMeta;
 import org.bukkit.plugin.Plugin;
 
public class IglesiasHubItem
  implements Listener
{
  public int position;
  public String id;
   public int data;
  public int amount;
  public String name;
  public List<String> lore;
  public String command;
  public ItemStack item;
  public boolean playerPoints = false;
  
  public IglesiasHubItem destra;
  public IglesiasHubItem sinistra;

  public IglesiasHubItem(int pos, String azione)
 {
			
	this.position = pos;
   if(azione == "")
   {
    this.id = Main.plugin.getConfig().getString(pos + ".id");
    this.data = Main.plugin.getConfig().getInt(pos + ".data", 1);
    this.amount = Main.plugin.getConfig().getInt(pos + ".amount", 1);
    this.name = Main.plugin.getConfig().getString(pos + ".name");
    this.lore = Main.plugin.getConfig().getStringList(pos + ".lore");
    this.command = Main.plugin.getConfig().getString(pos + ".command");
   }
   else
   if(azione == "destra"){
	    this.id = Main.plugin.getConfig().getString(pos + ".destra.id");
	    this.data = Main.plugin.getConfig().getInt(pos + ".destra.data", 1);
	    this.amount = Main.plugin.getConfig().getInt(pos + ".destra.amount", 1);
	    this.name = Main.plugin.getConfig().getString(pos + ".destra.name");
	    this.lore = Main.plugin.getConfig().getStringList(pos + ".destra.lore");
	    this.command = Main.plugin.getConfig().getString(pos + ".destra.command");
   }
   else
	   if(azione == "sinistra"){
		    this.id = Main.plugin.getConfig().getString(pos + ".sinistra.id");
		    this.data = Main.plugin.getConfig().getInt(pos + ".sinistra.data", 1);
		    this.amount = Main.plugin.getConfig().getInt(pos + ".sinistra.amount", 1);
		    this.name = Main.plugin.getConfig().getString(pos + ".sinistra.name");
		    this.lore = Main.plugin.getConfig().getStringList(pos + ".sinistra.lore");
		    this.command = Main.plugin.getConfig().getString(pos + ".sinistra.command");		   		   
	   }
    

		    
   }
 
   public void crea(Player player, int diff)
   {
    this.item = new ItemStack(Material.getMaterial(id.toUpperCase()),amount);
    ItemMeta im = this.item.getItemMeta();
	this.name = ChatColor.translateAlternateColorCodes('&', this.name); 
    im.setDisplayName(sostituisciGettoni(this.name, player, diff));
    for ( int c = 0; c <= this.lore.size()-1; c++)
    {
       this.lore.set(c,ChatColor.translateAlternateColorCodes('&',this.lore.get(c)));
    }
    im.setLore(this.lore);    
    for (int c = 0; c <= im.getLore().size() - 1; c++)
    {
      im.getLore().set(c, sostituisciGettoni(im.getLore().get(c), player,diff));    
    }
    
    this.item.setAmount(this.amount);
     this.item.setItemMeta(im);
    this.item.setDurability((short)this.data);
     player.getInventory().setItem(this.position-1, this.item);
   }
 
   
   
   public void click(Player player)
   {
			//System.out.println(player.getName() + "-"+ this.command);
	   if(this.command != "" && this.command != null)
	   {
	   Main.server.dispatchCommand(player, this.command);
	   }
  }
   
   
   public String sostituisciGettoni(String str, Player player, int diff)
   {
	   
	   if(str.contains("%points%"))
	   {
		   this.playerPoints = true;
		   String gettoni = "";
		   gettoni += ((Main.PlayerPoints.getPoints(player.getName())+diff)+"");		   
		   //System.out.println("sostituito"+gettoni);
		   str = str.replaceAll("%points%", gettoni);
	   }

	   //System.out.println(Main.PlayerPoints.getPoints(player.getName()) + "----"+str);
	   return str;
   }
 }