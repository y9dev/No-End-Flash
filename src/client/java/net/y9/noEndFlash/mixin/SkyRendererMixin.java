package net.y9.noEndFlash.mixin;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SkyRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkyRenderer.class)
public class SkyRendererMixin {

    @Inject(method = "renderEndFlash", at = @At("HEAD"), cancellable = true)
    public void renderEndFlash(PoseStack poseStack, float f, float g, float h, CallbackInfo ci) {
        ci.cancel();
    }
}
