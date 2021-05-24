import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

class ReceptureTest{
	Item[] a;
	Recepture[] b;
	@BeforeEach
	public void setUp() {
		a=new Item[18];
		b=new Recepture[10];
		LinkedList<Item> one = new LinkedList<Item>();
		LinkedList<Item> two = new LinkedList<Item>();
		LinkedList<Item> three = new LinkedList<Item>();

		a[0]=new Item(1,"Fishing Rod","C:/Users/Apka/Rod.jpg",null,"lowi sie tym");
		a[1]=new Item(20,"Wooden Sword","C:/Users/Apka/W_Sword.jpg",null,"bije sie tym");
		a[2]=new Item(22,"Iron Sword","C:/Users/Apka/I_Sword.jpg",null,"mocno bije sie tym");
		a[3]=new Item(23,"Diamond Sword","C:/Users/Apka/D_Sword.jpg",null,"mega mocno bije sie tym");

		a[4]=new Item(6237,"Bow","C:/Users/Apka/Bow.jpg",null);
		a[5]=new Item(43,"Stick","C:/Users/Apka/Stick.jpg",null);
		a[6]=new Item(969348,"Ladder","C:/Users/Apka/Planks.jpg",null);
		a[7]=new Item(201,"Minecart","C:/Users/Apka/Minecart.jpg",null);

		a[8]=new Item(326,"Oak Wood","C:/Users/Apka/O_Wood.jpg",null,"Oak dzwewo");
		a[9]=new Item(327,"Spruce Wood","C:/Users/Apka/S_Wood.jpg",null,"Spruce dzwewo");
		a[10]=new Item(328,"Birch Wood","C:/Users/Apka/B_Wood.jpg",null,"Birch dzwewo");
		one.add(a[8]);
		one.add(a[9]);
		one.add(a[10]);
		a[11]=new Item(329,"Wood","C:/Users/Apka/Wood.jpg",one,"Zwykle dzwewo");

		a[12]=new Item(1524,"Oak Button","C:/Users/Apka/O_Button.jpg",null);
		a[13]=new Item(1525,"Spruce Button","C:/Users/Apka/Sp_Button.jpg",null);
		a[14]=new Item(1526,"Birch Button","C:/Users/Apka/B_Button.jpg",null);
		two.add(a[12]);
		two.add(a[13]);
		two.add(a[14]);
		a[15]=new Item(1527,"Wood Button","C:/Users/Apka/W_Button.jpg",two);
		a[16]=new Item(1510,"Stone Button","C:/Users/Apka/St_Button.jpg",null);
		three.add(a[15]);
		three.add(a[16]);
		a[17]=new Item(1530,"Button","C:/Users/Apka/Button.jpg",three);

		b[0]=new Recepture(1,"crafting_table",new Item[] {a[5], a[6], null,   a[5], null, a[6],   a[5], a[6], null},a[4],64);
		b[1]=new Recepture(2,"crafting_table",new Item[] {null, null, null,   null, a[11], null,   null, null, null},a[12],6);
		b[2]=new Recepture(3,"crafting_table",new Item[] {a[11], a[11], a[11],   null, a[5], null,   null, a[5], null},a[1],1);
		b[3]=new Recepture(4,"crafting_table",new Item[] {a[15], a[15], null,   a[15], a[15], null,   null, null, null},a[15],4);
		b[4]=new Recepture(546,"furnance",new Item[] {a[9]},a[10],1);
		b[5]=new Recepture(15,"furnance",new Item[] {a[2], a[5]},a[3],500);
		b[6]=new Recepture(546643442,"brewing_stand",new Item[] {a[12], a[13], a[14]},a[15],5645737);
		b[7]=new Recepture(200,"smithing_table",new Item[] {a[5]},a[0],0);
		b[8]=new Recepture(27,"smithing_table",new Item[] {},a[8],2);
		b[9]=new Recepture(56,"",new Item[] {null, null, null,a[6],a[6],a[6]},a[4],17);
	}

