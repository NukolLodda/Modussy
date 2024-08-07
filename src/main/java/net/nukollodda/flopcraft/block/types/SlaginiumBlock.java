package net.nukollodda.flopcraft.block.types;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.nukollodda.flopcraft.entities.FlopEntities;
import net.nukollodda.flopcraft.entities.flops.traders.NickiMinaj;
import net.nukollodda.flopcraft.util.EntityMethods;
import net.nukollodda.flopcraft.util.ModUtil;
import net.nukollodda.flopcraft.util.ToolMethods;
import net.nukollodda.flopcraft.item.FlopItems;
import net.nukollodda.flopcraft.sound.FlopSounds;

import java.util.List;

public class SlaginiumBlock extends MaterialBlocks {
    public SlaginiumBlock() {
        super(Blocks.GOLD_BLOCK, SoundType.METAL);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        super.stepOn(pLevel, pPos, pState, pEntity);
        double x = pPos.getX();
        double y = pPos.getY();
        double z = pPos.getZ();
        if (pEntity instanceof ItemEntity item && item.getItem().is(FlopItems.SCARUSSY.get())
           && EntityMethods.canCertainEntitySpawn(NickiMinaj.class, pLevel, pPos.getCenter())) {
            Player player = null;
            List<LivingEntity> entities = ModUtil.getEntityListOfDist(pLevel, LivingEntity.class, pPos.getCenter(), 4);
            for (LivingEntity iterator : entities) {
                if (iterator instanceof Player closest) {
                    player = closest;
                    break;
                }
            }
            for (LivingEntity entity : entities) {
                ToolMethods.yassification(entity, pLevel, player);
                EntityMethods.addEffects(entity);
            }
            pLevel.destroyBlock(pPos, false, item);

            LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel);
            bolt.moveTo(x, y + 1, z);
            pLevel.addFreshEntity(bolt);

            NickiMinaj minaj = new NickiMinaj(FlopEntities.NICKI_MINAJ.get(), pLevel);
            minaj.moveTo(x, y + 1, z);
            minaj.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 3));
            if (player != null) {
                minaj.setTamed(player);
            }
            minaj.playSound(FlopSounds.NICKI_MINAJ_SUMMON.get());

            pLevel.addFreshEntity(minaj);
            item.setRemoved(Entity.RemovalReason.DISCARDED);
        }
    }
}
