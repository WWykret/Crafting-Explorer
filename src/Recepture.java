import java.util.ArrayList;

public class Recepture{
	int id;
	String method;
	ArrayList<Item> ingredients;
	Item result;
	int resultQuantity;

	public Recepture(int id, String method, ArrayList<Item> ingredients, Item result, int resultQuantity) {
		this.id = id;
		this.method = method;
		this.ingredients = new ArrayList<Item>(ingredients);
		this.result = result;
		this.resultQuantity = resultQuantity;
	}

	public int GetID() {
		return 0;
	}

	public String GetMethod() {
		return null;
	}

	public Item[] GetIngredients() {
		return null;
	}

	public Item GetResult() {
		return null;
	}

	public int GetResultQuantity() {
		return 0;
	}

}
