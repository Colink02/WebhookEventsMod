package com.colink02dev.webhookEvents;

import com.colink02dev.webhookEvents.webhooks.WebhookManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class WebhookEvents implements ModInitializer {

private static WebhookEvents INST;
private WebhookManager webhookManager;

@Override
public void onInitialize() {
    INST = this;
    ServerLifecycleEvents.SERVER_STARTED.register(server -> {
        server.getPlayerManager().getServer();
    });
}

public WebhookManager getWebhookManager() {
    return this.webhookManager;
}

public static WebhookEvents getInstance() {
    return INST;
}
}
