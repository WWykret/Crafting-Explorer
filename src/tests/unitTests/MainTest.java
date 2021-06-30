import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static ArrayList<Item> items = new ArrayList<>();
    void itemization() {
        items.add(new Item(0, "coal", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\coal.png", null));
        items.add(new Item(1, "charcoal", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\charcoal.png", null));
        items.add(new Item(2, "stick", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\stick.png", null));
        items.add(new Item(3, "torch", "..\\resources\\example_data\\assets\\minecraft\\textures\\bloc\\torch.png", null));
    };
    private static ArrayList<Recepture> receptures = new ArrayList<>();
    void recepturization() {
        Item[] items = new Item[10];
        ItemWindowTest.ItemsArrayCreator(items);
        ArrayList<Item> recepture1 = new ArrayList<Item>(Arrays.asList(
                items[1], items[0], items[0],
                items[1], items[0], items[0],
                items[0], items[0], items[0]
        ));
        ArrayList<Item> recepture2 = new ArrayList<Item>(Arrays.asList(
                items[8], items[8], items[0],
                items[8], items[8], items[0],
                items[0], items[0], items[0]
        ));
        receptures.add(new Recepture(0, Utils.CraftingType.craft3x3, recepture1, items[3], 4));
        receptures.add(new Recepture(0, Utils.CraftingType.craft3x3, recepture2, items[3], 4));
    };

    @Test
    void getItems() {
        Main.SetItems(null);
        assertNull(Main.GetItems());
        itemization();
        Main.SetItems(items);
        assertEquals(items, Main.GetItems());
    }

    @Test
    void getReceptures() {
        Main.SetReceptures(null);
        assertNull(Main.GetReceptures());
        recepturization();
        Main.SetReceptures(receptures);
        assertEquals(receptures, Main.GetReceptures());
    }
}