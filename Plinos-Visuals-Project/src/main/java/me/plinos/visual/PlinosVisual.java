package me.plinos.visual;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class PlinosVisual implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeys.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (ModKeys.CLICK_GUI.wasPressed()) {
                if (client.currentScreen instanceof VisualScreen) {
                    client.setScreen(null);
                } else if (client.currentScreen == null) {
                    client.setScreen(new VisualScreen());
                }
            }

            while (ModKeys.CINEMATIC.wasPressed()) {
                FeatureSettings.CINEMATIC_CAMERA = !FeatureSettings.CINEMATIC_CAMERA;
            }
        });
    }
}
