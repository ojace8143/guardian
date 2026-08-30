package com.ojace.guardian;

import net.fabricmc.api.ModInitializer;

public class Guardian implements ModInitializer {

    @Override
    public void onInitialize() {
        System.out.println("[Guardian] Loaded successfully!");

	PvPProtection.register();
    }
}
