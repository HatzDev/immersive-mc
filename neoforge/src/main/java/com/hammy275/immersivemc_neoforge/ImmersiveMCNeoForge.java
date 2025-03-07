package com.hammy275.immersivemc_neoforge;

import com.hammy275.immersivemc.ImmersiveMC;
import com.hammy275.immersivemc.common.compat.Lootr;
import com.hammy275.immersivemc.common.config.ImmersiveMCConfig;
import dev.architectury.platform.Platform;
import fuzs.forgeconfigapiport.neoforge.api.forge.v4.ForgeConfigRegistry;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(ImmersiveMC.MOD_ID)
public class ImmersiveMCNeoForge {
    public ImmersiveMCNeoForge() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> IExtensionPoint.DisplayTest.IGNORESERVERONLY, (a, b) -> true));
        ImmersiveMC.init();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            ClientSetup.doClientSetup();
        }
        // Need to specify that it's a FMLConstructModEvent since the type of event determines when this is called
        FMLJavaModLoadingContext.get().getModEventBus().addListener((FMLConstructModEvent event) -> ForgeConfigRegistry.INSTANCE.register(ImmersiveMC.MOD_ID, ModConfig.Type.COMMON, ImmersiveMCConfig.GENERAL_SPEC,
                "immersive_mc.toml"));

        if (Platform.isModLoaded("lootr")) {
            Lootr.lootrImpl = new LootrCompatImpl();
        }
    }
}
