package vexatos.backpacks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;
import forestry.api.storage.EnumBackpackType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vexatos.backpacks.backpack.BackpackTypes;
import vexatos.backpacks.misc.CreativeTabBackpacks;
import vexatos.backpacks.reference.Mods;

/**
 * @author Vexatos
 */
@Mod(modid = Mods.Backpacks, name = Mods.Backpacks_NAME, version = "@VERSION@",
	dependencies = "required-after:" + Mods.Forestry + "@[3.6,);after:" + Mods.BiblioCraft + "@[1.11.2,)")
public class ForecastersBackpacks {

	@Mod.Instance(Mods.Backpacks)
	public static ForecastersBackpacks instance;

	public static Configuration config;
	public static Logger log;

	public static CreativeTabs tab = new CreativeTabBackpacks();

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		log = LogManager.getLogger(Mods.Backpacks_NAME);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		OreDictionary.registerOre("chestWood", Blocks.chest);

		for(BackpackTypes backpack : BackpackTypes.VALUES) {
			if(backpack.getBackpack().shouldLoad() && config.get("enable", backpack.getBackpack().getKey(), true).getBoolean(true)) {
				registerBackpack(backpack, EnumBackpackType.T1);
				registerBackpack(backpack, EnumBackpackType.T2);
			}
		}
	}

	private Item registerBackpack(BackpackTypes backpack, EnumBackpackType type) {
		return registerBackpack(backpack, type, backpack.getBackpack().getNameBase(type));
	}

	private Item registerBackpack(BackpackTypes backpack, EnumBackpackType type, String name) {
		Item item = BackpackManager.backpackInterface.addBackpack(backpack.getBackpack(), type).setCreativeTab(tab).setUnlocalizedName(name);
		GameRegistry.registerItem(item, item.getUnlocalizedName());
		backpack.setItem(type, item);
		return item;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		Item silk = GameRegistry.findItem("Forestry", "craftingMaterial");

		for(BackpackTypes backpack : BackpackTypes.VALUES) {
			if(backpack.getBackpack().isLoaded()) {
				backpack.getBackpack().initialize();

				Item backpackItem1 = backpack.getItem(EnumBackpackType.T1);

				if(backpackItem1 != null) {
					Object craftingItem = backpack.getBackpack().getCraftingItem();
					if(craftingItem != null) {
						GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(backpackItem1),
							"X#X", "VYV", "X#X", 'Y', "chestWood", 'X', Items.string, '#', Blocks.wool, 'V', craftingItem));
					}
					if(silk != null) {
						Item backpackItem2 = backpack.getItem(EnumBackpackType.T2);
						RecipeManagers.carpenterManager.addRecipe(200, FluidRegistry.getFluidStack("water", 1000), null, new ItemStack(backpackItem2),
							"WXW", "WTW", "WWW", 'X', Items.diamond, 'W',
							new ItemStack(silk, 1, 3), 'T', backpackItem1);
					}
				}
			}
		}
		config.save();
	}
}
