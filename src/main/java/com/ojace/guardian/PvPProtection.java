package com.ojace.guardian;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;

public class PvPProtection {

    private static final int PROTECTION_RADIUS = 100;

    public static void register() {

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {

            if (!(entity instanceof Player)) {
                return InteractionResult.PASS;
            }

            BlockPos spawn = world.getSharedSpawnPos();

            double distance = player.position().distanceTo(
                    spawn.getCenter()
            );

            if (distance <= PROTECTION_RADIUS) {
                return InteractionResult.FAIL;
            }

            return InteractionResult.PASS;
        });
    }
}
