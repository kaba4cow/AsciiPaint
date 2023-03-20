package kaba4cow.paint.tools;

import kaba4cow.ascii.input.Keyboard;
import kaba4cow.ascii.input.Mouse;
import kaba4cow.ascii.toolbox.files.ImageFile;
import kaba4cow.paint.AsciiPaint;

public class GlyphPicker extends Tool {

	public GlyphPicker() {
		super("Glyph Picker", Keyboard.KEY_SHIFT_LEFT, Keyboard.KEY_I);
	}

	@Override
	public void update(ImageFile image, int x, int y, char glyph, int color) {
		if (Mouse.isKey(Mouse.LEFT))
			AsciiPaint.setGlyph(image.getChar(x, y));
	}

	@Override
	public void render(char glyph, int color) {

	}

}
