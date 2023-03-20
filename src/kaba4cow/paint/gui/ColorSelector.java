package kaba4cow.paint.gui;

import java.util.function.Consumer;

import kaba4cow.ascii.core.Display;
import kaba4cow.ascii.drawing.glyphs.Glyphs;
import kaba4cow.ascii.drawing.gui.GUIButton;
import kaba4cow.ascii.drawing.gui.GUIFrame;
import kaba4cow.ascii.drawing.gui.GUISeparator;
import kaba4cow.ascii.drawing.gui.GUISlider;
import kaba4cow.ascii.drawing.gui.GUIText;
import kaba4cow.ascii.toolbox.Colors;
import kaba4cow.paint.AsciiPaint;

public class ColorSelector extends GUIFrame {

	private final GUISlider brSlider;
	private final GUISlider bgSlider;
	private final GUISlider bbSlider;

	private final GUISlider frSlider;
	private final GUISlider fgSlider;
	private final GUISlider fbSlider;

	private final GUIText hex;
	private final GUIText example;

	public ColorSelector() {
		super(0, false, true);
		setWidth(20);
		setHeight(20);
		setTitle("Color Selector");

		new GUIText(this, -1, "Background");
		brSlider = new GUISlider(this, -1, 0f);
		bgSlider = new GUISlider(this, -1, 0f);
		bbSlider = new GUISlider(this, -1, 0f);

		new GUIText(this, -1, "Foreground");
		frSlider = new GUISlider(this, -1, 1f);
		fgSlider = new GUISlider(this, -1, 1f);
		fbSlider = new GUISlider(this, -1, 1f);

		new GUISeparator(this, -1, false);

		hex = new GUIText(this, -1, "");
		new GUISeparator(this, -1, false);

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <= 18; i++)
			builder.append(Glyphs.FULL_BLOCK);
		for (int i = 0; i <= 18; i++)
			builder.append(Glyphs.DARK_SHADE);
		for (int i = 0; i <= 18; i++)
			builder.append(Glyphs.MEDIUM_SHADE);
		for (int i = 0; i <= 18; i++)
			builder.append(Glyphs.LIGHT_SHADE);
		for (int i = 0; i <= 18; i++)
			builder.append(Glyphs.BLACK_SQUARE);

		example = new GUIText(this, -1, builder.toString());

		new GUIButton(this, -1, "Swap", new Consumer<Object>() {
			@Override
			public void accept(Object t) {
				float fr = frSlider.getPosition();
				float fg = fgSlider.getPosition();
				float fb = fbSlider.getPosition();

				frSlider.setPosition(brSlider.getPosition());
				fgSlider.setPosition(bgSlider.getPosition());
				fbSlider.setPosition(bbSlider.getPosition());

				brSlider.setPosition(fr);
				bgSlider.setPosition(fg);
				bbSlider.setPosition(fb);
			}
		});
	}

	@Override
	public void update() {
		super.update();

		if (getX() + getWidth() >= Display.getWidth() - Display.getWidth() / 4)
			setX(Display.getWidth() - Display.getWidth() / 4 - getWidth() - 1);
		if (getY() + getHeight() >= Display.getHeight())
			setY(Display.getHeight() - getHeight() - 1);
	}

	@Override
	public void render() {
		int color = getSelectorColor();
		hex.setText("Color: " + String.format("%06X", color));
		example.setColor(color);
		AsciiPaint.setColor(color);
		super.render();
	}

	public int getSelectorColor() {
		int br = (int) (0xF * brSlider.getPosition());
		int bg = (int) (0xF * bgSlider.getPosition());
		int bb = (int) (0xF * bbSlider.getPosition());
		int b = Colors.create(br, bg, bb);

		int fr = (int) (0xF * frSlider.getPosition());
		int fg = (int) (0xF * fgSlider.getPosition());
		int fb = (int) (0xF * fbSlider.getPosition());
		int f = Colors.create(fr, fg, fb);

		return Colors.combine(b, f);
	}

	public void setSelectorColor(int color) {
		example.setColor(color);

		int b = Colors.getBackground(color);
		float br = Colors.getRed(b) / (float) 0xF;
		float bg = Colors.getGreen(b) / (float) 0xF;
		float bb = Colors.getBlue(b) / (float) 0xF;

		int f = Colors.getForeground(color);
		float fr = Colors.getRed(f) / (float) 0xF;
		float fg = Colors.getGreen(f) / (float) 0xF;
		float fb = Colors.getBlue(f) / (float) 0xF;

		brSlider.setPosition(br);
		bgSlider.setPosition(bg);
		bbSlider.setPosition(bb);

		frSlider.setPosition(fr);
		fgSlider.setPosition(fg);
		fbSlider.setPosition(fb);
	}

	@Override
	public GUIFrame setColor(int color) {
		super.setColor(color);
		example.setColor(getSelectorColor());
		return this;
	}

}
