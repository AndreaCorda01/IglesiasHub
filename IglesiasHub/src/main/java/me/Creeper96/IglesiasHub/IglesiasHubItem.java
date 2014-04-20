/*    */ package me.Creeper96.IglesiasHub;
/*    */ 
/*    */ import java.util.List;

import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class IglesiasHubItem
/*    */   implements Listener
/*    */ {
/*    */   public int position;
/*    */   public String id;
/*    */   public int data;
/*    */   public int amount;
/*    */   public String name;
/*    */   public List<String> lore;
/*    */   public String command;
/*    */   public ItemStack item;
/*    */ 
/*    */   public IglesiasHubItem(int pos)
/*    */   {
			
/* 30 */     this.position = pos;
//System.out.println(pos + ".id");
/* 31 */     this.id = Main.plugin.getConfig().getString(pos + ".id");
/* 32 */     this.data = Main.plugin.getConfig().getInt(pos + ".data", 1);
/* 33 */     this.amount = Main.plugin.getConfig().getInt(pos + ".amount", 1);
/* 34 */     this.name = Main.plugin.getConfig().getString(pos + ".name");
/* 35 */     this.lore = Main.plugin.getConfig().getStringList(pos + ".lore");
/* 36 */     this.command = Main.plugin.getConfig().getString(pos + ".command");
//System.out.println(this.name+ "-" + this.command);
			this.name = ChatColor.translateAlternateColorCodes('&', this.name);
			
		    for (int c = 0; c < this.lore.size() - 1; ++c)
		    {
		      this.lore.set(c, ChatColor.translateAlternateColorCodes('&',this.lore.get(c)));
		   }
		    
/*    */   }
/*    */ 
/*    */   public void crea(Player player)
/*    */   {
				//System.out.println(position+"-"+this.id+"-"+this.amount);
				//System.out.println(Main.plugin.getConfig().getString("1.id"));
/* 42 */     this.item = new ItemStack(Material.getMaterial(id.toUpperCase()),amount);

/* 43 */     ItemMeta im = this.item.getItemMeta();
				//System.out.println("nome"+this.name);
/* 44 */     im.setDisplayName(this.name);
/* 49 */     im.setLore(this.lore);
/* 50 */     this.item.setAmount(this.amount);
/* 51 */     this.item.setItemMeta(im);
/* 52 */     this.item.setDurability((short)this.data);
			//System.out.println(item+""+this.position);
/* 53 */     player.getInventory().setItem(this.position-1, this.item);
/*    */   }
/*    */ 
/*    */   public void click(Player player)
/*    */   {
			//System.out.println(player.getName() + "-"+ this.command);
/* 59 */     Main.server.dispatchCommand(player, this.command);
/*    */   }
/*    */ }