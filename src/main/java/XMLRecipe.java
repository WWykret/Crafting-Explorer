import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class XMLRecipe {
    private int id;
    private Utils.CraftingType method;
    private ArrayList<String> ingredients;
    private String result;
    private String fileName;
    private int resultQuantity;

    public XMLRecipe() {}

    public XMLRecipe(Recepture recipe) {
        this.id = recipe.GetID();
        this.method = recipe.GetMethod();

        ArrayList<String> ingredientNames = new ArrayList<>();
        for (Item item: recipe.GetIngredients()) {
            if (item == null) ingredientNames.add("NULL");
            else ingredientNames.add(item.GetRawName());
        }
        this.ingredients = ingredientNames;

        this.result = recipe.GetResult().GetRawName();
        this.fileName = recipe.GetResult().GetName();
        this.resultQuantity = recipe.GetResultQuantity();
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Utils.CraftingType getMethod() {
        return method;
    }

    public void setMethod(Utils.CraftingType method) {
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

    public String getFileName() {
        return fileName;
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
