import java.util.Arrays;

public class FileLoader{
	private String path;

	public FileLoader(String _path) {

	}

	public void ChangePath(String newPath) {

	}

	public String GetPath() {
		return null;
	}

	public LoadedFiles LoadFiles() {
		return null;
	}
}

class LoadedFiles {
	public Item[] items;
	public Recepture[] receptures;
	public boolean success;

	public boolean Equals(LoadedFiles files) {
		return Arrays.equals(files.items, this.items) && Arrays.equals(files.receptures, this.receptures) && files.success == this.success;
	}
}
