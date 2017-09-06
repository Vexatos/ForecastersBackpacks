package vexatos.backpacks.misc;

import forestry.api.storage.EnumBackpackType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vexatos.backpacks.backpack.BackpackTypes;

/**
 * @author Vexatos
 */
public class CreativeTabBackpacks extends CreativeTabs {
	public CreativeTabBackpacks() {
		super("forecastersbackpacks");
	}

	@Override
	public ItemStack getTabIconItem() {
		for(BackpackTypes value : BackpackTypes.VALUES) {
			Item item = value.getItem(EnumBackpackType.NORMAL);
			if(item != null) {
				return new ItemStack(item);
			}
		}
		for(BackpackTypes value : BackpackTypes.VALUES) {
			Item item = value.getItem(EnumBackpackType.WOVEN);
			if(item != null) {
				return new ItemStack(item);
			}
		}
		return ItemStack.EMPTY;
	}
}
