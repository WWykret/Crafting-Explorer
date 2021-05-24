import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class SearchEngine{

	public static LinkedList<Recepture> FilterRecepies(LinkedList<Recepture> original, String filter) {
		LinkedList<Recepture> filtered = new LinkedList<>();
		for (Recepture i : original) {
			if (i.GetResult() != null)
			if (i.GetResult().GetName().startsWith(filter)) {
				filtered.add(i);
				System.out.println(i.GetResult().GetName());
			}
		}
		return filtered;
	}

}
