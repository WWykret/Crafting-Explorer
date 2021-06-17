import java.io.File;
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
}
