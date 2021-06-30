import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
class SearchEngineTest{
	@Test
	void filterRecepies(){
		ArrayList<Recepture> list = new ArrayList<>();
		Item[] items = new Item[10];

		ItemWindowTest.ItemsArrayCreator(items);

		list.add(new Recepture(0, Utils.CraftingType.craft3x3, null, items[0], 0));
		list.add(new Recepture(0, Utils.CraftingType.craft3x3, null, items[1], 0));
		list.add(new Recepture(0, Utils.CraftingType.craft3x3, null, items[2], 0));
		list.add(new Recepture(0, Utils.CraftingType.craft3x3, null, items[3], 0));
		list.add(new Recepture(0, Utils.CraftingType.craft3x3, null, items[4], 0));
		list.add(new Recepture(0, Utils.CraftingType.craft3x3, null, items[5], 0));
		list.add(new Recepture(0, Utils.CraftingType.craft3x3, null, items[6], 0));
		list.add(new Recepture(0, Utils.CraftingType.craft3x3, null, items[7], 0));
		list.add(new Recepture(0, Utils.CraftingType.craft3x3, null, items[8], 0));
		list.add(new Recepture(0, Utils.CraftingType.craft3x3, null, items[9], 0));

		ArrayList<Recepture> test1 = new ArrayList<Recepture>();
		test1.add(list.get(1));
		test1.add(list.get(2));
		test1.add(list.get(3));
		test1.add(list.get(9));

		ArrayList<Recepture> test2 = new ArrayList<Recepture>();

		ArrayList<Recepture> test3 = new ArrayList<Recepture>();
		test3.add(list.get(1));
		test3.add(list.get(2));

		ArrayList<Recepture> test4 = new ArrayList<Recepture>();
		test4.add(list.get(2));
		test4.add(list.get(3));

		ArrayList<Recepture> test5 = new ArrayList<Recepture>();
		test5.add(list.get(6));

		ArrayList<Recepture> test6 = new ArrayList<Recepture>();
		test6.add(list.get(7));
		test6.add(list.get(8));

		Assertions.assertEquals(test1, SearchEngine.FilterRecepies(list, "a"), "test 1");
		Assertions.assertEquals(test2, SearchEngine.FilterRecepies(list, "baba"), "test 2");
		Assertions.assertEquals(test3, SearchEngine.FilterRecepies(list, "aa"), "test 3");
		Assertions.assertEquals(test4, SearchEngine.FilterRecepies(list, "ab"), "test 4");
		Assertions.assertEquals(test5, SearchEngine.FilterRecepies(list, "z"), "test 5");
		Assertions.assertEquals(test6, SearchEngine.FilterRecepies(list, "k k"), "test 6");
	}
}