import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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
        //DLA TESTOWANIA
        path = "C:\\Users\\Admin\\AppData\\Roaming\\.minecraft\\versions\\1.16.5\\1.16.5.jar";
        Path sourcePath = Paths.get(path);
        //DLA TESTOWANIA
        Path destPath;
        try {
            destPath = Paths.get(new File("./files/minecraft.jar").getCanonicalPath());
            Files.copy(sourcePath, destPath);
        } catch (IOException e) {
            destPath = null;
        }
        return destPath;
    }

    private void Unpack(Path path) {

    }

    public LoadedFiles LoadFiles() {
        LoadedFiles result = new LoadedFiles();
        Path jarPath = CopyFiles();
        if (jarPath == null) return result;
        System.out.print("");
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
