package kaba4cow.paint;

import kaba4cow.ascii.core.Input;

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
		if (Input.isKey(Input.KEY_C))
			reset();

		if (Input.isKey(Input.KEY_W))
			y -= MOVE_SPEED * dt;
		if (Input.isKey(Input.KEY_S))
			y += MOVE_SPEED * dt;
		if (Input.isKey(Input.KEY_A))
			x -= MOVE_SPEED * dt;
		if (Input.isKey(Input.KEY_D))
			x += MOVE_SPEED * dt;

		if (Input.isKey(Input.KEY_SHIFT_LEFT))
			x += SCROLL_SPEED * Input.getScroll();
		else
			y -= SCROLL_SPEED * Input.getScroll();

		int mX = Input.getTileX();
		int mY = Input.getTileY();

		if (Input.isButtonDown(Input.MIDDLE)) {
			drag = true;
			dragX = (int) x + mX;
			dragY = (int) y + mY;
		} else if (Input.isButtonUp(Input.MIDDLE))
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
		return Input.getTileX() + (int) x;
	}

	public int getMouseY() {
		return Input.getTileY() + (int) y;
	}

}
