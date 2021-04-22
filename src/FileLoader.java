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

	@Override
	public boolean equals(Object obj) {
	    if (obj == null || obj.getClass() != this.getClass()) return false;
	    LoadedFiles files = (LoadedFiles) obj;
		return Arrays.equals(files.items, this.items) && Arrays.equals(files.receptures, this.receptures) && files.success == this.success;
	}
}
