import java.util.ArrayList;

public class Recepture {
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
        return method;
    }

    public ArrayList<Item> GetIngredients() {
        return ingredients;
    }

    public Item GetResult() {
        return result;
    }

    public int GetResultQuantity() {
        return resultQuantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Recepture other = (Recepture) obj;
        boolean ingredientsOK = true;
        if (ingredients.size() != other.GetIngredients().size()) return false;
        for (int i = 0; i < ingredients.size(); i++) {
            if (this.ingredients.get(i) == null && other.GetIngredients().get(i) != null) ingredientsOK = false;
            else if (this.ingredients.get(i) != null && other.GetIngredients().get(i) == null)
                ingredientsOK = false;
            else if (this.ingredients.get(i) != null) {
                if (!this.ingredients.get(i).equals(other.GetIngredients().get(i))) ingredientsOK = false;
            }
        }
        boolean resultOk = this.result.equals(other.GetResult());
        boolean countOk = this.resultQuantity == other.GetResultQuantity();
        boolean methodOK = this.method.equals(other.GetMethod());
        return ingredientsOK && resultOk && countOk && methodOK;
    }

}
