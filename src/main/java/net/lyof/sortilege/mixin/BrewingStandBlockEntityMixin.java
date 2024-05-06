package net.lyof.sortilege.mixin;

import net.lyof.sortilege.brewing.BetterBrewingRecipe;
import net.lyof.sortilege.brewing.BetterBrewingRegistry;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandBlockEntityMixin {
    @Inject(method = "isValid", at = @At("RETURN"), cancellable = true)
    public void isValid(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (slot != 3 && slot != 4) {
            for (BetterBrewingRecipe recipe : BetterBrewingRegistry.getAll()) {
                if (recipe.isInput(stack)) cir.setReturnValue(true);
            }
        }
    }
}
