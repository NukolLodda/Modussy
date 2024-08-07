package net.nukollodda.flopcraft.util;

import net.minecraft.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.nukollodda.flopcraft.effect.FlopEffects;
import net.nukollodda.flopcraft.entities.AbstractModEntity;
import net.nukollodda.flopcraft.entities.FlopEntities;
import net.nukollodda.flopcraft.entities.flops.AbstractIconicFigures;
import net.nukollodda.flopcraft.entities.flops.AbstractIcons;
import net.nukollodda.flopcraft.entities.flops.IFlopEntity;
import net.nukollodda.flopcraft.entities.flops.special.AranaGrande;
import net.nukollodda.flopcraft.entities.flops.special.MeganTheStallion;
import net.nukollodda.flopcraft.entities.flops.traders.Jiafei;
import net.nukollodda.flopcraft.entities.flops.traders.NickiMinaj;
import net.nukollodda.flopcraft.entities.flops.traders.Ranvision;
import net.nukollodda.flopcraft.entities.twink.Twink;

public class EntityMethods {
    public static boolean isFlop(Entity entity) {
        return isFlop((LivingEntity) entity) ||
                (entity instanceof TamableAnimal tamable && isFlop(tamable.getOwner()));
    }

    private static boolean isFlop(LivingEntity living) {
        return living instanceof IFlopEntity || (living instanceof Player player && PlayerMethods.isFlop(player));
    }

    public static boolean isMonster(Entity ent) {
        return ent instanceof Monster || ((ent instanceof Hoglin || ent instanceof Ghast
                || ent instanceof Shulker || ent instanceof Phantom)) ||
                (ent instanceof Player player && PlayerMethods.isDaboyz(player));
    }

    public static boolean areFlopFigure(Entity living) {
        return living instanceof AbstractIconicFigures || living instanceof AranaGrande || living instanceof MeganTheStallion
                || (living instanceof Player player && PlayerMethods.isFlopIcon(player));
    }

