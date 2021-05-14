import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
class SearchEngineTest{
	@Test
	void filterRecepies(){
		LinkedList<Recepture> list = new LinkedList<>();
		Item[] items = new Item[10];

		ItemWindowTest.ItemsArrayCreator(items);

		list.add(new Recepture(0, "crafting_table", null, items[0], 0));
		list.add(new Recepture(0, "crafting_table", null, items[1], 0));
		list.add(new Recepture(0, "crafting_table", null, items[2], 0));
		list.add(new Recepture(0, "crafting_table", null, items[3], 0));
		list.add(new Recepture(0, "crafting_table", null, items[4], 0));
		list.add(new Recepture(0, "crafting_table", null, items[5], 0));
		list.add(new Recepture(0, "crafting_table", null, items[6], 0));
		list.add(new Recepture(0, "crafting_table", null, items[7], 0));
		list.add(new Recepture(0, "crafting_table", null, items[8], 0));
		list.add(new Recepture(0, "crafting_table", null, items[9], 0));

		LinkedList<Recepture> test1 = new LinkedList<Recepture>();
		test1.add(list.get(1));
		test1.add(list.get(2));
		test1.add(list.get(9));

		LinkedList<Recepture> test2 = new LinkedList<Recepture>();

		LinkedList<Recepture> test3 = new LinkedList<Recepture>();
		test3.add(list.get(1));
		test3.add(list.get(2));

		LinkedList<Recepture> test4 = new LinkedList<Recepture>();

		LinkedList<Recepture> test5 = new LinkedList<Recepture>();
		test5.add(list.get(6));

		LinkedList<Recepture> test6 = new LinkedList<Recepture>();
		test6.add(list.get(7));
		test6.add(list.get(8));

		Assertions.assertEquals(test1, SearchEngine.FilterRecepies(list, "a"));
		Assertions.assertEquals(test2, SearchEngine.FilterRecepies(list, "baba"));
		Assertions.assertEquals(test3, SearchEngine.FilterRecepies(list, "aa"));
		Assertions.assertEquals(test4, SearchEngine.FilterRecepies(list, "ab"));
		Assertions.assertEquals(test5, SearchEngine.FilterRecepies(list, "z"));
		Assertions.assertEquals(test6, SearchEngine.FilterRecepies(list, "k k"));
	}
}