import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*
Single responsibility principle - jedyne zadanie jakie ma FileLoader to wczytanie plików z pliku .jar minecrafta do aplikacji
Open/closed principle -
Liskov substitution principle - Nic nie dziedziczy z tej klasy, a korzystanie z klasy nie wymaga jej znajomości
Interface segregation principle - Jedyne interfejsy z jakich korzystamy to te we wbudowanych klasach Javy (np. List)
Dependency inversion principle -
*/
public class FileLoader {
    private static FileLoader fileLoaderInstance = null; //instancja singletonu :)
    private JSONParser jsonParser;

    public FileLoader() {
        jsonParser = new JSONParser();
    }

    public static FileLoader GetInstance() {
        if (fileLoaderInstance == null) {
            fileLoaderInstance = new FileLoader();
        }
        return fileLoaderInstance;
    }

    private Path CopyFiles(String path) {
        Path sourcePath = Paths.get(path); //ścieżka do oryginału
        Path destPath; //ścieżka do kopii
        try {
            File destDir = new File("./files");
            if (!destDir.exists()) destDir.mkdir();

            destPath = Paths.get(destDir.getCanonicalPath() + "/minecraft.jar");

            Utils.ClearDir(destPath.getParent()); //kopiowanie nie nadpisuje plików, więc trzeba usunąć poprzednie

            Files.copy(sourcePath, destPath);
        } catch (Exception e) {
            destPath = null;
            System.out.println(e.getClass() + " --- " + e.getMessage());
        }
        return destPath;
    }

    private void UnpackJar(Path jarPath) throws IOException {
        java.util.jar.JarFile jarFile = new java.util.jar.JarFile(jarPath.toString());
        java.util.Enumeration<JarEntry> enumEntries = jarFile.entries();

        while (enumEntries.hasMoreElements()) { //iterowanie po elementach z pliku .jar
            JarEntry entry = enumEntries.nextElement();

            if (!IsCorrectFile(entry.getName())) continue; //ignorowanie plików nieistotnych z pkt. widzenia aplikacji

            File file = new File(jarPath.getParent().toString() + File.separator + entry.getName());
            file.getParentFile().mkdirs(); //tworzenie ew. nieistniejących wymaganych folderów
            if (entry.isDirectory()) continue; //puste foldery ignorowane

            MoveFileOutOfJar(jarFile, entry, file);
        }
        jarFile.close();
    }

    private void MoveFileOutOfJar(java.util.jar.JarFile jarFile, JarEntry jarEntry, File targetFile) throws IOException {
        InputStream inputStream = jarFile.getInputStream(jarEntry);
        FileOutputStream fileOutStream = new FileOutputStream(targetFile);

        //przepisywanie z jara poza niego
        while (inputStream.available() > 0) {
            fileOutStream.write(inputStream.read());
        }

        fileOutStream.close();
        inputStream.close();
    }

    private boolean IsCorrectFile(String filename) {
        String[] dirs = new String[]{
            "(.*/)?data/minecraft/tags/",
            "(.*/)?data/minecraft/recipes/",
            "(.*/)?data/minecraft/loot_tables/blocks/",
            "(.*/)?assets/minecraft/textures/"
        }; //regexy dozwolonych ścieżek :)

        for (String dir : dirs) {
            if (Pattern.matches(dir + ".*", filename)) return true;
        }
        return false;
    }

    private String GetItemNameFromFilePath(String path) {
        String[] splitPath = path.split("/");
        String[] splitFilename = splitPath[splitPath.length - 1].split("\\.");
        return "minecraft:" + splitFilename[0];
    }

