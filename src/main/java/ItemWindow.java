import java.util.ArrayList;

//Ta klasa jest iteratorem wg. tej definicji
//czynnościowy wzorzec projektowy (obiektowy), którego celem jest zapewnienie sekwencyjnego dostępu do podobiektów zgrupowanych w większym obiekcie
public class ItemWindow {
	private Item mainItem;
	private ArrayList<Recepture> receptures;
	private ArrayList<Item> nextStep;
	private int index = 0;

	public ItemWindow(Item itemIn) {
		mainItem = itemIn;
		receptures = new ArrayList<Recepture>();
		nextStep = new ArrayList<Item>();

		if (itemIn == null) return;

		for (int i = 0; i < Main.GetReceptures().size(); i++) {
			if (Main.GetReceptures().get(i).result == mainItem) {
				receptures.add(Main.GetReceptures().get(i));
			}
		}
		boolean h;
		for (int i = 0; i < Main.GetReceptures().size(); i++) {
			h=false;
			for (int j = 0; j < Main.GetReceptures().get(i).ingredients.size() && !h; j++) {
				if (Main.GetReceptures().get(i).ingredients.get(j) == mainItem) {
					boolean h2 = true;
					for (int k = 0; k < nextStep.size(); k++) {
						if (Main.GetReceptures().get(i).GetResult() == nextStep.get(k)) {
							h2 = false;
							break;
						}
					}
					if (h2) {
						nextStep.add(Main.GetReceptures().get(i).GetResult());
						h=true;
					}
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
		if (index >= receptures.size()) index = 0;
	}

	public void PrevRecepture() {
		index--;
		if (index < 0) index = receptures.size() - 1;
	}
}
