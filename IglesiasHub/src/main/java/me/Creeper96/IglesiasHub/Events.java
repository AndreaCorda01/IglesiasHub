package me.Creeper96.IglesiasHub;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener
 {
	
	
	public void piazzaBlocco(BlockPlaceEvent event)
	{
		 for (IglesiasHubItem i : Main.items)
	     {    	
	      if (event.getPlayer().getItemInHand().equals(i.item))
	         {
	    	  	event.setCancelled(true);
	         }
	     }
	}
	
	
@EventHandler
public void onInteract(PlayerInteractEntityEvent event)
{	
	   if(event.getPlayer().getItemInHand().getType() == Material.NAME_TAG)
	   {
		   event.setCancelled(true);
	   }
}
  @EventHandler
  public void onClickItem(PlayerInteractEvent event)
   {
   if ((event.getAction() != Action.RIGHT_CLICK_AIR) && (event.getAction() != Action.LEFT_CLICK_AIR) && (event.getAction() != Action.LEFT_CLICK_BLOCK) && (event.getAction() != Action.LEFT_CLICK_AIR))
      return;
   int c = 0;
    for (IglesiasHubItem i : Main.items)
     {
    	
    	
      if (event.getPlayer().getItemInHand().equals(i.item))
         {
    	  event.setCancelled(true);

		    if( event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
		    {
		    	if(i.destra != null)
		    	{
		    		i.destra.crea(event.getPlayer(),0);
		    		IglesiasHubItem IH = new IglesiasHubItem(i.position,"");
		    		IH = i.destra;
		    		IH.destra = i;
		    		IH.sinistra = i.sinistra;
		    		Main.items.set(c, IH);
		    	}
		    }
		    else
			if( event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
			{
				if(i.sinistra != null)
				{
		    		i.sinistra.crea(event.getPlayer(),0);
		    		IglesiasHubItem IH = new IglesiasHubItem(i.position,"");
		    		IH = i.sinistra;
		    		IH.sinistra = i;
		    		IH.destra = i.destra;
		    		Main.items.set(c, IH);		
				}
			}
       		i.click(event.getPlayer());
			
			return;
         }
      c++;
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