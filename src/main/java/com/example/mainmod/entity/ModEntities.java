package com.example.mainmod.entity;

import com.example.mainmod.MainMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static EntityType<CustomEntity> CUSTOM_ENTITY;

    public static void register() {
        CUSTOM_ENTITY = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(MainMod.MODID, "custom_entity"),
                FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CustomEntity::new)
                        .dimensions(EntityDimensions.fixed(0.6f, 1.8f))
                        .build()
        );
    }
}
