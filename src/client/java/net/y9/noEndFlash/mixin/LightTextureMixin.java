package net.y9.noEndFlash.mixin;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightTexture.class)
public class LightTextureMixin {

    @Redirect(method = "updateLightTexture", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/DimensionSpecialEffects;hasEndFlashes()Z"
    ))
    public boolean redirectHasEndFlashes(DimensionSpecialEffects instance) {
        return false;
    }
}
