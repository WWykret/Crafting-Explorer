import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
class SearchEngineTest{
	@Test
	void filterRecepies(){
		Recepture arr[] = new Recepture[10];
		Item[] items = new Item[10];

		ItemWindowTest.ItemsArrayCreator(items);

		arr[0] = new Recepture(0, "crafting_table", null, items[0], 0);
		arr[1] = new Recepture(0, "crafting_table", null, items[1], 0);
		arr[2] = new Recepture(0, "crafting_table", null, items[2], 0);
		arr[3] = new Recepture(0, "crafting_table", null, items[3], 0);
		arr[4] = new Recepture(0, "crafting_table", null, items[4], 0);
		arr[5] = new Recepture(0, "crafting_table", null, items[5], 0);
		arr[6] = new Recepture(0, "crafting_table", null, items[6], 0);
		arr[7] = new Recepture(0, "crafting_table", null, items[7], 0);
		arr[8] = new Recepture(0, "crafting_table", null, items[8], 0);
		arr[9] = new Recepture(0, "crafting_table", null, items[9], 0);

		Recepture test1[] = new Recepture[3];
		test1[0] = arr[9];
		test1[1] = arr[1];
		test1[2] = arr[2];

		Recepture test2[] = new Recepture[0];

		Recepture test3[] = new Recepture[2];
		test3[0] = arr[1];
		test3[1] = arr[2];

		Recepture test4[] = new Recepture[0];

		Recepture test5[] = new Recepture[0];

		Recepture test6[] = new Recepture[2];
		test6[0] = arr[7];
		test6[1] = arr[8];

		Assertions.assertEquals(SearchEngine.FilterRecepies(arr, "a"), test1);
		Assertions.assertEquals(SearchEngine.FilterRecepies(arr, "baba"), test2);
		Assertions.assertEquals(SearchEngine.FilterRecepies(arr, "aa"), test3);
		Assertions.assertEquals(SearchEngine.FilterRecepies(arr, "ab"), test4);
		Assertions.assertEquals(SearchEngine.FilterRecepies(arr, "z"), test5);
		Assertions.assertEquals(SearchEngine.FilterRecepies(arr, "k k"), test6);
	}
}