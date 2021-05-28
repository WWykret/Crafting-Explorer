import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class XMLRecipe {
    int id;
    String method;
    ArrayList<String> ingredients;
    String result;
    int resultQuantity;

    public XMLRecipe() {}

    public XMLRecipe(Recepture recipe) {
        this.id = recipe.GetID();
        this.method = recipe.GetMethod();

        ArrayList<String> ingredientNames = new ArrayList<>();
        for (Item item: recipe.GetIngredients()) {
            if (item == null) ingredientNames.add("NULL");
            else ingredientNames.add(item.GetName());
        }
        this.ingredients = ingredientNames;

        this.result = recipe.GetResult().GetName();
        this.resultQuantity = recipe.GetResultQuantity();
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getResultQuantity() {
        return resultQuantity;
    }

    public void setResultQuantity(int resultQuantity) {
        this.resultQuantity = resultQuantity;
    }
}