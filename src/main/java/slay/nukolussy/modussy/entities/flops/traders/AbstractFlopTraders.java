package slay.nukolussy.modussy.entities.flops.traders;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slay.nukolussy.modussy.entities.flops.AbstractFlopFigures;

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

    protected abstract void updateTrades();

    @Override
    public void setTradingPlayer(@Nullable Player pTradingPlayer) {
        this.trader = pTradingPlayer;
    }

    @Nullable
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

    public boolean isTrading() {
        return this.trader != null;
    }

    protected void stopTrading() {
        this.setTradingPlayer(null);
    }

    @Override
    public void die(DamageSource pDamageSource) {
        super.die(pDamageSource);
        this.stopTrading();
    }

    @Nullable
    @Override
    public Entity changeDimension(ServerLevel pDestination) {
        this.stopTrading();
        return super.changeDimension(pDestination);
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide;
    }
}