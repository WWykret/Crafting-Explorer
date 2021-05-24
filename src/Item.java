import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

public class Item{

	private int id;
	private String name;
	private BufferedImage graphics;
	private LinkedList<Item> types;
	private String description;

	public Item(int id, String name, String graphicsPath, LinkedList<Item> types, String description) {
		this.id = id;
		this.name = name;
		try {
			graphics = ImageIO.read(new File(graphicsPath));
		} catch (Exception e) {graphics = null;}

		this.types = types;
		this.description = description;
	}

	public Item(int id, String name, String graphicsPath, LinkedList<Item> types) {
		this(id, name, graphicsPath, types, "");
	}

	public int GetID() {
		return id;
	}

	public String GetDescription() {
		return description;
	}

	public void SetDescription(String newDesc) {
		description = newDesc;
	}

	public String GetName() {
		return name;
	}

	public BufferedImage GetGraphics() {
		return graphics;
	}

	public LinkedList<Item> GetTypes() {
		return types;
	}
}
