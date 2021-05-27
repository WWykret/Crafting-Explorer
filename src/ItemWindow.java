import java.util.ArrayList;

public class ItemWindow {
	private Item mainItem;
	private ArrayList<Recepture> receptures;
	private ArrayList<Item> nextStep;
	private int index = 0;

	public ItemWindow(Item itemIn) {
		mainItem = itemIn;
		receptures = new ArrayList<Recepture>();
		nextStep = new ArrayList<Item>();
		for (int i = 0; i < Main.GetReceptures().size(); i++) {
			if (Main.GetReceptures().get(i).result.equals(mainItem)) {
				receptures.add(Main.GetReceptures().get(i));
			}
		}
		boolean h;
		for (int i = 0; i < Main.GetReceptures().size(); i++) {
			h=false;
			for (int j = 0; j < Main.GetReceptures().get(i).ingredients.size() && !h; j++) {
				if (Main.GetReceptures().get(i).ingredients.get(j).equals(mainItem)) {
					nextStep.add(Main.GetReceptures().get(i).result);
					h=true;
				}
			}
		}

	}

	public ArrayList<Recepture> GetReceptures() {
		return receptures;
	}

	public ArrayList<Item> GetNextItems() {
		return nextStep;
	}

	public Recepture GetCurrentRecepture() {
		if(receptures.size()==0){
			return null;
		}else{
			return receptures.get(index);
		}
	}

	public Item GetMainItem() {
		return mainItem;
	}

	public void NextRecepture() {
		index++;
		if (index >= receptures.size()) index = 0;;
	}

	public void PrevRecepture() {
		index--;
		if (index < 0) index = receptures.size() - 1;
	}
}
