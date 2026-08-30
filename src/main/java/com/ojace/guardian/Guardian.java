package com.ojace.guardian;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;

import java.util.Iterator;

@Mod("guardian")
public class Guardian {

    public static final String MOD_ID = "guardian";

    public Guardian(IEventBus modEventBus, ModContainer modContainer) {

        modContainer.registerConfig(
                ModConfig.Type.SERVER,
                GuardianConfig.SPEC
        );

        // Register Guardian event handlers
        NeoForge.EVENT_BUS.addListener(Guardian::onAttackEntity);
        NeoForge.EVENT_BUS.addListener(Guardian::onExplosionDetonate);

        System.out.println("[Guardian] Loaded successfully!");
    }

    private static void onAttackEntity(AttackEntityEvent event) {

        if (!GuardianConfig.PROTECT_PVP.get()) {
            return;
        }

        if (!(event.getTarget() instanceof Player)) {
            return;
        }

        Player attacker = event.getEntity();

        if (isProtected(attacker.level(), attacker.position())) {
            event.setCanceled(true);
        }
    }
    private static void onExplosionDetonate(ExplosionEvent.Detonate event) {

        if (!GuardianConfig.PROTECT_EXPLOSIONS.get()) {
            return;
        }

        Level level = event.getLevel();

        // Remove blocks inside the protected area
        event.getAffectedBlocks().removeIf(
                pos -> isProtected(level, pos.getCenter())
        );

        // Remove entities inside the protected area
        Iterator<Entity> iterator =
                event.getAffectedEntities().iterator();

        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (isProtected(level, entity.position())) {
                iterator.remove();
            }
        }
    }

    public static boolean isProtected(Level level, Vec3 pos) {

        BlockPos spawn = level.getSharedSpawnPos();

        int radius = GuardianConfig.PROTECTION_RADIUS.get();

        return pos.distanceToSqr(spawn.getCenter())
                <= (double) radius * radius;
    }
}
