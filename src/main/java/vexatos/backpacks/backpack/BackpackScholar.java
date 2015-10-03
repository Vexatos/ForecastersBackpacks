package vexatos.backpacks.backpack;

import jds.bibliocraft.Config;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vexatos.backpacks.ForecastersBackpacks;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
public class BackpackScholar extends BackpackModBase {
	public BackpackScholar() {
		super("scholar", 0xB77400, 0xFFFFFF, Mods.BiblioCraft);
	}

	@Override
	protected Object getCraftingItem(String mod) {
		return Items.writable_book;
	}

	@Override
	public void initialize() {
		super.initialize();
		this.addValidItem(Items.paper);
		this.addValidItem(Items.book);
		this.addValidItem(Items.writable_book);
		this.addValidItem(Items.written_book);
		this.addValidItem(Items.map);
		this.addValidItem(Items.filled_map);
		this.addValidItem(Items.compass);
	}

	@Override
	public boolean isValidItem(ItemStack stack) {
		if(super.isValidItem(stack)) {
			return true;
		}
		if(stack == null) {
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
