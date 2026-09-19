package com.ojace.guardian.mixin;

import com.ojace.guardian.Guardian;
import com.ojace.guardian.GuardianConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Shadow
    @Final
    private World world;

    @Inject(method = "affectWorld", at = @At("HEAD"))
    private void guardian$protectSpawn(boolean particles, CallbackInfo ci) {
        if (!GuardianConfig.isProtectExplosions()) {
            return;
        }

        Explosion self = (Explosion) (Object) this;

        List<BlockPos> affectedBlocks = self.getAffectedBlocks();
        if (affectedBlocks != null) {
            affectedBlocks.removeIf(pos -> Guardian.isProtected(world, pos.toCenterPos()));
        }

        Map<PlayerEntity, Vec3d> affectedPlayers = self.getAffectedPlayers();
        if (affectedPlayers != null) {
            affectedPlayers.keySet().removeIf(player -> Guardian.isProtected(world, player.getPos()));
        }
    }
}