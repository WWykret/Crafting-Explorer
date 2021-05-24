import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileLoader {
    private String path;

    public FileLoader(String _path) {
        this.path = _path;
    }

    public void ChangePath(String newPath) {
        this.path = newPath;
    }

    public String GetPath() {
        return path;
    }

    private Path CopyFiles() {
        Path sourcePath = Paths.get(path);
        Path destPath;
        try {
            destPath = Paths.get(new File("./files/minecraft.jar").getCanonicalPath());
            ClearDir(destPath.getParent());
            Files.copy(sourcePath, destPath);
        } catch (Exception e) {
            destPath = null;
            System.out.println(e.getClass().toString() + " --- " + e.getMessage());
        }
        return destPath;
    }

    private void ClearDir(Path dirPath) throws Exception {
        File dir = new File(dirPath.toString());
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            boolean success = false;
            if (file.isDirectory()) success = deleteDirectory(file);
            else success = file.delete();
            if (!success) throw new Exception("nie udalo sie usunac " + file.toString());
        }
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    private void UnpackJar(Path jarPath) throws IOException {
        //zmodyfikowany kod z SO xd
        java.util.jar.JarFile jar = new java.util.jar.JarFile(jarPath.toString());
        java.util.Enumeration enumEntries = jar.entries();
        while (enumEntries.hasMoreElements()) {
            java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();
            if (!IsCorrectFile(file.getName())) continue; //moja zmiana xd
            java.io.File f = new java.io.File(jarPath.getParent().toString() + java.io.File.separator + file.getName());
            f.getParentFile().mkdirs();
            if (file.isDirectory()) {
                f.mkdir();
                continue;
            }
            java.io.InputStream is = jar.getInputStream(file);
            java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
            while (is.available() > 0) {
                fos.write(is.read());
            }
            fos.close();
            is.close();
        }
        jar.close();
    }

    private boolean IsCorrectFile(String filename) {
        String[] dirs = new String[]{
            "(.*/)?data/minecraft/tags/",
            "(.*/)?data/minecraft/recipes/",
            "(.*/)?assets/minecraft/textures/"
        };

        for (String dir : dirs) {
            if (Pattern.matches(dir + ".*", filename)) return true;
        }
        return false;
    }

    private LinkedHashSet<Item> ReadItemsFromFiles(Path dir) throws IOException, org.json.simple.parser.ParseException {
        ArrayList<File> files = GetAllFilesFromDir(dir.toString());

        //rodzdzielanie plikow
        ArrayList<String> tagsPath = new ArrayList<>();
        ArrayList<String> recipePath = new ArrayList<>();
        ArrayList<String> texturePath = new ArrayList<>();
        for (File f : files) {
            String filePath = f.toString().replace("\\", "/"); //jak są \ zamiast / to się regex psuje
            if (Pattern.matches(".*/tags/.*", filePath)) tagsPath.add(filePath);
            else if (Pattern.matches(".*/recipes/.*", filePath)) recipePath.add(filePath);
            else if (Pattern.matches(".*/textures/.*", filePath)) texturePath.add(filePath);
        }

        //tagi z plikow z katalogu .../tags/...
        LinkedHashSet<Item> allItems = GetTagsFromTagFiles(tagsPath, texturePath);
        allItems.addAll(GetTagsFromRecipeFiles(recipePath, texturePath));

        return allItems;
    }

    private LinkedHashSet<Item> GetTagsFromTagFiles(ArrayList<String> tagPaths, ArrayList<String> texturePaths) throws IOException, org.json.simple.parser.ParseException {
        LinkedHashSet<Item> items = new LinkedHashSet<Item>();
        ArrayList<Item> subItems = new ArrayList<Item>();
        HashMap<String, ArrayList<String>> itemsToAdd = new HashMap<String, ArrayList<String>>();

        JSONParser parser = new JSONParser();
        for (String path : tagPaths) {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(path));
            JSONArray tagValues = (JSONArray) obj.get("values");

            subItems.clear();

            String[] splitPath = path.split("/");
            String[] splitFilename = splitPath[splitPath.length - 1].split("\\.");
            String itemTag = "minecraft:" + splitFilename[0];

            for (Object subTagObj : tagValues) {
                String subTag = (String) subTagObj;
                if (subTag.charAt(0) != '#') {
                    Item subItem = new Item(0, subTag, GetTextureForTag(subTag, texturePaths), null);
                    subItems.add(subItem);
                    items.add(subItem);
                } else { //taki zaczynajace sie od #
                    ArrayList<String> itemsToAddList;
                    String name = subTag.substring(1);
                    if (!itemsToAdd.containsKey(itemTag)) {
                        itemsToAddList = new ArrayList<String>();
                        itemsToAddList.add(name);
                        itemsToAdd.put(itemTag, itemsToAddList);
                    } else {
                        itemsToAddList = itemsToAdd.get(itemTag);
                        itemsToAddList.add(name);
                    }
                }
            }

            items.add(new Item(0, itemTag, GetTextureForTag(itemTag, texturePaths), subItems));
        }

        //dodawanie podtypow
        for (Map.Entry<String, ArrayList<String>> item: itemsToAdd.entrySet()) {
            ArrayList<Item> childItems = new ArrayList<Item>();
            for (String itemName: item.getValue()) {
                childItems.add(GetItemFromHashSet(itemName, items));
            }
            Item parentItem = GetItemFromHashSet(item.getKey(), items);
            parentItem.AddTypes(childItems);
        }

        return items;
    }

    private LinkedHashSet<Item> GetTagsFromRecipeFiles(ArrayList<String> recipePaths, ArrayList<String> texturePaths) {
        LinkedHashSet<Item> items = new LinkedHashSet<Item>();

        for (String path : recipePaths) {
            String[] splitPath = path.split("/");
            String[] splitFilename = splitPath[splitPath.length - 1].split("\\.");
            String itemTag = "minecraft:" + splitFilename[0];
            items.add(new Item(0, itemTag, GetTextureForTag(itemTag, texturePaths), null));
        }

        return items;
    }

    private LinkedHashSet<Recepture> ReadRecipesFromFiles(Path dir, LinkedHashSet<Item> allItems) throws IOException, org.json.simple.parser.ParseException {
        ArrayList<File> files = GetAllFilesFromDir(dir.toString());

        //rodzdzielanie plikow
        ArrayList<String> recipePath = new ArrayList<>();
        for (File f : files) {
            String filePath = f.toString().replace("\\", "/"); //jak są \ zamiast / to się regex psuje
            if (Pattern.matches(".*/recipes/.*", filePath)) recipePath.add(filePath);
        }

        //receptury z plikow z katalogu .../recipes/...
        LinkedHashSet<Recepture> allRecipes = GetRecipesFromFiles(recipePath, allItems);

        return allRecipes;
    }

    private LinkedHashSet<Recepture> GetRecipesFromFiles(ArrayList<String> recipePaths, LinkedHashSet<Item> allItems) throws IOException, org.json.simple.parser.ParseException {
        LinkedHashSet<Recepture> recipes = new LinkedHashSet<Recepture>();

        JSONParser parser = new JSONParser();
        for (String path : recipePaths) {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(path));
            String craftingType = (String) obj.get("type");
            if (craftingType.equals("minecraft:crafting_shaped")) {
                JSONArray craftingPattern = (JSONArray) obj.get("pattern");
                String patternString = "";
                for (Object lineObj : craftingPattern) {
                    String line = (String) lineObj;
                    while (line.length() < 3) line = line + " ";
                    patternString += line;
                }
                ArrayList<ArrayList<Item>> recipesList = new ArrayList<ArrayList<Item>>();
                recipesList.add(new ArrayList<Item>());

                for (int i = 0; i < patternString.length(); i++) {
                    String ingredient = patternString.substring(i, i + 1);
                    JSONObject ingredientsDict = (JSONObject) obj.get("key");
                    try {
                        JSONObject itemEntry = (JSONObject) ingredientsDict.get(ingredient);
                        if (itemEntry == null) {
                            for (ArrayList<Item> recipe : recipesList) {
                                recipe.add(null);
                            }
                            continue;
                        }

                        Item craftingItem = GetItemFromJSON(itemEntry, allItems);

                        for (ArrayList<Item> recipe : recipesList) {
                            recipe.add(craftingItem);
                        }
                    } catch (ClassCastException e) {
                        JSONArray dictList = (JSONArray) ingredientsDict.get(ingredient);
                        ArrayList<ArrayList<Item>> tempRecipes = new ArrayList<ArrayList<Item>>();
                        for (Object dictObj : dictList) {
                            JSONObject itemDict = (JSONObject) dictObj;

                            Item itemVariant = GetItemFromJSON(itemDict, allItems);

                            ArrayList<ArrayList<Item>> recipesWithItem = new ArrayList<ArrayList<Item>>();
                            for (ArrayList<Item> recipe : recipesList) {
                                ArrayList<Item> recipeWithItem = new ArrayList<Item>(recipe);
                                recipeWithItem.add(itemVariant);
                                recipesWithItem.add(recipeWithItem);
                            }
                            tempRecipes.addAll(recipesWithItem);
                        }
                        recipesList = new ArrayList<ArrayList<Item>>(tempRecipes);
                    }
                }
                String resultItemName = (String) ((JSONObject) obj.get("result")).get("item");
                Item resultItem = GetItemFromHashSet(resultItemName, allItems);
                long itemQuantity = (long) ((JSONObject) obj.get("result")).get("count");
                for (ArrayList<Item> recipe : recipesList) {
                    recipes.add(new Recepture(0, craftingType, recipe, resultItem, (int) itemQuantity));
                }
            } else if (craftingType.equals("minecraft:smelting")) {
                //PIEC (zakładam że surowiec to zawsze obiekt)
                try {
                    JSONObject ingredientObj = (JSONObject) obj.get("ingredient");
                    Item ingredientItem = GetItemFromJSON(ingredientObj, allItems);
                    ArrayList<Item> smeltItem = new ArrayList<Item>();
                    smeltItem.add(ingredientItem);
                    String resultItemName = (String) obj.get("result");
                    Item resultItem = GetItemFromHashSet(resultItemName, allItems);
                    recipes.add(new Recepture(0, craftingType, smeltItem, resultItem, 1));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                //PIEC
            }
        }

        return recipes;
    }

    private Item GetItemFromJSON(JSONObject obj, LinkedHashSet<Item> allItems) {
        String itemName;
        if (obj.get("tag") != null) itemName = (String) obj.get("tag");
        else itemName = (String) obj.get("item");
        return allItems.stream().filter(item -> itemName.equals(item.GetName())).findFirst().orElse(null);
    }

    private Item GetItemFromHashSet(String itemName, LinkedHashSet<Item> allItems) {
        return allItems.stream().filter(item -> itemName.equals(item.GetName())).findFirst().orElse(null);
    }

    private String GetTextureForTag(String tag, ArrayList<String> texturePaths) {
        String[] splitTag = tag.split(":");
        String itemName = splitTag[splitTag.length - 1];

        Pattern pattern = Pattern.compile(".*/" + itemName + ".*\\.png");

        List<String> candidates = texturePaths.stream().filter(pattern.asPredicate()).collect(Collectors.toList());

        if (candidates.size() > 0) return candidates.get(0);
        else return "";
    }

    private ArrayList<File> GetAllFilesFromDir(String dir) throws IOException {
        ArrayList<File> files = new ArrayList<File>();
        File[] filesInDir = new File(dir).listFiles();
        if (filesInDir == null) return files;
        for (File file : filesInDir) {
            if (file.isFile()) files.add(file);
            else files.addAll(GetAllFilesFromDir(file.toString()));
        }
        return files;
    }

    public LoadedFiles LoadFiles() {
        //DLA TESTOWANIA
        //long start = System.currentTimeMillis();
        //path = "C:\\Users\\Admin\\AppData\\Roaming\\.minecraft\\versions\\1.16.5\\1.16.5.jar";
        //path = "C:\\Users\\Admin\\IdeaProjects\\Projekt-IO\\resources\\example.jar";
        //DLA TESTOWANIA
        LoadedFiles result = new LoadedFiles();
        result.success = false;
        Path jarPath = CopyFiles();
        //System.out.println(jarPath.getParent().toString());
        if (jarPath == null) return result;
        try {
            UnpackJar(jarPath);
            LinkedHashSet<Item> allItems = ReadItemsFromFiles(jarPath.getParent());
            LinkedHashSet<Recepture> allRecipes = ReadRecipesFromFiles(jarPath.getParent(), allItems);
            result.success = (allItems.size() > 0) && (allRecipes.size() > 0);
            ArrayList<Item> allItemsList = new ArrayList<Item>(allItems);
            result.items = allItemsList;
            ArrayList<Recepture> allRecipesList = new ArrayList<Recepture>(allRecipes);
            result.receptures = allRecipesList;
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
        //long finish = System.currentTimeMillis();
        //System.out.println((finish - start)/1000);
        return result;
    }
}

class LoadedFiles {
    public ArrayList<Item> items;
    public ArrayList<Recepture> receptures;
    public boolean success;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        LoadedFiles files = (LoadedFiles) obj;
        return this.items.equals(files.items) && this.receptures.equals(files.receptures) && files.success == this.success;
    }
}
