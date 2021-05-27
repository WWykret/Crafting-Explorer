import java.util.ArrayList;

public class Main{
	private static ArrayList<Item> items;
	private static ArrayList<Recepture> receptures;

	public static void main(String[] args) {
		FileLoader.GetInstance().LoadFiles("");
    }
	
	public static ArrayList<Item> GetItems() {
		return items;
	}

	public static void SetItems(ArrayList<Item> _items) {
		items = _items;
	}

	public static ArrayList<Recepture> GetReceptures() {
		return receptures;
	}

	public static void SetReceptures(ArrayList<Recepture> _receptures) {
		receptures = _receptures;
	}

    public static void Update() {

    }
}
