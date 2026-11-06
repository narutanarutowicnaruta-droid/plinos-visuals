package me.plinos.visual;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class InfoScreen extends Screen {
    private final String heading;
    private final String message;

    public InfoScreen(String heading, String message) {
        super(Text.literal(heading));
        this.heading = heading;
        this.message = message;
    }

    @Override
    protected void init() {
        addDrawableChild(ButtonWidget.builder(Text.literal("Back"), b -> {
            if (client != null) client.setScreen(new VisualScreen());
        }).dimensions(width / 2 - 100, height / 2 + 55, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, width, height, 0xCC090912);
        context.fill(width / 2 - 150, height / 2 - 75, width / 2 + 150, height / 2 + 90, 0xF0181822);
        context.drawCenteredTextWithShadow(textRenderer, heading, width / 2, height / 2 - 55, 0xFFB98BFF);
        String[] lines = message.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            context.drawCenteredTextWithShadow(textRenderer, lines[i], width / 2, height / 2 - 25 + i * 14, 0xFFFFFFFF);
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
