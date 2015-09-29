package vexatos.backpacks.backpack;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackPneumatic extends BackpackModBase {
	public BackpackPneumatic() {
		super("pneumatic", 0x808080, 0xFFFFFF, Mods.PneumaticCraft);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		Item plastic = GameRegistry.findItem(Mods.PneumaticCraft, "plastic");
		return plastic != null ? new ItemStack(plastic, 1, 8) : null;
	}
}
