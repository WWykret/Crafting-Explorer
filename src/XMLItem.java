import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class XMLItem {

    private int id;
    private String name;
    private String graphics;
    private ArrayList<String> types;
    private String description;

    public XMLItem() {
    }

    public XMLItem(int id, String name, String graphicsPath, ArrayList<String> types) {
        this.id = id;
        this.name = name;
        this.graphics = graphicsPath;
        this.types = types;
    }

    public XMLItem(Item item) {
        this.id = item.GetID();
        this.name = item.GetName();
        this.graphics = item.GetGraphicsPath();

        ArrayList<String> xmlTypes = new ArrayList<>();
        if (item.GetTypes() != null) {
            for (Item subItem : item.GetTypes()) xmlTypes.add(subItem.GetName());
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
