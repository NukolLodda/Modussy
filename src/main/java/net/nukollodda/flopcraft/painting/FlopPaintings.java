package net.nukollodda.flopcraft.painting;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nukollodda.flopcraft.Flopcraft;

public class FlopPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Flopcraft.MODID);

    public static final RegistryObject<PaintingVariant> FLOPTROPICA_MAP = PAINTING_VARIANTS.register("floptropica_map",
            () -> new PaintingVariant(48, 32));

    // 4 x 3 - map size
    // 16 x 9 - flag

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
