package cmd;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import v3anvil.main;

public class AddCMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs instanceof Player && cs.hasPermission("anvil.add")) {
			Player p = (Player) cs;
			if (p.getTargetBlock(null, 0).getType() == Material.ANVIL) {
				if (main.anvils.contains(p.getTargetBlock(null, 0)
						.getLocation())) {
					p.sendMessage(main.prefix
							+ "Dieser Amboss ist bereits gesichert!");
					return true;
				}
				addAnvil(p.getTargetBlock(null, 0).getLocation());
				p.sendMessage(main.prefix + "Der Amboss wurde §ahinzugefügt§3!");
			} else {
				p.sendMessage(main.prefix + "Dies ist kein Amboss!");
			}
		} else {
			cs.sendMessage(main.prefix + "§cDu hast keine Rechte!");
		}
		return false;
	}

	private void addAnvil(Location loc) {
		// TODO Auto-generated method stub
		main.anvils.add(loc);
	}
}
