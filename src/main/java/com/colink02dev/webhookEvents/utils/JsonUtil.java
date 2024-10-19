package com.colink02dev.webhookEvents.utils;

import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;

public class JsonUtil {
    public static JsonObject toJson(ServerPlayerEntity player) {
        JsonObject json = new JsonObject();
        json.addProperty("uuid", player.getUuid().toString());
        json.addProperty("username", player.getGameProfile().getName());
        return json;
    }

    public static JsonObject toJson(Advancement advancement) {
        JsonObject json = new JsonObject();
        json.addProperty("id", advancement.getId().toString());
        json.add("display", toJson(advancement.getDisplay()));

        return json;
    }

    public static JsonObject toJson(AdvancementDisplay display) {
        JsonObject json = new JsonObject();
        if(display == null) return json;
        if(display.getDescription() != null) {
            json.addProperty("description", display.getDescription().toString());
        }
        if(display.getIcon() != null) {
            json.addProperty("icon", Registries.ITEM.getId(display.getIcon().getItem()).toString());
        }
        json.addProperty("hidden", display.isHidden());
        json.addProperty("frame", display.getFrame().getId());
        return json;
    }
}
