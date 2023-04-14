package kaba4cow.paint.tools;

import kaba4cow.ascii.core.Input;
import kaba4cow.ascii.toolbox.files.ImageFile;
import kaba4cow.paint.AsciiPaint;

public class GlyphPicker extends Tool {

	public GlyphPicker() {
		super("Glyph Picker", Input.KEY_SHIFT_LEFT, Input.KEY_I);
	}

	@Override
	public void update(ImageFile image, int x, int y, char glyph, int color) {
		if (Input.isButton(Input.LEFT))
			AsciiPaint.setGlyph(image.getGlyph(x, y));
	}

	@Override
	public void render(char glyph, int color) {

	}

}
