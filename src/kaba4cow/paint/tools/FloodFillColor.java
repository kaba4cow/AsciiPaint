package kaba4cow.paint.tools;

import kaba4cow.ascii.input.Keyboard;
import kaba4cow.ascii.input.Mouse;
import kaba4cow.ascii.toolbox.files.ImageFile;
import kaba4cow.paint.AsciiPaint;

public class FloodFillColor extends Tool {

	public FloodFillColor() {
		super("Floodfill Color", Keyboard.KEY_F);
	}

	@Override
	public void update(ImageFile image, int x, int y, char glyph, int color) {
		if (Mouse.isKeyDown(Mouse.LEFT)) {
			AsciiPaint.updateImage();
			image.floodFill(x, y, image.getColor(x, y), color);
		}
	}

	@Override
	public void render(char glyph, int color) {

	}

}
