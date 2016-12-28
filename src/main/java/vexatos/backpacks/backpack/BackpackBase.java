package vexatos.backpacks.backpack;

import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.storage.EnumBackpackType;
import forestry.api.storage.IBackpackDefinition;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author CovertJaguar, Vexatos
 */
public abstract class BackpackBase implements IBackpackDefinition {

	private final String key;
	private final int primaryColor;
	private final int secondaryColor;
	protected ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	protected ArrayList<ItemStack> invalidItems = new ArrayList<ItemStack>();
	private boolean isLoaded;

	public BackpackBase(String key, int primaryColor, int secondaryColor) {
		this.key = key;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
	}

	@Override
	public void addValidItem(ItemStack stack) {
		if(stack != null && stack.getItem() != null) {
			items.add(stack);
		}
	}

	public void addValidItem(Item item) {
		if(item != null) {
			addValidItem(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
		}
	}

	public void addValidItem(String mod, String name) {
		Item item = GameRegistry.findItem(mod, name);
		if(item != null) {
			addValidItem(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
		}
	}

	public void addValidStack(String mod, String name) {
		ItemStack stack = GameRegistry.findItemStack(mod, name, 1);
		if(stack != null && stack.getItem() != null) {
			addValidItem(stack);
		}
	}

	@Override
	public void addValidItems(List<ItemStack> stacks) {
		for(ItemStack stack : stacks) {
			addValidItem(stack);
		}
	}

	public void addInvalidItem(ItemStack stack) {
		if(stack != null && stack.getItem() != null) {
			invalidItems.add(stack);
		}
	}

	public void addInvalidItem(Item item) {
		if(item != null) {
			addValidItem(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
		}
	}

	public void addInvalidItem(List<ItemStack> stacks) {
		for(ItemStack stack : stacks) {
			addValidItem(stack);
		}
	}

	@Override
	public boolean isValidItem(ItemStack toPickup) {
		if(toPickup == null) {
			return false;
		}
		for(ItemStack invalidStack : this.invalidItems) {
			if(isEqual(invalidStack, toPickup)) {
				return false;
			}
		}
		for(ItemStack stack : this.items) {
			if(isEqual(stack, toPickup)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @author CovertJaguar
	 */
	protected boolean isEqual(ItemStack stack, ItemStack toPickup) {
		if((stack == null) || (toPickup == null)) {
			return false;
		}
		if(stack.getItem() != toPickup.getItem()) {
			return false;
		}
		if(stack.getHasSubtypes()) {
			int damage1 = stack.getItemDamage();
			int damage2 = toPickup.getItemDamage();
			if(damage1 == -1 || damage1 == 32767 || damage2 == -1 || damage2 == 32767) {
				return true;
			}
			if(stack.getItemDamage() != toPickup.getItemDamage()) {
				return false;
			}
		}
		return true;
	}

	public void initialize() {

	}

	@Override
	public String getKey() {
		return this.key;
	}

	public String getNameBase(EnumBackpackType type) {
		return String.format("forecastersbackpacks.backpack.%s.%s", this.getKey(), type.name().toLowerCase(Locale.ENGLISH));
	}

	/**
	 * @author CovertJaguar
	 */
	@Override
	public String getName(ItemStack backpack) {
		Item item = backpack.getItem();
		String name = ("" + StatCollector.translateToLocal(item.getUnlocalizedNameInefficiently(backpack) + ".name")).trim();

		if(backpack.stackTagCompound != null && backpack.stackTagCompound.hasKey("display", 10)) {
			NBTTagCompound nbt = backpack.stackTagCompound.getCompoundTag("display");

			if(nbt.hasKey("Name", 8)) {
				name = nbt.getString("Name");
			}
		}

		return name;
	}

	@Override
	public int getPrimaryColour() {
		return this.primaryColor;
	}

	@Override
	public int getSecondaryColour() {
		return this.secondaryColor;
	}

	public boolean isLoaded() {
		return this.isLoaded;
	}

	public void setLoaded(boolean loaded) {
		this.isLoaded = loaded;
	}

	public abstract Object getCraftingItem();

	public abstract boolean shouldLoad();
}
