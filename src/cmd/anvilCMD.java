package cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import v3anvil.main;

public class anvilCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs.hasPermission("anvil.anvil")) {

			if (args.length == 1) {
				if (!cs.hasPermission("anvil.help")) {
					sendDefault(cs);
					return true;
				}
				if (args[0].equalsIgnoreCase("help")) {
					sendHelp(cs);
				} else if (args[0].equalsIgnoreCase("permission")) {
					sendPerm(cs);
				} else if (args[0].equalsIgnoreCase("commands")) {
					sendCommand(cs);
				} else if (args[0].equalsIgnoreCase("use")) {
					sendUse(cs);
				} else {
					sendDefault(cs);
				}
			} else {
				sendDefault(cs);
			}
		} else {
			cs.sendMessage(main.prefix + "§cDu hast keine Rechte!");
		}
		return false;
	}

	private void sendUse(CommandSender cs) {
		// TODO Auto-generated method stub
		cs.sendMessage(main.prefix + "Amboss sichern:");
		cs.sendMessage(main.prefix
				+ "Amboss §eanschauen §3und §e/addanvil §3eingeben!");
		cs.sendMessage(main.prefix + "Amboss entsichern:");
		cs.sendMessage(main.prefix
				+ "Amboss §eanschauen §3und §e/removeanvil §3eingeben!");
		cs.sendMessage(main.prefix + "Liste aller gesicherten Ambosse:");
		cs.sendMessage(main.prefix + "§e/listanvil §3eingeben!");
		cs.sendMessage(main.prefix + "Ambosse wiederherstellen:");
		cs.sendMessage(main.prefix
				+ "§e/restoreanvil §eeingeben! \n§cACHTUNG: Nimmt Änderungen an den Welten vor!");

	}

	private void sendCommand(CommandSender cs) {
		// TODO Auto-generated method stub
		cs.sendMessage(main.prefix + "Commands:");
		cs.sendMessage(main.prefix
				+ "§e/anvil §3Command für Hilfe/ Plugin Informationen");
		cs.sendMessage(main.prefix
				+ "§e/addanvil §3Command um Ambosse hinzuzufügen");
		cs.sendMessage(main.prefix
				+ "§e/removeanvil §3Command um Ambosse zu entfernen");
		cs.sendMessage(main.prefix
				+ "§e/listanvil §3Command für eine Liste aller gesicherten Ambosse");
		cs.sendMessage(main.prefix
				+ "§e/restoreanvil §3Command um alle gesicherten Ambosse wiederherzustellen");

	}

	private void sendPerm(CommandSender cs) {
		// TODO Auto-generated method stub
		cs.sendMessage(main.prefix + "Permissions:");
		cs.sendMessage(main.prefix + "§eanvil.anvil §3Default Permission");
		cs.sendMessage(main.prefix
				+ "§eanvil.help §3Permission für Plugin-Hilfe");
		cs.sendMessage(main.prefix
				+ "§eanvil.add §3Permission um Ambosse hinzuzufügen");
		cs.sendMessage(main.prefix
				+ "§eanvil.remove §3Permission um Ambosse entfernen");
		cs.sendMessage(main.prefix
				+ "§eanvil.list §3Permission um alle Gesicherten Ambosse anzuzeigen");
		cs.sendMessage(main.prefix
				+ "§eanvil.restore §3Permission um Ambosse wiederherzustellen");
		cs.sendMessage(main.prefix + "§canvil.* §3Gibt alle Plugin Permission");

	}

	private void sendHelp(CommandSender cs) {
		// TODO Auto-generated method stub

		cs.sendMessage(main.prefix + "Für weitere Hilfe");
		cs.sendMessage(main.prefix + "Nutze §c/anvil [use|permission|commands]");

	}

	private void sendDefault(CommandSender cs) {
		// TODO Auto-generated method stub
		cs.sendMessage(main.prefix + "Plugin wurde erstellt von §6V3lop5");
		cs.sendMessage(main.prefix + "Für Hilfe nutze §c/anvil help");
	}

}
