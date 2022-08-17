package com.example.modelrendertypetest;

import com.example.modelrendertypetest.data.ModBlockStateProvider;
import com.example.modelrendertypetest.data.ModItemModelProvider;
import com.example.modelrendertypetest.data.ModLanguageProvider;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(ModelRenderTypeTest.MODID)
public class ModelRenderTypeTest {
    public static final String MODID = "modelrendertypetest";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new SwordItem(Tiers.DIAMOND, 3, -2.4F, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT)));

    public ModelRenderTypeTest() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::gatherData);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }

    private void gatherData(final GatherDataEvent event) {
        var dataGenerator = event.getGenerator();
        var existingFileHelper = event.getExistingFileHelper();

        // Client
        boolean includesClient = event.includeClient();
        dataGenerator.addProvider(includesClient, new ModBlockStateProvider(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(includesClient, new ModItemModelProvider(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(includesClient, new ModLanguageProvider(dataGenerator));
    }
}
