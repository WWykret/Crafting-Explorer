import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

class ItemTest {
    Item[] a;

    @BeforeEach
    public void setUp() {
        ArrayList<Item> one = new ArrayList<>();
        ArrayList<Item> two = new ArrayList<>();
        ArrayList<Item> three = new ArrayList<>();
        a = new Item[18];

        a[0] = new Item(1, "Fishing Rod", null, null, "lowi sie tym");
        a[1] = new Item(20, "Wooden Sword", "C:/Users/Apka/W_Sword.jpg", null, "bije sie tym");
        a[2] = new Item(22, "Iron Sword", "C:/Users/Apka/I_Sword.jpg", null, "mocno bije sie tym");
        a[3] = new Item(23, "Diamond Sword", "C:/Users/Apka/D_Sword.jpg", null, "mega mocno bije sie tym");

        a[4] = new Item(6237, "Bow", "C:/Users/Apka/Bow.jpg", null);
        a[5] = new Item(43, "Stick", "C:/Users/Apka/Stick.jpg", null);
        a[6] = new Item(969348, "Ladder", "C:/Users/Apka/Planks.jpg", null);
        a[7] = new Item(201, "Minecart", "C:/Users/Apka/Minecart.jpg", null);

        a[8] = new Item(326, "Oak Wood", "C:/Users/Apka/O_Wood.jpg", null, "Oak dzwewo");
        a[9] = new Item(327, "Spruce Wood", "C:/Users/Apka/S_Wood.jpg", null, "Spruce dzwewo");
        a[10] = new Item(328, "Birch Wood", "C:/Users/Apka/B_Wood.jpg", null, "Birch dzwewo");
        one.add((a[8]));
        one.add((a[9]));
        one.add((a[10]));
        a[11] = new Item(329, "Wood", "C:/Users/Apka/Wood.jpg", one, "Zwykle dzwewo");

        a[12] = new Item(1524, "Oak Button", "C:/Users/Apka/O_Button.jpg", null);
        a[13] = new Item(1525, "Spruce Button", "C:/Users/Apka/Sp_Button.jpg", null);
        a[14] = new Item(1526, "Birch Button", "C:/Users/Apka/B_Button.jpg", null);
        two.add(a[12]);
        two.add(a[13]);
        two.add(a[14]);
        a[15] = new Item(1527, "Wood Button", "C:/Users/Apka/W_Button.jpg", two);
        a[16] = new Item(1510, "Stone Button", "C:/Users/Apka/St_Button.jpg", null);
        three.add(a[15]);
        three.add(a[16]);
        a[17] = new Item(1530, "Button", "C:/Users/Apka/Button.jpg", three);
    }

    @Test
    void getID() {
        Assertions.assertEquals(1, a[0].GetID(), "blad w tescie 1");
        Assertions.assertEquals(20, a[1].GetID(), "blad w tescie 2");
        Assertions.assertEquals(22, a[2].GetID(), "blad w tescie 3");
        Assertions.assertEquals(23, a[3].GetID(), "blad w tescie 4");

        Assertions.assertEquals(6237, a[4].GetID(), "blad w tescie 5");
        Assertions.assertEquals(43, a[5].GetID(), "blad w tescie 6");
        Assertions.assertEquals(969348, a[6].GetID(), "blad w tescie 7");
        Assertions.assertEquals(201, a[7].GetID(), "blad w tescie 8");

        Assertions.assertEquals(326, a[8].GetID(), "blad w tescie 9");
        Assertions.assertEquals(327, a[9].GetID(), "blad w tescie 10");
        Assertions.assertEquals(328, a[10].GetID(), "blad w tescie 11");
        Assertions.assertEquals(329, a[11].GetID(), "blad w tescie 12");

        Assertions.assertEquals(1524, a[12].GetID(), "blad w tescie 13");
        Assertions.assertEquals(1525, a[13].GetID(), "blad w tescie 14");
        Assertions.assertEquals(1526, a[14].GetID(), "blad w tescie 15");
        Assertions.assertEquals(1527, a[15].GetID(), "blad w tescie 16");
        Assertions.assertEquals(1510, a[16].GetID(), "blad w tescie 17");
        Assertions.assertEquals(1530, a[17].GetID(), "blad w tescie 18");
    }

