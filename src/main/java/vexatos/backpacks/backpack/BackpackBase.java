package vexatos.backpacks.backpack;

import forestry.api.storage.EnumBackpackType;
import forestry.api.storage.IBackpackDefinition;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

/**
 * @author CovertJaguar, Vexatos
 */
public abstract class BackpackBase implements IBackpackDefinition {

	private final String key;
	private final int primaryColor;
	private final int secondaryColor;
	protected ArrayList<ItemStack> items = new ArrayList<>();
	protected ArrayList<ItemStack> invalidItems = new ArrayList<>();
	private boolean isLoaded;

	public BackpackBase(String key, int primaryColor, int secondaryColor) {
		this.key = key;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
	}

	public void addValidItem(ItemStack stack) {
		if(!stack.isEmpty()) {
			items.add(stack);
		}
	}

	public void addValidItem(Item item) {
		addValidItem(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
	}

	public void addValidItem(String mod, String name) {
		Item item = Item.REGISTRY.getObject(new ResourceLocation(mod, name));
		if(item != null) {
			addValidItem(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
		}
	}

	public void addValidItem(@Nullable ResourceLocation name) {
		Item item = Item.REGISTRY.getObject(name);
		if(item != null) {
			addValidItem(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
		}
	}

	public void addValidItem(Block block) {
		addValidItem(block.getRegistryName());
	}

	public void addValidItems(List<ItemStack> stacks) {
		for(ItemStack stack : stacks) {
			addValidItem(stack);
		}
	}

	public void addInvalidItem(ItemStack stack) {
		if(!stack.isEmpty()) {
			invalidItems.add(stack);
		}
	}

	public void addInvalidItem(Item item) {
		addValidItem(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
	}

	@Nonnull
	@Override
	public Predicate<ItemStack> getFilter() {
		return this::isValidItem;
	}

	public void addInvalidItem(List<ItemStack> stacks) {
		for(ItemStack stack : stacks) {
			addValidItem(stack);
		}
	}

	public boolean isValidItem(ItemStack toPickup) {
		if(toPickup.isEmpty()) {
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
		if(stack.isEmpty() || toPickup.isEmpty()) {
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
		String name = ("" + I18n.translateToLocal(item.getUnlocalizedNameInefficiently(backpack) + ".name")).trim();

		if(backpack.getTagCompound() != null && backpack.getTagCompound().hasKey("display", 10)) {
			NBTTagCompound nbt = backpack.getTagCompound().getCompoundTag("display");

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

	@Nullable
	public abstract Object getCraftingItem();

	public abstract boolean shouldLoad();
}
