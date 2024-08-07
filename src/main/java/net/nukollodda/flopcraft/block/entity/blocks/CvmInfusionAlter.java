package net.nukollodda.flopcraft.block.entity.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import net.nukollodda.flopcraft.block.entity.ent.CvmInfusionAlterEntity;
import net.nukollodda.flopcraft.block.entity.ent.FlopBlockEntities;

public class CvmInfusionAlter extends BaseEntityBlock {
    public CvmInfusionAlter() {
        super(Properties.ofFullCopy(Blocks.SMITHING_TABLE)
                .strength(6f).requiresCorrectToolForDrops().sound(SoundType.METAL).noOcclusion());
    }

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 12,16);
    /*
    param 1-3 = beginning x y z respectively
    param 4-6 = ending x y z respectively
     */

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override // sets visibility for blocks
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override // calls the drops method allowing for drops
    public void onRemove(BlockState state, Level lvl, BlockPos pos, BlockState newState, boolean moving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEnt = lvl.getBlockEntity(pos);
            if (blockEnt instanceof CvmInfusionAlterEntity) {
                ((CvmInfusionAlterEntity) blockEnt).drops();
            }
        }
        super.onRemove(state, lvl, pos, newState, moving);
    }

    @Override
    public InteractionResult use(BlockState state, Level lvl, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (!lvl.isClientSide()) {
            BlockEntity ent = lvl.getBlockEntity(pos);
            if (ent instanceof CvmInfusionAlterEntity alterEntity) { // opens the screen upon right clicking
                player.stopUsingItem();
                NetworkHooks.openScreen(((ServerPlayer) player), alterEntity, pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return super.use(state, lvl, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CvmInfusionAlterEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level lvl, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, FlopBlockEntities.CVM_INFUSION_ALTER.get(),
                CvmInfusionAlterEntity::tick);
    }
}
