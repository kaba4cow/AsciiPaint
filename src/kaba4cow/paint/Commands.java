package kaba4cow.paint;

import java.io.File;

import kaba4cow.ascii.core.Display;
import kaba4cow.ascii.toolbox.files.ImageFile;
import kaba4cow.console.Command;
import kaba4cow.console.Console;

public final class Commands {

	private Commands() {

	}

	public static void init() {
		new Command("file-create", "[name] [width] [height]", "Creates new image with specified name") {
			@Override
			public void execute(String[] parameters, int numParameters, StringBuilder output) {
				if (invalidParameters(numParameters, 3, output))
					return;

				try {
					int width = Integer.parseInt(parameters[1]);
					int height = Integer.parseInt(parameters[2]);

					AsciiPaint.project = new ImageFile(width, height);
					AsciiPaint.projectName = parameters[0];
					output.append("File created\n");
				} catch (NumberFormatException e) {
					invalidParameters(output);
				}
			}
		};

		new Command("file-info", "", "Prints information about current file") {
			@Override
			public void execute(String[] parameters, int numParameters, StringBuilder output) {
				if (AsciiPaint.project == null) {
					output.append("No file selected\n");
					return;
				}

				output.append("Name: ");
				output.append(AsciiPaint.projectName);
				output.append("\nWidth: ");
				output.append(Integer.toString(AsciiPaint.project.getWidth()));
				output.append("\nHeight: ");
				output.append(Integer.toString(AsciiPaint.project.getHeight()));
				output.append('\n');
			}
		};

		new Command("file-open", "[name]", "Opens a file with specified name") {
			@Override
			public void execute(String[] parameters, int numParameters, StringBuilder output) {
				if (invalidParameters(numParameters, 1, output))
					return;

				File file = new File(Console.getDirectory().getAbsolutePath() + "/" + parameters[0]);
				if (!file.exists())
					output.append("File not found\n");
				else {
					AsciiPaint.project = ImageFile.read(file);
					AsciiPaint.projectName = file.getName();
					output.append("File opened\n");
				}
			}
		};

		new Command("file-save", "", "Saves current file") {
			@Override
			public void execute(String[] parameters, int numParameters, StringBuilder output) {
				if (AsciiPaint.project == null)
					output.append("No file selected\n");
				else if (ImageFile.write(AsciiPaint.project,
						Console.getDirectory().getAbsolutePath() + "/" + AsciiPaint.projectName))
					output.append("File saved\n");
				else
					output.append("Could not save the file\n");
			}
		};

		new Command("file-save-png", "", "Saves current file as a PNG image") {
			@Override
			public void execute(String[] parameters, int numParameters, StringBuilder output) {
				if (AsciiPaint.project == null)
					output.append("No file selected\n");
				else if (Display.saveImage(AsciiPaint.project.toFrame(),
						new File(Console.getDirectory().getAbsolutePath() + "/" + AsciiPaint.projectName + ".png")))
					output.append("Image saved\n");
				else
					output.append("Could not save the image\n");
			}
		};

		new Command("file-rename", "[name]", "Renames current file") {
			@Override
			public void execute(String[] parameters, int numParameters, StringBuilder output) {
				if (invalidParameters(numParameters, 1, output))
					return;

				if (AsciiPaint.project == null)
					output.append("No file selected\n");
				else {
					AsciiPaint.projectName = parameters[0];
					output.append("File renamed\n");
				}
			}
		};

		new Command("file-edit", "", "Opens editor") {
			@Override
			public void execute(String[] parameters, int numParameters, StringBuilder output) {
				if (AsciiPaint.project == null)
					output.append("No file selected\n");
				else
					AsciiPaint.openEditor();
			}
		};
	}

}