package com.example.mainmod.entity;

import com.example.mainmod.MainMod;
import com.example.mainmod.entity.CustomEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CustomEntityModel extends GeoModel<CustomEntity> {

    @Override
    public Identifier getModelResource(CustomEntity entity) {
        return MainMod.id("geo/" + entity.getModelPath());
    }

    @Override
    public Identifier getTextureResource(CustomEntity entity) {
        return MainMod.id("textures/entity/" + entity.getTexturePath());
    }

    @Override
    public Identifier getAnimationResource(CustomEntity entity) {
        return MainMod.id("animations/" + entity.getAnimationPath());
    }
}
