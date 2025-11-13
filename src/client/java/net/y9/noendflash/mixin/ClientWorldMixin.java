package net.y9.noendflash.mixin;

import net.minecraft.client.render.EndLightFlashManager;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @Redirect(method = "tick", at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/world/ClientWorld;endLightFlashManager:Lnet/minecraft/client/render/EndLightFlashManager;")
    )
    private EndLightFlashManager getEndLightFlashManager(ClientWorld instance) {
        return null;
    }

    @Redirect(method = "tick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/sound/SoundManager;play(Lnet/minecraft/client/sound/SoundInstance;I)V")
    )
    private void play(SoundManager instance, SoundInstance sound, int delay) {

    }

    @Inject(method = "getEndLightFlashManager", at = @At("HEAD"), cancellable = true)
    private void getEndLightFlashManager(CallbackInfoReturnable<EndLightFlashManager> cir) {
        cir.setReturnValue(null);
    }
}
