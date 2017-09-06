package vexatos.backpacks.backpack;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import vexatos.backpacks.reference.Mods;

import javax.annotation.Nullable;

/**
 * @author Vexatos
 */
public class BackpackFlamingo extends BackpackModBase {

	public BackpackFlamingo() {
		super("flamingo", 0xFF61D4, 0xFFFF00, Mods.Flamingo);
	}

	@Nullable
	@Override
	protected Object getCraftingItem(String mod) {
		return Item.REGISTRY.getObject(new ResourceLocation(Mods.Flamingo, "flamingo.flamingo"));
	}
}
