package net.nukollodda.flopcraft.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nukollodda.flopcraft.Flopcraft;
import net.nukollodda.flopcraft.block.ModBlocks;
import net.nukollodda.flopcraft.block.fluids.FlopFluids;
import net.nukollodda.flopcraft.datagen.tags.FlopTrimPatterns;
import net.nukollodda.flopcraft.effect.FlopEffects;
import net.nukollodda.flopcraft.entities.FlopEntities;
import net.nukollodda.flopcraft.item.tiers.FlopTiers;
import net.nukollodda.flopcraft.item.types.*;
import net.nukollodda.flopcraft.item.types.jiafei.*;
import net.nukollodda.flopcraft.item.types.sextoys.ClitmasButtplug;
import net.nukollodda.flopcraft.item.types.sextoys.Deeldo;
import net.nukollodda.flopcraft.item.types.sextoys.EnderDragonDeeldo;
import net.nukollodda.flopcraft.item.types.sextoys.Rosetoy;
import net.nukollodda.flopcraft.item.types.tampons.BloodyTampon;
import net.nukollodda.flopcraft.item.types.tampons.ExtraBloodyTampon;
import net.nukollodda.flopcraft.item.types.tampons.Tampon;
import net.nukollodda.flopcraft.item.types.tools.*;
import net.nukollodda.flopcraft.sound.FlopSounds;

