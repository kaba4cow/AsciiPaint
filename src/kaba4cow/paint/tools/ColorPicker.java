package kaba4cow.paint.tools;

import kaba4cow.ascii.core.Input;
import kaba4cow.ascii.toolbox.files.ImageFile;
import kaba4cow.paint.AsciiPaint;

public class ColorPicker extends Tool {

	public ColorPicker() {
		super("Color Picker", Input.KEY_I);
	}

	@Override
	public void update(ImageFile image, int x, int y, char glyph, int color) {
		if (Input.isButton(Input.LEFT))
			AsciiPaint.setColor(image.getColor(x, y));
	}

	@Override
	public void render(char glyph, int color) {

	}

}
