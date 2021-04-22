import java.awt.*;
public class Item{

	public Item(int id, String name,String graphicsPath,Item[] types ,String description) {
	}

	public Item(int id, String name,String graphicsPath,Item[] types) {
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
		return null;
	}

	public Image GetGraphics() {
		return null;
	}

	public Item[] GetTypes() {
		return null;
	}
}
