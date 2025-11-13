package net.y9.noendflash.mixin;

import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.client.sound.TickableSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundSystem.class)
public class SoundSystemMixin {

    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;I)V", at = @At("HEAD"), cancellable = true)
    private void play(SoundInstance sound, int delay, CallbackInfo ci) {
        if (sound.getId().getPath().contains("end_flash")) {
            ci.cancel();
        }
    }

    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)Lnet/minecraft/client/sound/SoundSystem$PlayResult;", at = @At("HEAD"), cancellable = true)
    private void play(SoundInstance sound, CallbackInfoReturnable<SoundSystem.PlayResult> cir) {
        if (sound.getId().getPath().contains("end_flash")) {
            cir.setReturnValue(SoundSystem.PlayResult.NOT_STARTED);
        }
    }

    @Inject(method = "playNextTick", at = @At("HEAD"), cancellable = true)
    private void playNextTick(TickableSoundInstance sound, CallbackInfo ci) {
        if (sound.getId().getPath().contains("end_flash")) {
            ci.cancel();
        }
    }
}
