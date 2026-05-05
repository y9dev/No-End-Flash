package net.y9.noendflash;

import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

public class NoEndFlashCommand {

	protected static void registerCommands() {
		ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("noendflash").executes(context -> handleHelp(context))
					.then(ClientCommandManager.literal("toggle").executes(context -> handleToggle(context)))
					.then(ClientCommandManager.literal("status").executes(context -> handleStatus(context))));
		}));
	}

	private static int handleHelp(CommandContext<FabricClientCommandSource> context) {
		context.getSource()
				.sendFeedback(Component.literal("Actual commands:\n")
						.append(Component.literal("		/status - Displays current status of End Flashes\n"))
						.append(Component.literal("		/toggle - Toggles current status of End Flashes")));
		return 1;
	}

	private static int handleToggle(CommandContext<FabricClientCommandSource> context) {
		try {
			boolean state = NoEndFlashClient.isEndFlashEnabled();
			Component statusComponent = !state
					? Component.literal("Enabled").withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))
					: Component.literal("Disabled").withStyle(Style.EMPTY.withColor(ChatFormatting.RED));

			if (state)
				NoEndFlashClient.disableEndFlash();
			else
				NoEndFlashClient.enableEndFlash();

			context.getSource().sendFeedback(Component.literal("Set to: ").append(statusComponent));
			return 1;
		} catch (Exception e) {
			NoEndFlash.LOGGER.error("Command error: NoEndFlashCommand#handleToggle - " + e.getMessage());

			context.getSource()
					.sendFeedback(Component.literal("Error...").withStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
			return 1;
		}
	}

	private static int handleStatus(CommandContext<FabricClientCommandSource> context) {
		boolean state = NoEndFlashClient.isEndFlashEnabled();
		Component statusComponent = state
				? Component.literal("Enabled").withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN))
				: Component.literal("Disabled").withStyle(Style.EMPTY.withColor(ChatFormatting.RED));

		context.getSource().sendFeedback(Component.literal("End Flashess: ").append(statusComponent));
		return 1;
	}
}
