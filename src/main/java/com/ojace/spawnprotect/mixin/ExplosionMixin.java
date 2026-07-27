package com.ojace.spawnprotect.mixin;

import com.ojace.spawnprotect.SpawnProtect;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Explosion.class)
public class ExplosionMixin {

    @Shadow
    private Level level;

    @Inject(
        method = "interactsWithBlocks",
        at = @At("HEAD"),
        cancellable = true
    )
    private void preventSpawnExplosion(
            CallbackInfoReturnable<Boolean> cir
    ) {

        Explosion explosion = (Explosion)(Object)this;

        if (SpawnProtect.isProtected(level, explosion.center())) {
            cir.setReturnValue(false);
        }
    }
}
