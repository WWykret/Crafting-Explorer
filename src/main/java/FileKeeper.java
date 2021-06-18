import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class FileKeeper {
    private static FileKeeper fileKeeperInstance = null;

    public static FileKeeper GetInstance() {
        if (fileKeeperInstance == null) fileKeeperInstance = new FileKeeper();
        return fileKeeperInstance;
    }

    public void SaveItemsAndRecipesAsXML(LinkedHashSet<Item> items, LinkedHashSet<Recepture> recipes) {
        ArrayList<XMLItem> xmlItems = new ArrayList<>();;
        ArrayList<XMLRecipe> xmlRecipes = new ArrayList<>();
        try {
            for (Item item: items) xmlItems.add(new XMLItem(item));
            SaveItemsAsXML(xmlItems);

            for (Recepture recipe: recipes) xmlRecipes.add(new XMLRecipe(recipe));
            SaveRecipesAsXML(xmlRecipes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SaveRecipesAsXML(ArrayList<XMLRecipe> recipes) throws Exception {
        File file = new File("./savedObjects/Recipes");
        String dirPath = file.getCanonicalPath();
        if (!file.exists()) file.mkdirs();
        Utils.ClearDir(Paths.get(dirPath));

        for (XMLRecipe recipe : recipes) {
            RecipeToXML(recipe, dirPath);
        }
    }

    private void SaveItemsAsXML(ArrayList<XMLItem> items) throws javax.xml.bind.JAXBException, Exception {
        File file = new File("./savedObjects/Items");
        String dirPath = file.getCanonicalPath();
        if (!file.exists()) file.mkdirs();
        Utils.ClearDir(Paths.get(dirPath));

        for (XMLItem item : items) {
            ItemToXML(item, dirPath);
        }
    }

    private void ItemToXML(XMLItem item, String path) throws javax.xml.bind.JAXBException, IOException {
        JAXBContext contextObj = JAXBContext.newInstance(XMLItem.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        String savePath = path + "\\" + item.getFileName() + ".xml";
        FileOutputStream outStream = new FileOutputStream(savePath);
        marshallerObj.marshal(item, outStream);
        outStream.close();
    }

    private void RecipeToXML(XMLRecipe xmlRecipe, String path) throws javax.xml.bind.JAXBException, IOException {
        JAXBContext contextObj = JAXBContext.newInstance(XMLRecipe.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        String basePath = path + "\\" + xmlRecipe.getFileName();
        String savePath;

        int orderNumber = 0;
        File recipeFile;
        do {
            savePath = basePath + orderNumber + ".xml";
            orderNumber++;
            recipeFile = new File(savePath);
        } while(recipeFile.exists());


        FileOutputStream outStream = new FileOutputStream(savePath);
        marshallerObj.marshal(xmlRecipe, outStream);
        outStream.close();
    }

    public ArrayList<Item> ReadItemsFromXML() {
        ArrayList<Item> allItems = new ArrayList<>();
        ArrayList<XMLItem> allXmlItems = new ArrayList<>();
        try {
            File dirFile = new File("./savedObjects/Items");
            if (!dirFile.exists()) return allItems;

            File[] itemFiles = dirFile.listFiles();
            if (itemFiles == null) return allItems;

            for (File itemFile : itemFiles) {
                if (!itemFile.getCanonicalPath().matches(".*\\.xml")) continue;
                ReadItemFromXML(itemFile, allXmlItems, allItems);
            }

            //dodawanie podtypow
            for (int i = 0; i < allItems.size(); i++) {
                XMLItem currentXmlItem = allXmlItems.get(i);
                if (currentXmlItem.getTypes() == null) continue;
                ArrayList<String> typesNames = currentXmlItem.getTypes();
                ArrayList<Item> types = new ArrayList<>();
                for (String name: typesNames) {
                    types.add(GetItemWithName(name, allItems));
                }
                allItems.get(i).AddTypes(types);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allItems;
    }

    public ArrayList<Recepture> ReadRecipesFromXML(ArrayList<Item> allItems) {
        ArrayList<Recepture> allRecipes = new ArrayList<>();
        try {
            File dirFile = new File("./savedObjects/Recipes");
            if (!dirFile.exists()) return allRecipes;

            File[] itemFiles = dirFile.listFiles();
            if (itemFiles == null) return allRecipes;

            for (File recipeFile : itemFiles) {
                if (!recipeFile.getCanonicalPath().matches(".*\\.xml")) continue;
                ReadRecipeFromXML(recipeFile, allRecipes, allItems);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRecipes;
    }

    private void ReadItemFromXML(File itemFile, ArrayList<XMLItem> xmlItems, ArrayList<Item> allItems) throws javax.xml.bind.JAXBException {
        JAXBContext contextObj = JAXBContext.newInstance(XMLItem.class);

        Unmarshaller jaxbUnmarshaller = contextObj.createUnmarshaller();
        XMLItem xmlItem = (XMLItem) jaxbUnmarshaller.unmarshal(itemFile);

        xmlItems.add(xmlItem);
        allItems.add(new Item(xmlItem.getID(), xmlItem.getItemName(), xmlItem.getGraphics(), null, xmlItem.getDescription()));
    }

    private void ReadRecipeFromXML(File recipeFile, ArrayList<Recepture> recipes, ArrayList<Item> allItems) throws javax.xml.bind.JAXBException {
        JAXBContext contextObj = JAXBContext.newInstance(XMLRecipe.class);

        Unmarshaller jaxbUnmarshaller = contextObj.createUnmarshaller();
        XMLRecipe xmlRecipe = (XMLRecipe) jaxbUnmarshaller.unmarshal(recipeFile);

        ArrayList<Item> ingredients = new ArrayList<>();
        for (String ingredientName: xmlRecipe.getIngredients()) {
            ingredients.add(GetItemWithName(ingredientName, allItems));
        }
        Item result = GetItemWithName(xmlRecipe.getResult(), allItems);

        recipes.add(new Recepture(xmlRecipe.getID(), xmlRecipe.getMethod(), ingredients, result, xmlRecipe.getResultQuantity()));
    }

    private Item GetItemWithName(String itemName, ArrayList<Item> allItems) {
        //wybieranie ze zbioru elementu o podanej nazwie (lambda!)
        return allItems.stream().filter(item -> itemName.equals(item.GetRawName())).findFirst().orElse(null);
    }
}