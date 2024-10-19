package com.colink02dev.webhookEvents.webhooks;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public record MinecraftWebhook(String url, String... events) {
public void trigger(String eventType, JsonObject eventPayload) {
    Gson gson = new Gson();
    JsonObject root = new JsonObject();
    root.addProperty("event", eventType);
    root.addProperty("data", gson.toJson(eventPayload));
}
}