    private Pair<LinkedHashSet<Item>, LinkedHashSet<Recepture>> ReadItemsAndRecipesFromFiles(Path dir) throws IOException, org.json.simple.parser.ParseException {
        ArrayList<File> files = GetAllFilesFromDir(dir.toString());

        System.out.println("szukanie ścieżek...");
        //rodzdzielanie ścieżek do plików
        ArrayList<String> tagsPath = new ArrayList<>();
        ArrayList<String> recipePath = new ArrayList<>();
        ArrayList<String> dropsPath = new ArrayList<>();
        ArrayList<String> texturePath = new ArrayList<>();
        for (File f : files) {
            String filePath = f.toString().replace("\\", "/"); //jak są \\ zamiast / to się regex psuje
            if (Pattern.matches(".*/tags/.*", filePath)) tagsPath.add(filePath);
            else if (Pattern.matches(".*/recipes/.*", filePath)) recipePath.add(filePath);
            else if (Pattern.matches(".*/loot_tables/.*", filePath)) dropsPath.add(filePath);
            else if (Pattern.matches(".*/textures/.*", filePath)) texturePath.add(filePath);
        }

        System.out.println("tworzenie przedmiotów...");
        //tagi z plikow z katalogu .../tags/...
        LinkedHashSet<Item> allItems = GetItemsFromTagFiles(tagsPath, texturePath);

        HashSet<Item> itemsFromRecipes = GetItemsFromRecipeFiles(recipePath, texturePath);
        HashSet<Item> itemsFromLoot = GetItemsFromRecipeFiles(dropsPath, texturePath);

        for (Item item: itemsFromRecipes) {
            if (GetItemFromHashSet(item.GetRawName(), allItems) == null) allItems.add(item);
        }

        for (Item item: itemsFromLoot) {
            if (GetItemFromHashSet(item.GetRawName(), allItems) == null) allItems.add(item);
        }

        System.out.println("szukanie receptur...");
        //receptury z plikow z katalogu .../recipes/...
        LinkedHashSet<Recepture> allRecipes = GetRecipesFromFiles(recipePath, allItems);

        System.out.println("wczytywanie gotowe!");
        return new Pair<>(allItems, allRecipes);
    }

    private LinkedHashSet<Item> GetItemsFromTagFiles(ArrayList<String> tagPaths, ArrayList<String> texturePaths) throws IOException, org.json.simple.parser.ParseException {
        LinkedHashSet<Item> items = new LinkedHashSet<>();
        ArrayList<Item> subItems = new ArrayList<>();
        HashMap<String, ArrayList<String>> queuedItemsToAdd = new HashMap<>(); //przedmioty, do których trzeba dodać podprzedmioty i jakie podprzedmioty dodać

        for (String path : tagPaths) {
            FileReader fileReader = new FileReader(path);
            JSONObject obj = (JSONObject) jsonParser.parse(fileReader);
            fileReader.close();
            JSONArray tagValues = (JSONArray) obj.get("values"); //wszystkie tagi w danym pliku

            subItems.clear();

            String itemTag = GetItemNameFromFilePath(path);
            String superItemGraphicsPath = "";

            for (Object subTagObj : tagValues) { //iterowanie po wszystkich tagach zawartych w pliku
                String subTag = (String) subTagObj;
                if (subTag.charAt(0) != '#') { //przedmioty, które nie mają podprzedmiotów
                    String subItemGraphicsPath = GetTextureForTag(subTag, texturePaths);
                    Item subItem = new Item(0, subTag, subItemGraphicsPath, null);
                    if (superItemGraphicsPath.equals(""))
                        superItemGraphicsPath = subItemGraphicsPath; //nadprzedmiot ma teksturę pierwszego podprzedmiotu

                    //dodaje do listy typów przedmiotów i do zwykłych przedmiotów
                    subItems.add(subItem);
                    items.add(subItem);
                } else { //tagi zaczynajace sie od # (przedmioty, które mają podprzemioty)
                    AddItemToItemsAddedLaterAsSubItems(itemTag, subTag, queuedItemsToAdd);
                }
            }

            items.add(new Item(0, itemTag, superItemGraphicsPath, subItems)); //przedmiot, którego podprzedmioty zostały już dodane
        }

        //dodawanie zakolejkowanych podtypow
        AddQueuedItemSubTypes(items, queuedItemsToAdd);
        return items;
    }

