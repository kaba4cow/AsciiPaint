package kaba4cow.paint.tools;

import kaba4cow.ascii.input.Keyboard;
import kaba4cow.ascii.input.Mouse;
import kaba4cow.ascii.toolbox.files.ImageFile;

public class FloodFillGlyph extends Tool {

	public FloodFillGlyph() {
		super("Floodfill Glyph", Keyboard.KEY_SHIFT_LEFT, Keyboard.KEY_F);
	}

	@Override
	public void update(ImageFile image, int x, int y, char glyph, int color) {
		if (Mouse.isKey(Mouse.LEFT))
			image.floodFill(x, y, image.getChar(x, y), glyph);
	}

	@Override
	public void render(char glyph, int color) {

	}

}
