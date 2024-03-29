package slay.nukolussy.modussy.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slay.nukolussy.modussy.Modussy;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Modussy.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
        boolean includeServer = event.includeServer();

        ModBlockTagProvider blockTagGenerator = generator.addProvider(includeServer,
                new ModBlockTagProvider(output, lookup, fileHelper));

        generator.addProvider(includeServer,
                new ModItemTagGenerator(output, lookup, blockTagGenerator.contentsGetter(), fileHelper));

        generator.addProvider(includeServer, new ModRecipeProvider(output));

        generator.addProvider(includeServer, new ModBlockStateProvider(output, fileHelper));
        generator.addProvider(includeServer, new ModItemModelProvider(output, fileHelper));

        generator.addProvider(includeServer, new ModWorldGenProvider(output, lookup));
        generator.addProvider(includeServer, new ModGlobalLootModifiersProvider(output));

        // generator.addProvider(includeServer, ModLootTableProvider.create(output));
    }
}
