package com.example.modelrendertypetest.data;

import com.example.modelrendertypetest.ModelRenderTypeTest;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ModelRenderTypeTest.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ResourceLocation exampleBlockId = ModelRenderTypeTest.EXAMPLE_BLOCK.getId();
        BlockModelBuilder exampleBlockInner = models()
                .nested()
                    .element()
                        .from(4, 4, 4).to(12, 12, 12)
                        .allFaces((dir, f) -> f.cullface(dir).emissive())
                        .textureAll("#inner")
                        .shade(false)
                        .end()
                    .texture("inner", mcLoc("block/diamond_block"))
                    .ao(false)
                    .renderType("solid");
        BlockModelBuilder exampleBlockOuter = models()
                .nested()
                    .element()
                        .allFaces((dir, f) -> f.cullface(dir))
                        .textureAll("#outer")
                        .end()
                    .texture("outer", mcLoc("block/white_stained_glass"))
                    .renderType("translucent");
        BlockModelBuilder exampleBlock = models()
                .withExistingParent(exampleBlockId.getPath(), "forge:block/default")
                    .customLoader(CompositeModelBuilder::begin)
                        .child(exampleBlockId.getPath() + "_inner", exampleBlockInner)
                        .child(exampleBlockId.getPath() + "_outer", exampleBlockOuter)
                        .end()
                    .texture("particle", mcLoc("block/white_stained_glass"));

        simpleBlock(ModelRenderTypeTest.EXAMPLE_BLOCK.get(), exampleBlock);
    }
}