    private void AddItemToItemsAddedLaterAsSubItems(String parentTag, String childTag, HashMap<String, ArrayList<String>> queuedItemsToAdd) {
        ArrayList<String> itemsToAddList;
        String childTagName = childTag.substring(1);

        //przypadek kiedy dany przemiot nie ma jeszcze poddypów do dodania
        if (!queuedItemsToAdd.containsKey(parentTag)) {
            itemsToAddList = new ArrayList<>();
            itemsToAddList.add(childTagName);
            queuedItemsToAdd.put(parentTag, itemsToAddList);
        } else { //przypadek kiedy jakiś podtyp przedmiotu jest już w kolejce do dodania
            itemsToAddList = queuedItemsToAdd.get(parentTag);
            itemsToAddList.add(childTagName);
        }
    }

    private void AddQueuedItemSubTypes(LinkedHashSet<Item> itemSet, HashMap<String, ArrayList<String>> queuedItemsToAdd) {
        for (Map.Entry<String, ArrayList<String>> item : queuedItemsToAdd.entrySet()) { //dla każdego zakolejkowanego przedmiotu
            ArrayList<Item> childItems = new ArrayList<>();
            for (String itemName : item.getValue()) {
                childItems.add(GetItemFromHashSet(itemName, itemSet));
            }
            Item parentItem = GetItemFromHashSet(item.getKey(), itemSet);
            parentItem.AddTypes(childItems);
        }
    }

    private LinkedHashSet<Item> GetItemsFromRecipeFiles(ArrayList<String> recipePaths, ArrayList<String> texturePaths) {
        LinkedHashSet<Item> items = new LinkedHashSet<>();

        //plik receptury zawsze jako wynik daje to, jak jest nazwany (np. 'torch.json' daje recepturę na 'torch')
        for (String path : recipePaths) {
            String itemName = GetItemNameFromFilePath(path);
            items.add(new Item(0, itemName, GetTextureForTag(itemName, texturePaths), null));
        }

        return items;
    }

    private LinkedHashSet<Recepture> GetRecipesFromFiles(ArrayList<String> recipePaths, LinkedHashSet<Item> allItems) throws IOException, org.json.simple.parser.ParseException {
        LinkedHashSet<Recepture> recipes = new LinkedHashSet<>();

        for (String path : recipePaths) {
            FileReader fileReader = new FileReader(path);
            JSONObject obj = (JSONObject) jsonParser.parse(fileReader);
            fileReader.close();
            String craftingType = (String) obj.get("type"); //w jaki sposób jest wytwarzany przedmiot

            if (craftingType.equals("minecraft:crafting_shaped")) {
                CreateReceptureCraftingType(obj, recipes, craftingType, allItems);
            } else if (craftingType.equals("minecraft:smelting")) {
                //(zakładam że surowiec to zawsze obiekt)
                CreateReceptureSmeltingType(obj, recipes, craftingType, allItems);
            }
        }

        return recipes;
    }

    private void CreateReceptureCraftingType(JSONObject jsonRecipeObj, LinkedHashSet<Recepture> recipes, String craftingMethod, LinkedHashSet<Item> allItems) {
        JSONArray craftingPattern = (JSONArray) jsonRecipeObj.get("pattern");
        String patternString = ConvertCraftingGridToString(craftingPattern);

        ArrayList<ArrayList<Item>> recipesList = new ArrayList<>(); //każdy przedmiot może mieć wiele receptur
        recipesList.add(new ArrayList<>());

        for (int i = 0; i < patternString.length(); i++) {
            String ingredient = "" + patternString.charAt(i); //konwersja znaku na napis
            JSONObject ingredientsDict = (JSONObject) jsonRecipeObj.get("key");

            //wczytywanie receptur z konkretnego pliku
            try {
                AddSingleItemToRecipe(ingredientsDict, ingredient, recipesList, allItems); //rzuca ClassCastException kiedy jest więcej niż jedna opcja przedmiotu
            } catch (ClassCastException e) { //kiedy dane pole ma więcej niż jedną jedną opcję przedmiotu (np. węgiel, węgiel drzewny)
                recipesList = AddMultipleItemsToRecipe(ingredientsDict, ingredient, recipesList, allItems);
            }
        }
        String resultItemName = (String) ((JSONObject) jsonRecipeObj.get("result")).get("item");
        Item resultItem = GetItemFromHashSet(resultItemName, allItems);

        long itemQuantity;
        try {
            itemQuantity = (long) ((JSONObject) jsonRecipeObj.get("result")).get("count"); //receptura może nie mieć tego pola dla wartości 1
        } catch (NullPointerException e) {
            itemQuantity = 1;
        }

        for (ArrayList<Item> recipe : recipesList) {
            recipes.add(new Recepture(0, craftingMethod, recipe, resultItem, (int) itemQuantity));
        }
    }

