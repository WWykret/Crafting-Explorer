import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine{

	public static ArrayList<Recepture> FilterRecepies(ArrayList<Recepture> original, String filter) {
		ArrayList<Recepture> filtered = new ArrayList<>();
		Pattern compiledPattern = Pattern.compile(filter.toLowerCase());

		for (Recepture i : original) {
			if (i.GetResult() != null) {
				Matcher matcher = compiledPattern.matcher(i.GetResult().GetName().toLowerCase());
				if (matcher.find()) {
					filtered.add(i);
				}

			}
		}
		return filtered;
	}

	public static ArrayList<Item> FilterItems(ArrayList<Item> original, String filter) {
		ArrayList<Item> filtered = new ArrayList<>();
		Pattern compiledPattern = Pattern.compile(filter.toLowerCase());

		for (Item i : original) {
			if (i != null) {
				Matcher matcher = compiledPattern.matcher(i.GetName().toLowerCase());
				if (matcher.find()) {
					filtered.add(i);
				}

			}
		}
		return filtered;
	}
}