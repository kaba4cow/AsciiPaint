package kaba4cow.paint.tools;

import kaba4cow.ascii.core.Input;
import kaba4cow.ascii.toolbox.files.ImageFile;
import kaba4cow.paint.AsciiPaint;

public class FloodFillColor extends Tool {

	public FloodFillColor() {
		super("Floodfill Color", Input.KEY_F);
	}

	@Override
	public void update(ImageFile image, int x, int y, char glyph, int color) {
		if (Input.isButtonDown(Input.LEFT)) {
			AsciiPaint.updateImage();
			image.floodFill(x, y, color);
		}
	}

	@Override
	public void render(char glyph, int color) {

	}

}
