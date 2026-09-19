package com.ojace.guardian;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Guardian implements DedicatedServerModInitializer {

    public static final String MOD_ID = "guardian";

    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeServer() {
        GuardianConfig.load();

        AttackEntityCallback.EVENT.register((attacker, world, hand, target, hitResult) -> {
            if (GuardianConfig.isProtectPvP()
                    && target instanceof PlayerEntity
                    && isProtected(world, attacker.getPos())) {
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        });

        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (!GuardianConfig.isProtectExplosions()
                    || (!source.isOf(DamageTypes.EXPLOSION) && !source.isOf(DamageTypes.PLAYER_EXPLOSION))) {
                return true;
            }
            return !isProtected(entity.getWorld(), entity.getPos());
        });

        LOGGER.info("[Guardian] Loaded successfully!");
    }

    public static boolean isProtected(World world, Vec3d position) {
        int radius = GuardianConfig.getProtectionRadius();
        return position.squaredDistanceTo(world.getSpawnPos().toCenterPos())
                <= (double) radius * radius;
    }
}