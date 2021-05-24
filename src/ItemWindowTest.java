import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

class ItemWindowTest{
	@Test
	void getReceptures(){
		LinkedList<Recepture> arr = new LinkedList<>();
		Item[] items = new Item[10];

		ItemsArrayCreator(items);
		ReceptureArrayCreator(arr, items);

		ItemWindow window = new ItemWindow(arr, null);
		Assertions.assertEquals(arr, window.GetReceptures());
	}

	@Test
	void getNextItems(){
		LinkedList<Recepture> arr = new LinkedList<>();
		Item[] items = new Item[10];

		ItemsArrayCreator(items);
		ReceptureArrayCreator(arr, items);

		LinkedList<Item> nextItems = new LinkedList<>();
		nextItems.add(new Item(0, "stick", null, null));
		nextItems.add(new Item(1, "flint", null, null));
		nextItems.add(new Item(2, "cooked porkchop", null, null));

		ItemWindow window = new ItemWindow(arr, nextItems);
		Assertions.assertEquals(nextItems, window.GetNextItems());
	}
	@Test
	void getCurrentRecepture(){
		LinkedList<Recepture> arr = new LinkedList<>();
		Item[] items = new Item[10];

		ItemsArrayCreator(items);
		ReceptureArrayCreator(arr, items);
		ItemWindow window = new ItemWindow(arr, null);
		Assertions.assertEquals(arr.get(0), window.GetCurrentRecepture());
	}
	@Test
	void nextRecepture(){
		LinkedList<Recepture> arr = new LinkedList<>();
		Item[] items = new Item[10];

		ItemsArrayCreator(items);
		ReceptureArrayCreator(arr, items);
		ItemWindow window = new ItemWindow((LinkedList<Recepture>) arr.clone(), null);

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
	void prevRecepture(){
		LinkedList<Recepture> arr = new LinkedList<>();
		Item[] items = new Item[10];

		ItemsArrayCreator(items);
		ReceptureArrayCreator(arr, items);
		ItemWindow window = new ItemWindow((LinkedList<Recepture>) arr.clone(), null);

		window.PrevRecepture();
		Assertions.assertEquals(arr.get(4), window.GetCurrentRecepture());

		window.PrevRecepture();
		Assertions.assertEquals(arr.get(3), window.GetCurrentRecepture());

		window.PrevRecepture();
		window.PrevRecepture();
		window.PrevRecepture();
		Assertions.assertEquals(arr.get(0), window.GetCurrentRecepture());
	}

	private static void ReceptureArrayCreator(LinkedList<Recepture> arr, Item[] items){
		Item[] recepture1 = {
				items[1], items[0], items[0],
				items[1], items[0], items[0],
				items[0], items[0], items[0]
		};
		Item[] recepture2 = {
				items[8], items[8], items[0],
				items[8], items[8], items[0],
				items[0], items[0], items[0]
		};
		Item[] recepture3 = {
				items[5], items[0], items[0],
				items[0], items[0], items[0],
				items[0], items[0], items[0]
		};
		Item[] recepture4 = {
				items[3], items[4], items[0],
				items[1], items[0], items[0],
				items[0], items[0], items[0]
		};
		Item[] recepture5 = {
				items[7], items[0], items[0],
				items[0], items[0], items[0],
				items[0], items[0], items[0]
		};
		arr.add(new Recepture(0, "crafting_table", recepture1, items[0], 0));
		arr.add(new Recepture(0, "crafting_table", recepture2, items[0], 0));
		arr.add(new Recepture(0, "furnance", recepture3, items[0], 0));
		arr.add(new Recepture(0, "brewing_stand", recepture4, items[0], 0));
		arr.add(new Recepture(0, "smithing_table", recepture5, items[0], 0));
	}

	public static void ItemsArrayCreator(Item[] items) {
		items[0] = null;
		items[1] = new Item(0, "aa", null, null);
		items[2] = new Item(1, "aab", null, null);
		items[3] = new Item(2, "bab", null, null);
		items[4] = new Item(3, "c", null, null);
		items[5] = new Item(4, "g", null, null);
		items[6] = new Item(5, "z", null, null);
		items[7] = new Item(6, "k klet", null, null);
		items[8] = new Item(7, "k k", null, null);
		items[9] = new Item(8, "a", null, null);
	}
}