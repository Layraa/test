package com.example.mainmod.gui.screenhandler;

import com.example.mainmod.MainMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<SimpleEditorScreenHandler> EDITOR_SCREEN_HANDLER;

    public static void register() {
        EDITOR_SCREEN_HANDLER = Registry.register(
                Registries.SCREEN_HANDLER,
                new Identifier(MainMod.MODID, "editor"),
                new ScreenHandlerType<>(SimpleEditorScreenHandler::new)
        );
    }

    // Простой заглушечный хендлер (он не нужен для GUI без инвентаря)
    public static class SimpleEditorScreenHandler extends ScreenHandler {
        public SimpleEditorScreenHandler(int syncId) {
            super(EDITOR_SCREEN_HANDLER, syncId);
        }

        @Override
        public boolean canUse(net.minecraft.entity.player.PlayerEntity player) {
            return true;
        }
    }
}
