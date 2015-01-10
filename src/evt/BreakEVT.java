package evt;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import v3anvil.main;

public class BreakEVT implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (main.anvils.contains(e.getBlock().getLocation())
				|| main.anvils.contains(new Location(e.getBlock().getWorld(), e
						.getBlock().getX(), e.getBlock().getY() + 1, e
						.getBlock().getZ()))) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(
					main.prefix + "Dieser Amboss ist geschützt!");
		}
	}

	/*
	 * @EventHandler public void onPhysik(BlockPhysicsEvent e) { if
	 * (e.getBlock().getType().equals(Material.ANVIL) &&
	 * main.anvils.contains(e.getBlock().getLocation())) {
	 * 
	 * e.setCancelled(true); Location temp = e.getBlock().getLocation();
	 * temp.setY(e.getBlock().getLocation().getY() - 1); //
	 * e.getBlock().getWorld().getBlockAt(temp).setType(Material.GRASS); } }
	 */
}
