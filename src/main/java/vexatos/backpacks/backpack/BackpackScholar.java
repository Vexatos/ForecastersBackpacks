package vexatos.backpacks.backpack;

import jds.bibliocraft.Config;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vexatos.backpacks.ForecastersBackpacks;
import vexatos.backpacks.reference.Mods;

import javax.annotation.Nullable;

/**
 * @author Vexatos
 */
public class BackpackScholar extends BackpackModBase {
	public BackpackScholar() {
		super("scholar", 0xB77400, 0xFFFFFF, Mods.BiblioCraft);
	}

	@Nullable
	@Override
	protected Object getCraftingItem(String mod) {
		return Items.WRITABLE_BOOK;
	}

	@Override
	public void initialize() {
		super.initialize();
		this.addValidItem(Items.PAPER);
		this.addValidItem(Items.BOOK);
		this.addValidItem(Items.WRITABLE_BOOK);
		this.addValidItem(Items.WRITTEN_BOOK);
		this.addValidItem(Items.MAP);
		this.addValidItem(Items.FILLED_MAP);
		this.addValidItem(Items.COMPASS);
	}

	@Override
	public boolean isValidItem(ItemStack stack) {
		if(super.isValidItem(stack)) {
			return true;
		}
		if(stack.isEmpty()) {
			return false;
		}
		try {
			if(!Config.isBlock(stack) && Config.testBookValidity(stack)) {
				return true;
			}
		} catch(Throwable t) {
			ForecastersBackpacks.log.error(t);
			return false;
		}
		return false;
	}
}
