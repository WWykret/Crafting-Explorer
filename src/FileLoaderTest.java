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

		loader = new FileLoader("C:/")
    }
}