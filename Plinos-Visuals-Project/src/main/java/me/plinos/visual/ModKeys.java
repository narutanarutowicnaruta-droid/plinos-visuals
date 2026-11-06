package me.plinos.visual;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public final class ModKeys {
    public static KeyBinding CLICK_GUI;
    public static KeyBinding CINEMATIC;

    private ModKeys() {}

    public static void register() {
        CLICK_GUI = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.plinos_visual.clickgui",
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            KeyBinding.Category.MISC
        ));

        CINEMATIC = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.plinos_visual.cinematic",
            GLFW.GLFW_KEY_LEFT_ALT,
            KeyBinding.Category.MISC
        ));
    }
}
