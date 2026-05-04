package net.y9.noendflash.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.level.dimension.DimensionType;

@Mixin(DimensionType.class)
public class DimensionTypeMixin {
	@Inject(method = "hasEndFlashes", at = @At("HEAD"), cancellable = true)
	public void cancelEndFlashes(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
}