package vexatos.backpacks.backpack;

import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackWarrior extends BackpackModBase {
	public BackpackWarrior() {
		super("warrior", 0x151515, 0xF0F000, Mods.WeaponMod, Mods.QuiverBow);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return Items.iron_sword;
	}

	@Override
	public void initialize() {
		super.initialize();
		this.addValidItem(Items.arrow);
	}

	@Override
	public boolean isValidItem(ItemStack stack) {
		return super.isValidItem(stack) || stack != null && (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow);
	}
}