    private void AddSingleItemToRecipe(JSONObject ingredientsDict, String ingredientSymbol, ArrayList<ArrayList<Item>> recipesList, LinkedHashSet<Item> allItems) throws ClassCastException {
        JSONObject itemEntry = (JSONObject) ingredientsDict.get(ingredientSymbol); //kiedy dane pole ma tylko jedną opcję przedmiotu
        if (itemEntry == null) {
            for (ArrayList<Item> recipe : recipesList) recipe.add(null);
            return;
        }

        Item craftingItem = GetItemFromJSON(itemEntry, allItems);

        for (ArrayList<Item> recipe : recipesList) recipe.add(craftingItem);
    }

    private ArrayList<ArrayList<Item>> AddMultipleItemsToRecipe(JSONObject ingredientsDict, String ingredientSymbol, ArrayList<ArrayList<Item>> recipesList, LinkedHashSet<Item> allItems) {
        JSONArray dictList = (JSONArray) ingredientsDict.get(ingredientSymbol);
        ArrayList<ArrayList<Item>> tempRecipes = new ArrayList<>();
        for (Object dictObj : dictList) { //dla każdego wariantu przedmiotu
            JSONObject itemDict = (JSONObject) dictObj;

            Item itemVariant = GetItemFromJSON(itemDict, allItems);

            ArrayList<ArrayList<Item>> recipesWithItem = new ArrayList<>();
            for (ArrayList<Item> recipe : recipesList) {
                ArrayList<Item> recipeWithItem = new ArrayList<>(recipe);
                recipeWithItem.add(itemVariant);
                recipesWithItem.add(recipeWithItem);
            }
            tempRecipes.addAll(recipesWithItem);
        }
        return new ArrayList<>(tempRecipes);
    }

    private String ConvertCraftingGridToString(JSONArray craftingPattern) {
        StringBuilder patternString = new StringBuilder(); //do zmiany ze wzoru 3d na listę 2d
        for (Object lineObj : craftingPattern) {
            StringBuilder line = new StringBuilder((String) lineObj);
            while (line.length() < 3) line.append(" "); //uzupełnia każdą linię do 3
            patternString.append(line);
        }
        //dopełnienie do siatki 3x3
        while (patternString.length() < 9) patternString.append(" ");
        return patternString.toString();
    }

    private void CreateReceptureSmeltingType(JSONObject jsonRecipeObj, LinkedHashSet<Recepture> recipes, String craftingMethod, LinkedHashSet<Item> allItems) {
        try {
            JSONObject ingredientObj = (JSONObject) jsonRecipeObj.get("ingredient"); //co jest przepalane

            Item ingredientItem = GetItemFromJSON(ingredientObj, allItems);
            ArrayList<Item> smeltItem = new ArrayList<>();
            smeltItem.add(ingredientItem);
            String resultItemName = (String) jsonRecipeObj.get("result");
            Item resultItem = GetItemFromHashSet(resultItemName, allItems);
            recipes.add(new Recepture(0, craftingMethod, smeltItem, resultItem, 1));
        } catch (Exception e) {
            System.err.println(e.getMessage() + " --- " + e.getMessage());
        }
    }