	@Test
	void getID(){
		Assertions.assertEquals(1,b[0].GetID(),"blad w tescie 1");
		Assertions.assertEquals(2,b[1].GetID(),"blad w tescie 2");
		Assertions.assertEquals(3,b[2].GetID(),"blad w tescie 3");
		Assertions.assertEquals(4,b[3].GetID(),"blad w tescie 4");
		Assertions.assertEquals(546,b[4].GetID(),"blad w tescie 5");
		Assertions.assertEquals(15,b[5].GetID(),"blad w tescie 6");
		Assertions.assertEquals(546643442,b[6].GetID(),"blad w tescie 7");
		Assertions.assertEquals(200,b[7].GetID(),"blad w tescie 8");
		Assertions.assertEquals(27,b[8].GetID(),"blad w tescie 9");
		Assertions.assertEquals(56,b[9].GetID(),"blad w tescie 10");
	}
	@Test
	void getMethod(){
		Assertions.assertEquals("crafting_table",b[0].GetMethod(),"blad w tescie 1");
		Assertions.assertEquals("crafting_table",b[1].GetMethod(),"blad w tescie 2");
		Assertions.assertEquals("crafting_table",b[2].GetMethod(),"blad w tescie 3");
		Assertions.assertEquals("crafting_table",b[3].GetMethod(),"blad w tescie 4");
		Assertions.assertEquals("furnance",b[4].GetMethod(),"blad w tescie 5");
		Assertions.assertEquals("furnance",b[5].GetMethod(),"blad w tescie 6");
		Assertions.assertEquals("brewing_stand",b[6].GetMethod(),"blad w tescie 7");
		Assertions.assertEquals("smithing_table",b[7].GetMethod(),"blad w tescie 8");
		Assertions.assertEquals("smithing_table",b[8].GetMethod(),"blad w tescie 9");
		Assertions.assertEquals("",b[9].GetMethod(),"blad w tescie 10");
	}
	@Test
	void getIngredients(){
		Assertions.assertArrayEquals(new Item[]{a[5], a[6], null, a[5], null, a[6], a[5], a[6], null}, b[0].GetIngredients(), "blad w tescie 1");
		Assertions.assertArrayEquals(new Item[]{null, null, null, null, a[11], null, null, null, null}, b[1].GetIngredients(), "blad w tescie 2");
		Assertions.assertArrayEquals(new Item[]{a[11], a[11], a[11], null, a[5], null, null, a[5], null}, b[2].GetIngredients(), "blad w tescie 3");
		Assertions.assertArrayEquals(new Item[]{a[15], a[15], null, a[15], a[15], null, null, null, null}, b[3].GetIngredients(), "blad w tescie 4");
		Assertions.assertArrayEquals(new Item[]{a[9]}, b[4].GetIngredients(), "blad w tescie 5");
		Assertions.assertArrayEquals(new Item[] {a[2], a[5]},b[5].GetIngredients(),"blad w tescie 6");
		Assertions.assertArrayEquals(new Item[] {a[5]},b[7].GetIngredients(),"blad w tescie 8");
		Assertions.assertArrayEquals(new Item[] {},b[8].GetIngredients(),"blad w tescie 9");
		Assertions.assertArrayEquals(new Item[] {null, null, null,a[6],a[6],a[6]},b[9].GetIngredients(),"blad w tescie 10");
	}
	@Test
	void getResult(){
		Assertions.assertEquals(a[4],b[0].GetResult(),"blad w tescie 1");
		Assertions.assertEquals(a[12],b[1].GetResult(),"blad w tescie 2");
		Assertions.assertEquals(a[1],b[2].GetResult(),"blad w tescie 3");
		Assertions.assertEquals(a[15],b[3].GetResult(),"blad w tescie 4");
		Assertions.assertEquals(a[10],b[4].GetResult(),"blad w tescie 5");
		Assertions.assertEquals(a[3],b[5].GetResult(),"blad w tescie 6");
		Assertions.assertEquals(a[15],b[6].GetResult(),"blad w tescie 7");
		Assertions.assertEquals(a[0],b[7].GetResult(),"blad w tescie 8");
		Assertions.assertEquals(a[8],b[8].GetResult(),"blad w tescie 9");
		Assertions.assertEquals(a[4],b[9].GetResult(),"blad w tescie 10");
	}
	@Test
	void getResultQuantity(){
		Assertions.assertEquals(64,b[0].GetResultQuantity(),"blad w tescie 1");
		Assertions.assertEquals(6,b[1].GetResultQuantity(),"blad w tescie 2");
		Assertions.assertEquals(1,b[2].GetResultQuantity(),"blad w tescie 3");
		Assertions.assertEquals(4,b[3].GetResultQuantity(),"blad w tescie 4");
		Assertions.assertEquals(1,b[4].GetResultQuantity(),"blad w tescie 5");
		Assertions.assertEquals(500,b[5].GetResultQuantity(),"blad w tescie 6");
		Assertions.assertEquals(5645737,b[6].GetResultQuantity(),"blad w tescie 7");
		Assertions.assertEquals(0,b[7].GetResultQuantity(),"blad w tescie 8");
		Assertions.assertEquals(2,b[8].GetResultQuantity(),"blad w tescie 9");
		Assertions.assertEquals(17,b[9].GetResultQuantity(),"blad w tescie 10");
	}
}