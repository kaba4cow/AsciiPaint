package kaba4cow.paint;

import kaba4cow.ascii.input.Keyboard;
import kaba4cow.ascii.input.Mouse;

public class Camera {

	private static final float MOVE_SPEED = 80f;
	private static final float SCROLL_SPEED = 10f;

	private float x;
	private float y;

	private boolean drag;
	private int dragX;
	private int dragY;

	public Camera() {
		reset();
	}

	public void update(float dt) {
		if (Keyboard.isKey(Keyboard.KEY_C))
			reset();

		if (Keyboard.isKey(Keyboard.KEY_W))
			y -= MOVE_SPEED * dt;
		if (Keyboard.isKey(Keyboard.KEY_S))
			y += MOVE_SPEED * dt;
		if (Keyboard.isKey(Keyboard.KEY_A))
			x -= MOVE_SPEED * dt;
		if (Keyboard.isKey(Keyboard.KEY_D))
			x += MOVE_SPEED * dt;

		if (Keyboard.isKey(Keyboard.KEY_SHIFT_LEFT))
			x += SCROLL_SPEED * Mouse.getScroll();
		else
			y -= SCROLL_SPEED * Mouse.getScroll();

		int mX = Mouse.getTileX();
		int mY = Mouse.getTileY();

		if (Mouse.isKeyDown(Mouse.MIDDLE)) {
			drag = true;
			dragX = (int) x + mX;
			dragY = (int) y + mY;
		} else if (Mouse.isKeyUp(Mouse.MIDDLE))
			drag = false;

		if (drag) {
			x = dragX - mX;
			y = dragY - mY;
		}
	}

	public void reset() {
		x = -1f;
		y = -1f;
		drag = false;
		dragX = 0;
		dragY = 0;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getMouseX() {
		return Mouse.getTileX() + (int) x;
	}

	public int getMouseY() {
		return Mouse.getTileY() + (int) y;
	}

}
