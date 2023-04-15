package kaba4cow.paint.gui;

import kaba4cow.ascii.core.Input;
import kaba4cow.ascii.core.Window;
import kaba4cow.ascii.gui.GUIFrame;
import kaba4cow.ascii.gui.GUIText;
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

		if (getX() + getWidth() >= Window.getWidth() - Window.getWidth() / 4)
			setX(Window.getWidth() - Window.getWidth() / 4 - getWidth() - 1);
		if (getY() + getHeight() >= Window.getHeight())
			setY(Window.getHeight() - getHeight() - 1);

		int mX = Input.getTileX();
		int mY = Input.getTileY();
		if (Input.isButtonDown(Input.LEFT) && wasClicked() && mX > getX() && mX < getX() + getWidth() && mY > getY()
				&& mY < getY() + getHeight()) {
			int x = mX - getX() - 1;
			int y = mY - getY() - 1;
			AsciiPaint.setGlyph(chars[x][y]);
		}
	}

}
