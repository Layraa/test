package com.example.mainmod.client;

import com.example.mainmod.MainMod;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

public class ModelReloader implements SimpleSynchronousResourceReloadListener {
    @Override
    public Identifier getFabricId() {
        return new Identifier(MainMod.MODID, "model_reload_listener");
    }

    @Override
    public void reload(ResourceManager manager) {
        System.out.println("[MainMod] 🔄 Models and animations reloaded!");
    }

    public static void register() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new ModelReloader());
    }
}
