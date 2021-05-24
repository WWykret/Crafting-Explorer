public class Recepture{

	private int id;
	private String method;
	private Item[] ingredients;
	private Item result;
	private int resultQuantity;

	public Recepture(int id, String method, Item[] ingredients, Item result, int resultQuantity) {
		this.id = id;
		this.method = method;
		this.ingredients = ingredients;
		this.result = result;
		this.resultQuantity = resultQuantity;
	}

	public int GetID() {
		return id;
	}

	public String GetMethod() {
		return method;
	}

	public Item[] GetIngredients() {
		return ingredients;
	}

	public Item GetResult() {
		return result;
	}

	public int GetResultQuantity() {
		return resultQuantity;
	}

}
