package vexatos.backpacks.backpack;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vexatos.backpacks.reference.Mods;

import javax.annotation.Nullable;

/**
 * @author Vexatos
 */
public class BackpackPneumatic extends BackpackModBase {
	public BackpackPneumatic() {
		super("pneumatic", 0x808080, 0xFFFFFF, Mods.PneumaticCraft);
	}

	@Nullable
	@Override
	protected Object getCraftingItem(String mod) {
		Item plastic = Item.REGISTRY.getObject(new ResourceLocation(Mods.PneumaticCraft, "plastic"));
		return plastic != null ? new ItemStack(plastic, 1, 8) : null;
	}
}