    @Test
    void getDescription() {
        Assertions.assertEquals("lowi sie tym", a[0].GetDescription(), "blad w tescie 1");
        Assertions.assertEquals("bije sie tym", a[1].GetDescription(), "blad w tescie 2");
        Assertions.assertEquals("mocno bije sie tym", a[2].GetDescription(), "blad w tescie 3");
        Assertions.assertEquals("mega mocno bije sie tym", a[3].GetDescription(), "blad w tescie 4");

        Assertions.assertEquals("", a[4].GetDescription(), "blad w tescie 5");
        Assertions.assertEquals("", a[5].GetDescription(), "blad w tescie 6");
        Assertions.assertEquals("", a[6].GetDescription(), "blad w tescie 7");
        Assertions.assertEquals("", a[7].GetDescription(), "blad w tescie 8");

        Assertions.assertEquals("Oak dzwewo", a[8].GetDescription(), "blad w tescie 9");
        Assertions.assertEquals("Spruce dzwewo", a[9].GetDescription(), "blad w tescie 10");
        Assertions.assertEquals("Birch dzwewo", a[10].GetDescription(), "blad w tescie 11");
        Assertions.assertEquals("Zwykle dzwewo", a[11].GetDescription(), "blad w tescie 12");

        Assertions.assertEquals("", a[12].GetDescription(), "blad w tescie 13");
        Assertions.assertEquals("", a[13].GetDescription(), "blad w tescie 14");
        Assertions.assertEquals("", a[14].GetDescription(), "blad w tescie 15");
        Assertions.assertEquals("", a[15].GetDescription(), "blad w tescie 16");
        Assertions.assertEquals("", a[16].GetDescription(), "blad w tescie 17");
        Assertions.assertEquals("", a[17].GetDescription(), "blad w tescie 18");
    }

    @Test
    void setDescription() {
        a[0].SetDescription("abc");
        a[1].SetDescription("b");
        a[2].SetDescription("");
        a[3].SetDescription("vud9vh8h3bh8b9dnpf9bnr800mvd0mvisdbf fd sngoer_rgc33gfss");

        a[4].SetDescription("ccc");
        a[5].SetDescription("d");
        a[6].SetDescription("");
        a[7].SetDescription("gsnig6392 fuofds9vo9sgusd__42 fsfsd6uu87oilkj;");

        Assertions.assertEquals("abc", a[0].GetDescription(), "blad w tescie 1");
        Assertions.assertEquals("b", a[1].GetDescription(), "blad w tescie 2");
        Assertions.assertEquals("", a[2].GetDescription(), "blad w tescie 3");
        Assertions.assertEquals("vud9vh8h3bh8b9dnpf9bnr800mvd0mvisdbf fd sngoer_rgc33gfss", a[3].GetDescription(), "blad w tescie 4");

        Assertions.assertEquals("ccc", a[4].GetDescription(), "blad w tescie 5");
        Assertions.assertEquals("d", a[5].GetDescription(), "blad w tescie 6");
        Assertions.assertEquals("", a[6].GetDescription(), "blad w tescie 7");
        Assertions.assertEquals("gsnig6392 fuofds9vo9sgusd__42 fsfsd6uu87oilkj;", a[7].GetDescription(), "blad w tescie 8");

        a[0].SetDescription("cde");
        a[4].SetDescription("ttt");

        Assertions.assertEquals("cde", a[0].GetDescription(), "blad w tescie 9");
        Assertions.assertEquals("ttt", a[4].GetDescription(), "blad w tescie 10");

        a[0].SetDescription("");
        a[4].SetDescription("");

        Assertions.assertEquals("", a[0].GetDescription(), "blad w tescie 9");
        Assertions.assertEquals("", a[4].GetDescription(), "blad w tescie 10");
    }

