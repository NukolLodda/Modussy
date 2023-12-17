package slay.nukolussy.modussy.item.types.jiafei;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import slay.nukolussy.modussy.effect.ModEffects;
import slay.nukolussy.modussy.item.ModItems;
import slay.nukolussy.modussy.sound.ModSounds;
import slay.nukolussy.modussy.util.EntityMethods;
import slay.nukolussy.modussy.util.ModUtil;
import slay.nukolussy.modussy.util.ToolMethods;

import java.util.List;

public class JiafeiPerfume extends Item implements Vanishable, IJiafeiProducts {
    public JiafeiPerfume() {
        super(new Properties().durability(20));
    }

    public ItemStack hasCraftRemainderItem(ItemStack itemstack) {
        return new ItemStack(Items.GLASS_BOTTLE);
    }

    public @NotNull ItemStack finishUsingItem(ItemStack item, Level lvl, LivingEntity entity) {
        return new ItemStack(Items.GLASS_BOTTLE);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        {
            final Vec3 center = new Vec3(x, y, z);
            world.getEntitiesOfClass(LivingEntity.class, new AABB(center, center)
                    .inflate(2 / 2d), e -> true).stream().toList().forEach(ent -> {
                if (ent instanceof Cat cat && entity.getRandom().nextInt(25) < 4)
                    cat.spawnAtLocation(ModItems.POSEI.get());
                ToolMethods.yassification(ent, world, entity);
                ent.addEffect(new MobEffectInstance(ModEffects.YASSIFIED.get(), 1000, 1));
            });
        }
        if (world instanceof ServerLevel level)
            level.sendParticles(ParticleTypes.DRIPPING_WATER, x, y, z, 15,1,1, 1, 1.0);
        entity.getMainHandItem().setDamageValue(entity.getMainHandItem().getDamageValue() + 1);
        entity.giveExperiencePoints(5);
        entity.playSound(ModSounds.SPRAY.get());
        EntityMethods.flopEffects(entity);
        if (entity.getMainHandItem().getDamageValue() >= entity.getMainHandItem().getMaxDamage())
            entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.GLASS_BOTTLE));
        return ar;
    }

    public void appendHoverText(ItemStack stack, Level lvl, List<Component> components, TooltipFlag tip) {
        components.add(getDisplayName().withStyle(ChatFormatting.LIGHT_PURPLE));
    }

    @Override
    public String getEnding() {
        return "这种香水能够让你摆脱难闻的汗味并治愈你。";
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
        return new ItemStack(Items.GLASS_BOTTLE);
    }
}
