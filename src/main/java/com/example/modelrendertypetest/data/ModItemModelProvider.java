package com.example.modelrendertypetest.data;

import com.example.modelrendertypetest.ModelRenderTypeTest;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ModelRenderTypeTest.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ResourceLocation exampleBlockId = ModelRenderTypeTest.EXAMPLE_BLOCK.getId();
        getBuilder(exampleBlockId.getPath())
                .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + exampleBlockId.getPath())));

        ResourceLocation exampleItemId = ModelRenderTypeTest.EXAMPLE_ITEM.getId();
        ItemModelBuilder grip = nested()
                .element()
                    .from(7.5f, 0f, 7.5f).to(8.5f, 5f, 8.5f)
                    .allFaces((dir, f) -> f.uvs(0f, 0f, 16f, 16f))
                    .texture("#grip")
                    .end()
                .element()
                    .from(6.5f, 5f, 6.5f).to(9.5f, 6f, 9.5f)
                    .allFaces((dir, f) -> f.uvs(0f, 0f, 16f, 16f))
                    .texture("#grip")
                    .end()
                .texture("grip", mcLoc("block/smooth_stone"))
                .renderType("solid");
        ItemModelBuilder innerBlade = nested()
                .element()
                    .from(7.25f, 6f, 7.5f).to(8.75f, 20f, 8.5f)
                    .allFaces((dir, f) -> f.uvs(1f, 1f, 15f, 15f).emissive())
                    .texture("#inner")
                    .end()
                .texture("inner", mcLoc("block/diamond_block"))
                .ao(false)
                .renderType("solid");
        ItemModelBuilder outerBlade = nested()
                .element()
                .from(7.5f, 6f, 6.75f).to(8.5f, 21f, 9.25f)
                .allFaces((dir, f) -> f.uvs(1f, 1f, 15f, 15f).emissive())
                .texture("#outer")
                .end()
                .texture("outer", mcLoc("block/white_stained_glass"))
                .renderType("translucent");
        withExistingParent(exampleItemId.getPath(), "forge:item/default")
                .customLoader(CompositeModelBuilder::begin)
                    .child(exampleItemId.getPath() + "_grip", grip)
                    .child(exampleItemId.getPath() + "_inner_blade", innerBlade)
                    .child(exampleItemId.getPath() + "_outer_blade", outerBlade)
                    .end()
                .guiLight(BlockModel.GuiLight.FRONT)
                .transforms()
                    .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
                        .rotation(-5f, 0f, 0f)
                        .translation(0f, 3.5f, 1f)
                        .end()
                    .transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND)
                        .rotation(-5f, 0f, 0f)
                        .translation(0f, 3.5f, 1f)
                        .end()
                    .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
                        .rotation(17f, 0f, 0f)
                        .translation(-1.25f, 4f, 4f)
                        .scale(0.6f, 0.68f, 0.6f)
                        .end()
                    .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)
                        .rotation(17f, 0f, 0f)
                        .translation(-1.25f, 4f, 4f)
                        .scale(0.6f, 0.68f, 0.6f)
                        .end()
                    .transform(ItemTransforms.TransformType.GROUND)
                        .rotation(90f, -45f, 90f)
                        .translation(-1.25f, 4f, 4f)
                        .scale(0.65f)
                        .end()
                    .transform(ItemTransforms.TransformType.GUI)
                        .rotation(90f, 45f, -90f)
                        .translation(-2f, -2f, 0f)
                        .end()
                    .transform(ItemTransforms.TransformType.FIXED)
                        .rotation(90f, -45f, 90f)
                        .translation(2f, -2f, -1.5f)
                        .end()
                    .end();
    }
}
