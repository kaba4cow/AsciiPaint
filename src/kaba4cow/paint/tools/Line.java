package kaba4cow.paint.tools;

import kaba4cow.ascii.core.Input;
import kaba4cow.ascii.drawing.Drawer;
import kaba4cow.ascii.toolbox.files.ImageFile;
import kaba4cow.ascii.toolbox.maths.Maths;
import kaba4cow.paint.AsciiPaint;

public class Line extends Tool {

	private int startX = -1;
	private int startY = -1;
	private int endX = -1;
	private int endY = -1;

	public Line() {
		super("Line", Input.KEY_L);
	}

	@Override
	public void update(ImageFile image, int x, int y, char glyph, int color) {
		endX = x;
		endY = y;
		if (Input.isButtonDown(Input.RIGHT)) {
			startX = -1;
			startY = -1;
		} else if (Input.isButtonDown(Input.LEFT)) {
			if (startX == -1 || startY == -1) {
				startX = x;
				startY = y;
			} else {
				int x0 = startX - AsciiPaint.getCamera().getX() - 1;
				int y0 = startY - AsciiPaint.getCamera().getY() - 1;
				int x1 = endX - AsciiPaint.getCamera().getX() - 1;
				int y1 = endY - AsciiPaint.getCamera().getY() - 1;

				if (x0 != x1 || y0 != y1) {
					AsciiPaint.updateImage();
					int dx = Maths.abs(x1 - x0);
					int dy = Maths.abs(y1 - y0);
					int sx = x0 < x1 ? 1 : -1;
					int sy = y0 < y1 ? 1 : -1;
					int e = dx - dy;
					int e2 = 0;
					image.set(x0, y0, glyph, color);
					while (true) {
						e2 = 2 * e;
						if (e2 > -dy) {
							e -= dy;
							x0 += sx;
						}
						if (e2 < dx) {
							e += dx;
							y0 += sy;
						}
						image.set(x0, y0, glyph, color);
						if (x0 == x1 && y0 == y1)
							break;
					}
				}

				startX = -1;
				startY = -1;
			}
		}
	}

	@Override
	public void render(char glyph, int color) {
		if (startX == -1 || startY == -1)
			return;

		int x0 = startX - AsciiPaint.getCamera().getX();
		int y0 = startY - AsciiPaint.getCamera().getY();
		int x1 = endX - AsciiPaint.getCamera().getX();
		int y1 = endY - AsciiPaint.getCamera().getY();

		if (x0 == x1 && y0 == y1)
			Drawer.draw(x0, y0, glyph, color);
		else {
			int dx = Maths.abs(x1 - x0);
			int dy = Maths.abs(y1 - y0);
			int sx = x0 < x1 ? 1 : -1;
			int sy = y0 < y1 ? 1 : -1;
			int e = dx - dy;
			int e2 = 0;
			Drawer.draw(x0, y0, glyph, color);
			while (true) {
				e2 = 2 * e;
				if (e2 > -dy) {
					e -= dy;
					x0 += sx;
				}
				if (e2 < dx) {
					e += dx;
					y0 += sy;
				}
				Drawer.draw(x0, y0, glyph, color);
				if (x0 == x1 && y0 == y1)
					return;
			}
		}
	}

}