public class FlopItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Flopcraft.MODID);

    public static final RegistryObject<Item> BARBIE_CRYSTALS = ITEMS.register("barbie_crystals",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> BRAT = ITEMS.register("brat",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> BRAT_APPLE = ITEMS.register("brat_apple",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(1f)
                            .effect(() -> new MobEffectInstance(FlopEffects.BRATIFIED.get(), 800, 2), 1)
                            .build())));

    public static final RegistryObject<Item> CUPCAKE = ITEMS.register("cupcake",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(8).saturationMod(10f).build())));

    public static final RegistryObject<Item> LOVELY_PEACH = ITEMS.register("lovely_peach",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(4).saturationMod(5f).build())));

    public static final RegistryObject<Item> KAMALOCONUT = ITEMS.register("kamaloconut",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(3f).build())));

    public static final RegistryObject<Item> BLOOD_CLUMP = ITEMS.register("blood_clump", () ->
            new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(1f)
                    .effect(() -> new MobEffectInstance(MobEffects.WITHER, 1000, 2), 1).build())));

    public static final RegistryObject<Item> CVM = ITEMS.register("cvm", CvmItem::new);

    public static final RegistryObject<Item> CVMIUM = ITEMS.register("cvmium", CvmItem::new);

    public static final RegistryObject<Item> CVM_PIE = ITEMS.register("cvm_pie",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)
                    .food(new FoodProperties.Builder().nutrition(12).saturationMod(10f)
                            .effect(() -> new MobEffectInstance(FlopEffects.CVMMED.get(), 1000, 0), 1).build())));

    public static final RegistryObject<Item> CVMTITPLASM = ITEMS.register("cvmtitplasm",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLOPIUM = ITEMS.register("flopium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAGASTONE = ITEMS.register("gagastone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAGINA = ITEMS.register("gagina",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAIRUSSY = ITEMS.register("hairussy",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HUNBAO = ITEMS.register("hunbao", Hunbao::new);

    public static final RegistryObject<Item> JIAFEI_PRODUCT = ITEMS.register("jiafei_product",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BUSSIAN_DOLLAR = ITEMS.register("bussian_dollar",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MYSTICAL_FLOPIUM = ITEMS.register("mystical_flopium",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> POSEI = ITEMS.register("posei",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCARUSSY = ITEMS.register("scarussy",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SLAGINIUM = ITEMS.register("slaginium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INFUSED_SLAGINIUM = ITEMS.register("infused_slaginium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHENSEIUM = ITEMS.register("shenseium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TAMPON = ITEMS.register("tampon", Tampon::new);
    public static final RegistryObject<Item> BLOODY_TAMPON = ITEMS.register("bloody_tampon", BloodyTampon::new);
    public static final RegistryObject<Item> EXTRA_BLOODY_TAMPON = ITEMS.register("extra_bloody_tampon", ExtraBloodyTampon::new);

    public static final RegistryObject<Item> TWINK_EGG = ITEMS.register("twink_egg", TwinkEgg::new);

    public static final RegistryObject<Item> TWINK_EGG_PILE = ITEMS.register("twink_egg_pile",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TWINK_EGG_SHELLS = ITEMS.register("twink_egg_shells",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WONYOUNG_ESSENCE = ITEMS.register("wonyoung_essence",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> SLAGINIUM_YASSIFIER = ITEMS.register("slaginium_yassifier",
            () -> new SlaginiumYassifier(FlopTiers.SLAGINIUM,42069, 1, Rarity.COMMON));

    public static final RegistryObject<Item> SLAGINIUM_PICKAXOL = ITEMS.register("slaginium_pickaxol",
            () -> new Pickaxol(FlopTiers.SLAGINIUM, 3, -2.8f, Rarity.COMMON));
    public static final RegistryObject<Item> SLAGINIUM_YASSIFIER_PLUS = ITEMS.register("slaginium_yassifier_plus",
            () -> new SlaginiumYassifier(FlopTiers.INFUSED_SLAGINIUM,69420, 2, Rarity.UNCOMMON));

    public static final RegistryObject<Item> INFUSED_SLAGINIUM_PICKAXOL = ITEMS.register("infused_slaginium_pickaxol",
            () -> new Pickaxol(FlopTiers.INFUSED_SLAGINIUM, 3, -2.8f, Rarity.UNCOMMON));
    public static final RegistryObject<Item> SLAGINIUM_YASSIFIER_PREMIUM = ITEMS.register("slaginium_yassifier_premium",
            () -> new SlaginiumYassifier(FlopTiers.FLOPIUM,71690, 3, Rarity.RARE));

    public static final RegistryObject<Item> FLOPIUM_PICKAXOL = ITEMS.register("flopium_pickaxol",
            () -> new Pickaxol(FlopTiers.FLOPIUM, 3, -2.8f, Rarity.RARE));
    public static final RegistryObject<Item> SLAGINIUM_YASSIFIER_DELUXE = ITEMS.register("slaginium_yassifier_deluxe",
            () -> new SlaginiumYassifier(FlopTiers.MYSTICAL_FLOPIUM,97169, 4, Rarity.EPIC));

    public static final RegistryObject<Item> TWINK_TRANSFORMER = ITEMS.register("twink_transformer", TwinkTransformer::new);

    public static final RegistryObject<Item> MYSTICAL_FLOPIUM_PICKAXOL = ITEMS.register("mystical_flopium_pickaxol",
            () -> new Pickaxol(FlopTiers.MYSTICAL_FLOPIUM, 3, -2.8f, Rarity.EPIC));

    public static final RegistryObject<Item> JIAFEI_BOOTS = ITEMS.register("jiafei_boots", JiafeiArmor.Boots::new);
    public static final RegistryObject<Item> JIAFEI_LEGGINGS = ITEMS.register("jiafei_leggings", JiafeiArmor.Leggings::new);
    public static final RegistryObject<Item> JIAFEI_CHESTPLATE = ITEMS.register("jiafei_chestplate", JiafeiArmor.Chestplate::new);
    public static final RegistryObject<Item> JIAFEI_HELMET = ITEMS.register("jiafei_helmet", JiafeiArmor.Helmet::new);
    public static final RegistryObject<Item> ARANA_GRANDE = ITEMS.register("arana_grande", AranaGrande::new);
    public static final RegistryObject<Item> JIAFEI_PERFUME = ITEMS.register("jiafei_perfume", JiafeiPerfume::new);
    public static final RegistryObject<Item> KYLIE_JENNER_LIPSTICK = ITEMS.register("kylie_jenner_lipstick", KylieJennerLipstick::new);
    public static final RegistryObject<Item> LANCVM_CREAM = ITEMS.register("lancvm_cream", LancvmCream::new);
    public static final RegistryObject<Item> FLOPTROPICA_TICKET = ITEMS.register("floptropica_ticket", FloptropicaTicket::new);
    public static final RegistryObject<Item> JIAFEI_SEED = ITEMS.register("jiafei_seed", JiafeiSeed::new);
    public static final RegistryObject<Item> CLITMAS_PRESENT = ITEMS.register("clitmas_present", ClitmasPresent::new);
    public static final RegistryObject<Item> REMIX_PRESENT = ITEMS.register("remix_present", RemixPresent::new);
    public static final RegistryObject<Item> BRA = ITEMS.register("bra", InnerWear.Bra::new);
    public static final RegistryObject<Item> THONGS = ITEMS.register("thongs", InnerWear.Thongs::new);
    public static final RegistryObject<Item> JIAFEI_TRIM_TEMPLATE = ITEMS.register("jiafei_armor_trim_template",
            () -> SmithingTemplateItem.createArmorTrimTemplate(FlopTrimPatterns.JIAFEI));

    public static final RegistryObject<Item> JIAFEI_UPGRADE_TEMPLATE = ITEMS.register("jiafei_upgrade_smithing_template",
            ModSmithingTemplates::createJiafeiUpgradeTemplate);

    public static final RegistryObject<Item> BRATIFICATIONER = ITEMS.register("bratificationer", Bratificationer::new);
    public static final RegistryObject<Item> JIAFEI_AXE = ITEMS.register("jiafei_axe", JiafeiAxe::new);
    public static final RegistryObject<Item> JIAFEI_HAMMER = ITEMS.register("jiafei_hammer", JiafeiHammer::new);
    public static final RegistryObject<Item> JIAFEI_PICKAXE = ITEMS.register("jiafei_pickaxe", JiafeiPickaxe::new);
    public static final RegistryObject<Item> JIAFEI_SICKLE = ITEMS.register("jiafei_sickle", JiafeiSickle::new);
    public static final RegistryObject<Item> JIAFEI_SHOVEL = ITEMS.register("jiafei_shovel", JiafeiShovel::new);
    public static final RegistryObject<Item> CLITMAS_BUTTPLUG = ITEMS.register("clitmas_buttplug", ClitmasButtplug::new);
    public static final RegistryObject<Item> ENDER_DRAGON_DEELDO = ITEMS.register("ender_dragon_deeldo", EnderDragonDeeldo::new);
    public static final RegistryObject<Item> DEELDO = ITEMS.register("deeldo", Deeldo::new);
    public static final RegistryObject<Item> RANPAPI_TABLET = ITEMS.register("ranpapi_tablet", RanpapiTablet::new);
    public static final RegistryObject<Item> ROSETOY = ITEMS.register("rosetoy", Rosetoy::new);

    public static final RegistryObject<Item> CVM_FLUID_BUCKET = ITEMS.register("cvm_fluid_bucket",
            () -> new BucketItem(FlopFluids.SOURCE_CVM_FLUID, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

    public static final RegistryObject<Item> POOSAY_JUICE_BUCKET = ITEMS.register("poosay_juice_bucket",
            () -> new BucketItem(FlopFluids.SOURCE_POOSAY_JUICE, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

    public static final RegistryObject<Item> DISC_C1 = ITEMS.register("music_disc_c1", () -> new ModDiscs(FlopSounds.DISC_C1.get(), 3480));
    public static final RegistryObject<Item> DISC_C2 = ITEMS.register("music_disc_c2", () -> new ModDiscs(FlopSounds.DISC_C2.get(), 620));
    public static final RegistryObject<Item> DISC_C3 = ITEMS.register("music_disc_c3",  () -> new ModDiscs(FlopSounds.DISC_C3.get(), 5860));
    public static final RegistryObject<Item> DISC_C4 = ITEMS.register("music_disc_c4",  () -> new ModDiscs(FlopSounds.DISC_C4.get(), 3850));
    public static final RegistryObject<Item> DISC_C5 = ITEMS.register("music_disc_c5",  () -> new ModDiscs(FlopSounds.DISC_C5.get(), 2840));
    public static final RegistryObject<Item> DISC_C6 = ITEMS.register("music_disc_c6",  () -> new ModDiscs(FlopSounds.DISC_C6.get(), 3140));
    public static final RegistryObject<Item> DISC_C7 = ITEMS.register("music_disc_c7",  () -> new ModDiscs(FlopSounds.DISC_C7.get(), 3840));
    public static final RegistryObject<Item> DISC_C8 = ITEMS.register("music_disc_c8",  () -> new ModDiscs(FlopSounds.DISC_C8.get(), 1640));
    public static final RegistryObject<Item> DISC_C9 = ITEMS.register("music_disc_c9",  () -> new ModDiscs(FlopSounds.DISC_C9.get(), 4320));
    public static final RegistryObject<Item> DISC_C10 = ITEMS.register("music_disc_c10",  () -> new ModDiscs(FlopSounds.DISC_C10.get(), 3600));
    public static final RegistryObject<Item> DISC_C11 = ITEMS.register("music_disc_c11",  () -> new ModDiscs(FlopSounds.DISC_C11.get(), 2900));
    public static final RegistryObject<Item> DISC_C12 = ITEMS.register("music_disc_c12",  () -> new ModDiscs(FlopSounds.DISC_C12.get(), 4760));
    public static final RegistryObject<Item> DISC_C13 = ITEMS.register("music_disc_c13",  () -> new ModDiscs(FlopSounds.DISC_C13.get(), 4200));
    public static final RegistryObject<Item> DISC_C14 = ITEMS.register("music_disc_c14",  () -> new ModDiscs(FlopSounds.DISC_C14.get(), 3640));
    public static final RegistryObject<Item> DISC_C15 = ITEMS.register("music_disc_c15",  () -> new ModDiscs(FlopSounds.DISC_C15.get(), 4700));
    public static final RegistryObject<Item> DISC_C16 = ITEMS.register("music_disc_c16",  () -> new ModDiscs(FlopSounds.DISC_C16.get(), 4200));
    public static final RegistryObject<Item> DISC_C17 = ITEMS.register("music_disc_c17",  () -> new ModDiscs(FlopSounds.DISC_C17.get(), 3600));
    public static final RegistryObject<Item> DISC_C18 = ITEMS.register("music_disc_c18",  () -> new ModDiscs(FlopSounds.DISC_C18.get(), 3320));
    public static final RegistryObject<Item> DISC_C19 = ITEMS.register("music_disc_c19",  () -> new ModDiscs(FlopSounds.DISC_C19.get(), 3120));
    public static final RegistryObject<Item> DISC_C20 = ITEMS.register("music_disc_c20",  () -> new ModDiscs(FlopSounds.DISC_C20.get(), 3320));
    public static final RegistryObject<Item> DISC_C21 = ITEMS.register("music_disc_c21",  () -> new ModDiscs(FlopSounds.DISC_C21.get(), 4300));
    public static final RegistryObject<Item> DISC_C22 = ITEMS.register("music_disc_c22",  () -> new ModDiscs(FlopSounds.DISC_C22.get(), 5220));
    public static final RegistryObject<Item> DISC_C23 = ITEMS.register("music_disc_c23",  () -> new ModDiscs(FlopSounds.DISC_C23.get(), 3800));
    public static final RegistryObject<Item> DISC_C24 = ITEMS.register("music_disc_c24",  () -> new ModDiscs(FlopSounds.DISC_C24.get(), 4860));
    public static final RegistryObject<Item> DISC_C25 = ITEMS.register("music_disc_c25",  () -> new ModDiscs(FlopSounds.DISC_C25.get(), 4880));
    public static final RegistryObject<Item> DISC_C26 = ITEMS.register("music_disc_c26",  () -> new ModDiscs(FlopSounds.DISC_C26.get(), 4000));
    public static final RegistryObject<Item> DISC_C27 = ITEMS.register("music_disc_c27",  () -> new ModDiscs(FlopSounds.DISC_C27.get(), 3800));
    public static final RegistryObject<Item> DISC_C28 = ITEMS.register("music_disc_c28",  () -> new ModDiscs(FlopSounds.DISC_C28.get(), 3600));
    public static final RegistryObject<Item> DISC_C29 = ITEMS.register("music_disc_c29",  () -> new ModDiscs(FlopSounds.DISC_C29.get(), 3920));
    public static final RegistryObject<Item> DISC_C30 = ITEMS.register("music_disc_c30",  () -> new ModDiscs(FlopSounds.DISC_C30.get(), 4640));
    public static final RegistryObject<Item> DISC_C31 = ITEMS.register("music_disc_c31",  () -> new ModDiscs(FlopSounds.DISC_C31.get(), 2600));
    public static final RegistryObject<Item> DISC_C32 = ITEMS.register("music_disc_c32",  () -> new ModDiscs(FlopSounds.DISC_C32.get(), 2320));
    public static final RegistryObject<Item> DISC_CJ1 = ITEMS.register("music_disc_cj1",  () -> new ModDiscs(FlopSounds.DISC_CJ1.get(), 5100));
    public static final RegistryObject<Item> DISC_CPM1 = ITEMS.register("music_disc_cpm1",  () -> new ModDiscs(FlopSounds.DISC_CPM1.get(), 3300));
    public static final RegistryObject<Item> DISC_CUPCAKKE1 = ITEMS.register("music_disc_cupcakke1",  () -> new ModDiscs(FlopSounds.DISC_CUPCAKKE1.get(), 4160));
    public static final RegistryObject<Item> DISC_CUPCAKKE2 = ITEMS.register("music_disc_cupcakke2",  () -> new ModDiscs(FlopSounds.DISC_CUPCAKKE2.get(), 3960));
    public static final RegistryObject<Item> DISC_CUPCAKKE3 = ITEMS.register("music_disc_cupcakke3",  () -> new ModDiscs(FlopSounds.DISC_CUPCAKKE3.get(), 4120));
    public static final RegistryObject<Item> DISC_CUPCAKKE4 = ITEMS.register("music_disc_cupcakke4",  () -> new ModDiscs(FlopSounds.DISC_CUPCAKKE4.get(), 3720));
    public static final RegistryObject<Item> DISC_CUPCAKKE5 = ITEMS.register("music_disc_cupcakke5",  () -> new ModDiscs(FlopSounds.DISC_CUPCAKKE5.get(), 2700));
    public static final RegistryObject<Item> DISC_CUPCAKKE6 = ITEMS.register("music_disc_cupcakke6",  () -> new ModDiscs(FlopSounds.DISC_CUPCAKKE6.get(), 2860));
    public static final RegistryObject<Item> DISC_DB1 = ITEMS.register("music_disc_db1",  () -> new ModDiscs(FlopSounds.DISC_DB1.get(), 2260));
    public static final RegistryObject<Item> DISC_DEJE_BULLYING = ITEMS.register("music_disc_deje_bullying",  () -> new ModDiscs(FlopSounds.DISC_DEJE_BULLYING.get(), 480));
    public static final RegistryObject<Item> DISC_J1 = ITEMS.register("music_disc_j1",  () -> new ModDiscs(FlopSounds.DISC_J1.get(), 5820));
    public static final RegistryObject<Item> DISC_J2 = ITEMS.register("music_disc_j2",  () -> new ModDiscs(FlopSounds.DISC_J2.get(), 4740));
    public static final RegistryObject<Item> DISC_J3 = ITEMS.register("music_disc_j3",  () -> new ModDiscs(FlopSounds.DISC_J3.get(), 1420));
    public static final RegistryObject<Item> DISC_J4 = ITEMS.register("music_disc_j4",  () -> new ModDiscs(FlopSounds.DISC_J4.get(), 1760));
    public static final RegistryObject<Item> DISC_J5 = ITEMS.register("music_disc_j5",  () -> new ModDiscs(FlopSounds.DISC_J5.get(), 4060));
    public static final RegistryObject<Item> DISC_J6 = ITEMS.register("music_disc_j6",  () -> new ModDiscs(FlopSounds.DISC_J6.get(), 3000));
    public static final RegistryObject<Item> DISC_J7 = ITEMS.register("music_disc_j7",  () -> new ModDiscs(FlopSounds.DISC_J7.get(), 1100));
    public static final RegistryObject<Item> DISC_JC1 = ITEMS.register("music_disc_jc1",  () -> new ModDiscs(FlopSounds.DISC_JC1.get(), 3340));
    public static final RegistryObject<Item> DISC_JC2 = ITEMS.register("music_disc_jc2",  () -> new ModDiscs(FlopSounds.DISC_JC2.get(), 4160));
    public static final RegistryObject<Item> DISC_JC3 = ITEMS.register("music_disc_jc3",  () -> new ModDiscs(FlopSounds.DISC_JC3.get(), 1300));
    public static final RegistryObject<Item> DISC_JIAFEI = ITEMS.register("music_disc_jiafei",  () -> new ModDiscs(FlopSounds.DISC_JIAFEI.get(), 3800));
    public static final RegistryObject<Item> DISC_K1 = ITEMS.register("music_disc_k1",  () -> new ModDiscs(FlopSounds.DISC_K1.get(), 700));
    public static final RegistryObject<Item> DISC_K2 = ITEMS.register("music_disc_k2",  () -> new ModDiscs(FlopSounds.DISC_K2.get(), 1160));
    public static final RegistryObject<Item> DISC_K3 = ITEMS.register("music_disc_k3",  () -> new ModDiscs(FlopSounds.DISC_K3.get(), 1940));
    public static final RegistryObject<Item> DISC_PM1 = ITEMS.register("music_disc_pm1",  () -> new ModDiscs(FlopSounds.DISC_PM1.get(), 6160));
    public static final RegistryObject<Item> DISC_PONMI = ITEMS.register("music_disc_ponmi",  () -> new ModDiscs(FlopSounds.DISC_PONMI.get(), 2740));
    public static final RegistryObject<Item> DISC_RANVISION = ITEMS.register("music_disc_ranvision",  () -> new ModDiscs(FlopSounds.DISC_RANVISION.get(), 720));

    public static final RegistryObject<Item> CHARLI_XCX_SPAWN_EGG = ITEMS.register("charlixcx_spawn_egg",
            () -> new ForgeSpawnEggItem(FlopEntities.CHARLI_XCX, 9162496, 329216,  new Item.Properties()));
    public static final RegistryObject<Item> CUPCAKKE_SPAWN_EGG = ITEMS.register("cupcakke_spawn_egg",
            () -> new ForgeSpawnEggItem(FlopEntities.CUPCAKKE, -13108, -7328129,  new Item.Properties()));
    public static final RegistryObject<Item> JIAFEI_SPAWN_EGG = ITEMS.register("jiafei_spawn_egg",
            () -> new ForgeSpawnEggItem(FlopEntities.JIAFEI,  -5389115, 14354697,  new Item.Properties()));
    public static final RegistryObject<Item> LOVELY_PEACHES_SPAWN_EGG = ITEMS.register("lovely_peaches_spawn_egg",
            () -> new ForgeSpawnEggItem(FlopEntities.LOVELY_PEACHES,  14241460, 16733695,  new Item.Properties()));
    public static final RegistryObject<Item> MARIAH_CAREY_SPAWN_EGG = ITEMS.register("mariah_carey_spawn_egg",
            () -> new ForgeSpawnEggItem(FlopEntities.MARIAH_CAREY,  13378335, 3450463,  new Item.Properties()));
    public static final RegistryObject<Item> KAMALA_HARRIS_SPAWN_EGG = ITEMS.register("kamala_harris_spawn_egg",
            () -> new ForgeSpawnEggItem(FlopEntities.KAMALA_HARRIS,  2430995, 13800304,  new Item.Properties()));
    public static final RegistryObject<Item> NICKI_MINAJ_SPAWN_EGG = ITEMS.register("nicki_minaj_spawn_egg",
            () -> new ForgeSpawnEggItem(FlopEntities.NICKI_MINAJ,  43690, 15782375,  new Item.Properties()));
    public static final RegistryObject<Item> RANVISION_SPAWN_EGG = ITEMS.register("ranvision_spawn_egg",
            () -> new ForgeSpawnEggItem(FlopEntities.RANVISION,  12094717, 6471409,  new Item.Properties()));
    public static final RegistryObject<Item> TWINK_SPAWN_EGG = ITEMS.register("twink_spawn_egg",
            () -> new ForgeSpawnEggItem(FlopEntities.TWINK,  170, 5636095,  new Item.Properties()));
    public static final RegistryObject<Item> TWINK_SIVAN_SPAWN_EGG = ITEMS.register("twink_sivan_spawn_egg",
            () -> new ForgeSpawnEggItem(FlopEntities.TWINK_SIVAN,  14693145, 2364032,  new Item.Properties()));

    public static final RegistryObject<Item> LOVELY_PEACH_SIGN = ITEMS.register("lovely_peach_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.LOVELY_PEACH_SIGN.get(),
                    ModBlocks.LOVELY_PEACH_WALL_SIGN.get()));

    public static final RegistryObject<Item> LOVELY_PEACH_HANGING_SIGN = ITEMS.register("lovely_peach_hanging_sign",
            () -> new HangingSignItem(ModBlocks.LOVELY_PEACH_HANGING_SIGN.get(), ModBlocks.LOVELY_PEACH_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
