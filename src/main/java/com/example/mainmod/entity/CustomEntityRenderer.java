package com.example.mainmod.entity.client;

import com.example.mainmod.entity.CustomEntity;
import com.example.mainmod.entity.CustomEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import com.example.mainmod.MainMod;

public class CustomEntityRenderer extends GeoEntityRenderer<CustomEntity> {
    public CustomEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CustomEntityModel());
    }

    @Override
    public Identifier getTextureLocation(CustomEntity entity) {
        // Текстура больше не нужна, если мы получаем путь через GeoModel
        // Но если хочешь, можешь оставить заглушку:
        return MainMod.id("textures/entity/" + entity.getTexturePath());
    }
}
