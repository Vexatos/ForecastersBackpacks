package vexatos.backpacks.backpack;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IBoxable;
import ic2.core.item.IHandHeldInventory;
import ic2.core.item.ItemRadioactive;
import net.minecraft.item.ItemStack;
import vexatos.backpacks.ForecastersBackpacks;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackIndustrialist extends BackpackModBase {
	public BackpackIndustrialist() {
		super("industrialist", 0xD1D9D9, 0xFFFFFF, Mods.IC2);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return GameRegistry.findItem(Mods.IC2, "itemToolbox");
	}

	@Override
	public boolean isValidItem(ItemStack stack) {
		if(stack == null) {
			return false;
		}
		if(stack.getItem() instanceof IBoxable && ((IBoxable) stack.getItem()).canBeStoredInToolbox(stack)) {
			return true;
		}
		if(super.isValidItem(stack)) {
			try {
				return !(stack.getItem() instanceof ItemRadioactive || stack.getItem() instanceof IHandHeldInventory);
			} catch(Throwable t) {
				ForecastersBackpacks.log.error(t);
				return false;
			}
		}
		return false;
	}
}
