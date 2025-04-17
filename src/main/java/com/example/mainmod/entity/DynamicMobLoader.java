package com.example.mainmod.entity;

import com.example.mainmod.manager.Preset;
import com.example.mainmod.manager.PresetManager;
import net.minecraft.client.MinecraftClient;
import java.io.File;

public class DynamicMobLoader {
    public static void loadAllPresets() {
        File dir = new File(MinecraftClient.getInstance().runDirectory, "presets");
        if (!dir.exists()) {
            dir.mkdirs();
            return;
        }
        File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
        if (files == null) return;
        for (File file : files) {
            String name = file.getName().replace(".json", "");
            Preset preset = PresetManager.loadPreset(name);
            if (preset != null) {
                System.out.println("[MainMod] Loaded preset: " + name + " (HP=" + preset.health + ")");
            }
        }
    }
}
