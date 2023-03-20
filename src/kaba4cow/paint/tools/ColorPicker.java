package kaba4cow.paint.tools;

import kaba4cow.ascii.input.Keyboard;
import kaba4cow.ascii.input.Mouse;
import kaba4cow.ascii.toolbox.files.ImageFile;
import kaba4cow.paint.AsciiPaint;

public class ColorPicker extends Tool {

	public ColorPicker() {
		super("Color Picker", Keyboard.KEY_I);
	}

	@Override
	public void update(ImageFile image, int x, int y, char glyph, int color) {
		if (Mouse.isKey(Mouse.LEFT))
			AsciiPaint.setColor(image.getColor(x, y));
	}

	@Override
	public void render(char glyph, int color) {

	}

}
