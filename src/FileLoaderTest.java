import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class FileLoaderTest{
	@Test
	void getPath(){
		FileLoader loader = new FileLoader("C:/Users/Admin/");
		Assertions.assertEquals("C:/Users/Admin/", loader.GetPath());
	}
	@Test
	void changePath(){
		FileLoader loader = new FileLoader("C:/Users/Admin/");
		loader.ChangePath("C:/Users/Admin/minecraft/");
		Assertions.assertEquals("C:/Users/Admin/minecraft/", loader.GetPath());
	}
	@Test
	void loadFiles(){
	}
}