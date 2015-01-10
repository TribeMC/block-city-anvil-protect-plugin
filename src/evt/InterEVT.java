package evt;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import v3anvil.main;

public class InterEVT implements Listener {

	@EventHandler
	public void onInter(PlayerInteractEvent e) {
		if (e.getAction().equals(
				org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK)) {

			if (e.getClickedBlock().getType().equals(Material.ANVIL)
					&& main.anvils.contains(e.getClickedBlock().getLocation())) {

				pa.put(e.getPlayer(), e.getClickedBlock().getLocation());
			}
		}
	}

	private HashMap<Player, Location> pa = new HashMap<>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e) {

		if (e.getWhoClicked() instanceof Player
				&& e.getInventory().getSize() == 3) {
			Player p = (Player) e.getWhoClicked();

			if (pa.containsKey(p) && e.getSlot() == 2) {
				if (pa.get(p).getWorld().getBlockAt(pa.get(p)).getData() >= (byte) 8) {
					pa.get(p).getWorld().getBlockAt(pa.get(p))
							.setType(Material.ANVIL);
					pa.get(p)
							.getWorld()
							.getBlockAt(pa.get(p))
							.setData(
									(byte) (pa.get(p).getWorld()
											.getBlockAt(pa.get(p)).getData() - ((byte) 8)));
				}

				if (e.getCurrentItem().getAmount() > main.max) {
					p.sendMessage(main.prefix + "Du darfst maximal §c"
							+ main.max + " §3Items gleichzeitig reperieren!");
					e.setCancelled(true);
				}
			}

		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {

		if (pa.containsKey(e.getPlayer())) {
			pa.remove(e.getPlayer());
		}
	}
}
