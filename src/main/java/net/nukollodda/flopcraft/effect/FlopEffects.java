package net.nukollodda.flopcraft.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nukollodda.flopcraft.Flopcraft;

public class FlopEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Flopcraft.MODID);

    public static final RegistryObject<MobEffect> BRATIFIED = MOB_EFFECTS.register("bratified", BratifiedEffect::new);
    public static final RegistryObject<MobEffect> CVMMED = MOB_EFFECTS.register("cvmmed", CvmmedEffect::new);
    public static final RegistryObject<MobEffect> YASSIFIED = MOB_EFFECTS.register("yassified", YassifiedEffect::new);
    public static final RegistryObject<MobEffect> YUH = MOB_EFFECTS.register("yuh", YuhEffect::new);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
