package vexatos.backpacks.reference;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModAPIManager;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.VersionParser;

import java.util.HashMap;
import java.util.Locale;

/**
 * @author Vexatos
 */
public class Mods {

	//The mod itself
	public static final String
		Backpacks = "forecastersbackpacks",
		Backpacks_NAME = "Forecaster's Backpacks";

	public static final String
		BiblioCraft = "bibliocraft",
		Charset = "charset",
		Flamingo = "flamingo",
		Forestry = "forestry",
		IC2 = "ic2",
		PneumaticCraft = "pneumaticcraft";

	//BuildCraft
	public static final String
		BuildcraftBuilders = "buildbraftbuilders",
		BuildCraftCore = "buildcraftcore",
		BuildCraftEnergy = "buildcraftenergy",
		BuildCraftFactory = "buildcraftfactory",
		BuildCraftRobotics = "buildcraftrobotics",
		BuildCraftSilicon = "buildcraftsilicon",
		BuildCraftTransport = "buildcrafttransport";

	// Weapon mods
	public static final String
		WeaponMod = "weaponmod",
		QuiverBow = "quiverchevsky";

	// Logic mods

	public static final String
		ComplexLogic = "complex-logic";

	//Computer mods
	public static final String
		Computronics = "computronics",
		OpenPeripheralAddons = "openperipheral",

	ComputerCraft = "computercraft",
		ComputerCraftEdu = "computercraftedu",
		RandomPeripherals = "randomperipherals",
		OpenCCSensors = "ocs",
		CCLights2 = "cclights2",
		PeripheralsPlusPlus = "peripheralsplusplus",
		MoarPeripherals = "moarperipherals",
		CCTweaks = "cctweaks",
		LyqydPeripherals = "lyqydperipherals",
		ChunkyPeripherals = "chunkyperipherals",
		ImmibisPeripherals = "immibisperipherals",
		FirePeripherals = "fireperipherals",
		DixPeripherals = "dixpr",
		Quadracopters = "quadracoptors",

	OpenComputers = "opencomputers",
		OCLights2 = "oclights2",
		OCMinecarts = "ocminecart",
		OpenSecurity = "opensecurity",
		OpenPrinter = "openprinter",
		OpenLights = "openlights",
		OpenGlasses = "openglasses",
		MassSound = "masssound",
		OpenAutomation = "openautomation",
		OpenFM = "openfm";

	//Other APIs
	public static class API {

		private static HashMap<String, ArtifactVersion> apiList;

		public static ArtifactVersion getVersion(String name) {
			if(apiList == null) {
				apiList = new HashMap<>();
				Iterable<? extends ModContainer> apis = ModAPIManager.INSTANCE.getAPIList();

				for(ModContainer api : apis) {
					apiList.put(api.getModId(), api.getProcessedVersion());
				}
			}

			if(apiList.containsKey(name)) {
				return apiList.get(name);
			}
			throw new IllegalArgumentException("API '" + name + "' does not exist!");
		}

		public static boolean hasVersion(String name, String version) {
			if(ModAPIManager.INSTANCE.hasAPI(name)) {
				ArtifactVersion v1 = VersionParser.parseVersionReference(name + "@" + version);
				ArtifactVersion v2 = getVersion(name);
				return v1.containsVersion(v2);
			}
			return false;
		}

		public static boolean hasAPI(String name) {
			return ModAPIManager.INSTANCE.hasAPI(name);
		}
	}

	public static boolean isLoaded(String name) {
		return Loader.isModLoaded(name.toLowerCase(Locale.ROOT));
	}
}
