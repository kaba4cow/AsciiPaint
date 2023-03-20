package kaba4cow.paint.gui;

import kaba4cow.ascii.drawing.gui.GUIFrame;
import kaba4cow.ascii.drawing.gui.GUIText;
import kaba4cow.ascii.input.Mouse;
import kaba4cow.paint.AsciiPaint;

public class GlyphSelector extends GUIFrame {

	private final char[][] chars;

	public GlyphSelector() {
		super(0, false, true);
		setWidth(17);
		setHeight(17);
		setTitle("Glyph Selector");

		String string = "";

		chars = new char[16][16];
		int i = 0;
		for (int y = 0; y < 16; y++)
			for (int x = 0; x < 16; x++) {
				chars[x][y] = (char) i;
				string += (char) i;
				i++;
			}

		new GUIText(this, -1, string);
	}

	@Override
	public void update() {
		super.update();
		int mX = Mouse.getTileX();
		int mY = Mouse.getTileY();
		if (wasClicked() && mX > getX() && mX < getX() + getWidth() && mY > getY() && mY < getY() + getHeight()) {
			int x = mX - getX() - 1;
			int y = mY - getY() - 1;
			AsciiPaint.setGlyph(chars[x][y]);
		}
	}

}
