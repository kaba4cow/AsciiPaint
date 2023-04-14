package kaba4cow.paint;

import java.util.ArrayList;
import java.util.Stack;

import kaba4cow.ascii.MainProgram;
import kaba4cow.ascii.core.Engine;
import kaba4cow.ascii.core.Input;
import kaba4cow.ascii.core.Window;
import kaba4cow.ascii.drawing.drawers.BoxDrawer;
import kaba4cow.ascii.drawing.drawers.Drawer;
import kaba4cow.ascii.drawing.gui.GUIFrame;
import kaba4cow.ascii.toolbox.files.ImageFile;
import kaba4cow.console.Console;
import kaba4cow.console.ConsoleProgram;
import kaba4cow.paint.gui.ColorSelector;
import kaba4cow.paint.gui.GlyphSelector;
import kaba4cow.paint.gui.ToolFrame;
import kaba4cow.paint.tools.ColorPicker;
import kaba4cow.paint.tools.FloodFillColor;
import kaba4cow.paint.tools.FloodFillGlyph;
import kaba4cow.paint.tools.GlyphPicker;
import kaba4cow.paint.tools.Line;
import kaba4cow.paint.tools.Pencil;
import kaba4cow.paint.tools.Tool;

public class AsciiPaint extends ConsoleProgram implements MainProgram {

	public static ImageFile project = null;
	public static String projectName = null;

	private static boolean gui = false;

	private static GlyphSelector glyphSelector;
	private static ColorSelector colorSelector;
	private ToolFrame toolFrame;
	private ArrayList<GUIFrame> frames;

	private static Camera camera;
	private static Tool tool;
	private static char glyph;
	private static int color;

	private static Stack<ImageFile> history;

	public AsciiPaint() {
		super(AsciiPaint.class, "PAINT by kaba4cow");
		Commands.init();
	}

	@Override
	public void init() {
		tool = new Pencil();
		new Line();
		new ColorPicker();
		new GlyphPicker();
		new FloodFillColor();
		new FloodFillGlyph();

		frames = new ArrayList<>();
		frames.add(colorSelector = new ColorSelector());
		frames.add(glyphSelector = new GlyphSelector());
		frames.add(toolFrame = new ToolFrame());

		camera = new Camera();
	}

	@Override
	public void update(float dt) {
		if (project == null)
			gui = false;

		if (gui)
			updateGUI(dt);
		else
			updateConsole(projectName);
	}

	public void updateGUI(float dt) {
		if (Input.isKeyDown(Input.KEY_ESCAPE)) {
			gui = false;
			return;
		}

		if (Input.isKey(Input.KEY_CONTROL_LEFT)) {
			if (Input.isKeyDown(Input.KEY_S))
				ImageFile.write(project, Console.getDirectory().getAbsolutePath() + "/" + projectName);
			else if (!history.isEmpty() && Input.isKeyDown(Input.KEY_Z))
				project = history.pop();
			return;
		}

		tool = toolFrame.getTool();

		int clicked = -1;
		GUIFrame frame;
		for (int i = frames.size() - 1; i >= 0; i--) {
			frame = frames.get(i);
			frame.update();
			if (frame.wasClicked())
				clicked = i;
		}

		if (clicked != -1) {
			frame = frames.remove(clicked);
			frames.add(frame);
		} else if (!GUIFrame.framesClicked()) {
			camera.update(dt);

			int x = camera.getMouseX();
			int y = camera.getMouseY();

			if (x >= 0 && x < project.getWidth() && y >= 0 && y < project.getHeight())
				tool.update(project, x, y, glyph, color);
		}
	}

	@Override
	public void render() {
		updateWindow();
		if (gui)
			renderGUI();
		else
			renderConsole();
	}

	public void renderGUI() {
		Window.setDrawCursor(true);

		int width = toolFrame.getX();
		int height = Window.getHeight();
		int w = project.getWidth();
		int h = project.getHeight();
		BoxDrawer.drawBox(-camera.getX() - 1, -camera.getY() - 1, w + 1, h + 1, true, consoleColor);
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				int ix = x + camera.getX();
				int iy = y + camera.getY();

				if (ix < 0 || ix >= w || iy < 0 || iy >= h)
					continue;
				char c = project.getGlyph(ix, iy);
				int color = project.getColor(ix, iy);
				Drawer.draw(x, y, c, color);
			}
		tool.render(glyph, color);

		BoxDrawer.disableCollision();
		for (int i = 0; i < frames.size(); i++) {
			frames.get(i).setColor(consoleColor);
			frames.get(i).render();
		}
		BoxDrawer.enableCollision();
	}

	public static void openEditor() {
		gui = true;
		history = new Stack<>();
		glyphSelector.setX(Window.getWidth());
		glyphSelector.setY(0);
		colorSelector.setX(Window.getWidth());
		colorSelector.setY(Window.getHeight());
	}

	public static void updateImage() {
		if (history.isEmpty() || !history.lastElement().equals(project))
			history.push(project.copy());
	}

	public static char getGlyph() {
		return glyph;
	}

	public static void setGlyph(char glyph) {
		AsciiPaint.glyph = glyph;
	}

	public static int getColor() {
		return color;
	}

	public static void setColor(int color) {
		AsciiPaint.color = color;
		colorSelector.setSelectorColor(color);
	}

	public static Tool getTool() {
		return tool;
	}

	public static void setTool(Tool tool) {
		AsciiPaint.tool = tool;
	}

	public static Camera getCamera() {
		return camera;
	}

	public static void main(String[] args) {
		Engine.init("Paint", 60);
		Engine.start(new AsciiPaint());
	}

}
