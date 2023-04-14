package kaba4cow.paint.tools;

import kaba4cow.ascii.core.Input;
import kaba4cow.ascii.toolbox.files.ImageFile;
import kaba4cow.paint.AsciiPaint;

public class Pencil extends Tool {

	private boolean drawing = false;

	public Pencil() {
		super("Pencil", Input.KEY_P);
	}

	@Override
	public void update(ImageFile image, int x, int y, char glyph, int color) {
		if (Input.isButton(Input.LEFT)) {
			if (!drawing)
				AsciiPaint.updateImage();
			drawing = true;
		} else if (drawing)
			drawing = false;
		if (drawing)
			image.set(x, y, glyph, color);
	}

	@Override
	public void render(char glyph, int color) {

	}

}
