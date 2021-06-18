import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileLoaderTest {

    @Test
    void loadFiles() {
        FileLoader loader;
        LoadedFiles result;

        result = new LoadedFiles();
        result.success = false;
        result.items = null;
        result.receptures = null;
        assertEquals(result, FileLoader.GetInstance().LoadFiles("C:/"));

        String path;
        try{
            File file = Utils.GetFileFromResourceFile("example.jar");
            path = file.getCanonicalPath();
        } catch (Exception e) {
            path = "";
        }


        result = new LoadedFiles();
        result.success = true;

        Item coal = new Item(0, "minecraft:coal", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\coal.png", null);
        Item charcoal = new Item(0, "minecraft:charcoal", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\charcoal.png", null);
        Item coals = new Item(0, "minecraft:coals", "", new ArrayList<Item>(Arrays.asList(coal, charcoal)));
        Item oakPlank = new Item(0, "minecraft:oak_planks", "..\\resources\\example_data\\assets\\minecraft\\textures\\block\\oak_planks.png", null);
        Item plank = new Item(0, "minecraft:planks", "", new ArrayList<Item>(Arrays.asList(oakPlank)));
        Item stick = new Item(0, "minecraft:stick", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\stick.png", null);
        Item torch = new Item(0, "minecraft:torch", "..\\resources\\example_data\\assets\\minecraft\\textures\\bloc\\torch.png", null);
        result.items = new ArrayList<>(Arrays.asList(coal, charcoal, coals, oakPlank, plank, stick, torch));

        Recepture stickRec = new Recepture(0, "minecraft:crafting_shaped", new ArrayList<>(Arrays.asList(oakPlank, null, null, oakPlank, null, null, null, null, null)),stick, 4);
        Recepture torchRec1 = new Recepture(0, "minecraft:crafting_shaped", new ArrayList<>(Arrays.asList(coal, null, null, stick, null, null, null, null, null)),torch, 4);
        Recepture torchRec2 = new Recepture(0, "minecraft:crafting_shaped", new ArrayList<>(Arrays.asList(charcoal, null, null, stick, null, null, null, null, null)),torch, 4);
        result.receptures = new ArrayList<>(Arrays.asList(stickRec, torchRec1, torchRec2));

        assertEquals(result, FileLoader.GetInstance().LoadFiles(path));
    }
}