package com.ojace.guardian;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;

import java.util.Iterator;

@EventBusSubscriber(modid = "guardian")
public class Guardian {

    public static void init() {
        NeoForge.EVENT_BUS.register(Guardian.class);
    }

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        Player attacker = event.getEntity();
        Entity target = event.getTarget();

        if (!(target instanceof Player)) {
            return;
        }

        if (isProtected(attacker.level(), attacker.position())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        Level level = event.getLevel();

        event.getAffectedBlocks().removeIf(
                pos -> isProtected(level, pos.getCenter())
        );

        Iterator<Entity> iterator = event.getAffectedEntities().iterator();

        while (iterator.hasNext()) {
            Entity entity = iterator.next();

            if (isProtected(level, entity.position())) {
                iterator.remove();
            }
        }
    }

    public static boolean isProtected(Level level, Vec3 pos) {
        BlockPos spawn = level.getSharedSpawnPos();

        double radius = GuardianConfig.PROTECTION_RADIUS.get();

        return pos.distanceToSqr(spawn.getCenter()) <= radius * radius;
    }
}