    private Item GetItemFromJSON(JSONObject jsonRecipeObj, LinkedHashSet<Item> allItems) {
        String itemName;
        //składniki są opisane jako 'item' lub 'tag'
        if (jsonRecipeObj.get("tag") != null) itemName = (String) jsonRecipeObj.get("tag");
        else itemName = (String) jsonRecipeObj.get("item");
        return GetItemFromHashSet(itemName, allItems);
    }

    private Item GetItemFromHashSet(String itemName, LinkedHashSet<Item> allItems) {
        //wybieranie ze zbioru elementu o podanej nazwie (lambda!)
        return allItems.stream().filter(item -> itemName.equals(item.GetRawName())).findFirst().orElse(null);
    }

    private String GetTextureForTag(String tag, ArrayList<String> texturePaths) {
        String[] splitTag = tag.split(":");
        String itemName = splitTag[splitTag.length - 1];

        Pattern pattern = Pattern.compile(".*/" + itemName + ".*\\.png");

        List<String> candidates = texturePaths.stream().filter(pattern.asPredicate()).collect(Collectors.toList());

        if (candidates.size() > 0) return candidates.get(0);
        else return "";
    }

    private ArrayList<File> GetAllFilesFromDir(String dir) {
        ArrayList<File> files = new ArrayList<>();

        File[] filesInDir = new File(dir).listFiles(); //zwraca null dla pliku

        if (filesInDir == null) return files;
        for (File file : filesInDir) {
            if (file.isFile()) files.add(file); //dodawanie plików
            else files.addAll(GetAllFilesFromDir(file.toString())); //rekurencyjnde dodawanie z podkatalogów
        }
        return files;
    }

    public LoadedFiles LoadFiles(String path) {
        //domyślny wynik działania funckji
        LoadedFiles result = new LoadedFiles();
        result.success = false;

        Path jarPath = CopyFiles(path); //ścieżka do pliku .jar

        if (jarPath == null) return result;
        try {
            UnpackJar(jarPath);
            Pair<LinkedHashSet<Item>, LinkedHashSet<Recepture>> itemsAndRecipes = ReadItemsAndRecipesFromFiles(jarPath.getParent());

            result.items = new ArrayList<>(itemsAndRecipes.getKey());
            result.receptures = new ArrayList<>(itemsAndRecipes.getValue());
            result.success = true;

            FileKeeper.GetInstance().SaveItemsAndRecipesAsXML(itemsAndRecipes.getKey(), itemsAndRecipes.getValue());
        } catch (Exception e) {
            System.out.println(e.getClass() + " --- " + e.getMessage());
        }
        return result;
    }
}


/*
Single responsibility principle - jedyne zadanie tej klasy to przechowywanie wyniku działania FileLoader
Open/closed principle - to tylko pojemnik na dane
Liskov substitution principle - Nic nie dziedziczy z tej klasy, a korzystanie z klasy nie wymaga jej znajomości
Interface segregation principle - Jedyne interfejsy z jakich korzystamy to te we wbudowanych klasach Javy (np. List)
Dependency inversion principle -
*/

class LoadedFiles {
    public ArrayList<Item> items;
    public ArrayList<Recepture> receptures;
    public boolean success;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else if (obj.getClass() != this.getClass()) return false;

        //oba obiekty są tej samej klasy
        if (obj == this) return true;
        LoadedFiles other = (LoadedFiles) obj;
        boolean itemsOK, recipesOK;

        if (this.items == null && other.items == null) itemsOK = true; //oba nie mają przedmiotów
        else if (this.items != null && other.items != null) {
            itemsOK = this.items.containsAll(other.items) && other.items.containsAll(this.items); //sprawdzanie czy zawierają te same przedmioty (ignorowanie kolejności)
        } else itemsOK = false; //kiedy jedna jeden nie ma przedmiptów a drugi ma

        //analogicznie jak przedmioty
        if (this.receptures == null && other.receptures == null) recipesOK = true;
        else if (this.receptures != null && other.receptures != null) {
            recipesOK = this.receptures.containsAll(other.receptures) && other.receptures.containsAll(this.receptures);
        } else recipesOK = false;

        return (itemsOK && recipesOK && this.success == other.success);
    }
}