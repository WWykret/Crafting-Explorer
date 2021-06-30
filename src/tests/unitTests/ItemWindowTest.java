import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class ItemWindowTest {

    @Test
    void getReceptures() {
        ArrayList<Recepture> arr = new ArrayList<>();
        ArrayList<Item> arr2 = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);
        for (int i = 0; i < 10; i++) {
            arr2.add(items[i]);
        }

        Main.SetItems(arr2);
        Main.SetReceptures(arr);

        ItemWindow window = new ItemWindow(items[1]);
        Assertions.assertEquals(new ArrayList<Item>(), window.GetReceptures());
    }

    @Test
    void getNextItems() {
        ArrayList<Recepture> arr = new ArrayList<>();
        ArrayList<Item> arr2 = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);
        for (int i = 0; i < 10; i++) {
            arr2.add(items[i]);
        }

        Main.SetItems(arr2);
        Main.SetReceptures(arr);


        ItemWindow window = new ItemWindow(items[1]);
        ArrayList<Item> test = new ArrayList<>();
        test.add(items[0]);
        Assertions.assertEquals(new ArrayList<Item>(), window.GetReceptures());
    }

    @Test
    void getCurrentRecepture() {
        ArrayList<Recepture> arr = new ArrayList<>();
        ArrayList<Item> arr2 = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);
        for (int i = 0; i < 10; i++) {
            arr2.add(items[i]);
        }

        Main.SetItems(arr2);
        Main.SetReceptures(arr);

        ItemWindow window = new ItemWindow(items[0]);
        Assertions.assertEquals(arr.get(0), window.GetCurrentRecepture());
    }

    @Test
    void nextRecepture() {
        ArrayList<Recepture> arr = new ArrayList<>();
        ArrayList<Item> arr2 = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);
        for (int i = 0; i < 10; i++) {
            arr2.add(items[i]);
        }

        Main.SetItems(arr2);
        Main.SetReceptures(arr);

        ItemWindow window = new ItemWindow(items[0]);

        window.NextRecepture();
        Assertions.assertEquals(arr.get(1), window.GetCurrentRecepture());

        window.NextRecepture();
        Assertions.assertEquals(arr.get(2), window.GetCurrentRecepture());

        window.NextRecepture();
        window.NextRecepture();
        window.NextRecepture();
        Assertions.assertEquals(arr.get(0), window.GetCurrentRecepture());
    }

    @Test
    void prevRecepture() {
        ArrayList<Recepture> arr = new ArrayList<>();
        ArrayList<Item> arr2 = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);
        for (int i = 0; i < 10; i++) {
            arr2.add(items[i]);
        }

        Main.SetItems(arr2);
        Main.SetReceptures(arr);

        ItemWindow window = new ItemWindow(items[0]);

        window.PrevRecepture();
        Assertions.assertEquals(arr.get(4), window.GetCurrentRecepture());

        window.PrevRecepture();
        Assertions.assertEquals(arr.get(3), window.GetCurrentRecepture());

        window.PrevRecepture();
        window.PrevRecepture();
        window.PrevRecepture();
        Assertions.assertEquals(arr.get(0), window.GetCurrentRecepture());
    }

    @Test
    void GetMainItem() {
        ArrayList<Recepture> arr = new ArrayList<>();
        ArrayList<Item> arr2 = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);
        for (int i = 0; i < 10; i++) {
            arr2.add(items[i]);
        }

        Main.SetItems(arr2);
        Main.SetReceptures(arr);

        ItemWindow window = new ItemWindow(items[2]);
        Assertions.assertEquals(items[2], window.GetMainItem());
    }

    private static void ReceptureArrayCreator(ArrayList<Recepture> arr, Item[] items) {
		ArrayList<Item> rec1 = new ArrayList<>(Arrays.asList(
		        items[1], items[0], items[0],
			    items[1], items[0], items[0],
			    items[0], items[0], items[0]));
		ArrayList<Item> rec2 = new ArrayList<>(Arrays.asList(
			    items[8], items[8], items[0],
			    items[8], items[8], items[0],
			    items[0], items[0], items[0]));
		ArrayList<Item> rec3 = new ArrayList<>(Arrays.asList(
			    items[5], items[0], items[0],
			    items[0], items[0], items[0],
			    items[0], items[0], items[0]));
		ArrayList<Item> rec4 = new ArrayList<>(Arrays.asList(
			    items[3], items[4], items[0],
			    items[1], items[0], items[0],
			    items[0], items[0], items[0]));
		ArrayList<Item> rec5 = new ArrayList<>(Arrays.asList(
			    items[7], items[0], items[0],
			    items[0], items[0], items[0],
			    items[0], items[0], items[0]));

        arr.add(new Recepture(0, Utils.CraftingType.craft3x3, rec1, items[0], 0));
        arr.add(new Recepture(0, Utils.CraftingType.craft3x3, rec2, items[0], 0));
        arr.add(new Recepture(0, Utils.CraftingType.smelt, rec3, items[0], 0));
        arr.add(new Recepture(0, Utils.CraftingType.brew, rec4, items[0], 0));
        arr.add(new Recepture(0, Utils.CraftingType.smith, rec5, items[0], 0));
    }

    public static void ItemsArrayCreator(Item[] items) {
        items[0] = null;
        items[1] = new Item(0, "minecraft:aa", null, null);
        items[2] = new Item(1, "minecraft:aab", null, null);
        items[3] = new Item(2, "minecraft:bab", null, null);
        items[4] = new Item(3, "minecraft:c", null, null);
        items[5] = new Item(4, "minecraft:g", null, null);
        items[6] = new Item(5, "minecraft:z", null, null);
        items[7] = new Item(6, "minecraft:k klet", null, null);
        items[8] = new Item(7, "minecraft:k k", null, null);
        items[9] = new Item(8, "minecraft:a", null, null);
    }


}
