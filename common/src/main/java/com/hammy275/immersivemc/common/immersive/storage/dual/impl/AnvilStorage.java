package com.hammy275.immersivemc.common.immersive.storage.dual.impl;

import com.hammy275.immersivemc.common.immersive.handler.ImmersiveHandler;
import com.hammy275.immersivemc.common.immersive.handler.ImmersiveHandlers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class AnvilStorage extends ItemStorage {

    public int xpLevels = 0;

    public AnvilStorage() {
        super(3, 1);
    }

    @Override
    public ImmersiveHandler getHandler() {
        return ImmersiveHandlers.anvilHandler;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.xpLevels = nbt.getInt("xpLevels");
    }

    @Override
    public CompoundTag save(CompoundTag nbtIn) {
        CompoundTag nbt = super.save(nbtIn);
        nbt.putInt("xpLevels", xpLevels);
        return nbt;
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        super.encode(buffer);
        buffer.writeInt(this.xpLevels);
    }

    @Override
    public void decode(FriendlyByteBuf buffer) {
        super.decode(buffer);
        this.xpLevels = buffer.readInt();
    }
}
