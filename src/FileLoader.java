import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
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
        for (File file: files) {
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
        String[] dirs = new String[] {
            "data/minecraft/tags/",
            "data/minecraft/recipes/",
            "assets/minecraft/textures/"
        };

        for (String dir: dirs) {
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
        for (File f: files) {
            String filePath = f.toString().replace("\\","/"); //jak są \ zamiast / to się regex psuje
            if (Pattern.matches(".*/tags/.*", filePath)) tagsPath.add(filePath);
            else if (Pattern.matches(".*/recipes/.*", filePath)) recipePath.add(filePath);
            else if (Pattern.matches(".*/textures/.*", filePath)) texturePath.add(filePath);
        }

        //tagi z plikow z katalogu .../tags/...
        LinkedHashSet<Item> allItems = GetTagsFromTagFiles(tagsPath, texturePath);
        System.out.println("1/2.....");
        allItems.addAll(GetTagsFromRecipeFiles(recipePath, texturePath));

        return allItems;
    }

    private LinkedHashSet<Item> GetTagsFromTagFiles(ArrayList<String> tagPaths, ArrayList<String> texturePaths) throws IOException, org.json.simple.parser.ParseException{
        LinkedHashSet<Item> items = new LinkedHashSet<Item>();
        ArrayList<Item> subItems = new ArrayList<Item>();

        JSONParser parser = new JSONParser();
        for (String path: tagPaths) {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(path));
            JSONArray tagValues = (JSONArray) obj.get("values");

            subItems.clear();

            for (Object subTagObj: tagValues) {
                String subTag = (String) subTagObj;
                if (subTag.charAt(0) != '#') {
                    Item subItem = new Item(0, subTag, GetTextureForTag(subTag, texturePaths), null);
                    subItems.add(subItem);
                    items.add(subItem);
                }
            }

            String[] splitPath = path.split("/");
            String[] splitFilename = splitPath[splitPath.length - 1].split("\\.");
            String itemTag = "minecraft:" + splitFilename[0];
            items.add(new Item(0, itemTag, GetTextureForTag(itemTag, texturePaths), subItems));
        }

        return items;
    }

    private LinkedHashSet<Item> GetTagsFromRecipeFiles(ArrayList<String> recipePaths, ArrayList<String> texturePaths) {
        LinkedHashSet<Item> items = new LinkedHashSet<Item>();

        for (String path: recipePaths) {
            String[] splitPath = path.split("/");
            String[] splitFilename = splitPath[splitPath.length - 1].split("\\.");
            String itemTag = "minecraft:" + splitFilename[0];
            items.add(new Item(0, itemTag, GetTextureForTag(itemTag, texturePaths), null));
        }

        return items;
    }

    private String GetTextureForTag(String tag, ArrayList<String> texturePaths) {
        String[] splitTag = tag.split(":");
        String itemName = splitTag[splitTag.length - 1];

        Pattern pattern = Pattern.compile(".*/" + itemName + ".*\\.png");

        List<String> candidates = texturePaths.stream().filter(pattern.asPredicate()).collect(Collectors.toList());

        if (candidates.size() > 0) return candidates.get(0);
        else return "";
    }

    private ArrayList<File> GetAllFilesFromDir(String dir) throws IOException{
        ArrayList<File> files = new ArrayList<File>();
        File[] filesInDir = new File(dir).listFiles();
        if (filesInDir == null) return files;
        for (File file: filesInDir) {
            if (file.isFile()) files.add(file);
            else files.addAll(GetAllFilesFromDir(file.toString()));
        }
        return files;
    }

    public LoadedFiles LoadFiles() {
        //DLA TESTOWANIA
        path = "C:\\Users\\Admin\\AppData\\Roaming\\.minecraft\\versions\\1.16.5\\1.16.5.jar";
        //DLA TESTOWANIA
        LoadedFiles result = new LoadedFiles();
        Path jarPath = CopyFiles();
        //System.out.println(jarPath.getParent().toString());
        if (jarPath == null) return result;
        try {
            UnpackJar(jarPath);
            LinkedHashSet<Item> allItems = ReadItemsFromFiles(jarPath.getParent());
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
        return result;
    }
}

class LoadedFiles {
    public Item[] items;
    public Recepture[] receptures;
    public boolean success;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        LoadedFiles files = (LoadedFiles) obj;
        return Arrays.equals(files.items, this.items) && Arrays.equals(files.receptures, this.receptures) && files.success == this.success;
    }
}
