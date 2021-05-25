import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Item{

	private int id;
	private String name;
	private BufferedImage graphics;
	private ArrayList<Item> types;
	private String description;

	public Item(int id, String name, String graphicsPath, ArrayList<Item> types, String description) {
		this.id = id;
		this.name = name;
		try {
			graphics = ImageIO.read(new File(graphicsPath));
		} catch (Exception e) {graphics = null;}

		if (types != null && types.size() > 0) this.types = new ArrayList<Item>(types);
		else this.types = null;
		this.description = description;
	}

	public Item(int id, String name, String graphicsPath, ArrayList<Item> types) {
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
		return this.name;
	}

	public void AddTypes(ArrayList<Item> typesToAdd) {
		if (this.types != null) this.types.addAll(typesToAdd);
		else this.types = new ArrayList<Item>(typesToAdd);
	}

	public BufferedImage GetGraphics() {
		return graphics;
	}

	public ArrayList<Item> GetTypes() {
		return types;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) return false;
		if (obj == this) return true;
		Item other = (Item) obj;
		boolean subItemsOk;
		if (this.types == null && other.GetTypes() == null) subItemsOk = true;
		else if (this.types != null && other.GetTypes() != null) {
			subItemsOk = this.types.containsAll(other.GetTypes()) && other.GetTypes().containsAll(this.types);
		}
		else subItemsOk = false;
		boolean nameOk = other.GetName().equals(this.name);
		return nameOk && subItemsOk;
	}
}
