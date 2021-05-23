import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileLoaderTest {
    @Test
    void getPath() {
        FileLoader loader;
        loader = new FileLoader("C:/Users/Admin/");
        assertEquals("C:/Users/Admin/", loader.GetPath());

        loader = new FileLoader("C:\\Users\\Admin\\AppData\\Roaming\\.minecraft");
        assertEquals("C:\\Users\\Admin\\AppData\\Roaming\\.minecraft", loader.GetPath());

        loader = new FileLoader("");
        assertEquals("", loader.GetPath());
    }

    @Test
    void changePath() {
        FileLoader loader;
        loader = new FileLoader("C:/Users/Admin/");
        loader.ChangePath("C:/Users/Admin/");
        Assertions.assertEquals("C:/Users/Admin/", loader.GetPath());

        loader = new FileLoader("C:/Users/Admin/");
        loader.ChangePath("C:/Users/Admin/minecraft/");
        Assertions.assertEquals("C:/Users/Admin/minecraft/", loader.GetPath());

        loader = new FileLoader("C:/Users/Admin/");
        loader.ChangePath("C:\\Users\\Admin\\AppData\\Roaming\\.minecraft");
        Assertions.assertEquals("C:\\Users\\Admin\\AppData\\Roaming\\.minecraft", loader.GetPath());

        loader = new FileLoader("C:/Users/Admin/");
        loader.ChangePath("");
        Assertions.assertEquals("", loader.GetPath());
    }

    @Test
    void loadFiles() {
        FileLoader loader;
        LoadedFiles result;

        loader = new FileLoader("C:/");
        result = new LoadedFiles();
        result.success = false;
        result.items = null;
        result.receptures = null;
        assertEquals(result, loader.LoadFiles());

        loader = new FileLoader("");
        result = new LoadedFiles();
        result.success = true;
        Item[] items = new Item[]{
                new Item(0, "coal", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\coal.png", null),
                new Item(1, "charcoal", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\charcoal.png", null),
                new Item(2, "stick", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\stick.png", null),
                new Item(3, "torch", "..\\resources\\example_data\\assets\\minecraft\\textures\\bloc\\torch.png", null),
        };
        result.items = items;
        Item[][] receptures = new Item[][]{
                {items[0], null, null, items[2], null, null, null, null, null},
                {items[1], null, null, items[2], null, null, null, null, null}
        };
        result.receptures = new Recepture[]{
                /*new Recepture(0, "crafting_table", receptures[0], items[3], 4),
                new Recepture(1, "crafting_table", receptures[1], items[3], 4)*/
        };
        assertEquals(result, loader.LoadFiles());
    }
}