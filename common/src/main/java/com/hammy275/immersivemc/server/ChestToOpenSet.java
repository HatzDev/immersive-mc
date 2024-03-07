package com.hammy275.immersivemc.server;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.*;

public class ChestToOpenSet {

    private static final Map<PosLevel, Set<UUID>> chestImmersiveOpenSet = new HashMap<>();

    public static void openChest(Player player, BlockPos pos) {
        Set<UUID> set = getOpenSet(player.level(), pos, true);
        set.add(player.getUUID());
    }

    public static void closeChest(Player player, BlockPos pos) {
        Set<UUID> set = getOpenSet(player.level(), pos, false);
        if (set != null) {
            set.remove(player.getUUID());
            if (set.isEmpty()) {
                chestImmersiveOpenSet.remove(new PosLevel(pos, player.level()));
            }
        }
    }

    public static int getOpenCount(BlockPos pos, Level level) {
        Set<UUID> set = getOpenSet(level, pos, false);
        return set != null ? set.size() : 0;
    }

    public static void clear() {
        chestImmersiveOpenSet.clear();
    }

    private static Set<UUID> getOpenSet(Level level, BlockPos pos, boolean createIfNotPresent) {
        Set<UUID> set = chestImmersiveOpenSet.get(new PosLevel(pos, level));
        if (createIfNotPresent && set == null) {
            set = new HashSet<>();
            chestImmersiveOpenSet.put(new PosLevel(pos, level), set);
        }
        return set;
    }


    private record PosLevel(BlockPos pos, Level level) {
        public boolean matches(BlockPos otherPos, Level otherLevel) {
            return this.pos.equals(otherPos) && this.level == otherLevel;
        }
    }
}
