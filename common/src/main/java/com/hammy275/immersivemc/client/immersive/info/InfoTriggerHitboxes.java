package com.hammy275.immersivemc.client.immersive.info;

import net.minecraft.world.phys.AABB;

/**
 * Attached to immersive infos to signify that they have hitboxes that should only activate
 * when holding the trigger while in VR.
 */
public interface InfoTriggerHitboxes {

    public AABB getTriggerHitbox(int hitboxNum);

    public AABB[] getTriggerHitboxes();
}
