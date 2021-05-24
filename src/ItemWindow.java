import javax.swing.*;
import java.util.LinkedList;

public class ItemWindow{
	static private LinkedList<Recepture> receptures;
	static private LinkedList<Item> nextStep;

	public ItemWindow(LinkedList<Recepture> input, LinkedList<Item> nexStep) {
		receptures = input;
		nextStep = nexStep;
	}

	public LinkedList<Recepture> GetReceptures() {
		return receptures;
	}

	public LinkedList<Item> GetNextItems() {
		return nextStep;
	}

	public Recepture GetCurrentRecepture() {
		return receptures.peek();
	}

	public void NextRecepture() {
		Recepture temp = receptures.pollFirst();
		receptures.addLast(temp);
	}

	public void PrevRecepture() {
		Recepture temp = receptures.pollLast();
		receptures.addFirst(temp);
	}
}
