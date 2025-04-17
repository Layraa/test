package com.example.mainmod.entity;

import com.example.mainmod.manager.Preset;
import com.example.mainmod.manager.PresetManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class CustomEntity extends PathAwareEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.custom_entity.idle");

    // 📌 Эти поля должны быть здесь, чтобы их могли вызывать геттеры
    private String modelPath     = "custom_entity.geo.json";
    private String texturePath   = "custom_entity.png";
    private String animationPath = "custom_entity.animation.json";

    public CustomEntity(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return PathAwareEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> {
            state.setAnimation(IDLE);
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public void loadPreset(String presetName) {
        Preset preset = PresetManager.loadPreset(presetName);
        if (preset == null) return;

        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                .setBaseValue(preset.health);
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)
                .setBaseValue(preset.speed);

        if ("aggressive".equalsIgnoreCase(preset.aiType)) {
            this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, true));
        } else if ("wander".equalsIgnoreCase(preset.aiType)) {
            this.goalSelector.add(1, new WanderAroundFarGoal(this, 1.0));
        }

        if (preset.model     != null && !preset.model.isEmpty())     modelPath     = preset.model;
        if (preset.texture   != null && !preset.texture.isEmpty())   texturePath   = preset.texture;
        if (preset.animation != null && !preset.animation.isEmpty()) animationPath = preset.animation;
    }

    // ✅ Геттеры для GeoModel
    public String getModelPath() {
        return modelPath;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public String getAnimationPath() {
        return animationPath;
    }
}
