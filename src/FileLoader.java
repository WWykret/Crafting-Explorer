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
            File target = new File(destPath.toString());
            if (target.exists()) target.delete();
            Files.copy(sourcePath, destPath);
        } catch (IOException e) {
            destPath = null;
        }
        return destPath;
    }

    private void Unpack(Path jarDir, Path unpackerDir) throws IOException, InterruptedException {
        //rozpakowanie pliku .jar konsola, bo jest prosciej xd

        String command = String.format("%s\\unpacker.bat %s", unpackerDir.toString(), jarDir.toString());

        System.out.println(command);

        Process cmd = Runtime.getRuntime().exec(command);
        cmd.waitFor();
    }

    public LoadedFiles LoadFiles() {
        LoadedFiles result = new LoadedFiles();
        Path jarPath = CopyFiles();
        if (jarPath == null) return result;
        try {
            Unpack(jarPath.getParent(), jarPath.getParent().getParent());
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
        //System.out.print(jarPath.toString());
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
