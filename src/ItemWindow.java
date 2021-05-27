import java.util.ArrayList;

public class ItemWindow{
	static private ArrayList<Recepture> receptures;
	static private ArrayList<Item> nextStep;
	static private int index = 0;

	public ItemWindow(ArrayList<Recepture> input, ArrayList<Item> nexStep) {
		receptures = input;
		nextStep = nexStep;
	}

	public ArrayList<Recepture> GetReceptures() {
		return receptures;
	}

	public ArrayList<Item> GetNextItems() {
		return nextStep;
	}

	public Recepture GetCurrentRecepture() {
		return receptures.get(index);
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
