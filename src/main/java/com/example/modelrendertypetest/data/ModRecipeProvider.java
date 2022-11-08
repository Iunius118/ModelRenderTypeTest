package com.example.modelrendertypetest.data;

import com.example.modelrendertypetest.ModelRenderTypeTest;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // Example Block
        Block exampleBlock = ModelRenderTypeTest.EXAMPLE_BLOCK.get();
        ShapedRecipeBuilder.shaped(exampleBlock, 2)
                .pattern("###")
                .pattern("#d#")
                .pattern("###")
                .define('#', Tags.Items.GLASS)
                .define('d', Tags.Items.GEMS_DIAMOND)
                .unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND))
                .save(consumer);

        // Example Item
        ShapedRecipeBuilder.shaped(ModelRenderTypeTest.EXAMPLE_ITEM.get())
                .pattern("X")
                .pattern("X")
                .pattern("s")
                .define('X', exampleBlock)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_modelrendertypetest_example_block", has(exampleBlock))
                .save(consumer);

        // Diamond Stairs
        Block diamondStairBlock = ModelRenderTypeTest.DIAMOND_STAIR_BLOCK.get();
        ShapedRecipeBuilder.shaped(diamondStairBlock, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .unlockedBy("has_diamond", has(Tags.Items.GEMS_DIAMOND))
                .save(consumer);
    }
}
