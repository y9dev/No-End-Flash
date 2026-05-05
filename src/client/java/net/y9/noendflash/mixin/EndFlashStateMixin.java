package net.y9.noendflash.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.EndFlashState;
import net.y9.noendflash.NoEndFlashClient;

@Mixin(EndFlashState.class)
public class EndFlashStateMixin {

	@Shadow
	private float intensity;

	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	public void resolveEndFlashTick(long l, CallbackInfo ci) {
		if (!NoEndFlashClient.isEndFlashEnabled() && intensity == 0.0F) {
			ci.cancel();
		}
	}
}
