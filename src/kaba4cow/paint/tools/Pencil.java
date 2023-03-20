package kaba4cow.paint.tools;

import kaba4cow.ascii.input.Keyboard;
import kaba4cow.ascii.input.Mouse;
import kaba4cow.ascii.toolbox.files.ImageFile;

public class Pencil extends Tool {

	public Pencil() {
		super("Pencil", Keyboard.KEY_P);
	}

	@Override
	public void update(ImageFile image, int x, int y, char glyph, int color) {
		if (Mouse.isKey(Mouse.LEFT))
			image.set(x, y, glyph, color);
	}

	@Override
	public void render(char glyph, int color) {

	}

}
