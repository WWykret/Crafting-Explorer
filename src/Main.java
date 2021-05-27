import java.util.ArrayList;

public class Main {
    private static ArrayList<Item> items;
    private static ArrayList<Recepture> receptures;

    public static void main(String[] args) {
		FileLoader f = new FileLoader("D:\\Studia\\4 Semestr\\IO\\Zadania\\github repo\\Projekt-IO\\resources\\example.jar");
        LoadedFiles lo = f.LoadFiles();

        items = lo.items;
        receptures = lo.receptures;

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
        FileLoader f = new FileLoader(path);
        LoadedFiles lo = f.LoadFiles();
        items = lo.items;
        receptures = lo.receptures;
    }
}
