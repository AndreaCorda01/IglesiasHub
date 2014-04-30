package me.Creeper96.IglesiasHub;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events
  implements Listener
 {
  @EventHandler
  public void onClickItem(PlayerInteractEvent event)
   {
   if ((event.getAction() != Action.RIGHT_CLICK_AIR) && (event.getAction() != Action.LEFT_CLICK_AIR) && (event.getAction() != Action.LEFT_CLICK_BLOCK) && (event.getAction() != Action.LEFT_CLICK_AIR))
      return;
    for (IglesiasHubItem i : Main.items)
     {
      if (event.getPlayer().getItemInHand().equals(i.item))
         {
       		i.click(event.getPlayer());
					event.setCancelled(true);
					return;
				}
     }
   }

		@EventHandler
		public void onJoin(PlayerJoinEvent event)
		{
			event.getPlayer().getInventory().clear();
			for(IglesiasHubItem item : Main.items)
			{
				item.crea(event.getPlayer(),0);
			}
		}
		
		@EventHandler
		public void onRespawn(PlayerRespawnEvent event)
		{
			event.getPlayer().getInventory().clear();
			for(IglesiasHubItem item : Main.items)
			{
				item.crea(event.getPlayer(),0);
			}
		}
		
		@EventHandler
		public void noDrop(PlayerDropItemEvent event)
		{
			if(event.getPlayer().isOp() || event.getPlayer().hasPermission("IglesiasHub.bypass"))
			{
				return;
			}
			else
			{
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Non puoi buttare oggetti");
			}
		}

		
		public void noDrop(PlayerPickupItemEvent event)
		{
			if(event.getPlayer().isOp() || event.getPlayer().hasPermission("IglesiasHub.bypass"))
			{
				return;
			}
			else
			{
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Non puoi raccogliere oggetti");
				event.getItem().remove();
			}
		}

}