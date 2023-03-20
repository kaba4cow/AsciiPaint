package kaba4cow.paint.tools;

import java.util.LinkedHashMap;

import kaba4cow.ascii.input.Keyboard;
import kaba4cow.ascii.toolbox.files.ImageFile;

public abstract class Tool {

	private static final LinkedHashMap<String, Tool> tools = new LinkedHashMap<>();

	private final String name;

	private final int[] shortcut;

	public Tool(String name, int... shortcut) {
		this.name = name;
		this.shortcut = shortcut;
		tools.put(name, this);
	}

	public abstract void update(ImageFile image, int x, int y, char glyph, int color);

	public abstract void render(char glyph, int color);

	public boolean isSelected() {
		for (int i = 0; i < shortcut.length; i++)
			if (!Keyboard.isKey(shortcut[i]))
				return false;
		return true;
	}

	public static Tool getTool(String name) {
		return tools.get(name);
	}

	public static LinkedHashMap<String, Tool> getTools() {
		return tools;
	}

	public String getName() {
		return name;
	}

}