    public static void monsterEffects(LivingEntity entity, int lvl, int amp) {
        if (entity instanceof Mob) {
            ((Mob) entity).setGuaranteedDrop(EquipmentSlot.MAINHAND);
        }
        if (lvl == 1) {
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 1380 * lvl, 0));
        } else {
            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 1380 * lvl, amp));
            entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1380 * lvl, amp));
            if (lvl > 2) entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 600, 0));
        }
        removeFlopEffects(entity, lvl);
    }

    public static void monsterEffects(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 6900, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 1710, 0));
        removeFlopEffects(entity);
    }

    public static void flopEffects(LivingEntity entity, int lvl, int amp) {
        if (entity instanceof Player player) {
            PlayerMethods.addPlayerYassification(player, amp + lvl);
        }
        entity.addEffect(new MobEffectInstance(FlopEffects.YASSIFIED.get(), 600 * lvl, amp));
        entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 600 * lvl, 0));
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600 * lvl, amp));
        entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300 * lvl, 0));
        if (lvl > 1) {
            entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 600 * lvl, amp));
            entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 600 * lvl, 0));
        }

        removeMonsterEffects(entity, lvl);
    }

    public static void flopEffects(LivingEntity entity) {
        if (entity instanceof Player player) {
            PlayerMethods.addPlayerYassification(player, 2);
        }
        entity.addEffect(new MobEffectInstance(FlopEffects.YASSIFIED.get(), 1000, 0));
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1710, 0));
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6900, 1));
        entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 1710, 0));
        entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1710, 0));

        removeMonsterEffects(entity);
    }

    public static void removeMonsterEffects(LivingEntity entity, int lvl) {
        entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        entity.removeEffect(MobEffects.BLINDNESS);
        entity.removeEffect(MobEffects.POISON);
        if (lvl > 1) {
            entity.removeEffect(MobEffects.WITHER);
            if (lvl > 2) {
                entity.removeEffect(MobEffects.LEVITATION);
            }
        }
    }

    public static void addEffects(LivingEntity entity) {
        if (entity instanceof Player player) {
            PlayerMethods.addPlayerYassification(player, 1);
        }
        if (isFlop(entity)) {
            flopEffects(entity);
            removeMonsterEffects(entity);
        } else if (isMonster(entity)) {
            monsterEffects(entity);
            removeFlopEffects(entity);
        }
        entity.addEffect(new MobEffectInstance(FlopEffects.YASSIFIED.get(), 1000, 0));
    }

    public static void addEffects(LivingEntity entity, int lvl, int amp) {
        if (entity instanceof Player player) {
            PlayerMethods.addPlayerYassification(player, lvl);
        }
        if (isFlop(entity)) {
            flopEffects(entity, lvl, amp);
            removeMonsterEffects(entity, lvl);
        } else if (isMonster(entity)) {
            monsterEffects(entity, lvl, amp);
            removeFlopEffects(entity, lvl);
        }
    }

    public static void removeMonsterEffects(LivingEntity entity) {
        entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        entity.removeEffect(MobEffects.WITHER);
        entity.removeEffect(MobEffects.DIG_SLOWDOWN);
        entity.removeEffect(MobEffects.WEAKNESS);
        entity.removeEffect(MobEffects.BLINDNESS);
    }

    public static void removeFlopEffects(LivingEntity entity, int lvl) {
        removeFlopEffects(entity);
        if (lvl > 2) entity.removeEffect(MobEffects.HEALTH_BOOST);
    }
    public static void removeFlopEffects(LivingEntity entity) {
        entity.removeEffect(MobEffects.MOVEMENT_SPEED);
        entity.removeEffect(MobEffects.REGENERATION);
        entity.removeEffect(MobEffects.ABSORPTION);
        entity.removeEffect(MobEffects.FIRE_RESISTANCE);
        entity.removeEffect(MobEffects.DAMAGE_RESISTANCE);
        entity.removeEffect(MobEffects.DIG_SPEED);
        entity.removeEffect(MobEffects.DAMAGE_BOOST);
        entity.removeEffect(MobEffects.WATER_BREATHING);
        entity.removeEffect(MobEffects.NIGHT_VISION);
    }

    public static boolean canCertainEntitySpawn(Class<? extends Entity> pEntClass, LevelAccessor pLevel, Vec3 pCenter) {
        return canCertainEntitySpawn(pEntClass, pLevel, 128, pCenter);
    }

    public static boolean canCertainEntitySpawn(Class<? extends Entity> pEntClass, LevelAccessor pLevel, int pForbidden, Vec3 pCenter) {
        return ModUtil.getEntityListOfDist(pLevel, pEntClass, pCenter, pForbidden).isEmpty();
    }

    static void villagerYassification(AbstractVillager entity, LevelAccessor world, Player player) {
        int randval = ModUtil.RANDOM.nextInt(6971);
        float xRot = entity.getXRot();
        float yRot = entity.getYRot();
        EntityType<? extends AbstractModEntity> type = FlopEntities.TWINK.get();
        // the villager should have a 50/50 chance of either becoming a female flop or a twink
        if (randval < 34)
            type = FlopEntities.CUPCAKKE.get();
        else if (randval < 69)
            type = FlopEntities.JIAFEI.get();
        else if (randval < 90)
            type = FlopEntities.RANVISION.get();
        else if (randval < 120 && canCertainEntitySpawn(NickiMinaj.class ,world, entity.position())) {
            type = FlopEntities.NICKI_MINAJ.get();
        }
        AbstractModEntity modEntity = entity.convertTo(type, true);
        if (modEntity != null) {
            if (modEntity instanceof Twink twink)
                twink.setVariant(Util.getRandom(Twink.Variant.values(), world.getRandom()));
            if (modEntity instanceof Jiafei jiafei)
                jiafei.setVariant(Util.getRandom(Jiafei.Variant.values(), world.getRandom()));
            if (modEntity instanceof NickiMinaj minaj)
                minaj.setVariant(Util.getRandom(NickiMinaj.Variant.values(), world.getRandom()));
            if (modEntity instanceof Ranvision ranvision)
                ranvision.setVariant(Util.getRandom(Ranvision.Variant.values(), world.getRandom()));
            modEntity.setCanPickUpLoot(true);
            modEntity.setXRot(xRot);
            modEntity.setYRot(yRot);
            modEntity.setTamed(player);
            modEntity.addEffect(new MobEffectInstance(FlopEffects.YASSIFIED.get()));
            modEntity.addAdditionalSaveData(modEntity.getPersistentData());
        }
    }

    static void witchYassification(Witch entity, LevelAccessor world, Player player) {
        float xRot = entity.getXRot();
        float yRot = entity.getYRot();
        EntityType<? extends AbstractIcons> type;
        if (ModUtil.RANDOM.nextInt(3) == 0 && canCertainEntitySpawn(NickiMinaj.class, world, entity.position()))
            type = FlopEntities.NICKI_MINAJ.get();
        else
            type = ModUtil.RANDOM.nextBoolean() ? FlopEntities.CUPCAKKE.get() : FlopEntities.JIAFEI.get();
        // witches can only turn into female flops, and not into twinks, this will be implemented at a different time
        AbstractIcons flop = entity.convertTo(type, true);
        if (flop != null) {
            if (flop instanceof Jiafei jiafei) {
                jiafei.setVariant(Util.getRandom(Jiafei.Variant.values(), world.getRandom()));
            } if (flop instanceof NickiMinaj minaj)
                minaj.setVariant(Util.getRandom(NickiMinaj.Variant.values(), world.getRandom()));
            flop.setCanPickUpLoot(true);
            flop.setXRot(xRot);
            flop.setYRot(yRot);
            flop.setTamed(player);
            flop.getBrain().eraseMemory(MemoryModuleType.ANGRY_AT);
            flop.addEffect(new MobEffectInstance(FlopEffects.YASSIFIED.get()));
            flop.addAdditionalSaveData(flop.getPersistentData());
        }
    }

    public static void alertFlops(Level world, Vec3 pos, Player player) {
        ModUtil.getEntityListOfDist(world, AbstractIcons.class, pos, 2.5).forEach(flop -> flop.alertFlops(player));
    }

    public static void twinkification() {
        // todo
    }
}