    @Test
    void getName() {
        Assertions.assertEquals("Fishing Rod", a[0].GetRawName(), "blad w tescie 1");
        Assertions.assertEquals("Wooden Sword", a[1].GetRawName(), "blad w tescie 2");
        Assertions.assertEquals("Iron Sword", a[2].GetRawName(), "blad w tescie 3");
        Assertions.assertEquals("Diamond Sword", a[3].GetRawName(), "blad w tescie 4");

        Assertions.assertEquals("Bow", a[4].GetRawName(), "blad w tescie 5");
        Assertions.assertEquals("Stick", a[5].GetRawName(), "blad w tescie 6");
        Assertions.assertEquals("Ladder", a[6].GetRawName(), "blad w tescie 7");
        Assertions.assertEquals("Minecart", a[7].GetRawName(), "blad w tescie 8");

        Assertions.assertEquals("Oak Wood", a[8].GetRawName(), "blad w tescie 9");
        Assertions.assertEquals("Spruce Wood", a[9].GetRawName(), "blad w tescie 10");
        Assertions.assertEquals("Birch Wood", a[10].GetRawName(), "blad w tescie 11");
        Assertions.assertEquals("Wood", a[11].GetRawName(), "blad w tescie 12");

        Assertions.assertEquals("Oak Button", a[12].GetRawName(), "blad w tescie 13");
        Assertions.assertEquals("Spruce Button", a[13].GetRawName(), "blad w tescie 14");
        Assertions.assertEquals("Birch Button", a[14].GetRawName(), "blad w tescie 15");
        Assertions.assertEquals("Wood Button", a[15].GetRawName(), "blad w tescie 16");
        Assertions.assertEquals("Stone Button", a[16].GetRawName(), "blad w tescie 17");
        Assertions.assertEquals("Button", a[17].GetRawName(), "blad w tescie 18");
    }

    @Test
    void getGraphics() {
        try {
            BufferedImage[] images = {
                    null,
                    ImageIO.read(new File("C:/Users/Apka/W_Sword.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/I_Sword.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/D_Sword.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/Bow.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/Stick.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/Planks.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/Minecart.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/O_Wood.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/S_Wood.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/B_Wood.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/Wood.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/O_Button.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/Sp_Button.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/B_Button.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/W_Button.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/St_Button.jpg")),
                    ImageIO.read(new File("C:/Users/Apka/Button.jpg"))
            };

            Assertions.assertNull(a[0].GetGraphics(),"blad w tescie " + 1);
            for (int i = 1; i < 18; i++) {
                Assertions.assertTrue(compareImages(images[i], a[i].GetGraphics()), "blad w tescie " + (i+1));
            }
        } catch (Exception e) {
        }
    }

    @Test
    void getTypes() {
        ArrayList<Item> one = new ArrayList<>();
        ArrayList<Item> two = new ArrayList<>();
        ArrayList<Item> three = new ArrayList<>();

		Assertions.assertNull(a[0].GetTypes(), "blad w tescie 1");
		Assertions.assertNull(a[1].GetTypes(), "blad w tescie 2");
		Assertions.assertNull(a[2].GetTypes(), "blad w tescie 3");
		Assertions.assertNull(a[3].GetTypes(), "blad w tescie 4");

		Assertions.assertNull(a[4].GetTypes(), "blad w tescie 5");
		Assertions.assertNull(a[5].GetTypes(), "blad w tescie 6");
		Assertions.assertNull(a[6].GetTypes(), "blad w tescie 7");
		Assertions.assertNull(a[7].GetTypes(), "blad w tescie 8");

		Assertions.assertNull(a[8].GetTypes(), "blad w tescie 9");
		Assertions.assertNull(a[9].GetTypes(), "blad w tescie 10");
		Assertions.assertNull(a[10].GetTypes(), "blad w tescie 11");
		one.add(a[8]);
		one.add(a[9]);
		one.add(a[10]);
        Assertions.assertEquals(one, a[11].GetTypes(), "blad w tescie 12");

		Assertions.assertNull(a[12].GetTypes(), "blad w tescie 13");
		Assertions.assertNull(a[13].GetTypes(), "blad w tescie 14");
		Assertions.assertNull(a[14].GetTypes(), "blad w tescie 15");
		two.add(a[12]);
		two.add(a[13]);
		two.add(a[14]);
        Assertions.assertEquals(two, a[15].GetTypes(), "blad w tescie 16");
		Assertions.assertNull(a[16].GetTypes(), "blad w tescie 17");
		three.add(a[15]);
		three.add(a[16]);
        Assertions.assertEquals(three, a[17].GetTypes(), "blad w tescie 18");
    }

	public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
		// The images must be the same size.
		if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
			return false;
		}

		int width  = imgA.getWidth();
		int height = imgA.getHeight();

		// Loop over every pixel.
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Compare the pixels for equality.
				if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
					return false;
				}
			}
		}

		return true;
	}
}