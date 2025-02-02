package net.crypticverse.betterbiomes.block;

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.crypticverse.betterbiomes.BetterBiomes;
import net.crypticverse.betterbiomes.block.custom.MapleSyrupStationBlock;
import net.crypticverse.betterbiomes.block.custom.TappedBucketBlock;
import net.crypticverse.betterbiomes.world.tree.BetterBiomesSaplingGen;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BiomeBlocks {

    public static final Block MAPLE_LOG = registerBlock("maple_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block MAPLE_WOOD = registerBlock("maple_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block MAPLE_PLANKS = registerBlock("maple_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block MAPLE_LEAVES = registerBlock("maple_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).requiresTool()));
    public static final Block MAPLE_SAPLING = registerBlock("maple_sapling", new SaplingBlock(BetterBiomesSaplingGen.MAPLE, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Block MAPLE_SYRUP_BOILER = registerBlock("maple_syrup_boiler", new MapleSyrupStationBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block MAPLE_STAIRS = registerBlock("maple_stairs", new StairsBlock(BiomeBlocks.MAPLE_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));
    public static final Block MAPLE_SLAB = registerBlock("maple_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
    public static final Block MAPLE_BUTTON = registerBlock("maple_button", new ButtonBlock(BlockSetType.OAK, 10, FabricBlockSettings.copyOf(Blocks.OAK_BUTTON)));
    public static final Block MAPLE_PRESSURE_PLATE = registerBlock("maple_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE)));
    public static final Block MAPLE_FENCE = registerBlock("maple_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block MAPLE_FENCE_GATE = registerBlock("maple_fence_gate", new FenceGateBlock(WoodType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE)));
    public static final Block MAPLE_DOOR = registerBlock("maple_door", new DoorBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_DOOR).nonOpaque()));
    public static final Block MAPLE_TRAPDOOR = registerBlock("maple_trapdoor", new TrapdoorBlock(BlockSetType.OAK, FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR).nonOpaque()));

    public static final Identifier MAPLE_SIGN_TEXTURE = new Identifier(BetterBiomes.MOD_ID, "entity/signs/maple");
    public static final Identifier MAPLE_HANGING_SIGN_TEXTURE = new Identifier(BetterBiomes.MOD_ID, "entity/signs/hanging/maple");
    public static final Identifier MAPLE_HANGING_GUI_SIGN_TEXTURE = new Identifier(BetterBiomes.MOD_ID, "textures/gui/hanging_signs/maple");

    public static final Block TAPPED_BUCKET = registerWithoutBlockItem("tapped_bucket_block", new TappedBucketBlock(FabricBlockSettings.copyOf(Blocks.COCOA)));

    public static final Block STANDING_MAPLE_SIGN = Registry.register(Registries.BLOCK, new Identifier(BetterBiomes.MOD_ID, "maple_standing_sign"), new TerraformSignBlock(MAPLE_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)));
    public static final Block WALL_MAPLE_SIGN = Registry.register(Registries.BLOCK, new Identifier(BetterBiomes.MOD_ID, "maple_wall_sign"), new TerraformWallSignBlock(MAPLE_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN)));
    public static final Block HANGING_MAPLE_SIGN = Registry.register(Registries.BLOCK, new Identifier(BetterBiomes.MOD_ID, "maple_hanging_sign"), new TerraformHangingSignBlock(MAPLE_HANGING_SIGN_TEXTURE, MAPLE_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN)));
    public static final Block WALL_HANGING_MAPLE_SIGN = Registry.register(Registries.BLOCK, new Identifier(BetterBiomes.MOD_ID, "maple_wall_hanging_sign"), new TerraformWallHangingSignBlock(MAPLE_HANGING_SIGN_TEXTURE, MAPLE_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final BlockFamily MAPLE_FAMILY = BlockFamilies.register(BiomeBlocks.MAPLE_PLANKS).sign(BiomeBlocks.STANDING_MAPLE_SIGN, BiomeBlocks.WALL_MAPLE_SIGN).group("wooden").unlockCriterionName("has_planks").build();



    private static Block registerBlock(String name, Block block) {registerBlockItem(name, block);return Registry.register(Registries.BLOCK, new Identifier(BetterBiomes.MOD_ID, name), block);}
    private static Item registerBlockItem(String name, Block block) {return Registry.register(Registries.ITEM, new Identifier(BetterBiomes.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));}
    public static void registerModBlocks() {BetterBiomes.LOGGER.info("Registering Blocks for" + BetterBiomes.MOD_ID);}

    public static Block registerWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(BetterBiomes.MOD_ID, name), block);
    }
}
