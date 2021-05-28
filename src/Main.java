import java.util.ArrayList;

public class Main {
    private static ArrayList<Item> items;
    private static ArrayList<Recepture> receptures;

    public static void main(String[] args) {
        items = FileKeeper.GetInstance().ReadItemsFromXML();
        receptures = FileKeeper.GetInstance().ReadRecipesFromXML(items);

        Window window = new Window();
        window.displayWindow();
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

    public static void Update(String path) {
        System.out.println("starting the loading");
        LoadedFiles loadedFiles = FileLoader.GetInstance().LoadFiles(path);

        items = loadedFiles.items;
        receptures = loadedFiles.receptures;
        System.out.println("files successfully loaded");
    }
}
