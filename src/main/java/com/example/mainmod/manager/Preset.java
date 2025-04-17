package com.example.mainmod.manager;

public class Preset {
    public String name;
    public float health, speed;
    public String model, texture, animation;
    public String aiType;

    public Preset(String name, float health, float speed,
                  String model, String texture, String animation,
                  String aiType) {
        this.name      = name;
        this.health    = health;
        this.speed     = speed;
        this.model     = model;
        this.texture   = texture;
        this.animation = animation;
        this.aiType    = aiType;
    }
}
