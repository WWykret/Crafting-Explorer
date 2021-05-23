import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static Item[] items = new Item[]{
            new Item(0, "coal", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\coal.png", null),
            new Item(1, "charcoal", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\charcoal.png", null),
            new Item(2, "stick", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\stick.png", null),
            new Item(3, "torch", "..\\resources\\example_data\\assets\\minecraft\\textures\\bloc\\torch.png", null),
    };
    private static Item[][] craftings = new Item[][]{
            {items[0], null, null, items[2], null, null, null, null, null},
            {items[1], null, null, items[2], null, null, null, null, null}
    };
    private static Recepture[] receptures = new Recepture[]{
            /*new Recepture(0, "crafting", craftings[0], items[3], 4),
            new Recepture(0, "crafting", craftings[1], items[3], 4)*/
    };

    @Test
    void getItems() {

        Main.SetItems(null);
        assertNull(Main.GetItems());

        Main.SetItems(items);
        assertEquals(items, Main.GetItems());
    }

    @Test
    void getReceptures() {
        Main.SetReceptures(null);
        assertNull(Main.GetReceptures());

        Main.SetReceptures(receptures);
        assertEquals(receptures, Main.GetReceptures());
    }
}