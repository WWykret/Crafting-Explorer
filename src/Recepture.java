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
        if (ingredients != null) this.ingredients = new ArrayList<Item>(ingredients);
        else this.ingredients = null;
        this.result = result;
        this.resultQuantity = resultQuantity;
    }

    public int GetID() {
        return id;
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
        if (obj == null) return false;
        else if (obj.getClass() != this.getClass()) return false;

        if (obj == this) return true;

        Recepture other = (Recepture) obj;
        boolean ingredientsOK = true;
        if (this.ingredients != null && other.ingredients != null) { //niepuste składniki
            if (ingredients.size() != other.GetIngredients().size()) return false;

            for (int i = 0; i < ingredients.size(); i++) {
                if ((this.ingredients.get(i) == null) == (other.ingredients.get(i) == null)) { //oba mają/nie mają naraz null
                    if (this.ingredients.get(i) != null && !this.ingredients.get(i).equals(other.ingredients.get(i))) {
                        ingredientsOK = false;
                    }
                }
            }
        } else {
            ingredientsOK = (this.ingredients == null && other.ingredients == null); //puste składniki
        }

        boolean resultOk = this.result.equals(other.GetResult());
        boolean countOk = this.resultQuantity == other.GetResultQuantity();
        boolean methodOK = this.method.equals(other.GetMethod());
        return (ingredientsOK && resultOk && countOk && methodOK);
    }

}
