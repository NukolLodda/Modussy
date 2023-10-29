package slay.nukolussy.modussy.entities.flops.traders;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;
import slay.nukolussy.modussy.entities.flops.AbstractFlopFigures;
import slay.nukolussy.modussy.util.PlayerMethods;

public abstract class AbstractFlopTraders extends AbstractFlopFigures implements InventoryCarrier, Npc, Merchant {
    protected MerchantOffers offers;
    private Player trader;

    private final SimpleContainer inventory = new SimpleContainer(8);
    public AbstractFlopTraders(EntityType type, Level world) {
        super(type, world);
    }

    @Override
    public SimpleContainer getInventory() {
        return inventory;
    }

    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.updateTrades();
        }
        return this.offers;
    }
    protected SoundEvent getEatSound() {
        return null;
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        InteractionResult result = InteractionResult.sidedSuccess(this.level().isClientSide);
        Item item = itemStack.getItem();

        if (this.level().isClientSide) {
            boolean flag = this.itemIsFood(item);
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (this.itemIsFood(item)) {
                if (this.getHealth() < this.getMaxHealth()) {
                    this.heal(3f);
                }
                if (!pPlayer.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                this.playSound(getEatSound());
                this.setInLove(pPlayer);
                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            } else if (this.isAlive() && !(this.isTrading() || pPlayer.isSecondaryUseActive() || this.itemIsSpawnEgg(item))) {
                if (this.getOffers().isEmpty()) {
                    this.playSound(getTradelessSound());
                } else if (!(this.isInsidePortal || this.isUnderWater())) {
                    pPlayer.stopUsingItem();
                    this.playSound(getNotifyTradeSound());
                    this.startTrading(pPlayer);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }
        return result;
    }
    protected boolean itemIsFood(Item pItem) {
        return false;
    }

    @Override
    public void setTradingPlayer(@Nullable Player pTradingPlayer) {
        if (pTradingPlayer != null) {
            if (!PlayerMethods.isNewgen(pTradingPlayer)) {
                this.trader = pTradingPlayer;
            }
        } else {
            this.trader = null;
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        this.stopTrading();
        return super.hurt(source, amount);
    }

    @Override
    public Player getTradingPlayer() {
        return this.trader;
    }

    @Override
    public boolean showProgressBar() {
        return false;
    }
    public void notifyTrade(MerchantOffer pOffer) {
    }

    @Override
    public boolean isUnderWater() {
        this.stopTrading();
        return super.isUnderWater();
    }

    public boolean isTrading() {
        return this.trader != null;
    }

    public void stopTrading() {
        this.trader = null;
    }

    @Override
    public void baseTick() { // method must exist to allow multiple trades on a server
        if (this.trader != null && !this.trader.hasContainerOpen()) {
            this.stopTrading();
        }
        super.baseTick();
    }

    @Override
    public void die(DamageSource pDamageSource) {
        this.stopTrading();
        super.die(pDamageSource);
    }

    @Nullable
    @Override
    public Entity changeDimension(ServerLevel pDestination) {
        this.stopTrading();
        return super.changeDimension(pDestination);
    }
    protected void startTrading(Player pPlayer) {
        this.setTradingPlayer(pPlayer);
        this.openTradingScreen(pPlayer, this.getDisplayName(), 1);
        this.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        this.getNavigation().stop();
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide;
    }

    protected abstract void updateTrades();
    protected abstract SoundEvent getTradelessSound();
    protected abstract boolean itemIsSpawnEgg(Item pItem);

    /*
     * Mobs under this category:
     * Jiafei
     * Nicki Minaj
     */
}
