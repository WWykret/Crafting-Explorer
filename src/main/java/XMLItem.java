import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class XMLItem {

    private int id;
    private String itemName;
    private String fileName;
    private String graphics;
    private ArrayList<String> types;
    private String description;

    public XMLItem() {
    }

    public XMLItem(int id, String name, String fileName, String graphicsPath, ArrayList<String> types) {
        this.id = id;
        this.itemName = name;
        this.fileName = fileName;
        this.graphics = graphicsPath;
        this.types = types;
    }

    public XMLItem(Item item) {
        this.id = item.GetID();
        this.itemName = item.GetRawName();
        this.fileName = item.GetName();
        this.graphics = item.GetGraphicsPath();

        ArrayList<String> xmlTypes = new ArrayList<>();
        if (item.GetTypes() != null) {
            for (Item subItem : item.GetTypes()) xmlTypes.add(subItem.GetRawName());
        }
        this.types = xmlTypes;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDesc) {
        description = newDesc;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }

    public void setFileName(String name) {
        this.fileName = name;
    }

    public ArrayList<String> getTypes() {
    	return this.types;
	}

	public void setTypes(ArrayList<String> types) {
    	this.types = types;
	}

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }
}
