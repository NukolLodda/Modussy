package net.nukollodda.flopcraft.datagen.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.nukollodda.flopcraft.Flopcraft;

public class FlopTrimPatterns {
    // note, the trim pattern below needs to be registered somehow
    public static final ResourceKey<TrimPattern> JIAFEI = registryKey("jiafei");

    private static ResourceKey<TrimPattern> registryKey(String pKey) {
        return ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(Flopcraft.MODID, pKey));
    }
}
