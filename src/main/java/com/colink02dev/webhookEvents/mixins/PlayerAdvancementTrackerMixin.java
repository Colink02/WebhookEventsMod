package com.colink02dev.webhookEvents.mixins;

import com.colink02dev.webhookEvents.WebhookEvents;
import com.colink02dev.webhookEvents.utils.JsonUtil;
import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerAdvancementTracker.class)
public abstract class PlayerAdvancementTrackerMixin {

@Shadow
private ServerPlayerEntity owner;

@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/AdvancementRewards;apply(Lnet/minecraft/server/network/ServerPlayerEntity;)V"), method = "grantCriterion")
public void onAdvancementCompleted(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
    JsonObject object = new JsonObject();
    object.add("player", JsonUtil.toJson(owner));
    object.add("advancement", JsonUtil.toJson(advancement));
    WebhookEvents.getInstance().getWebhookManager().fireHooks("advancement_completed", object);
}
}
