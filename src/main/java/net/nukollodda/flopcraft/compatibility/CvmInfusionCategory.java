package net.nukollodda.flopcraft.compatibility;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.nukollodda.flopcraft.Flopcraft;
import net.nukollodda.flopcraft.block.ModBlocks;
import net.nukollodda.flopcraft.recipes.CvmInfusionAlterRecipe;

public class CvmInfusionCategory implements IRecipeCategory<CvmInfusionAlterRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Flopcraft.MODID, "cvm_infusion");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Flopcraft.MODID, "textures/screens/cvm_infusion_altar_gui.png");

    public static final RecipeType<CvmInfusionAlterRecipe> CVM_INFUSION_TYPE =
            new RecipeType<>(UID, CvmInfusionAlterRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public CvmInfusionCategory(IGuiHelper pHelper) {
        this.background = pHelper.createDrawable(TEXTURE, 0, 0, 176,84);
        this.icon = pHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.CVM_INFUSION_ALTER.get()));
    }

    @Override
    public RecipeType<CvmInfusionAlterRecipe> getRecipeType() {
        return CVM_INFUSION_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.flopcraft.cvm_infusion_alter");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CvmInfusionAlterRecipe recipe, IFocusGroup focuses) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                builder.addSlot(RecipeIngredientRole.INPUT, 30 + (j * 18), 17 + (i * 18))
                        .addIngredients(recipe.getIngredients().get((i * 3) + j));
            }
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 48, 53).addIngredients(recipe.getIngredients().get(6));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 35).addItemStack(recipe.getResultItem(null));
    }
}
