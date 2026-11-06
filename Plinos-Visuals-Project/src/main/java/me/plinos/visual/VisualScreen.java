package me.plinos.visual;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class VisualScreen extends Screen {
    private static final int PURPLE = 0xFF8B50E8;
    private static final int PANEL = 0xF0181822;
    private String category = "VISUALS";
    private final List<ButtonWidget> categoryButtons = new ArrayList<>();

    public VisualScreen() {
        super(Text.literal("Plinos Visuals"));
    }

    @Override
    protected void init() {
        categoryButtons.clear();
        int left = this.width / 2 - 150;
        int top = this.height / 2 - 105;

        addDrawableChild(ButtonWidget.builder(Text.literal("Visuals"), b -> setCategory("VISUALS"))
            .dimensions(left - 112, top, 108, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Safe PvP"), b -> setCategory("SAFE PVP"))
            .dimensions(left - 112, top + 25, 108, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Screen FX"), b -> setCategory("SCREEN FX"))
            .dimensions(left - 112, top + 50, 108, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("HUD"), b -> setCategory("HUD"))
            .dimensions(left - 112, top + 75, 108, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Streamer"), b -> setCategory("STREAMER"))
            .dimensions(left - 112, top + 100, 108, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Accounts"), b -> setCategory("ACCOUNTS"))
            .dimensions(left - 112, top + 125, 108, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Close"), b -> close())
            .dimensions(left + 45, top + 155, 200, 20).build());

        buildFeatureButtons(left + 45, top + 5);
    }

    private void setCategory(String value) {
        this.category = value;
        this.clearChildren();
        this.init();
    }

    private void addToggle(String name, BooleanSupplier getter, Consumer<Boolean> setter, int x, int y) {
        boolean enabled = getter.getAsBoolean();
        addDrawableChild(ButtonWidget.builder(label(name, enabled), button -> {
            boolean next = !getter.getAsBoolean();
            setter.accept(next);
            button.setMessage(label(name, next));
        }).dimensions(x, y, 200, 20).build());
    }

    private Text label(String name, boolean on) {
        return Text.literal(name + "  [" + (on ? "ON" : "OFF") + "]");
    }

    private void buildFeatureButtons(int x, int y) {
        switch (category) {
            case "VISUALS" -> {
                addToggle("Custom Glint", () -> FeatureSettings.CUSTOM_GLINT, v -> FeatureSettings.CUSTOM_GLINT = v, x, y);
                addToggle("Item Glow", () -> FeatureSettings.ITEM_GLOW, v -> FeatureSettings.ITEM_GLOW = v, x, y + 25);
            }
            case "SAFE PVP" -> {
                addToggle("Hitmarker", () -> FeatureSettings.HITMARKER, v -> FeatureSettings.HITMARKER = v, x, y);
                addToggle("Custom Particles", () -> FeatureSettings.CUSTOM_PARTICLES, v -> FeatureSettings.CUSTOM_PARTICLES = v, x, y + 25);
                addToggle("Damage Effect", () -> FeatureSettings.DAMAGE_EFFECT, v -> FeatureSettings.DAMAGE_EFFECT = v, x, y + 50);
                addToggle("Hit Sounds", () -> FeatureSettings.HIT_SOUNDS, v -> FeatureSettings.HIT_SOUNDS = v, x, y + 75);
                addToggle("Kill Sounds", () -> FeatureSettings.KILL_SOUNDS, v -> FeatureSettings.KILL_SOUNDS = v, x, y + 100);
            }
            case "SCREEN FX" -> {
                addToggle("Cinematic Camera", () -> FeatureSettings.CINEMATIC_CAMERA, v -> FeatureSettings.CINEMATIC_CAMERA = v, x, y);
                addToggle("Motion Blur", () -> FeatureSettings.MOTION_BLUR, v -> FeatureSettings.MOTION_BLUR = v, x, y + 25);
                addToggle("Color Correction", () -> FeatureSettings.COLOR_CORRECTION, v -> FeatureSettings.COLOR_CORRECTION = v, x, y + 50);
                addToggle("Green Screen Sky", () -> FeatureSettings.GREEN_SKY, v -> FeatureSettings.GREEN_SKY = v, x, y + 75);
            }
            case "HUD" -> {
                addToggle("Armor HUD", () -> FeatureSettings.ARMOR_HUD, v -> FeatureSettings.ARMOR_HUD = v, x, y);
                addToggle("Ping HUD", () -> FeatureSettings.PING_HUD, v -> FeatureSettings.PING_HUD = v, x, y + 25);
                addToggle("Timer HUD", () -> FeatureSettings.TIMER_HUD, v -> FeatureSettings.TIMER_HUD = v, x, y + 50);
                addToggle("Custom Crosshair", () -> FeatureSettings.CUSTOM_CROSSHAIR, v -> FeatureSettings.CUSTOM_CROSSHAIR = v, x, y + 75);
            }
            case "STREAMER" -> addToggle("Hide Player Names (UI only)", () -> FeatureSettings.STREAMER_MODE, v -> FeatureSettings.STREAMER_MODE = v, x, y);
            case "ACCOUNTS" -> {
                addDrawableChild(ButtonWidget.builder(Text.literal("Account Manager info"), b -> {
                    if (client != null) client.setScreen(new InfoScreen(
                        "SKLauncher Accounts",
                        "SKLauncher controls Microsoft/offline authentication.\nA normal Fabric mod cannot safely add or switch launcher accounts.\nUse the SKLauncher account selector before launching the game."
                    ));
                }).dimensions(x, y, 200, 20).build());
                addDrawableChild(ButtonWidget.builder(Text.literal("Password masking info"), b -> {
                    if (client != null) client.setScreen(new InfoScreen(
                        "Password safety",
                        "Never type or store your password in a public chat.\nA safe implementation must hide the outgoing command locally\nwithout changing the command sent to the server."
                    ));
                }).dimensions(x, y + 25, 200, 20).build());
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, width, height, 0x99000000);
        int cx = width / 2;
        int cy = height / 2;
        context.fill(cx - 165, cy - 120, cx + 165, cy + 95, PANEL);
        context.fill(cx - 165, cy - 120, cx + 165, cy - 91, PURPLE);
        context.drawCenteredTextWithShadow(textRenderer, "PLINOS VISUALS", cx, cy - 110, 0xFFFFFFFF);
        context.drawCenteredTextWithShadow(textRenderer, category, cx + 45, cy - 78, 0xFFBFA0FF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
