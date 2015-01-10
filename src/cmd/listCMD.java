package cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import v3anvil.main;

public class listCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		if (cs.hasPermission("anvil.list")) {
			List<String> temp = new ArrayList<>();
			for (Location loc : main.anvils) {
				String s = loc.getWorld().getName() + "; " + loc.getBlockX()
						+ "; " + loc.getBlockY() + "; " + loc.getBlockZ();
				temp.add(s);
			}
			if (temp.size() == 0) {
				cs.sendMessage(main.prefix
						+ "Es wurden keine Ambosse gesichert!");

			} else {
				int i = 1;
				cs.sendMessage(main.prefix + "Alle gesicherten Ambosse:");
				for (String s : temp) {
					cs.sendMessage("§6" + i + "§8: §3" + s);
					i++;
				}
			}
		}
		return false;
	}

}
