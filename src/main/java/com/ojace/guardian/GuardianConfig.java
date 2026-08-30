package com.ojace.guardian;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class GuardianConfig {

    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.IntValue PROTECTION_RADIUS;
    public static final ModConfigSpec.BooleanValue PROTECT_PVP;
    public static final ModConfigSpec.BooleanValue PROTECT_EXPLOSIONS;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        PROTECTION_RADIUS = builder
                .comment("Radius around world spawn where Guardian protection applies.")
                .defineInRange("protectionRadius", 100, 0, 1000000);

        PROTECT_PVP = builder
                .comment("Prevent players from attacking other players inside the protected area.")
                .define("protectPvP", true);

        PROTECT_EXPLOSIONS = builder
                .comment("Prevent explosions from affecting the protected area.")
                .define("protectExplosions", true);

        SPEC = builder.build();
    }

    private GuardianConfig() {
    }
}
