import java.util.LinkedList;

public class Main{
	private static LinkedList<Item> items;
	private static LinkedList<Recepture> receptures;

	public static LinkedList<Item> GetItems() {
		return items;
	}

	public static void SetItems(LinkedList<Item> _items) {
		items = _items;
	}

	public static LinkedList<Recepture> GetReceptures() {
		return receptures;
	}

	public static void SetReceptures(LinkedList<Recepture> _receptures) {
		receptures = _receptures;
	}

	public static void Update() {

	}
}
