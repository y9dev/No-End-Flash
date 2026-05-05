package net.y9.noendflash.config;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.y9.noendflash.NoEndFlash;

public class ConfigManager {
	private final String FILE_NAME;
	private final Path FILE_DIR;
	private final Gson GSON = new GsonBuilder().create();

	public Config CONFIG = Config.DEFAULT;

	public ConfigManager(String fileName, Path filePath) {
		this.FILE_NAME = fileName;
		this.FILE_DIR = filePath;
	}

	public Config getConfig() {
		return CONFIG;
	}

	public void loadOrCreate() {
		Path filePath = FILE_DIR.resolve(FILE_NAME);

		if (!Files.exists(filePath)) {
			NoEndFlash.LOGGER.info("Config not exist, trying create new config..");
			save(CONFIG);
		}
		try (Reader reader = new FileReader(filePath.toString())) {
			CONFIG = GSON.fromJson(reader, Config.class);

		} catch (Exception e) {
			NoEndFlash.LOGGER.error("Config error: ConfigManager#load - " + e.getMessage());
		}
	}

	public void save(Config config) {
		Path filePath = FILE_DIR.resolve(FILE_NAME);

		try (FileWriter writer = new FileWriter(filePath.toString())) {
			GSON.toJson(config, writer);
			CONFIG = config;
			NoEndFlash.LOGGER.info("Config successfull saved. (or created)");
		} catch (Exception e) {
			NoEndFlash.LOGGER.error("Config error: ConfigManager#save - " + e.getMessage());
		}
	}
}
