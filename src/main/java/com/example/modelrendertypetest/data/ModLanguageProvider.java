package com.example.modelrendertypetest.data;

import com.example.modelrendertypetest.ModelRenderTypeTest;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(DataGenerator generator) {
        super(generator, ModelRenderTypeTest.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModelRenderTypeTest.EXAMPLE_ITEM.get(), "Example Item");
        add(ModelRenderTypeTest.EXAMPLE_BLOCK.get(), "Example Block");
    }
}
