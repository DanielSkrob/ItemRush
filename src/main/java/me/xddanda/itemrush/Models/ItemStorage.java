package me.xddanda.itemrush.Models;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemStorage {

    private static ItemStack currentItem = null;

    private static final List<ItemStack> items1 = Arrays.asList(
            new ItemStack(Material.DIRT),
            new ItemStack(Material.COBBLESTONE),
            new ItemStack(Material.GLASS),
            new ItemStack(Material.TUFF),
            new ItemStack(Material.OAK_WOOD),
            new ItemStack(Material.SPRUCE_WOOD),
            new ItemStack(Material.ACACIA_WOOD),
            new ItemStack(Material.DARK_OAK_WOOD),
            new ItemStack(Material.CUT_SANDSTONE),
            new ItemStack(Material.CHAIN),
            new ItemStack(Material.IRON_BARS),
            new ItemStack(Material.OAK_LEAVES),
            new ItemStack(Material.MOSS_BLOCK),
            new ItemStack(Material.STRING),
            new ItemStack(Material.ROTTEN_FLESH),
            new ItemStack(Material.CLAY),
            new ItemStack(Material.LIGHT_GRAY_CONCRETE),
            new ItemStack(Material.GRAY_CONCRETE),
            new ItemStack(Material.MAGENTA_CONCRETE),
            new ItemStack(Material.BIRCH_LEAVES),
            new ItemStack(Material.GLOW_LICHEN),
            new ItemStack(Material.LILAC),
            new ItemStack(Material.PEONY),
            new ItemStack(Material.ROSE_BUSH),
            new ItemStack(Material.FLOWER_POT),
            new ItemStack(Material.WHITE_BANNER),
            new ItemStack(Material.ORANGE_BANNER),
            new ItemStack(Material.YELLOW_BANNER),
            new ItemStack(Material.LIGHT_GRAY_BANNER),
            new ItemStack(Material.BLUE_BANNER),
            new ItemStack(Material.RED_BANNER),
            new ItemStack(Material.BLACK_BANNER),
            new ItemStack(Material.WHITE_GLAZED_TERRACOTTA),
            new ItemStack(Material.PINK_GLAZED_TERRACOTTA),
            new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA),
            new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA),
            new ItemStack(Material.GREEN_GLAZED_TERRACOTTA),
            new ItemStack(Material.WHITE_TERRACOTTA),
            new ItemStack(Material.RED_TERRACOTTA),
            new ItemStack(Material.BROWN_TERRACOTTA),
            new ItemStack(Material.GRAY_TERRACOTTA),
            new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE),
            new ItemStack(Material.BLUE_STAINED_GLASS_PANE),
            new ItemStack(Material.ORANGE_STAINED_GLASS_PANE),
            new ItemStack(Material.PURPLE_STAINED_GLASS_PANE),
            new ItemStack(Material.BLACK_CONCRETE),
            new ItemStack(Material.BONE_BLOCK),
            new ItemStack(Material.PUMPKIN),
            new ItemStack(Material.STONE_BUTTON),
            new ItemStack(Material.LAPIS_LAZULI),
            new ItemStack(Material.WHEAT),
            new ItemStack(Material.GUNPOWDER),
            new ItemStack(Material.SPIDER_EYE),
            new ItemStack(Material.COOKED_BEEF),
            new ItemStack(Material.BROWN_MUSHROOM),
            new ItemStack(Material.RED_MUSHROOM),
            new ItemStack(Material.GREEN_CONCRETE),
            new ItemStack(Material.PINK_CONCRETE),
            new ItemStack(Material.CHEST),
            new ItemStack(Material.SMOKER),
            new ItemStack(Material.CARTOGRAPHY_TABLE)
    );

    private static final List<ItemStack> items2 = Arrays.asList(
            new ItemStack(Material.CYAN_CONCRETE),
            new ItemStack(Material.CYAN_STAINED_GLASS),
            new ItemStack(Material.LIME_GLAZED_TERRACOTTA),
            new ItemStack(Material.LIME_BANNER),
            new ItemStack(Material.BROWN_STAINED_GLASS),
            new ItemStack(Material.BROWN_CONCRETE_POWDER),
            new ItemStack(Material.HAY_BLOCK),
            new ItemStack(Material.MAGMA_BLOCK),
            new ItemStack(Material.NETHERRACK),
            new ItemStack(Material.GOLD_INGOT),
            new ItemStack(Material.DIAMOND),
            new ItemStack(Material.CARVED_PUMPKIN),
            new ItemStack(Material.MOSSY_COBBLESTONE),
            new ItemStack(Material.BOOKSHELF),
            new ItemStack(Material.DRIED_KELP_BLOCK),
            new ItemStack(Material.BAMBOO),
            new ItemStack(Material.LILY_PAD),
            new ItemStack(Material.GLOW_ITEM_FRAME),
            new ItemStack(Material.POINTED_DRIPSTONE),
            new ItemStack(Material.TNT),
            new ItemStack(Material.LIGHTNING_ROD),
            new ItemStack(Material.TRIPWIRE_HOOK),
            new ItemStack(Material.SADDLE),
            new ItemStack(Material.CARROT),
            new ItemStack(Material.BAKED_POTATO),
            new ItemStack(Material.HONEY_BLOCK),
            new ItemStack(Material.COOKED_RABBIT),
            new ItemStack(Material.RABBIT_HIDE),
            new ItemStack(Material.COD_BUCKET),
            new ItemStack(Material.SNOWBALL),
            new ItemStack(Material.ENDER_PEARL),
            new ItemStack(Material.WRITABLE_BOOK),
            new ItemStack(Material.COMPASS)
    );

    public void generate(int round) {
        if (round < 7){
            currentItem = items1.get((int) Math.floor(Math.random() * ((items1.size() - 1) + 1) + 0));
        } else {
            int decider = (int) (Math.random() * (2 - 1 + 1)) +1;

            switch (decider){
                case 1 -> currentItem = items1.get((int) Math.floor(Math.random() * ((items1.size() - 1) + 1) + 0));
                case 2 -> currentItem = items2.get((int) Math.floor(Math.random() * ((items2.size() - 1) + 1) + 0));
            }
        }
    }

    public static ItemStack getCurrentItem() {
        return currentItem;
    }
}
