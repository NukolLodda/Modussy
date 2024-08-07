package net.nukollodda.flopcraft.entities.flops.figures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.network.PlayMessages;
import net.nukollodda.flopcraft.block.ModBlocks;
import net.nukollodda.flopcraft.entities.FlopEntities;
import net.nukollodda.flopcraft.entities.flops.AbstractIconicFigures;
import net.nukollodda.flopcraft.entities.flops.IMerflop;
import net.nukollodda.flopcraft.item.FlopItems;
import net.nukollodda.flopcraft.sound.FlopSounds;
import net.nukollodda.flopcraft.util.ModUtil;

import java.util.Collection;
import java.util.Random;

public class CupcakKe extends AbstractIconicFigures implements IMerflop {
    public ItemLike item = FlopItems.CVMTITPLASM.get();

    public CupcakKe(EntityType<CupcakKe> type, Level world) {
        super(type, world);
    }

    public CupcakKe(PlayMessages.SpawnEntity spawnEntity, Level level) {
        super(spawnEntity.getEntity().getType(), level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2d, FOOD_ITEMS, false));
        super.registerGoals();
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public SoundEvent getAmbientSound() {
        if (ModUtil.isNewYears()) {
            int randVal = this.random.nextInt(3);
            return switch (randVal) {
                case 1 -> FlopSounds.CUPCAkKE_NEW_YEARS_1.get();
                case 2 -> FlopSounds.CUPCAkKE_NEW_YEARS_2.get();
                default -> FlopSounds.CUPCAkKE_NEW_YEARS_3.get();
            };
        }
        int randNum = this.random.nextInt(10);
        return switch (randNum) {
            case 1 -> FlopSounds.CUPCAkKE_1.get();
            case 2 -> FlopSounds.CUPCAkKE_2.get();
            case 3 -> FlopSounds.CUPCAkKE_3.get();
            case 4 -> FlopSounds.CUPCAkKE_4.get();
            case 5 -> FlopSounds.CUPCAkKE_5.get();
            case 6 -> FlopSounds.CUPCAkKE_6.get();
            case 7 -> FlopSounds.CUPCAkKE_7.get();
            case 8 -> FlopSounds.CUPCAkKE_8.get();
            case 9 -> FlopSounds.CUPCAkKE_9.get();
            default -> FlopSounds.CUPCAkKE_10.get();
        };
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        int hurtNum = this.random.nextInt(4);
        return switch (hurtNum) {
            case 1 -> FlopSounds.CUPCAkKE_HURT1.get();
            case 2 -> FlopSounds.CUPCAkKE_HURT2.get();
            case 3 -> FlopSounds.CUPCAkKE_HURT3.get();
            default -> FlopSounds.CUPCAkKE_HURT4.get();
        };
    }

    @Override
    public SoundEvent getDeathSound() {
        return FlopSounds.CUPCAkKE_DEATH.get();
    }

    @Override
    public void tick() {
        super.tick();
        this.baseTick();
    }

    @Override
    public void baseTick() {
        super.baseTick();
        Level world = this.level();
        if (this.random.nextInt(4200) == 0) {
            if (!world.isClientSide()) {
                ItemEntity entityToSpawn = new ItemEntity(world, this.getX(), this.getY(), this.getZ(), new ItemStack(FlopItems.CUPCAKE.get()));
                entityToSpawn.setPickUpDelay(10);
                world.addFreshEntity(entityToSpawn);
            }
            if (world.getBlockState(this.getOnPos().above()).getBlock().equals(ModBlocks.CVM_FLUID.get())) {
                cupcakkeDuplication(FlopItems.CVM.get(), this);
            }
        }
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        InteractionResult result = InteractionResult.sidedSuccess(this.level().isClientSide);
        super.mobInteract(player, hand);
        Item item = itemStack.getItem();
        boolean isCvm = itemStack.is(FlopItems.CVM.get()) || itemStack.is(FlopItems.CVMIUM.get());
        if (this.level().isClientSide) {
            return isCvm ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (isCvm) {
                if (this.getHealth() < this.getMaxHealth()) {
                    this.heal(3f);
                }
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                cupcakkeDuplication(item, this);
                this.playSound(FlopSounds.CUPCAkKE_SLURP.get());
                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            }
        }
        return result;
    }

