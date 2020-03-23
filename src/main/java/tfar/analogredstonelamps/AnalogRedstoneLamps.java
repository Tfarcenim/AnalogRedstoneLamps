package tfar.analogredstonelamps;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AnalogRedstoneLamps implements ModInitializer {

	public static final Block ANALOG_REDSTONE_LAMP = new AnalogRedstoneLampBlock(Block.Settings.copy(Blocks.REDSTONE_LAMP));
	public static final Block INVERTED_ANALOG_REDSTONE_LAMP = new InverseAnalogRedstoneLampBlock(Block.Settings.copy(Blocks.REDSTONE_LAMP));
	public static final String MODID = "analogredstonelamps";
	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK,new Identifier(MODID,"analogredstonelamp"),ANALOG_REDSTONE_LAMP);
		Registry.register(Registry.ITEM,new Identifier(MODID,"analogredstonelamp"),
						new BlockItem(ANALOG_REDSTONE_LAMP,new Item.Settings().group(ItemGroup.REDSTONE)));

		Registry.register(Registry.BLOCK,new Identifier(MODID,"invertedanalogredstonelamp"),INVERTED_ANALOG_REDSTONE_LAMP);
		Registry.register(Registry.ITEM,new Identifier(MODID,"invertedanalogredstonelamp"),
						new BlockItem(INVERTED_ANALOG_REDSTONE_LAMP,new Item.Settings().group(ItemGroup.REDSTONE)));
	}
}
