import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Item{

	int id;
	String name;
	String graphicsPath;
	ArrayList<Item> types;

	public Item(int id, String name,String graphicsPath,ArrayList<Item> types ,String description) {
		this.id = id;
		this.name = name;
		this.graphicsPath = graphicsPath;
		if (types != null && types.size() > 0) this.types = new ArrayList<Item>(types);
		else this.types = null;
	}

	public Item(int id, String name,String graphicsPath,ArrayList<Item> types) {
		this(id, name, graphicsPath, types, "");
	}


	public int GetID() {
		return 0;
	}

	public String GetDescription() {
		return null;
	}

	public void SetDescription(String newDesc) {

	}

	public String GetName() {
		return this.name;
	}

	public BufferedImage GetGraphics() {
		return null;
	}

	public Item[] GetTypes() {
		return null;
	}
}
