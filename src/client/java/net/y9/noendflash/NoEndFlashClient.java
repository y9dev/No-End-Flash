package net.y9.noendflash;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.y9.noendflash.config.Config;
import net.y9.noendflash.config.ConfigManager;

public class NoEndFlashClient implements ClientModInitializer {

	private final static ConfigManager configManager = new ConfigManager("no-end-flash.json",
			FabricLoader.getInstance().getConfigDir());

	@Override
	public void onInitializeClient() {
		configManager.loadOrCreate();

		NoEndFlashCommand.registerCommands();
		NoEndFlash.LOGGER.info("NO MORE F***ING END FLASH!!!!!");
	}

	public static boolean isEndFlashEnabled() {
		return configManager.getConfig().endFlashesEnabled();
	}

	public static void enableEndFlash() {
		configManager.save(new Config(true));
	}

	public static void disableEndFlash() {
		configManager.save(new Config(false));
	}
}
