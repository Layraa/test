package com.example.mainmod.gui;

import com.example.mainmod.manager.Preset;
import com.example.mainmod.manager.PresetManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;

public class EntityEditorScreen extends Screen {
    private TextFieldWidget nameField, healthField, speedField, modelField, textureField, animationField;

    public EntityEditorScreen() {
        super(Text.literal("Entity Editor"));
    }

    @Override
    protected void init() {
        int mid = width / 2 - 100;
        nameField      = addSelectableChild(new TextFieldWidget(textRenderer, mid,  40, 200, 20, Text.literal("Name")));
        healthField    = addSelectableChild(new TextFieldWidget(textRenderer, mid,  70, 200, 20, Text.literal("Health")));
        speedField     = addSelectableChild(new TextFieldWidget(textRenderer, mid, 100, 200, 20, Text.literal("Speed")));
        modelField     = addSelectableChild(new TextFieldWidget(textRenderer, mid, 130, 200, 20, Text.literal("Model file (geo)")));
        textureField   = addSelectableChild(new TextFieldWidget(textRenderer, mid, 160, 200, 20, Text.literal("Texture file")));
        animationField = addSelectableChild(new TextFieldWidget(textRenderer, mid, 190, 200, 20, Text.literal("Animation file")));

        // Сохранить пресет
        addDrawableChild(ButtonWidget.builder(Text.literal("Save Preset"), btn -> {
            Preset p = new Preset(
                    nameField.getText(),
                    parseFloat(healthField.getText()),
                    parseFloat(speedField.getText()),
                    modelField.getText(),
                    textureField.getText(),
                    animationField.getText(),
                    "wander"  // или другой тип AI по умолчанию
            );
            PresetManager.savePreset(p, p.name);
        }).dimensions(mid, 220, 200, 20).build());

        // Кнопка горячей перезагрузки моделей/анимаций
        addDrawableChild(ButtonWidget.builder(Text.literal("Reload Models"), btn ->
                MinecraftClient.getInstance().reloadResources()
        ).dimensions(mid, 250, 200, 20).build());
    }

    private float parseFloat(String s) {
        try { return Float.parseFloat(s); }
        catch (Exception e) { return 0f; }
    }

    @Override
    public void render(MatrixStack ms, int mx, int my, float delta) {
        renderBackground(ms);
        drawCenteredText(ms, textRenderer, Text.literal("Custom Entity Editor").asOrderedText(), width/2, 15, 0xFFFFFF);
        nameField.render(ms, mx, my, delta);
        healthField.render(ms, mx, my, delta);
        speedField.render(ms, mx, my, delta);
        modelField.render(ms, mx, my, delta);
        textureField.render(ms, mx, my, delta);
        animationField.render(ms, mx, my, delta);
        super.render(ms, mx, my, delta);
    }
}
