package net.y9.noendflash.config;

public record Config(boolean endFlashesEnabled) {
	public static Config DEFAULT = new Config(false);
}
