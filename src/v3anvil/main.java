package v3anvil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

	CommandSender cs = getServer().getConsoleSender();
	PluginManager pm = getServer().getPluginManager();
	public static String prefix = "§8[§eAnvil§cProtect§8] §3";

	File f = new File("plugins/Anvil", "data.yml");
	public static List<Location> anvils = new ArrayList<>();
	public static int max = 64;

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		cs.sendMessage(prefix + "§aPlugin wird geladen!");

		loadData();
		cs.sendMessage(prefix + "§eDaten wurden §aerfolgreich §egeladen!");
		registerEvent();
		registerCommands();
		cs.sendMessage(prefix
				+ "§eCommands und Events wurden §aerfolgreich §eregistriert!");
		restoreAnvil();
		cs.sendMessage(prefix + "§aErfolgreich geladen!");
		super.onEnable();
	}

	@SuppressWarnings("deprecation")
	private void restoreAnvil() {
		// TODO Auto-generated method stub
		for (Location loc : anvils) {
			if (!loc.getBlock().getType().equals(Material.ANVIL)) {
				loc.getBlock().setType(Material.ANVIL);
				loc.getBlock().setData((byte) 0);
			}
		}
	}

	private void registerCommands() {
		// TODO Auto-generated method stub

		getCommand("addanvil").setExecutor(new cmd.AddCMD());
		getCommand("removeanvil").setExecutor(new cmd.RemoveCMD());
		getCommand("listanvil").setExecutor(new cmd.listCMD());
		getCommand("restoreanvil").setExecutor(new cmd.restoreCMD());
		getCommand("anvil").setExecutor(new cmd.anvilCMD());

	}

	private void registerEvent() {
		// TODO Auto-generated method stub

		pm.registerEvents(new evt.BreakEVT(), this);
		pm.registerEvents(new evt.InterEVT(), this);
	}

	private void loadData() {
		// TODO Auto-generated method stub
		if (!f.exists()) {
			File dir = new File("plugins/Anvil");
			dir.mkdir();
			try {
				f.createNewFile();
				setDefaults();
			} catch (IOException e) {
				cs.sendMessage(prefix + "§cKonnte Data File nicht erstellen!");
			}
		}

		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		try {
			prefix = ChatColor.translateAlternateColorCodes('&',
					cfg.getString("Prefix"));

			if (prefix == null) {
				prefix = "§8[§eAnvil§cProtect§8] §3";
			}
			List<String> temp = cfg.getStringList("Locations");
			for (String s : temp) {
				String[] ls = s.split("; ");
				anvils.add(new Location(Bukkit.getWorld(ls[0]), Integer
						.valueOf(ls[1]), Integer.valueOf(ls[2]), Integer
						.valueOf(ls[3])));
			}
			max = cfg.getInt("Anvil.MaxStack");
		} catch (Exception e) {

			cs.sendMessage(prefix
					+ "§cEin Fehler ist aufgetreten! Die Daten werden neu gesetzt!");
			setDefaults();
		}

	}

	private void setDefaults() {
		// TODO Auto-generated method stub
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		cfg.set("Prefix", prefix);

		cfg.set("Locations", anvils);

		cfg.set("Anvil.MaxStack", 64);
		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		saveData();
		super.onDisable();
	}

	private void saveData() {
		// TODO Auto-generated method stub
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		List<String> temp = new ArrayList<>();
		for (Location loc : anvils) {
			String s = loc.getWorld().getName() + "; " + loc.getBlockX() + "; "
					+ loc.getBlockY() + "; " + loc.getBlockZ();
			temp.add(s);

		}
		cfg.set("Locations", temp);

		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
