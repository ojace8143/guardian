package com.ojace.spawnprotect;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SpawnProtect implements ModInitializer {

    public static final int PROTECTION_RADIUS = 100;

    @Override
    public void onInitialize() {
        System.out.println("[SpawnProtect] Loaded successfully!");

        PvPProtection.register();
    }

    public static boolean isProtected(Level level, Vec3 pos) {

        BlockPos spawn = level.getSharedSpawnPos();

        return pos.distanceTo(spawn.getCenter())
                <= PROTECTION_RADIUS;
    }
}
