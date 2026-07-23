package com.ojace.spawnprotect;

import net.fabricmc.api.ModInitializer;

public class SpawnProtect implements ModInitializer {

    @Override
    public void onInitialize() {
        System.out.println("[SpawnProtect] Loaded successfully!");

	PvPProtection.register();
    }
}
