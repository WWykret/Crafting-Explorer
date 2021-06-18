import java.io.File;
import java.net.URL;
import java.nio.file.Path;

public class Utils {
    public static void ClearDir(Path dirPath) throws Exception {
        File dir = new File(dirPath.toString());
        File[] files = dir.listFiles();

        if (files == null) return;
        for (File file : files) {
            boolean success;
            if (file.isDirectory()) success = DeleteDirectory(file);
            else success = file.delete();

            if (!success) {
                throw new Exception("nie udalo sie usunac " + file.toString());
            }
        }
    }

    private static boolean DeleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles(); //dla pliku zwraca null
        if (allContents != null) { //sprawdzanie czy allContents to plik
            for (File file : allContents) {
                DeleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete(); //czy udało się usunąć katalog
    }

    public static File GetFileFromResourceFile(String name) throws java.net.URISyntaxException{
        URL resource = Utils.class.getClassLoader().getResource(name);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }
        return new File(resource.toURI());
    }
}
