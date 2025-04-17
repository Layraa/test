package com.example.mainmod;

import com.example.mainmod.client.ModelReloader;
import com.example.mainmod.entity.CustomEntity;
import com.example.mainmod.entity.ModEntities;
import com.example.mainmod.gui.ModScreens;
import com.example.mainmod.entity.DynamicMobLoader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.Identifier;

public class MainMod implements ModInitializer, ClientModInitializer {
    public static final String MODID = "mainmod";

    @Override
    public void onInitialize() {
        // Регистрируем все EntityType
        ModEntities.register();
        // Загружаем пресеты из папки presets/ при старте
        DynamicMobLoader.loadAllPresets();
    }

    @Override
    public void onInitializeClient() {
        // Подключаем рендеры
        ModScreens.register();
        // Регистрируем атрибуты для CustomEntity
        FabricDefaultAttributeRegistry.register(ModEntities.CUSTOM_ENTITY, CustomEntity.createAttributes());
        // Регистрируем слушатель горячей перезагрузки моделей/анимаций
        ModelReloader.register();
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
