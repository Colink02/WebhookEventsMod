package com.colink02dev.webhookEvents.webhooks;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manager to handle calling webhooks
 */
public class WebhookManager {

    private List<MinecraftWebhook> hooks = new ArrayList<>();

    public WebhookManager() {}
    public MinecraftWebhook addWebhook(String url, String... events) {
        MinecraftWebhook webhook = new MinecraftWebhook(url, events);
        this.hooks.add(webhook);
        return webhook;
    }

    public void removeWebhook(String url) {
        List<MinecraftWebhook> hooks = this.hooks;
        for(MinecraftWebhook webhook : hooks) {
            if(webhook.url().equals(url)) {
                this.hooks.remove(webhook);
            }
        }
    }

    public void fireHooks(String eventType, JsonObject eventPayload) {
        for(MinecraftWebhook hook : this.hooks) {
            if(Arrays.stream(hook.events()).anyMatch(hookEventTypes -> hookEventTypes.contains(eventType))) {
                hook.trigger(eventType, eventPayload);
            }
        }
    }

}
