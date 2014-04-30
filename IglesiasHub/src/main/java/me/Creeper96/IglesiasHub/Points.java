package me.Creeper96.IglesiasHub;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.event.PlayerPointsChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class Points implements Listener{

private PlayerPoints playerPoints;

/**
 * Validate that we have access to PlayerPoints
 *
 * @return True if we have PlayerPoints, else false.
 */
private boolean hookPlayerPoints() {
    final Plugin plugin = Main.server.getPluginManager().getPlugin("PlayerPoints");
    playerPoints = PlayerPoints.class.cast(plugin);
    return playerPoints != null; 
}
	
	
/**
 * Accessor for other parts of your plugin to retrieve PlayerPoints.
 *
 * @return PlayerPoints plugin instance
 */
public PlayerPoints getPlayerPoints() {
    return playerPoints;
}	
	

public Points()
{
	hookPlayerPoints();
}


public int getPoints(String player)
{
	int gettoni = 0;
	gettoni = playerPoints.getAPI().look(player);
	return gettoni;
}


@EventHandler
public void changePoints(PlayerPointsChangeEvent event)
{
	for(IglesiasHubItem item:Main.items)
	{
		if(item.playerPoints)
		{
			item.crea(Main.server.getPlayer(event.getPlayerName()),event.getChange());
		}
	}
	//System.out.println("Evento ChangePoints"+event.getChange());
}

}
