import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ItemWindowTest{
	@Test
	void getReceptures(){
		Recepture[] arr = new Recepture[5];
		Item[] items = new Item[10];

		ItemsArrayCreator(items);
		ReceptureArrayCreator(arr, items);

		ItemWindow window = new ItemWindow(arr, null);
		Assertions.assertEquals(window.GetReceptures(), arr);

	}

	@Test
	void getNextItems(){
		Recepture[] arr = new Recepture[5];
		Item[] items = new Item[10];

		ItemsArrayCreator(items);
		ReceptureArrayCreator(arr, items);

		Item[] nextItems = new Item[3];
		items[0] = new Item(0, "stick", null, null);
		items[1] = new Item(1, "flint", null, null);
		items[2] = new Item(2, "cooked porkchop", null, null);

		ItemWindow window = new ItemWindow(arr, nextItems);
		Assertions.assertEquals(window.GetNextItems(), nextItems);
	}
	@Test
	void getCurrentRecepture(){
		Recepture[] arr = new Recepture[5];
		Item[] items = new Item[10];

		ItemsArrayCreator(items);
		ReceptureArrayCreator(arr, items);
		ItemWindow window = new ItemWindow(arr, null);
		Assertions.assertEquals(window.GetCurrentRecepture(), arr[0]);
	}
	@Test
	void nextRecepture(){
		Recepture[] arr = new Recepture[5];
		Item[] items = new Item[10];

		ItemsArrayCreator(items);
		ReceptureArrayCreator(arr, items);
		ItemWindow window = new ItemWindow(arr, null);
		window.NextRecepture();
		Assertions.assertEquals(window.GetCurrentRecepture(), arr[1]);
	}
	@Test
	void prevRecepture(){
		Recepture[] arr = new Recepture[5];
		Item[] items = new Item[10];

		ItemsArrayCreator(items);
		ReceptureArrayCreator(arr, items);
		ItemWindow window = new ItemWindow(arr, null);
		window.PrevRecepture();
		Assertions.assertEquals(window.GetCurrentRecepture(), arr[4]);
	}

	private static void ReceptureArrayCreator(Recepture[] arr, Item[] items){
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
		arr[0] = new Recepture(0, "crafting_table", recepture1, items[0], 0);
		arr[1] = new Recepture(0, "crafting_table", recepture2, items[0], 0);
		arr[2] = new Recepture(0, "furnance", recepture3, items[0], 0);
		arr[3] = new Recepture(0, "brewing_stand", recepture4, items[0], 0);
		arr[4] = new Recepture(0, "smithing_table", recepture5, items[0], 0);
	}

	public static void ItemsArrayCreator(Item[] items) {
		items[0] = new Item(-1, null, null, null);
		items[1] = new Item(0, "aa", null, null);
		items[2] = new Item(1, "aab", null, null);
		items[3] = new Item(2, "bab", null, null);
		items[4] = new Item(3, "c", null, null);
		items[5] = new Item(4, "g", null, null);
		items[6] = new Item(5, "z", null, null);
		items[7] = new Item(6, "k klet", null, null);
		items[8] = new Item(7, "kk", null, null);
		items[9] = new Item(8, "a", null, null);
	}
}