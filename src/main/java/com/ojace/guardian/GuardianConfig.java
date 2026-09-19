package com.ojace.guardian;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;

public final class GuardianConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuardianConfig.class);

    private static final Path CONFIG_PATH =
            FabricLoader.getInstance().getConfigDir().resolve("guardian.json");

    private static int protectionRadius = 100;
    private static boolean protectPvP = true;
    private static boolean protectExplosions = true;

    private GuardianConfig() {
    }

    public static void load() {
        try {
            if (Files.notExists(CONFIG_PATH)) {
                write();
                return;
            }

            JsonObject json = JsonParser.parseString(Files.readString(CONFIG_PATH)).getAsJsonObject();

            if (json.has("protectionRadius")) {
                protectionRadius = json.get("protectionRadius").getAsInt();
            }
            if (json.has("protectPvP")) {
                protectPvP = json.get("protectPvP").getAsBoolean();
            }
            if (json.has("protectExplosions")) {
                protectExplosions = json.get("protectExplosions").getAsBoolean();
            }
        } catch (Exception e) {
            LOGGER.error("[Guardian] Failed to read config at {}, using defaults", CONFIG_PATH, e);
        }
    }

    private static void write() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());

            JsonObject json = new JsonObject();
            json.addProperty("protectionRadius", protectionRadius);
            json.addProperty("protectPvP", protectPvP);
            json.addProperty("protectExplosions", protectExplosions);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Files.writeString(CONFIG_PATH, gson.toJson(json));
        } catch (Exception e) {
            LOGGER.error("[Guardian] Failed to write default config at {}", CONFIG_PATH, e);
        }
    }

    public static int getProtectionRadius() {
        return protectionRadius;
    }

    public static boolean isProtectPvP() {
        return protectPvP;
    }

    public static boolean isProtectExplosions() {
        return protectExplosions;
    }
}