package net.nukollodda.flopcraft.entities.twink;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.nukollodda.flopcraft.effect.FlopEffects;
import net.nukollodda.flopcraft.entities.AbstractModEntity;
import net.nukollodda.flopcraft.entities.FlopEntities;
import net.nukollodda.flopcraft.entities.flops.bosses.GirlbossSivan;
import net.nukollodda.flopcraft.item.FlopItems;
import net.nukollodda.flopcraft.item.types.tools.TwinkTransformer;
import net.nukollodda.flopcraft.sound.FlopSounds;
import net.nukollodda.flopcraft.util.ModUtil;

import java.util.function.IntFunction;

public class TwinkSivan extends AbstractTwink {
    private static final EntityDataAccessor<Integer> TWINK_ID_DATATYPE_VARIANT = SynchedEntityData.defineId(TwinkSivan.class, EntityDataSerializers.INT);
    public TwinkSivan(PlayMessages.SpawnEntity packet, Level world) {
        super(FlopEntities.TWINK_SIVAN.get(), world);
    }
    public TwinkSivan(EntityType<? extends TwinkSivan> type, Level world) {
        super(type, world);
    }

    public static void init() {
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return switch (this.random.nextInt(6)) {
            case 1 -> FlopSounds.TWINK_SIVAN_1.get();
            case 2 -> FlopSounds.TWINK_SIVAN_2.get();
            case 3 -> FlopSounds.TWINK_SIVAN_3.get();
            case 4 -> FlopSounds.TWINK_SIVAN_4.get();
            case 5 -> FlopSounds.TWINK_SIVAN_5.get();
            default -> FlopSounds.TWINK_SIVAN_6.get();
        };
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return this.random.nextBoolean() ? FlopSounds.TWINK_SIVAN_DEATH1.get() : FlopSounds.TWINK_SIVAN_DEATH2.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return this.random.nextBoolean() ? FlopSounds.TWINK_SIVAN_HURT1.get() : FlopSounds.TWINK_SIVAN_HURT2.get();
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        InteractionResult result = InteractionResult.sidedSuccess(this.level().isClientSide);
        super.mobInteract(player, hand);
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
                player.spawnAtLocation(FlopItems.TWINK_EGG.get());
                this.playSound(FlopSounds.CUPCAkKE_SLURP.get()); // may change in the future
                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            }
            double x = getX();
            double y = getY();
            double z = getZ();
            if (itemStack.getItem() instanceof TwinkTransformer &&
                    ModUtil.getEntityListOfDist(level(), GirlbossSivan.class, new Vec3(x, y, z), 128).isEmpty()) {
                this.addEffect(new MobEffectInstance(FlopEffects.YASSIFIED.get()));
                this.playSound(FlopSounds.TROYE_TRANSFORMATION.get());
                Level level = Minecraft.getInstance().level;
                if (level != null) {
                    for (int i = 0; i < 16; i++) {
                        double x0 = x + random.nextFloat();
                        double y0 = y + random.nextFloat();
                        double z0 = z + random.nextFloat();
                        double dx = (random.nextFloat() - 0.5) * 0.5;
                        double dy = (random.nextFloat() - 0.5) * 0.5;
                        double dz = (random.nextFloat() - 0.5) * 0.5;

                        level.addParticle(ParticleTypes.HEART, x0, y0, z0, dx, dy, dz);
                        level.addParticle(ParticleTypes.POOF, x0, y0, z0, dx, dy, dz);
                    }
                }
                this.convertTo(FlopEntities.GIRLBOSS_SIVAN.get(), true);
            }
        }
        return result;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() != null) {
            alertTwinks(source);
        }
        if (this.getRandom().nextBoolean()) {
            Twink twink = new Twink(FlopEntities.TWINK.get(), this.level(), true);
            twink.teleportTo(this.getX(), this.getY(), this.getZ());
            this.level().addFreshEntity(twink);
        }
        if (source.is(DamageTypeTags.IS_DROWNING) || source.is(DamageTypeTags.IS_FIRE) ||
                source.is(DamageTypeTags.IS_FIRE) || source.is(DamageTypeTags.IS_LIGHTNING) ||
                source.is(DamageTypeTags.IS_EXPLOSION) || source.is(DamageTypeTags.IS_FREEZING)) {
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public boolean canMate(AbstractModEntity pEntity) {
        return false;
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
        tag.put("Inventory", this.inventory.createTag());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setTypeVariant(tag.getInt("Variant"));
        this.inventory.fromTag(tag.getList("Inventory",10));
    }

    public void setVariant(TwinkSivan.Variant variant) {
        this.setTypeVariant(variant.getId());
    }

    public void setRandomVariant() {
        this.setTypeVariant(this.random.nextInt(4));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TWINK_ID_DATATYPE_VARIANT, 0);
    }

    private void setTypeVariant(int id) {
        this.entityData.set(TWINK_ID_DATATYPE_VARIANT, id);
    }
    private int getTypeVariant() {
        return this.entityData.get(TWINK_ID_DATATYPE_VARIANT);
    }
    public TwinkSivan.Variant getVariant() {
        return TwinkSivan.Variant.byId(this.getTypeVariant() & 255);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, @NotNull DifficultyInstance instance, @NotNull MobSpawnType type, SpawnGroupData data, CompoundTag tag) {
        RandomSource randomSource = level.getRandom();
        TwinkSivan.Variant variant = Util.getRandom(TwinkSivan.Variant.values(), randomSource);
        setVariant(variant);
        return super.finalizeSpawn(level, instance, type, data, tag);
    }

    protected SpawnGroupData superFinalize(ServerLevelAccessor level, DifficultyInstance instance, MobSpawnType type, SpawnGroupData data, CompoundTag tag) {
        return super.finalizeSpawn(level, instance, type, data, tag);
    }

    public enum Variant implements StringRepresentable {
        RUSH(0, "rush"),
        STARTED(1, "started"),
        BATH(2, "bath"),
        VEST(3, "vest"); // girlboss sivan is his own entity

        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        public final int id;
        public final String name;
        Variant(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public int getId() {
            return this.id;
        }
        @Override
        public String getSerializedName() {
            return this.name;
        }
        public static Variant byId(int id) {
            return BY_ID.apply(id);
        }
    }
}