    public static Item cupcakkeDrops() {
        int rand = new Random().nextInt(69);
        Item cupcakkeDrops;
        if (rand < 1) {
            int discRand = new Random().nextInt(29);
            cupcakkeDrops = switch(discRand) {
                case 1 -> FlopItems.DISC_C1.get();
                case 2 -> FlopItems.DISC_C2.get();
                case 3 -> FlopItems.DISC_C3.get();
                case 4 -> FlopItems.DISC_C4.get();
                case 5 -> FlopItems.DISC_C5.get();
                case 6 -> FlopItems.DISC_C6.get();
                case 7 -> FlopItems.DISC_C7.get();
                case 8 -> FlopItems.DISC_C8.get();
                case 9 -> FlopItems.DISC_C9.get();
                case 10 -> FlopItems.DISC_C10.get();
                case 11 -> FlopItems.DISC_C11.get();
                case 12 -> FlopItems.DISC_C12.get();
                case 13 -> FlopItems.DISC_C13.get();
                case 14 -> FlopItems.DISC_C14.get();
                case 15 -> FlopItems.DISC_C15.get();
                case 16 -> FlopItems.DISC_C16.get();
                case 17 -> FlopItems.DISC_C17.get();
                case 18 -> FlopItems.DISC_C18.get();
                case 19 -> FlopItems.DISC_C19.get();
                case 20 -> FlopItems.DISC_C20.get();
                case 21 -> FlopItems.DISC_C21.get();
                case 22 -> FlopItems.DISC_C22.get();
                case 23 -> FlopItems.DISC_C23.get();
                case 24 -> FlopItems.DISC_C24.get();
                case 25 -> FlopItems.DISC_C25.get();
                case 26 -> FlopItems.DISC_C26.get();
                case 27 -> FlopItems.DISC_C27.get();
                case 28 -> FlopItems.DISC_C28.get();
                default -> FlopItems.DISC_C29.get();
            };
        } else {
            cupcakkeDrops = switch (rand) {
                case 1 -> FlopItems.DISC_CUPCAKKE1.get();
                case 2 -> FlopItems.DISC_CUPCAKKE2.get();
                case 3 -> FlopItems.DISC_CUPCAKKE3.get();
                case 4 -> FlopItems.DISC_CUPCAKKE4.get();
                case 5 -> FlopItems.DISC_CUPCAKKE5.get();
                case 6 -> FlopItems.DISC_CUPCAKKE6.get();
                default -> FlopItems.CVMTITPLASM.get();
            };
        }
        return cupcakkeDrops;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        onHurt(source);
        if (source.is(DamageTypeTags.IS_DROWNING)) {
            return false;
        }
        return super.hurt(source, amount);
    }

    public void aiStep() {
        super.aiStep();

        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.level();
        for (int i = 0; i < 4; i++) {
            double x0 = x + random.nextFloat();
            double y0 = y + random.nextFloat();
            double z0 = z + random.nextFloat();
            double dx = (random.nextFloat() - 0.5) * 0.5;
            double dy = (random.nextFloat() - 0.5) * 0.5;
            double dz = (random.nextFloat() - 0.5) * 0.5;

            world.addParticle(ParticleTypes.DRAGON_BREATH, x0, y0, z0, dx, dy, dz);
        }
        this.baseTick();
    }

    public static void init() {
    }

    public static boolean canSpawn(EntityType<CupcakKe> entityType, ServerLevelAccessor level, MobSpawnType spawnType,
                                   BlockPos pos, RandomSource randomSource) {
        return Mob.checkMobSpawnRules(entityType, level, spawnType, pos, randomSource);
    }

    public static void cupcakkeDuplication(Item item, CupcakKe cupcakKe) {
        if (cupcakKe.getMaxHealth() == cupcakKe.getHealth()) {
            Collection<MobEffectInstance> inst = cupcakKe.getActiveEffects();
            CupcakKe newMob = new CupcakKe(FlopEntities.CUPCAKKE.get(), cupcakKe.level());
            newMob.moveTo(cupcakKe.getX(), cupcakKe.getY(), cupcakKe.getZ());
            newMob.setXRot(cupcakKe.getXRot());
            newMob.setYRot(cupcakKe.getYRot());
            if (item.equals(FlopItems.CVM.get())) {
                cupcakKe.hurt(cupcakKe.level().damageSources().freeze(), 10);
            }
            if (cupcakKe.tamedBy != null) {
                newMob.setTamed(cupcakKe.tamedBy);
            }
            for (MobEffectInstance effect : inst) {
                newMob.addEffect(effect);
            }
            cupcakKe.level().addFreshEntity(newMob);
            cupcakKe.spawnAtLocation(cupcakkeDrops());
            if (ModUtil.isNewYears()) {
                cupcakKe.spawnAtLocation(FlopItems.HUNBAO.get());
            }
        }
    }
}
