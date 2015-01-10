package cmd;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import v3anvil.main;

public class restoreCMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs instanceof Player && cs.hasPermission("anvil.restore")) {
			Player p = (Player) cs;
			int i = 0;
			for (Location loc : main.anvils) {
				if (!loc.getBlock().getType().equals(Material.ANVIL)) {
					loc.getBlock().setType(Material.ANVIL);
					loc.getBlock().setData((byte) 0);
					i++;
				}
			}
			p.sendMessage(main.prefix
					+ "Es "
					+ ((i == 0) ? "wurden §akeine §3Ambosse"
							: ((i == 1) ? "wurde §c" + i + "§3 Amboss"
									: "wurden §c" + i + "§3 Ambosse"))
					+ " §3wiederhergestellt!");
		} else {
			cs.sendMessage(main.prefix + "§cDu hast keine Rechte!");
		}
		return false;
	}

}
