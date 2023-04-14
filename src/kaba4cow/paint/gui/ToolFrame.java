package kaba4cow.paint.gui;

import java.util.ArrayList;

import kaba4cow.ascii.core.Window;
import kaba4cow.ascii.drawing.glyphs.Glyphs;
import kaba4cow.ascii.drawing.gui.GUIFrame;
import kaba4cow.ascii.drawing.gui.GUIRadioButton;
import kaba4cow.ascii.drawing.gui.GUIRadioPanel;
import kaba4cow.ascii.drawing.gui.GUISeparator;
import kaba4cow.ascii.drawing.gui.GUIText;
import kaba4cow.paint.AsciiPaint;
import kaba4cow.paint.tools.Tool;

public class ToolFrame extends GUIFrame {

	private GUIText infoGlyph;
	private GUIText infoColor1;
	private GUIText infoColor2;
	private GUIText infoTool;

	private GUIRadioPanel panel;

	public ToolFrame() {
		super(0, false, false);
		setTitle("Tools");

		infoTool = new GUIText(this, -1, "");

		infoGlyph = new GUIText(this, -1, "");

		StringBuilder builder = new StringBuilder();
		builder.append(Glyphs.FULL_BLOCK);
		builder.append(Glyphs.DARK_SHADE);
		builder.append(Glyphs.MEDIUM_SHADE);
		builder.append(Glyphs.LIGHT_SHADE);
		builder.append(Glyphs.BLACK_SQUARE);

		infoColor1 = new GUIText(this, -1, "");
		infoColor2 = new GUIText(this, -1, builder.toString());
		new GUISeparator(this, -1, false);

		panel = new GUIRadioPanel(this, -1, "Tools:");

		ArrayList<Tool> tools = Tool.getTools();
		for (int i = 0; i < tools.size(); i++)
			new GUIRadioButton(panel, -1, tools.get(i).getName());
	}

	@Override
	public void render() {
		infoTool.setText("Tool: " + AsciiPaint.getTool().getName());
		infoGlyph.setText("Glyph: " + AsciiPaint.getGlyph());
		infoColor1.setText(String.format("Color: %06X", AsciiPaint.getColor()));
		infoColor2.setColor(AsciiPaint.getColor());
		render(Window.getWidth() - Window.getWidth() / 4, 0, Window.getWidth() / 4, Window.getHeight(), false);
	}

	public Tool getTool() {
		return Tool.getTool(panel.getIndex());
	}

}
