import java.util.ArrayList;

public class SearchEngine{
	public static ArrayList<Recepture> FilterRecepies(ArrayList<Recepture> original, String filter) {
		ArrayList<Recepture> filtered = new ArrayList<>();
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
