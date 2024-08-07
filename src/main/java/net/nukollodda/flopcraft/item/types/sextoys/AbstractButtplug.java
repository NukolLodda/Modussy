package net.nukollodda.flopcraft.item.types.sextoys;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nukollodda.flopcraft.sound.FlopSounds;
import net.nukollodda.flopcraft.util.EntityMethods;
import net.nukollodda.flopcraft.util.ModUtil;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractButtplug extends Item {
    public AbstractButtplug(int durability) {
        super(new Properties().fireResistant().durability(durability));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack item = pPlayer.getItemInHand(pUsedHand);
        AtomicInteger dura = new AtomicInteger();
        EntityMethods.addEffects(pPlayer);
        pPlayer.playSound(FlopSounds.SQUIRT.get());
        ModUtil.getEntityListOfDist(pLevel, LivingEntity.class, pPlayer.position(), 4).forEach(entity -> {
            EntityMethods.addEffects(entity);
            dura.getAndIncrement();
        });
        item.setDamageValue(item.getDamageValue() + dura.get());
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
