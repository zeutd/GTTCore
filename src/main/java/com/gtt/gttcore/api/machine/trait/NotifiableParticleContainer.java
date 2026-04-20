package com.gtt.gttcore.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.trait.MachineTraitType;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gtt.gttcore.api.GTTCapabilityHelper;
import com.gtt.gttcore.api.capability.IParticleContainer;
import com.gtt.gttcore.api.capability.recipe.ParticleRecipeCapability;
import com.gtt.gttcore.api.particle.ParticleStack;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class NotifiableParticleContainer extends NotifiableRecipeHandlerTrait<ParticleStack> implements IParticleContainer {

    public static final MachineTraitType<NotifiableParticleContainer> TYPE = new MachineTraitType<>(NotifiableParticleContainer.class);

    @Getter
    @Setter
    public boolean enabled = true;

    @Getter
    public final IO handlerIO;
    @Setter
    @Getter
    public ParticleStack stored = ParticleStack.EMPTY;

    @Setter
    @Getter
    public int capacity;

    @Setter
    @Getter
    protected int ticksPerCycle = 5;

    @Nullable
    protected TickableSubscription autoIOSubs;

    public NotifiableParticleContainer(IO io, int capacity) {
        this.capacity = capacity;
        this.handlerIO = io;
    }

    public void exportToNearby(Direction... facings) {
        if (isEmpty()) return;
        var level = getMachine().getLevel();
        var pos = getMachine().getBlockPos();
        for (Direction facing : facings) {
            if (!canOutput(facing)) continue;
            var particleContainer = GTTCapabilityHelper.getParticle(level, pos.relative(facing), facing.getOpposite());
            if (particleContainer != null && particleContainer.canInput(facing.getOpposite())){
                extractParticle(particleContainer.acceptParticle(stored));
            }
        }
    }

    public void importFromNearby(Direction... facings) {
        var level = getMachine().getLevel();
        var pos = getMachine().getBlockPos();
        for (Direction facing : facings) {
            if (!canInput(facing)) continue;
            var particleContainer = GTTCapabilityHelper.getParticle(level, pos.relative(facing), facing.getOpposite());
            if (particleContainer != null && particleContainer.canOutput(facing.getOpposite())){
                particleContainer.extractParticle(acceptParticle(particleContainer.getStored()));
            }
        }
    }

    @Override
    public void onMachineLoad() {
        super.onMachineLoad();
        autoIOSubs = getMachine().subscribeServerTick(autoIOSubs, this::autoIO);
    }

    @Override
    public void onMachineUnload() {
        super.onMachineUnload();
        if (autoIOSubs != null) {
            autoIOSubs.unsubscribe();
            autoIOSubs = null;
        }
    }

    @Override
    public List<ParticleStack> handleRecipeInner(IO io, GTRecipe recipe, List<ParticleStack> left, boolean simulate) {
        if (io == IO.OUT) {
            for (var it = left.listIterator(); it.hasNext();) {
                var stack = it.next();
                if (stored.isEmpty()) stored = new ParticleStack(stack.getType(), 0);
                if (stack.is(stored.getType())){
                    int amountToAdd = Math.max(Math.min(capacity - stored.getAmount(), stack.getAmount()), 0);
                    stack.addAmount(-amountToAdd);
                    if (!simulate) stored.addAmount(amountToAdd);
                    if (stack.isEmpty()) it.remove();
                    if (stored.getAmount() >= capacity) break;
                }
            }
        }
        else {
            if (stored.isEmpty()) return left;
            for (var it = left.listIterator(); it.hasNext();) {
                var stack = it.next();
                if (stack.is(stored.getType())){
                    int amountToSubtract = Math.min(stored.getAmount(), stack.getAmount());
                    stack.addAmount(-amountToSubtract);
                    if (!simulate) stored.addAmount(-amountToSubtract);
                    if (stack.isEmpty()) it.remove();
                    if (stored.getAmount() <= 0) break;
                }
            }
        }
        return left.isEmpty() ? null : left;
    }

    @Override
    public @NotNull List<Object> getContents() {
        return Collections.singletonList(stored);
    }


    public boolean isEmpty() {
        return stored.isEmpty();
    }

    @Override
    public double getTotalContentAmount() {
        return stored.getAmount();
    }

    @Override
    public RecipeCapability<ParticleStack> getCapability() {
        return ParticleRecipeCapability.CAP;
    }

    @Override
    public @NotNull MachineTraitType<?> getTraitType() {
        return TYPE;
    }

    @Override
    public int acceptParticle(ParticleStack stack) {
        if (stack == null || stack.isEmpty()) return 0;
        if (stored == null || stored.isEmpty()){
            int amountToAdd = Math.min(capacity, stack.getAmount());
            stored = stack.copy();
            stored.setAmount(amountToAdd);
            return amountToAdd;
        }
        if (stack.is(stored.getType())){
            int amountToAdd = Math.min(capacity - stored.getAmount(), stack.getAmount());
            stored.addAmount(amountToAdd);
            return amountToAdd;
        }
        return 0;
    }

    @Override
    public int extractParticle(int amountToExtract){
        if (stored == null || stored.isEmpty()) return 0;
        amountToExtract = Math.min(stored.getAmount(), amountToExtract);
        stored.addAmount(-amountToExtract);
        return amountToExtract;
    }

    @Override
    public boolean canInput(Direction face) {
        return handlerIO.support(IO.IN);
    }

    @Override
    public boolean canOutput(Direction face) {
        return handlerIO.support(IO.OUT);
    }

    protected void autoIO(){
        if (!enabled || (getMachine().getOffsetTimer() % ticksPerCycle) != 0) return;
        if (handlerIO == IO.OUT) {
            exportToNearby(getMachine().getFrontFacing());
        } else if (handlerIO == IO.IN) {
            importFromNearby(getMachine().getFrontFacing());
        }
    }
}
