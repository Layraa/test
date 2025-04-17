package com.example.mainmod.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

public class PresetManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File PRESET_DIR = new File(MinecraftClient.getInstance().runDirectory, "presets");

    public static void savePreset(Preset preset, String name) {
        try {
            if (!PRESET_DIR.exists()) PRESET_DIR.mkdirs();
            FileWriter writer = new FileWriter(new File(PRESET_DIR, name + ".json"));
            GSON.toJson(preset, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Preset loadPreset(String name) {
        try {
            FileReader reader = new FileReader(new File(PRESET_DIR, name + ".json"));
            return GSON.fromJson(reader, Preset.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
